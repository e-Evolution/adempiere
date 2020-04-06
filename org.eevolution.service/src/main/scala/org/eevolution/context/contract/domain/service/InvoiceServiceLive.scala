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

package org.eevolution.context.contract.domain.service

import java.sql.Timestamp
import java.time.LocalDateTime

import org.compiere.process.DocAction
import org.eevolution.context.contract.api.Context
import org.eevolution.context.contract.api.Context.Context
import org.eevolution.context.contract.api.repository.InvoiceRepository
import org.eevolution.context.contract.api.service.InvoiceService.Service
import org.eevolution.context.contract.domain.ubiquitouslanguage._
import org.eevolution.context.contract.infrastructure.persistence.model.{I_S_Contract, X_S_Contract}
import zio.ZIO

import scala.util.Try

/**
 * Invoice Domain Service Implementation for Invoice Entity
 */

case class InvoiceServiceLive(contextService : Context.Service, invoiceRepositoryService: InvoiceRepository.Service) extends Service {
  override def newInstance(): ZIO[Context, Throwable, Invoice] = invoiceRepositoryService.newInstance()

  override def create(partnerToInvoice: Partner, contractLine: ContractLine, timeExpenseLine: TimeExpenseLine, dateInvoiced: LocalDateTime): ZIO[Context, Throwable, Invoice] = for {
    newInvoice <- newInstance()
    _ <- ZIO.fromTry(Try {
      newInvoice.setBPartner(partnerToInvoice)
      newInvoice.setDateInvoiced(Timestamp.valueOf(dateInvoiced))
      newInvoice.setDateAcct(Timestamp.valueOf(dateInvoiced))
      newInvoice.setC_Currency_ID(timeExpenseLine.getC_Currency_ID)
      newInvoice.setS_Contract_ID(contractLine.getS_Contract_ID)
      newInvoice.setDocStatus(DocAction.STATUS_Drafted)
      newInvoice.setDocAction(DocAction.ACTION_Complete)
    })
    invoice <- save(newInvoice)
  } yield invoice

  override def save(invoice: Invoice): ZIO[Context, Throwable, Invoice] = invoiceRepositoryService.save(invoice)

  override def getById(id: Id): ZIO[Context, Throwable, Invoice] = invoiceRepositoryService.getById(id)

  override def createFromTimeExpenseLine(timeExpenseLine: TimeExpenseLine, dateInvoiced: LocalDateTime): ZIO[Context, Throwable, Invoice] = {
    for {
      timeExpenseLinePartnerId <- ZIO.effectTotal(timeExpenseLine.getC_BPartner_ID)
      //contractLine <- environment.contractLineService.getById(timeExpenseLine.getS_ContractLine_ID)
      //partner <- environment.partnerService.getById(if (timeExpenseLinePartnerId < 0) timeExpenseLinePartnerId else contractLine.getC_BPartner_ID)
      //newInvoice <- create(partner, contractLine, timeExpenseLine, dateInvoiced)
      //_ <- save(newInvoice)
      _ <- ZIO.succeed(null)
    } yield ???
  }

}
