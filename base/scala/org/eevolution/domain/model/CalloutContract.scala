/** ****************************************************************************
  * Product: Adempiere ERP & CRM Smart Business Solution                       *
  * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
  * This program is free software; you can redistribute it and/or modify it    *
  * under the terms version 2 of the GNU General Public License as published   *
  * by the Free Software Foundation. This program is distributed in the hope   *
  * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
  * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
  * See the GNU General Public License for more details.                       *
  * You should have received a copy of the GNU General Public License along    *
  * with this program; if not, write to the Free Software Foundation, Inc.,    *
  * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
  * For the text or an alternative of this public license, you may reach us    *
  * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
  * or via info@compiere.org or http://www.compiere.org/license.html           *
  * ****************************************************************************/
package org.eevolution.domain.model

import java.math.BigDecimal
import java.sql.{PreparedStatement, ResultSet, SQLException, Timestamp}
import java.util.Properties
import java.util.logging.Level

import org.adempiere.model.GridTabWrapper
import org.compiere.model._
import org.compiere.model.MUOMConversion.convertProductFrom
import org.compiere.util.{CLogger, DB, Env, Msg}


/**
  * Contract Callouts.
  *
  * @author Raul Capecce, raul.capecce@openupsolutions.com, Openup Solutions http://openupsolutions.com/
  *         <a href="https://github.com/adempiere/adempiere/issues/2156">
  *         @see FR [ 2156 ] Error selecting online contract product
  *
  */
class CalloutContract extends CalloutEngine {
  private val steps = false

  /**
    * Contract Line - Amount.
    *		- called from QtyOrdered, Discount and PriceActual
    *		- calculates Discount or Actual Amount
    *		- calculates LineNetAmt
    *		- enforces PriceLimit
    *
    * @param context   context
    * @param windowNo  current Window No
    * @param gridTab   Grid Tab
    * @param gridField Grid Field
    * @param value     New Value
    * @return null or error message
    */
  def amt(context: Properties, windowNo: Int, gridTab: GridTab, gridField: GridField, value: Any): String = {
    if (isCalloutActive || value == null) return ""
    if (steps) log.warning("init")
    val amount = new BigDecimal(value.toString)
    val contractLine = GridTabWrapper.create(gridTab, classOf[I_S_ContractLine])
    val uomId = Env.getContextAsInt(context, windowNo, I_S_ContractLine.COLUMNNAME_C_UOM_ID)
    val productId = Env.getContextAsInt(context, windowNo, I_S_ContractLine.COLUMNNAME_M_Product_ID)
    val priceListId = Env.getContextAsInt(context, windowNo, I_S_Contract.COLUMNNAME_M_PriceList_ID)
    val standardPrecision = MPriceList.getStandardPrecision(context, priceListId)
    val isDiscountSchema = "N" == Env.getContext(context, windowNo, "DiscountSchema")
    log.fine("QtyEntered=" + contractLine.getQtyEntered + ", Ordered=" + contractLine.getQtyOrdered + ", UOM=" + uomId)
    log.fine("PriceList=" + contractLine.getPriceList + ", Limit=" + contractLine.getPriceLimit + ", Precision=" + standardPrecision)
    log.fine("PriceEntered=" + contractLine.getPriceEntered + ", Actual=" + contractLine.getPriceActual + ", Discount=" + contractLine.getDiscount)

    //		No Product
    if (productId == 0) { // if price change sync price actual and entered
      // else ignore
      if (gridField.getColumnName == I_S_ContractLine.COLUMNNAME_PriceEntered) {
        contractLine.setPriceEntered(amount)
      }
      else if (gridField.getColumnName == I_S_ContractLine.COLUMNNAME_PriceEntered) {
        contractLine.setPriceActual(amount)
      }
    } else { //	Product Qty changed - recalc price
      if ((gridField.getColumnName == I_S_ContractLine.COLUMNNAME_QtyOrdered
        || gridField.getColumnName == I_S_ContractLine.COLUMNNAME_QtyEntered
        || gridField.getColumnName == I_S_ContractLine.COLUMNNAME_C_UOM_ID
        || gridField.getColumnName == I_S_ContractLine.COLUMNNAME_M_Product_ID)
        && !isDiscountSchema) {
        val partnerId = Env.getContextAsInt(context, windowNo, I_S_ContractLine.COLUMNNAME_C_BPartner_ID)
        val newQuantityOrdered = Option(convertProductFrom(context, productId, uomId, contractLine.getQtyEntered)) match {
          case Some(newQtyOrdered) => newQtyOrdered
          case None => contractLine.getQtyEntered
        }
        val isSOTrx = Option(Env.getContext(context, windowNo, "IsSOTrx")) match {
          case Some(value) if value == "Y" => true
          case None => false
        }
        val priceListVersionId = Env.getContextAsInt(context, windowNo, I_M_PriceList_Version.COLUMNNAME_M_PriceList_Version_ID)
        val productPricing = new MProductPricing(productId, partnerId, newQuantityOrdered, isSOTrx, null)
        productPricing.setM_PriceList_ID(priceListId)
        productPricing.setM_PriceList_Version_ID(priceListVersionId)
        productPricing.setPriceDate(contractLine.getDateStart)

        val newPriceEntered = Option(convertProductFrom(context, productId, uomId, productPricing.getPriceStd)) match {
          case Some(newPriceEntered) => newPriceEntered
          case None => productPricing.getPriceStd
        }
        log.fine("QtyChanged -> PriceActual=" + productPricing.getPriceStd + ", PriceEntered=" + newPriceEntered + ", Discount=" + productPricing.getDiscount)
        contractLine.setPriceActual(productPricing.getPriceStd)
        contractLine.setDiscount(productPricing.getDiscount)
        contractLine.setPriceEntered(newPriceEntered)
        Env.setContext(context, windowNo, "DiscountSchema", if (productPricing.isDiscountSchema) "Y" else "N")
      } else if (gridField.getColumnName == I_S_ContractLine.COLUMNNAME_PriceActual) {
        val newPriceEntered = Option(convertProductFrom(context, productId, uomId, amount)) match {
          case Some(newPriceEntered) => newPriceEntered
          case None => amount
        }
        log.fine("PriceActual=" + amount + " -> PriceEntered=" + newPriceEntered)
        contractLine.setPriceEntered(newPriceEntered)
      } else if (gridField.getColumnName == I_S_ContractLine.COLUMNNAME_PriceEntered) {
        val newPriceActual = Option(MUOMConversion.convertProductTo(context, productId, uomId, amount)) match {
          case Some(newPriceActual) => newPriceActual
          case None => amount
        }
        log.fine("PriceEntered=" + amount + " -> PriceActual=" + newPriceActual)
        contractLine.setPriceActual(newPriceActual)
      }
    }
    val oneHundred = new java.math.BigDecimal(100)
    //  Discount entered - Calculate Actual/Entered
    if (gridField.getColumnName == I_S_ContractLine.COLUMNNAME_Discount) {
      val newPriceActual = if (contractLine.getPriceList.signum() != 0)
        oneHundred.subtract(contractLine.getDiscount).divide(oneHundred).multiply(contractLine.getPriceList)
      else
        contractLine.getPriceActual
      val newPriceActualRound = if (newPriceActual.scale() > standardPrecision)
        newPriceActual.setScale(standardPrecision, BigDecimal.ROUND_HALF_UP)
      else
        newPriceActual
      val newPriceEntered = Option(convertProductFrom(context, productId, uomId, newPriceActualRound)) match {
        case Some(newPriceEntered) => newPriceEntered
        case None => newPriceActualRound
      }
      contractLine.setPriceEntered(newPriceActualRound)
      contractLine.setPriceEntered(newPriceEntered)
    } else { //	calculate Discount
      val newDiscount = if (contractLine.getPriceList.signum() == 0)
        java.math.BigDecimal.ZERO
      else
        contractLine.getPriceList.subtract(contractLine.getPriceActual).divide(contractLine.getPriceList.multiply(oneHundred))

      val newDiscountRound = if (newDiscount.scale() > 2)
        newDiscount.setScale(2, BigDecimal.ROUND_HALF_UP)
      else
        newDiscount
      contractLine.setDiscount(newDiscountRound)
    }
    log.fine("PriceEntered=" + contractLine.getPriceEntered + ", Actual=" + contractLine.getPriceActual + ", Discount=" + contractLine.getDiscount)
    //	Check PriceLimit
    val enforcePriceLimit = Option(Env.getContext(context, windowNo, "EnforcePriceLimit")) match {
      case Some(value) => true
      case None => false
    }
    val enforce = Env.isSOTrx(context, windowNo) && enforcePriceLimit && MRole.getDefault.isOverwritePriceLimit
    //	Check Price Limit?
    if (enforce && contractLine.getPriceLimit.signum() != 0 && contractLine.getPriceActual.compareTo(contractLine.getPriceLimit) < 0) {
      val newPriceEntered = Option(convertProductFrom(context, productId, uomId, contractLine.getPriceLimit)) match {
        case Some(newPriceEntered) => newPriceEntered
        case None => contractLine.getPriceLimit
      }
      log.fine("(under) PriceEntered=" + newPriceEntered + ", Actual" + contractLine.getPriceLimit)
      contractLine.setPriceActual(contractLine.getPriceLimit)
      contractLine.setPriceEntered(newPriceEntered)
      gridTab.fireDataStatusEEvent("UnderLimitPrice", "", false)
      //	Repeat Discount calc
      if (contractLine.getPriceList.signum() != 0) {
        val newDiscount = contractLine.getPriceList.subtract(contractLine.getPriceActual).divide(contractLine.getPriceList).multiply(oneHundred)
        val newDiscountRound = if (newDiscount.scale() > 2) newDiscount.setScale(2, BigDecimal.ROUND_HALF_UP) else newDiscount
        contractLine.setDiscount(newDiscountRound)
      }
    }
    //	Line Net Amt
    val lineNetAmt = contractLine.getQtyOrdered.multiply(contractLine.getPriceActual)
    val lineNetAmtRound = if (lineNetAmt.scale > standardPrecision)
      lineNetAmt.setScale(standardPrecision, BigDecimal.ROUND_HALF_UP)
    else
      lineNetAmt
    contractLine.setLineNetAmt(lineNetAmtRound)
    ""
  }

  /**
    * Contract Line - Quantity.
    *		- called from C_UOM_ID, QtyEntered, QtyOrdered
    *		- enforces qty UOM relationship
    *
    * @param context   context
    * @param windowNo  current Window No
    * @param gridTab   Grid Tab
    * @param gridField Grid Field
    * @param value     New Value
    * @return null or error message
    */
  def qty(context: Properties, windowNo: Int, gridTab: GridTab, gridField: GridField, value: Any): String = {
    if (!isCalloutActive && value != null) {
      val productID: Int = Env.getContextAsInt(context, windowNo, "M_Product_ID")
      if (steps) log.warning("init - M_Product_ID=" + productID + " - ")

      val contractLine = GridTabWrapper.create(gridTab, classOf[I_S_ContractLine])

      //	No Product
      if (productID == 0) {
        contractLine.setQtyOrdered(contractLine.getQtyEntered)
      }
      else { //	UOM Changed - convert from Entered -> Product
        if (gridField.getColumnName == "C_UOM_ID") {
          val unitOfMeasureID = value.asInstanceOf[Integer].intValue
          val roundedQtyEntered = contractLine.getQtyEntered.setScale(MUOM.getPrecision(context, unitOfMeasureID), BigDecimal.ROUND_HALF_UP)
          if (contractLine.getQtyEntered.compareTo(roundedQtyEntered) != 0) {
            log.fine("Corrected QtyEntered Scale UOM=" + unitOfMeasureID + "; QtyEntered=" + contractLine.getQtyEntered + "->" + roundedQtyEntered)
            contractLine.setQtyEntered(roundedQtyEntered)
          }
          val QtyOrdered = Option(MUOMConversion.convertProductFrom(context, productID, unitOfMeasureID, contractLine.getQtyEntered)) match {
            case Some(newQuantityOrdered) => newQuantityOrdered
            case None => contractLine.getQtyEntered
          }
          val conversion = contractLine.getQtyEntered.compareTo(QtyOrdered) != 0
          val PriceEntered = Option(MUOMConversion.convertProductFrom(context, productID, unitOfMeasureID, contractLine.getPriceActual)) match {
            case Some(newPriceEntered) => newPriceEntered
            case None => contractLine.getPriceActual
          }
          log.fine("UOM=" + unitOfMeasureID + ", QtyEntered/PriceActual=" + contractLine.getQtyEntered + "/" + contractLine.getPriceActual + " -> " + conversion + " QtyOrdered/PriceEntered=" + QtyOrdered + "/" + PriceEntered)
          Env.setContext(context, windowNo, "UOMConversion", if (conversion) "Y" else "N")
          contractLine.setQtyOrdered(QtyOrdered)
          contractLine.setPriceEntered(PriceEntered)
        }
        else { //	QtyEntered changed - calculate QtyOrdered
          if (gridField.getColumnName == I_S_ContractLine.COLUMNNAME_QtyEntered) {
            val unitOfMeasureID = Env.getContextAsInt(context, windowNo, "C_UOM_ID")
            contractLine.setQtyEntered(value.asInstanceOf[BigDecimal])
            val QtyEntered1 = contractLine.getQtyEntered.setScale(MUOM.getPrecision(context, unitOfMeasureID), BigDecimal.ROUND_HALF_UP)
            if (contractLine.getQtyEntered.compareTo(QtyEntered1) != 0) {
              log.fine("Corrected QtyEntered Scale UOM=" + unitOfMeasureID + "; QtyEntered=" + contractLine.getQtyEntered + "->" + QtyEntered1)
              contractLine.setQtyEntered(QtyEntered1)
            }
            val QtyOrdered = Option(MUOMConversion.convertProductFrom(context, productID, unitOfMeasureID, contractLine.getQtyEntered)) match {
              case Some(newQuantityOrdered) => newQuantityOrdered
              case None => contractLine.getQtyEntered
            }
            val conversion = contractLine.getQtyEntered.compareTo(QtyOrdered) != 0
            log.fine("UOM=" + unitOfMeasureID + ", QtyEntered=" + contractLine.getQtyEntered + " -> " + conversion + " QtyOrdered=" + QtyOrdered)
            Env.setContext(context, windowNo, "UOMConversion", if (conversion) "Y" else "N")
            contractLine.setQtyOrdered(QtyOrdered)
          }
          else { //	QtyOrdered changed - calculate QtyEntered (should not happen)
            if (gridField.getColumnName == I_S_ContractLine.COLUMNNAME_QtyOrdered) {
              val unitOfMeasureID = Env.getContextAsInt(context, windowNo, "C_UOM_ID")
              val qtyOrdered = value.asInstanceOf[BigDecimal]
              val precision = MProduct.get(context, productID).getUOMPrecision
              val roundedQtyOrdered = qtyOrdered.setScale(precision, BigDecimal.ROUND_HALF_UP)
              if (qtyOrdered.compareTo(roundedQtyOrdered) != 0) {
                log.fine("Corrected QtyOrdered Scale " + qtyOrdered + "->" + roundedQtyOrdered)
                contractLine.setQtyOrdered(roundedQtyOrdered)
              }
              val QtyEntered = Option(MUOMConversion.convertProductTo(context, productID, unitOfMeasureID, qtyOrdered)) match {
                case Some(newQuantityEntered) => newQuantityEntered
                case None => qtyOrdered
              }
              val conversion = qtyOrdered.compareTo(QtyEntered) != 0
              log.fine("UOM=" + unitOfMeasureID + ", QtyOrdered=" + qtyOrdered + " -> " + conversion + " QtyEntered=" + QtyEntered)
              Env.setContext(context, windowNo, "UOMConversion", if (conversion) "Y"
              else "N")
              gridTab.setValue("QtyEntered", QtyEntered)
            }
          }
        }
      }
      //	Storage
      if (productID != 0 && Env.isSOTrx(context, windowNo) && contractLine.getQtyOrdered.signum > 0) { //	no negative (returns)
        val product = MProduct.get(context, productID)
        if (product.isStocked) {
          val M_Warehouse_ID = Env.getContextAsInt(context, windowNo, "M_Warehouse_ID")
          val M_AttributeSetInstance_ID = Env.getContextAsInt(context, windowNo, "M_AttributeSetInstance_ID")
          val available = Option(MStorage.getQtyAvailable(M_Warehouse_ID, productID, M_AttributeSetInstance_ID, null)) match {
            case Some(newAvailable) => newAvailable
            case None => Env.ZERO
          }
          if (available.signum == 0) gridTab.fireDataStatusEEvent("NoQtyAvailable", "0", false)
          else if (available.compareTo(contractLine.getQtyOrdered) < 0) gridTab.fireDataStatusEEvent("InsufficientQtyAvailable", available.toString, false)
          else {
            val notReserved = Env.ZERO // TODO: Implement this business logic
            val total = available.subtract(notReserved)
            if (total.compareTo(contractLine.getQtyOrdered) < 0) {
              val info = Msg.parseTranslation(context, "@QtyAvailable@=" + available + "  -  @QtyNotReserved@=" + notReserved + "  =  " + total)
              gridTab.fireDataStatusEEvent("InsufficientQtyAvailable", info, false)
            }
          }
        }
      }
    }
    ""
  }

  /**
    *	Contract Line - Product.
    *		- reset C_Charge_ID / M_AttributeSetInstance_ID
    *		- PriceList, PriceStd, PriceLimit, C_Currency_ID, EnforcePriceLimit
    *		- UOM
    *	Calls Tax
    *
    * @param context   context
    * @param windowNo  current Window No
    * @param gridTab   Grid Tab
    * @param gridField Grid Field
    * @param value     New Value
    * @return null or error message
    */
  def product(context: Properties, windowNo: Int, gridTab: GridTab, gridField: GridField, value: Any): String = {
    val contractLine = GridTabWrapper.create(gridTab, classOf[I_S_ContractLine])

    val productId: Integer = value.asInstanceOf[Integer]
    //
    if (productId == null || productId.intValue == 0) { //  If the product information is deleted, zero the other items as well
      contractLine.setM_AttributeSetInstance_ID(0)
      contractLine.setPriceList(new BigDecimal(0))
      contractLine.setPriceLimit(new BigDecimal(0))
      contractLine.setPriceActual(new BigDecimal(0))
      contractLine.setPriceEntered(new BigDecimal(0))
      contractLine.setC_Currency_ID(0)
      contractLine.setDiscount(new BigDecimal(0))
      contractLine.setC_UOM_ID(0)
      ""
    } else {
      if (steps) log.warning("init")
      val attributeSetInstance: I_M_AttributeSetInstance = MProduct.get(context, productId.intValue).getM_AttributeSetInstance
      contractLine.setC_Charge_ID(0)
      //	Set Attribute from context or, if null, from the Product
      //	Get Model and check the Attribute Set Instance from the context
      val product: MProduct = MProduct.get(Env.getCtx, productId)
      contractLine.setM_AttributeSetInstance_ID(product.getEnvAttributeSetInstance(context, windowNo))
      if (Env.getContextAsInt(context, windowNo, Env.TAB_INFO, "M_Product_ID") == productId.intValue && Env.getContextAsInt(context, windowNo, Env.TAB_INFO, "M_AttributeSetInstance_ID") != 0) gridTab.setValue("M_AttributeSetInstance_ID", Env.getContextAsInt(context, windowNo, Env.TAB_INFO, "M_AttributeSetInstance_ID"))
      else contractLine.setM_AttributeSetInstance_ID(attributeSetInstance.getM_AttributeSetInstance_ID)
      /** ***	Price Calculation see also qty	 ****/
      val C_BPartner_ID: Int = Env.getContextAsInt(context, windowNo, "C_BPartner_ID")
      val Qty: BigDecimal = contractLine.getQtyOrdered
      val IsSOTrx: Boolean = Env.getContext(context, windowNo, "IsSOTrx") == "Y"
      val pp = new MProductPricing(productId.intValue, C_BPartner_ID, Qty, IsSOTrx, null)
      val M_PriceList_ID: Int = Env.getContextAsInt(context, windowNo, "M_PriceList_ID")
      pp.setM_PriceList_ID(M_PriceList_ID)
      val dateStart: Timestamp = contractLine.getDateStart
      /** PLV is only accurate if PL selected in header */
      pp.setM_PriceList_Version_ID(Env.getContextAsInt(context, windowNo, "M_PriceList_Version_ID"))
      if (pp.getM_PriceList_Version_ID == 0 && M_PriceList_ID > 0) {
        val sql = "SELECT plv.M_PriceList_Version_ID " + "FROM M_PriceList_Version plv " + "WHERE plv.M_PriceList_ID=? " + " AND plv.ValidFrom <= ? " + "ORDER BY plv.ValidFrom DESC"
        //	Use newest price list - may not be future
        pp.setM_PriceList_Version_ID(DB.getSQLValueEx(null, sql, M_PriceList_ID.asInstanceOf[Integer], dateStart))
        if (pp.getM_PriceList_Version_ID > 0) Env.setContext(context, windowNo, "M_PriceList_Version_ID", pp.getM_PriceList_Version_ID)
      }
      ////pp.setM_PriceList_Version_ID(priceListVersionId)
      pp.setPriceDate(dateStart)
      //
      contractLine.setPriceList(pp.getPriceList)
      contractLine.setPriceLimit(pp.getPriceLimit)
      contractLine.setPriceActual(pp.getPriceStd)
      contractLine.setPriceEntered(pp.getPriceStd)
      contractLine.setC_Currency_ID(new Integer(pp.getC_Currency_ID))
      contractLine.setDiscount(pp.getDiscount)
      contractLine.setC_UOM_ID(new Integer(pp.getC_UOM_ID))
      contractLine.setQtyOrdered(contractLine.getQtyEntered)
      Env.setContext(context, windowNo, "EnforcePriceLimit", if (pp.isEnforcePriceLimit) "Y" else "N")
      Env.setContext(context, windowNo, "DiscountSchema", if (pp.isDiscountSchema) "Y" else "N")
      if (Env.isSOTrx(context, windowNo)) if (product.isStocked) {
        val warehouseId = Env.getContextAsInt(context, windowNo, "M_Warehouse_ID")
        val attributeSetInstanceId = Env.getContextAsInt(context, windowNo, "M_AttributeSetInstance_ID")
        val available = Option(MStorage.getQtyAvailable(warehouseId, productId.intValue, attributeSetInstanceId, null)) match {
          case Some(newAvailable) => newAvailable
          case None => Env.ZERO
        }
        if (available.signum == 0) gridTab.fireDataStatusEEvent("NoQtyAvailable", "0", false)
        else if (available.compareTo(contractLine.getQtyOrdered) < 0) gridTab.fireDataStatusEEvent("InsufficientQtyAvailable", available.toString, false)
        else {
          val orderId = Option(contractLine.getS_ContractLine_ID) match {
            case Some(newOrderId) => newOrderId
            case None => 0
          }
          val notReserved = Option(MOrderLine.getNotReserved(context, warehouseId, productId, attributeSetInstanceId, orderId)) match {
            case Some(newNotReserved) => newNotReserved
            case None => Env.ZERO
          }
          val total = available.subtract(notReserved)
          if (total.compareTo(contractLine.getQtyOrdered) < 0) {
            val info = Msg.parseTranslation(context, "@QtyAvailable@=" + available + " - @QtyNotReserved@=" + notReserved + " = " + total)
            gridTab.fireDataStatusEEvent("InsufficientQtyAvailable", info, false)
          }
        }
      }
      if (steps) log.warning("fini")
      tax(context, windowNo, gridTab, gridField, value)
    }
  } //	product

  /**
    *	Contract Line - Tax.
    *		- basis: Product, Charge, BPartner Location
    *		- sets C_Tax_ID
    *  Calls Amount
    *
    * @param context   context
    * @param windowNo  current Window No
    * @param gridTab   Grid Tab
    * @param gridField Grid Field
    * @param value     New Value
    * @return null or error message
    */
  def tax(context: Properties, windowNo: Int, gridTab: GridTab, gridField: GridField, value: Any): String = {
    val contractLine = GridTabWrapper.create(gridTab, classOf[I_S_ContractLine])

    val column: String = gridField.getColumnName
    if (value == null) ""
    else {
      if (steps) log.warning("init")
      //	Check Product
      val productId = if (column == "M_Product_ID") value.asInstanceOf[Integer].intValue else Env.getContextAsInt(context, windowNo, "M_Product_ID")
      val chargeId = if (column == "C_Charge_ID") value.asInstanceOf[Integer].intValue else Env.getContextAsInt(context, windowNo, "C_Charge_ID")
      log.fine("Product=" + productId + ", C_Charge_ID=" + chargeId)
      if (productId == 0 && chargeId == 0) amt(context, windowNo, gridTab, gridField, value)
      else {
        //	Check Partner Location
        val shipPartnerLocationId = if (column == "C_BPartner_Location_ID") value.asInstanceOf[Integer].intValue else Env.getContextAsInt(context, windowNo, "C_BPartner_Location_ID")

        if (shipPartnerLocationId == 0) amt(context, windowNo, gridTab, gridField, value)
        else {
          log.fine("Ship BP_Location=" + shipPartnerLocationId)
          val billDate: Timestamp = Env.getContextAsDate(context, windowNo, "DateOrdered")
          log.fine("Bill Date=" + billDate)
          val shipDate: Timestamp = Env.getContextAsDate(context, windowNo, "DatePromised")
          log.fine("Ship Date=" + shipDate)
          val orgId: Int = Env.getContextAsInt(context, windowNo, "AD_Org_ID")
          log.fine("Org=" + orgId)
          val warehouseId: Int = Env.getContextAsInt(context, windowNo, "M_Warehouse_ID")
          log.fine("Warehouse=" + warehouseId)
          val billPartnerLocationId: Int = Env.getContextAsInt(context, windowNo, "Bill_Location_ID") match {
            case 0 => shipPartnerLocationId
            case default => default
          }
          log.fine("Bill BP_Location=" + billPartnerLocationId)
          val taxId: Int = Tax.get(context, productId, chargeId, billDate, shipDate, orgId, warehouseId, billPartnerLocationId, shipPartnerLocationId, "Y" == Env.getContext(context, windowNo, "IsSOTrx"))
          log.info("Tax ID=" + taxId)
          if (taxId == 0) gridTab.fireDataStatusEEvent(CLogger.retrieveError)
          else contractLine.setC_Tax_ID(taxId)
          if (steps) log.warning("fini")
          amt(context, windowNo, gridTab, gridField, value)
          //	tax
        }
      }
    }
  }

  /**
    *	Contract Line - Charge.
    * 		- updates PriceActual from Charge
    * 		- sets PriceLimit, PriceList to zero
    * 	Calls tax
    *
    * @param context   context
    * @param windowNo  current Window No
    * @param gridTab   Grid Tab
    * @param gridField Grid Field
    * @param value     New Value
    * @return null or error message
    */
  def charge(context: Properties, WindowNo: Int, gridTab: GridTab, gridField: GridField, value: Any): String = {
    val contractLine = GridTabWrapper.create(gridTab, classOf[I_S_ContractLine])

    val chargeId: Integer = value.asInstanceOf[Integer]
    if (chargeId == null || chargeId.intValue == 0) return ""
    //	No Product defined
    if (contractLine.getM_Product_ID != null) {
      contractLine.setC_Charge_ID(0)
      "ChargeExclusively"
    } else {
      contractLine.setM_AttributeSetInstance_ID(0)
      contractLine.setS_ResourceAssignment_ID(0)
      contractLine.setC_UOM_ID(new Integer(100)) //	EA

      Env.setContext(context, WindowNo, "DiscountSchema", "N")
      val sql = "SELECT ChargeAmt FROM C_Charge WHERE C_Charge_ID=?"
      var pstmt: PreparedStatement = null
      var rs: ResultSet = null
      try {
        pstmt = DB.prepareStatement(sql, null)
        pstmt.setInt(1, chargeId.intValue)
        rs = pstmt.executeQuery
        if (rs.next) {
          contractLine.setPriceEntered(rs.getBigDecimal(1))
          contractLine.setPriceActual(rs.getBigDecimal(1))
          contractLine.setPriceLimit(Env.ZERO)
          contractLine.setPriceList(Env.ZERO)
          contractLine.setDiscount(Env.ZERO)
        }
        tax(context, WindowNo, gridTab, gridField, value)
      } catch {
        case e: SQLException =>
          log.log(Level.SEVERE, sql, e)
          e.getLocalizedMessage
      } finally {
        DB.close(rs, pstmt)
        rs = null
        pstmt = null
      }
    }
  }//	charge
}
