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

package org.eevolution.domain.process

import org.compiere.model.{I_C_ProjectTask, I_M_Product, I_S_TimeExpenseLine}
import org.eevolution.domain.api.process.GenerateInvoicesFromTimeExpenseReportComponent
import org.eevolution.domain.service._
import org.eevolution.domain.util.ImplicitConverters._
import org.eevolution.domain.ubiquitouslanguage.Invoice
import org.eevolution.infrastructure.repository._
import org.eevolution.domain.model.I_S_Contract
import org.eevolution.process.GenerateInvoicesFromTimeExpenseReportAbstract

import scala.collection.mutable
import scala.util.{Failure, Success}

/**
  * Generate Invoices from Time and Expense Report
  * This process allows you to generate invoices for a specific period based on Time and Expense reports.
  */
class GenerateInvoicesFromTimeExpenseReport extends GenerateInvoicesFromTimeExpenseReportAbstract
  with GenerateInvoicesFromTimeExpenseReportComponent
  with PartnerRepository
  with PartnerService
  with ContractLineRepository
  with ContractLineService
  with ProductRepository
  with ProductService
  with TimeExpenseLineRepository
  with TimeExpenseLineService
  with InvoiceRepository
  with InvoiceLineRepository
  with InvoiceLineService
  with InvoiceService {

  override def doIt(): String = generateInvoicesFromTimeExpenseReport.doIt()

  final override object generateInvoicesFromTimeExpenseReport extends GenerateInvoicesFromTimeExpenseReportTrait {

    val invoices = mutable.Map[String, Invoice]()

    override def doIt(): String = {
      // Get tuple with query and parameters
      val (whereClause, parameters) = getWhereClause()
      // Get Time and Expense Lines based on query
      timeExpenseLineService.query(whereClause, parameters).foreach(timeExpenseLine => {
        val key = s"${timeExpenseLine.getC_BPartner_ID} : ${timeExpenseLine.getPP_Period_ID}"
        val invoice = invoices get key match {
          case Some(invoice) => invoice
          case None => {
            invoiceService.createFromTimeExpenseLine(timeExpenseLine, getDateInvoiced) match {
              case Success(invoice) =>
                invoices += (key -> invoice)
                addLog(invoice.getDocumentInfo)
                invoice
              case Failure(exception) => throw exception
            }
          }
        }
        invoiceLineService.createFromTimeExpenseLine(invoice, timeExpenseLine) match {
          case Success(invoiceLine) =>
            val productDescription = productService.getById(invoiceLine.getM_Product_ID).orElse(Option(timeExpenseLine.getDescription)).getOrElse("")
            val logMessage = s" @C_InvoiceLine_ID@ ${invoiceLine.getLine} @M_Product_ID@  ${productDescription} @QtyInvoiced@ ${invoiceLine.getQtyInvoiced}"
            addLog(logMessage)
          case Failure(exception) => throw exception
        }
      })
      "@Ok@"
    }

    protected def getWhereClause(): (String, List[Any]) = {
      val parameters = mutable.ListBuffer[Any]()
      val whereClause = new StringBuilder("")
      whereClause ++= s"${I_S_TimeExpenseLine.COLUMNNAME_C_InvoiceLine_ID} IS NULL "
      Option(getDateExpense).foreach(dateExpense => {
        whereClause ++= s" AND ${I_S_TimeExpenseLine.COLUMNNAME_DateExpense}>=?"
        parameters += getDateExpense
      })
      Option(getDateExpenseTo).foreach(dateExpenseTo => {
        whereClause ++= s" AND ${I_S_TimeExpenseLine.COLUMNNAME_DateExpense}<=?"
        parameters += getDateExpenseTo
      })
      if (getPeriodDefinitionId > 0) {
        whereClause ++= s" AND ${I_S_TimeExpenseLine.COLUMNNAME_PP_PeriodDefinition_ID}=?"
        parameters += getPeriodDefinitionId
      }
      if (getPeriodId > 0) {
        whereClause ++= s" AND ${I_S_TimeExpenseLine.COLUMNNAME_PP_Period_ID}=?"
        parameters += getPeriodId
      }
      if (getContractId > 0) {
        whereClause ++= s" AND EXISTS ("
        whereClause ++= s" SELECT 1 FROM ${I_S_Contract.Table_Name} " +
          s"WHERE ${I_S_Contract.COLUMNNAME_S_Contract_ID}=? "
        parameters += getContractId
        if (getBillBPartnerId > 0) {
          whereClause ++= s" AND ${I_S_Contract.COLUMNNAME_Bill_BPartner_ID}=?"
          parameters += getBillBPartnerId
        }
        if (getPayBPartnerId > 0) {
          whereClause ++= s" AND ${I_S_Contract.COLUMNNAME_Pay_BPartner_ID}=?"
          parameters += getPayBPartnerId
        }
        whereClause ++= ")"
      }
      if (getOrgId > 0) {
        whereClause ++= s" AND ${I_S_TimeExpenseLine.COLUMNNAME_AD_Org_ID}=?"
        parameters += getOrgId
      }
      if (getBPartnerId > 0) {
        whereClause ++= s" AND ${I_S_TimeExpenseLine.COLUMNNAME_C_BPartner_ID}=?"
        parameters += getBPartnerId
      }
      if (getServiceTypeId > 0) {
        whereClause ++= s" AND EXISTS (SELECT 1 FROM ${I_M_Product.Table_Name} p " +
          s"WHERE p.${I_M_Product.COLUMNNAME_M_Product_ID}=${I_S_TimeExpenseLine.COLUMNNAME_M_Product_ID} " +
          s"AND p.${I_M_Product.COLUMNNAME_S_ServiceType_ID}=?)"
        parameters += getServiceTypeId
      }
      if (getProductId > 0) {
        whereClause ++= s" AND ${I_S_TimeExpenseLine.COLUMNNAME_M_Product_ID}=?"
        parameters += getProductId
      }
      if (getTimeTypeId > 0) {
        whereClause ++= s" AND ${I_S_TimeExpenseLine.COLUMNNAME_S_TimeType_ID}=?"
        parameters += getTimeTypeId
      }
      if (getProjectId > 0) {
        whereClause ++= s" AND ${I_S_TimeExpenseLine.COLUMNNAME_C_Project_ID}=?"
        parameters += getProductId
        if (getProjectPhaseId > 0) {
          whereClause ++= s" AND ${I_S_TimeExpenseLine.COLUMNNAME_C_ProjectPhase_ID}=?"
          parameters += getProjectPhaseId
        }
        if (getProjectTaskId > 0) {
          whereClause ++= s" AND ${I_S_TimeExpenseLine.COLUMNNAME_C_ProjectTask_ID}=?"
          parameters += getProjectTaskId
        }
        Option(getIsMilestone).foreach(getIsMilestone => {
          whereClause ++= s" AND EXISTS (SELECT 1 FROM ${I_C_ProjectTask.Table_Name} WHERE " +
            s"${I_C_ProjectTask.COLUMNNAME_C_ProjectTask_ID}=${I_S_TimeExpenseLine.COLUMNNAME_C_ProjectTask_ID} " +
            s"AND ${I_C_ProjectTask.COLUMNNAME_IsMilestone}=?)"
          parameters += getIsMilestone
        })
      }
      if (getActivityId > 0) {
        whereClause ++= s" AND ${I_S_TimeExpenseLine.COLUMNNAME_C_Activity_ID}=?"
        parameters += getActivityId
      }
      if (getCampaignId > 0) {
        whereClause ++= s" AND ${I_S_TimeExpenseLine.COLUMNNAME_C_Campaign_ID}=?"
        parameters += getCampaignId
      }
      if (getOrgTrxId > 0) {
        whereClause ++= s"AND ${I_S_TimeExpenseLine.COLUMNNAME_AD_OrgTrx_ID}=?"
        parameters += getOrgTrxId
      }
      if (getUser1Id > 0) {
        whereClause ++= s" AND ${I_S_TimeExpenseLine.COLUMNNAME_User1_ID}=?"
        parameters += getUser1Id
      }
      if (getUser2Id > 0) {
        whereClause ++= s" AND ${I_S_TimeExpenseLine.COLUMNNAME_User2_ID}=?"
        parameters += getUser2Id
      }
      if (getUser3Id > 0) {
        whereClause ++= s" AND ${I_S_TimeExpenseLine.COLUMNNAME_User3_ID}=?"
        parameters += getUser3Id
      }
      if (getUser4Id > 0) {
        whereClause ++= s" AND ${I_S_TimeExpenseLine.COLUMNNAME_User4_ID}=?"
        parameters += getUser4Id
      }
      (whereClause.toString(), parameters.toList)
    }
  }


}