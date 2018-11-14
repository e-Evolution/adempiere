/**
  * Copyright (C) 2003-2018, e-Evolution Consultants S.A. , http://www.e-evolution.com
  * This program is free software, you can redistribute it and/or modify it
  * under the terms version 2 of the GNU General Public License as published
  * or (at your option) any later version.
  * by the Free Software Foundation. This program is distributed in the hope
  * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied
  * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
  * See the GNU General Public License for more details.
  * You should have received a copy of the GNU General Public License along
  * with this program, if not, write to the Free Software Foundation, Inc.,
  * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
  * For the text or an alternative of this public license, you may reach us
  * or via info@adempiere.net or http://www.adempiere.net/license.html
  * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
  * Created by victor.perez@e-evolution.com , www.e-evolution.com
  */

package org.eevolution.domain.service

import java.time.LocalDateTime

import org.adempiere.exceptions.AdempiereException
import org.compiere.process.DocAction
import org.eevolution.domain.api.repository.InvoiceRepositoryComponent
import org.eevolution.domain.api.service._
import org.eevolution.domain.util.ImplicitConverters._
import org.eevolution.domain.ubiquitouslanguage.{ContractLine, Invoice, Partner, TimeExpenseLine}

import scala.util.{Failure, Success, Try}

/**
  * Invoice Domain Service Implementation for Invoice Entity
  */
trait InvoiceService extends InvoiceServiceComponent {
  this: InvoiceRepositoryComponent with
    InvoiceLineServiceComponent with
    TimeExpenseLineServiceComponent with
    ProductServiceComponent with
    PartnerServiceComponent with
    ContractLineServiceComponent =>

  final override object invoiceService extends InvoiceServiceTrait {

    override def newInstance(): Try[Invoice] = invoiceRepository.newInstance()

    override def getById(id: Integer): Option[Invoice] = invoiceRepository.getById(id)

    override def save(invoice: Invoice): Try[Invoice] = invoiceRepository.save(invoice)

    override def create(partnerToInvoice: Partner, contractLine: Option[ContractLine], timeExpenseLine: TimeExpenseLine, dateInvoiced: LocalDateTime): Try[Invoice] = {
      newInstance() match {
        case Success(invoice) => {
          invoice.setBPartner(partnerToInvoice)
          invoice.setDateInvoiced(dateInvoiced)
          invoice.setDateAcct(dateInvoiced)
          invoice.setC_Currency_ID(timeExpenseLine.getC_Currency_ID)
          contractLine.foreach(cl => invoice.setS_Contract_ID(cl.getS_Contract_ID))
          invoice.setDocStatus(DocAction.STATUS_Drafted)
          invoice.setDocAction(DocAction.ACTION_Complete)
          Success(invoice)
        }
        case Failure(exception) => throw exception
      }
    }


    override def createFromTimeExpenseLine(timeExpenseLine: TimeExpenseLine, dateInvoiced: LocalDateTime): Try[Invoice] = {
      val partnerId = if (timeExpenseLine.getC_BPartner_ID > 0)
        timeExpenseLine.getC_BPartner_ID
      else if (timeExpenseLine.getS_ContractLine_ID > 0) {
        contractLineService.getById(timeExpenseLine.getS_ContractLine_ID) match {
          case Some(contractLine) => contractLine.getC_BPartner_ID
          case None => throw new AdempiereException("@C_BPartner_ID@ @NotFound@")
        }
      }
      else throw new AdempiereException("@C_BPartner_ID@ @NotFound@")

      partnerService.getById(partnerId) match {
        case Some(partner) => {
          // get the Contract line based on Time and Expense Line
          val contractLine = contractLineService.getById(timeExpenseLine.getS_ContractLine_ID)
          invoiceService.create(partner, contractLine, timeExpenseLine, dateInvoiced) match {
            // Create an Invoice based on the Partner , Contract Line , Time and Expense Line and Date Invoiced parameters
            case Success(invoice) => {
              // Persist Invoice
              invoiceService.save(invoice)
            }
            // Propagate Exception
            case Failure(exception) => throw exception
          }
        }
        case None => throw new AdempiereException("@C_BPartner_ID@ @NotFound@")
      }
    }
  }

}
