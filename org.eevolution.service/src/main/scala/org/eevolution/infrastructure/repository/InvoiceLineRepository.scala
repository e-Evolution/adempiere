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

package org.eevolution.infrastructure.repository

import org.compiere.model.{I_C_InvoiceLine, Query}
import org.compiere.util.Env
import org.eevolution.domain.api.repository.InvoiceLineRepositoryComponent
import org.eevolution.domain.ubiquitouslanguage.{Invoice, InvoiceLine, TimeExpenseLine}

import scala.collection.JavaConverters._
import scala.collection.immutable.List
import scala.util.Try

/**
  * Invoice Line Repository Implementation the infrastructure for Invoice Line Entity
  */
trait InvoiceLineRepository extends InvoiceLineRepositoryComponent {

  final override object invoiceLineRepository extends InvoiceLineRepositoryTrait {

    override def newInstance(invoice: Invoice): InvoiceLine = {
      return new InvoiceLine(invoice)
    }

    override def save(invoiceLine: InvoiceLine): Try[InvoiceLine] = {
      Try {
        invoiceLine.saveEx()
        invoiceLine
      }
    }

    override def getById(id: Integer): Option[InvoiceLine] = {
      val whereClause = s"${I_C_InvoiceLine.COLUMNNAME_C_InvoiceLine_ID}=?"
      Option(new Query(Env.getCtx, I_C_InvoiceLine.Table_Name, whereClause.toString, null)
        .setClient_ID()
        .setParameters(id)
        .first()
      )
    }

    override def getByInvoiceId(invoiceId: Integer): List[InvoiceLine] = {
      val whereClause = s"${I_C_InvoiceLine.COLUMNNAME_C_Invoice_ID}=?"
      new Query(Env.getCtx, I_C_InvoiceLine.Table_Name, whereClause.toString, null)
        .setClient_ID()
        .setParameters(invoiceId)
        .list().asScala.toList
    }

    override def createFromTimeExpenseLine(invoice: Invoice, timeExpenseLine: TimeExpenseLine) : Try[InvoiceLine] = {
      Try {
        val invoiceLine = newInstance(invoice)
        invoiceLine.setS_ContractLine_ID(timeExpenseLine.getS_ContractLine_ID)
        invoiceLine.setPP_PeriodDefinition_ID(timeExpenseLine.getPP_PeriodDefinition_ID)
        invoiceLine.setPP_Period_ID(timeExpenseLine.getPP_Period_ID)
        invoiceLine.setM_Product_ID(timeExpenseLine.getM_Product_ID)
        invoiceLine.setQtyEntered(timeExpenseLine.getQtyInvoiced)
        invoiceLine.setPriceEntered(timeExpenseLine.getPriceInvoiced)
        invoiceLine.setC_Project_ID(timeExpenseLine.getC_Project_ID)
        invoiceLine.setC_ProjectPhase_ID(timeExpenseLine.getC_ProjectPhase_ID)
        invoiceLine.setC_ProjectTask_ID(timeExpenseLine.getC_ProjectTask_ID)
        invoiceLine.setC_Activity_ID(timeExpenseLine.getC_Activity_ID)
        invoiceLine.setC_Campaign_ID(timeExpenseLine.getC_Campaign_ID)
        invoiceLine.setAD_Org_ID(timeExpenseLine.getAD_Org_ID)
        invoiceLine.setAD_OrgTrx_ID(timeExpenseLine.getAD_OrgTrx_ID)
        invoiceLine.setUser1_ID(timeExpenseLine.getUser1_ID)
        invoiceLine.setUser2_ID(timeExpenseLine.getUser2_ID)
        invoiceLine.setUser3_ID(timeExpenseLine.getUser3_ID)
        invoiceLine.setUser4_ID(timeExpenseLine.getUser4_ID)
        invoiceLine
      }
    }
  }

}
