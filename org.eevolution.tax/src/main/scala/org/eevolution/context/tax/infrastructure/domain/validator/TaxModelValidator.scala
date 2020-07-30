/**
 * Copyright (C) 2003-2020, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 **/

package org.eevolution.context.tax.infrastructure.domain.validator

import org.compiere.model._
import org.compiere.util.{CLogger, Env}
import org.eevolution.context.contract.api.service.ContractLineService.ContractLineService
import org.eevolution.context.contract.api.service.ContractTaxService.ContractTaxService
import org.eevolution.context.contract.api.service.{ContractLineService, ContractTaxService}
import org.eevolution.context.contract.infrastructure.persistence.model.{I_S_Contract, I_S_ContractLine, MSContract, MSContractLine, MSContractTax, X_S_Contract, X_S_ContractTax}
import org.eevolution.context.tax.api.service.TaxBaseService.TaxBaseService
import org.eevolution.context.tax.api.service.TaxDefinitionService.TaxDefinitionService
import org.eevolution.context.tax.api.service.{TaxBaseService, TaxDefinitionService, TaxService}
import org.eevolution.context.tax.api.service.TaxService.TaxService
import org.eevolution.context.tax.domain.aggregate.{TaxAggregate, TaxDocumentLine}
import zio.{RIO, ZEnv, ZIO}

import scala.collection.mutable.ListBuffer
import scala.util.Try

object TaxModelValidator extends ModelValidator {

  type programEnvironment = TaxBaseService with TaxBaseService with TaxService
  val logger: CLogger = CLogger.getCLogger(getClass)
  val runtime = zio.Runtime.default
  val dependences = ZEnv.live ++ TaxDefinitionService.live ++ TaxBaseService.live ++ TaxService.live
  val withoutErrors = ""

  override def initialize(engine: ModelValidationEngine, client: MClient): Unit = {

    engine.addModelChange(I_C_InvoiceLine.Table_Name, this)
    engine.addModelChange(I_C_OrderLine.Table_Name, this)
    engine.addModelChange(I_S_ContractLine.Table_Name, this)

    engine.addDocValidate(I_C_Order.Table_Name, this)
    engine.addDocValidate(I_C_Invoice.Table_Name, this)
    engine.addDocValidate(I_S_Contract.Table_Name, this)

  }


  override def getAD_Client_ID: Int = {
    val clientId = Env.getAD_Client_ID(Env.getCtx)
    clientId
  }

  override def login(AD_Org_ID: Int, AD_Role_ID: Int, AD_User_ID: Int): String = withoutErrors

  override def modelChange(instance: PO, typeEvent: Int): String = instance match {
    case _ if I_C_InvoiceLine.Table_ID == instance.get_Table_ID() && ModelValidator.TYPE_AFTER_NEW == typeEvent =>
      val invoiceLine = instance.asInstanceOf[MInvoiceLine]
      val taxes = getInvoiceLineTaxes(invoiceLine)
      withoutErrors
    case _ if I_C_OrderLine.Table_ID == instance.get_Table_ID() && ModelValidator.TYPE_BEFORE_CHANGE == typeEvent => {
      val orderLine = instance.asInstanceOf[MOrderLine]
      val taxes = getOrderLineTaxes(orderLine)
      withoutErrors
    }
    case _ if I_S_ContractLine.Table_ID == instance.get_Table_ID() && ModelValidator.TYPE_BEFORE_CHANGE == typeEvent => {
      val contractLine = instance.asInstanceOf[MSContractLine]
      val taxes = getContractLineTaxes(contractLine)
      withoutErrors
    }
    case _ => withoutErrors
  }

  override def docValidate(instance: PO, timing: Int): String = instance match {
    // Validate reverse Payment
    case _ if I_C_Order.Table_ID == instance.get_Table_ID && ModelValidator.TIMING_AFTER_COMPLETE == timing => {
      withoutErrors
    }
    case _ if I_C_Invoice.Table_ID == instance.get_Table_ID && ModelValidator.TIMING_AFTER_COMPLETE == timing => {
      withoutErrors
    }
    case _ if I_S_Contract.Table_ID == instance.get_Table_ID && ModelValidator.TIMING_AFTER_COMPLETE == timing => {
      withoutErrors
    }
    case _ => withoutErrors
  }

  /**
   * save Order Taxes
   *
   * @param order Order
   * @return
   */
  def saveOrderTaxes(order: MOrder): ZIO[Any, Throwable, Unit] = {
    // Get currency
    val currency = order.getC_Currency
    for {
      // Effect for get the order taxes ids
      orderTaxesIds <- RIO.fromTry(
        Try(PO.getAllIDs(I_C_OrderTax.Table_Name
          , s"${I_C_Order.COLUMNNAME_C_Order_ID}=${order.getC_Order_ID}"
          , order.get_TrxName())
        )
      )
      // Effect for delete current order taxes
      _ <- RIO.fromTry(
        Try(orderTaxesIds.foreach(orderTaxId => {
          val orderTax = new MOrderTax(order.getCtx, orderTaxId, order.get_TrxName())
          orderTax.delete(true)
        })))
      // Effect get taxes based on order lines
      taxes <- RIO.effectTotal {
        val taxes = new ListBuffer[TaxDocumentLine]
        val orderLines = order.getLines(true, I_C_OrderLine.COLUMNNAME_Line)
        orderLines.foreach(orderLine => taxes :+ getOrderLineTaxes(orderLine))
        taxes
      }
      // Effect get Order Tax Total by Tax
      orderTaxes <- TaxAggregate.getTaxes(taxes, currency.getStdPrecision)
      // Effect persistence calculate Order Taxes
      _ <- RIO.foreach(orderTaxes) { taxLine =>
        RIO.fromTry {
          Try {
            val orderTax = new MOrderTax(order.getCtx, 0, order.get_TrxName())
            orderTax.setC_Order_ID(order.getC_Order_ID)
            orderTax.setAD_Org_ID(order.getAD_Org_ID)
            orderTax.setC_Tax_ID(taxLine.taxId)
            orderTax.setIsTaxIncluded(taxLine.isTaxIncluded)
            orderTax.setIsActive(true)
            orderTax.setTaxBaseAmt(taxLine.taxBaseAmount.bigDecimal)
            orderTax.setTaxAmt(taxLine.taxAmount.bigDecimal)
            orderTax.saveEx()
          }
        }
      }
    } yield ()
  }

  /**
   * Save Invoice Taxes
   *
   * @param invoice Invoice
   * @return
   */
  def saveInvoiceTaxes(invoice: MInvoice): ZIO[Any, Throwable, Unit] = {
    // Get currency
    val currency = invoice.getC_Currency
    for {
      // Effect for get the invoice taxes ids
      invoiceTaxesIds <- RIO.fromTry(
        Try(PO.getAllIDs(I_C_InvoiceTax.Table_Name
          , s"${I_C_InvoiceTax.COLUMNNAME_C_Invoice_ID}=${invoice.getC_Order_ID}"
          , invoice.get_TrxName())
        )
      )
      // Effect for delete current invoice taxes
      _ <- RIO.fromTry(
        Try(invoiceTaxesIds.foreach(invoiceTaxId => {
          val orderTax = new MOrderTax(invoice.getCtx, invoiceTaxId, invoice.get_TrxName())
          orderTax.delete(true)
        })))
      // Effect get taxes based on invoice lines
      taxes <- RIO.effectTotal {
        val taxes = new ListBuffer[TaxDocumentLine]
        val invoiceLines = invoice.getLines(true)
        invoiceLines.foreach(invoiceLine => taxes :+ getInvoiceLineTaxes(invoiceLine))
        taxes
      }
      // Effect get Invoice Tax Total by Tax
      invoiceTaxes <- TaxAggregate.getTaxes(taxes, currency.getStdPrecision)
      // Effect persistence calculate Invoice Taxes
      _ <- RIO.foreach(invoiceTaxes) { taxLine =>
        RIO.fromTry {
          Try {
            val invoiceTax = new MInvoiceTax(invoice.getCtx, 0, invoice.get_TrxName())
            invoiceTax.setC_Invoice_ID(invoice.getC_Invoice_ID)
            invoiceTax.setAD_Org_ID(invoice.getAD_Org_ID)
            invoiceTax.setC_Tax_ID(taxLine.taxId)
            invoiceTax.setIsTaxIncluded(taxLine.isTaxIncluded)
            invoiceTax.setIsActive(true)
            invoiceTax.setTaxBaseAmt(taxLine.taxBaseAmount.bigDecimal)
            invoiceTax.setTaxAmt(taxLine.taxAmount.bigDecimal)
            invoiceTax.saveEx()
          }
        }
      }
    } yield ()
  }

  /**
   * Save Contract Taxes
   *
   * @param contract Contract
   * @return
   */
  def saveContractTaxes(contract: X_S_Contract): ZIO[ContractTaxService with ContractLineService, Throwable, Unit] = {
    // Get currency
    val currency = contract.getC_Currency
    for {
      // Effect for get the invoice taxes ids
      contractTaxes <- ContractTaxService.getByContractId(contract.getS_Contract_ID)
      // Effect for delete current invoice taxes
      _ <- RIO.foreach(contractTaxes)(contractTax =>
        RIO fromTry {
          Try {
            val orderTax = new X_S_ContractTax(contract.getCtx, contractTax.contractTaxId, contract.get_TrxName())
            orderTax.delete(true)
          }
        }
      )
      // Effect get taxes based on invoice lines
      contractLines <- ContractLineService.getByContractId(contract.getS_Contract_ID)
      taxes <- ZIO.fromTry {
        Try {
          val taxes = new ListBuffer[TaxDocumentLine]
          contractLines.foreach(contractLine =>
            //contractLine.getClass.getFields.foreach(field => field.getType)
            taxes :+ getContractLineTaxes(new MSContractLine(contract.getCtx, contractLine.contractLineId, contract.get_TrxName()))
          )
          taxes
        }
      }
      // Effect get Invoice Tax Total by Tax
      documentTaxes <- TaxAggregate.getTaxes(taxes, currency.getStdPrecision)
      // Effect persistence calculate Invoice Taxes
      _ <- RIO.foreach(documentTaxes) { taxLine =>
        RIO.fromTry {
          Try {
            val contractTax = new MSContractTax(contract.getCtx, 0, contract.get_TrxName())
            contractTax.setS_Contract_ID(contract.getS_Contract_ID)
            contractTax.setAD_Org_ID(contract.getAD_Org_ID)
            contractTax.setC_Tax_ID(taxLine.taxId)
            contractTax.setIsTaxIncluded(taxLine.isTaxIncluded)
            contractTax.setIsActive(true)
            contractTax.setTaxBaseAmt(taxLine.taxBaseAmount.bigDecimal)
            contractTax.setTaxAmt(taxLine.taxAmount.bigDecimal)
            contractTax.saveEx()
          }
        }
      }
    } yield ()
  }

  def getOrderLineTaxes(orderLine: MOrderLine): RIO[TaxDefinitionService with TaxBaseService with TaxService, ListBuffer[TaxDocumentLine]] = {
    val order = orderLine.getParent
    val priceList = order.getM_PriceList
    val currency = priceList.getC_Currency
    val org = MOrg.get(orderLine.getCtx, orderLine.getAD_Org_ID)
    val partner = order.getC_BPartner
    val maybeProduct = Option(orderLine.getProduct)
    val maybeCharge = Option(orderLine.getC_Charge)
    for {
      taxes <- TaxAggregate.calculator(
        Option.empty, Option(order.isSOTrx)
        , Option(false)
        , Option(orderLine.getAD_Client_ID)
        , Option(orderLine.getAD_Org_ID)
        , Option(org.getInfo.getAD_OrgType_ID)
        , Option(partner.getC_BP_Group_ID)
        , Option(partner.getC_BP_Group_ID)
        , if (maybeProduct.isDefined) Option(maybeProduct.get.getM_Product_Category_ID) else Option.empty
        , if (maybeProduct.isDefined) Option(maybeProduct.get.getM_Product_ID) else Option.empty
        , if (maybeProduct.isDefined) Option(maybeProduct.get.getC_TaxCategory_ID)
        else if (maybeCharge.isDefined) Option(maybeCharge.get.getC_TaxCategory_ID)
        else Option.empty
        , Option(partner.getC_TaxGroup_ID)
        , if (maybeProduct.isDefined) Option(maybeProduct.get.getC_TaxType_ID)
        else if (maybeCharge.isDefined) Option(maybeCharge.get.getC_TaxType_ID)
        else Option.empty, Option(priceList.isTaxIncluded)
        , Option(BigDecimal.valueOf(0.0)), Option(orderLine.getPriceActual)
        , Option(orderLine.getPriceLimit), Option(orderLine.getQtyInvoiced)
        , if (maybeProduct.isDefined) Option(maybeProduct.get.getWeight.multiply(orderLine.getQtyOrdered)) else Option.empty
        , Option(orderLine.getDateInvoiced.toLocalDateTime)
        , Option(orderLine.getC_BPartner_Location_ID)
        , currency.getStdPrecision)
    } yield taxes
  }

  def getInvoiceLineTaxes(invoiceLine: MInvoiceLine): RIO[TaxDefinitionService with TaxBaseService with TaxService, ListBuffer[TaxDocumentLine]] = {
    val invoice = invoiceLine.getParent
    val priceList = invoice.getM_PriceList
    val currency = priceList.getC_Currency
    val org = MOrg.get(invoiceLine.getCtx, invoiceLine.getAD_Org_ID)
    val partner = invoice.getC_BPartner
    val maybeProduct = Option(invoiceLine.getProduct)
    val maybeCharge = Option(invoiceLine.getC_Charge)
    for {
      taxes <- TaxAggregate.calculator(
        Option.empty
        , Option(invoice.isSOTrx)
        , Option(invoiceLine.getTaxAmt.signum() > 0)
        , Option(invoiceLine.getAD_Client_ID)
        , Option(invoiceLine.getAD_Org_ID)
        , Option(org.getInfo.getAD_OrgType_ID)
        , Option(partner.getC_BP_Group_ID)
        , Option(partner.getC_BP_Group_ID)
        , if (maybeProduct.isDefined) Option(maybeProduct.get.getM_Product_Category_ID) else Option.empty
        , if (maybeProduct.isDefined) Option(maybeProduct.get.getM_Product_ID) else Option.empty
        , if (maybeProduct.isDefined) Option(maybeProduct.get.getC_TaxCategory_ID)
        else if (maybeCharge.isDefined) Option(maybeCharge.get.getC_TaxCategory_ID)
        else Option.empty
        , Option(partner.getC_TaxGroup_ID)
        , if (maybeProduct.isDefined) Option(maybeProduct.get.getC_TaxType_ID)
        else if (maybeCharge.isDefined) Option(maybeCharge.get.getC_TaxType_ID)
        else Option.empty, Option(priceList.isTaxIncluded), Option(BigDecimal.valueOf(0.0))
        , Option(invoiceLine.getPriceActual)
        , Option(invoiceLine.getPriceLimit)
        , Option(invoiceLine.getQtyInvoiced)
        , if (maybeProduct.isDefined) Option(maybeProduct.get.getWeight.multiply(invoiceLine.getQtyInvoiced)) else Option.empty
        , Option(invoice.getDateInvoiced.toLocalDateTime)
        , Option(invoice.getC_BPartner_Location_ID)
        , currency.getStdPrecision)
    } yield taxes
  }

  def getContractLineTaxes(contractLine: MSContractLine): RIO[TaxDefinitionService with TaxBaseService with TaxService, ListBuffer[TaxDocumentLine]] = {
    val contract = new MSContract(contractLine.getCtx, contractLine.getS_Contract_ID, contractLine.get_TrxName())
    val priceList = contract.getM_PriceList
    val currency = priceList.getC_Currency
    val org = MOrg.get(contractLine.getCtx, contractLine.getAD_Org_ID)
    val partner = contract.getC_BPartner
    val maybeProduct = Option(contractLine.getM_Product)
    val maybeCharge = Option(contractLine.getC_Charge)
    for {
      taxes <- TaxAggregate.calculator(
        Option.empty
        , Option(contract.isSOTrx)
        , Option(false), Option(contractLine.getAD_Client_ID)
        , Option(contractLine.getAD_Org_ID)
        , Option(org.getInfo.getAD_OrgType_ID)
        , Option(partner.getC_BP_Group_ID)
        , Option(partner.getC_BP_Group_ID)
        , if (maybeProduct.isDefined) Option(maybeProduct.get.getM_Product_Category_ID) else Option.empty
        , if (maybeProduct.isDefined) Option(maybeProduct.get.getM_Product_ID) else Option.empty
        , if (maybeProduct.isDefined) Option(maybeProduct.get.getC_TaxCategory_ID)
        else if (maybeCharge.isDefined) Option(maybeCharge.get.getC_TaxCategory_ID)
        else Option.empty
        , Option(partner.getC_TaxGroup_ID)
        , if (maybeProduct.isDefined) Option(maybeProduct.get.getC_TaxType_ID)
        else if (maybeCharge.isDefined) Option(maybeCharge.get.getC_TaxType_ID)
        else Option.empty
        , Option(priceList.isTaxIncluded)
        , Option(BigDecimal.valueOf(0.0))
        , Option(contractLine.getPriceActual)
        , Option(contractLine.getPriceLimit)
        , Option(contractLine.getQtyInvoiced)
        , if (maybeProduct.isDefined) Option(maybeProduct.get.getWeight.multiply(contractLine.getQtyOrdered)) else Option.empty
        , Option(contractLine.getDateInvoiced.toLocalDateTime)
        , Option(contractLine.getC_BPartner_Location_ID)
        , currency.getStdPrecision)
    } yield taxes
  }
}
