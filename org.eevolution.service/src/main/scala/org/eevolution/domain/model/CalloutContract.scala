package org.eevolution.domain.model

import java.math.BigDecimal
import java.util.Properties

import org.adempiere.model.GridTabWrapper
import org.compiere.model._
import org.compiere.util.{CLogger, DisplayType, Env, Msg}

class CalloutContract extends CalloutEngine {

  def bPartner(context: Properties, windowNo: Int, gridTab: GridTab, gridField: GridField, value: Object): String = {
    val contract = GridTabWrapper.create(gridTab, classOf[I_S_Contract])
    val partnerId = value.asInstanceOf[Integer]
    if (partnerId == null || partnerId == 0)
      return ""
    val isSOTrx = "Y".equals(Env.getContext(context, windowNo, "IsSOTrx"))
    val partner = MBPartner.get(context, partnerId)
    if (partner.getSalesRep_ID > 0)
      contract.setSalesRep_ID(partner.getSalesRep_ID)
    val priceListId = if (isSOTrx) partner.getM_PriceList_ID else partner.getPO_PriceList_ID
    if (priceListId > 0)
      contract.setM_PriceList_ID(priceListId)
    else {
      val priceListId = Env.getContextAsInt(context, "#M_PriceList_ID")
      contract.setM_PriceList_ID(priceListId)
    }
    val partnerBillLocations = MBPartnerLocation.getForBPartner(context, partner.get_ID(), null).toList.filter(location => location.isBillTo)
    val partnerShipLocations = MBPartnerLocation.getForBPartner(context, partner.get_ID(), null).toList.filter(location => location.isShipTo)
    val partnerBillId = Option(partnerBillLocations.last.getC_BPartner_ID).getOrElse(partner.get_ID())
    val partnerShipId = Option(partnerShipLocations.last.getC_BPartner_ID).getOrElse(Env.getContextAsInt(context, windowNo, Env.TAB_INFO, "C_BPartner_Location_ID"))
    contract.setBill_BPartner_ID(partnerBillId)
    contract.setC_BPartner_Location_ID(partnerShipId)
    val contactId = Option(MUser.getOfBPartner(context, partner.get_ID(), null).toList.last.get_ID()).getOrElse(Env.getContextAsInt(context, windowNo, Env.TAB_INFO, "AD_User_ID"))
    contract.setAD_User_ID(contactId)
    contract.setBill_User_ID(contactId)

    if (isSOTrx) {
      val creditLimit = partner.getSO_CreditLimit
      if (creditLimit.signum() != 0) {
        val creditAvailable = partner.getSO_CreditLimit.subtract(partner.getSO_CreditUsed)
        if (creditAvailable.signum() < 0) {
          gridTab.fireDataStatusEEvent("CreditLimitOver", DisplayType.getNumberFormat(DisplayType.Amount).format(creditAvailable), false)
        }
      }
    }
    val maybePOReference = Option(partner.getPOReference)
    maybePOReference.foreach(poReference => contract.setPOReference(poReference))
    val maybeDescription = Option(partner.getSO_Description)
    maybeDescription.foreach(description => contract.setDescription(description))
    contract.setIsDiscountPrinted(partner.isDiscountPrinted)
    val maybePaymentRule = if (isSOTrx) Option(partner.getPaymentRule) else Option(partner.getPaymentRulePO)
    contract.setPaymentRule(maybePaymentRule.getOrElse(X_S_Contract.PAYMENTRULE_OnCredit))
    val paymentTermId = if (isSOTrx) partner.getC_PaymentTerm_ID else partner.getPO_PaymentTerm_ID
    contract.setC_PaymentTerm_ID(paymentTermId)
    val maybeInvoiceRule = Option(if (isSOTrx) partner.getPaymentRule else partner.getPaymentRulePO)
    contract.setInvoiceRule(maybeInvoiceRule.getOrElse(X_S_Contract.INVOICERULE_AfterDelivery))
    val maybeDeliveryRule = if (isSOTrx) Option(partner.getDeliveryRule) else Option(partner.getDeliveryRule)
    contract.setDeliveryRule(maybeDeliveryRule.getOrElse(X_S_Contract.DELIVERYRULE_Availability))
    val maybeDeliveryViaRule = if (isSOTrx) Option(partner.getDeliveryViaRule) else Option(partner.getDeliveryViaRule)
    contract.setDeliveryViaRule(maybeDeliveryViaRule.getOrElse(X_S_Contract.DELIVERYVIARULE_Pickup))
    val maybeFreightCostRule = if (isSOTrx) Option(partner.getFreightCostRule) else Option(partner.getFreightCostRule)
    contract.setFreightCostRule(maybeFreightCostRule.getOrElse(X_S_Contract.FREIGHTCOSTRULE_Calculated))
    return "";
  }

  def bPartnerBill(context: Properties, windowNo: Int, gridTab: GridTab, gridField: GridField, value: Object): String = {
    val contract = GridTabWrapper.create(gridTab, classOf[I_S_Contract])
    if (isCalloutActive)
      return ""
    val billPartnerId = value.asInstanceOf[Integer]
    if (billPartnerId == null || billPartnerId == 0)
      return ""

    val billPartner = MBPartner.get(context, billPartnerId)
    val partnerBillLocations = MBPartnerLocation.getForBPartner(context, billPartnerId, null).toList
    val isSOTrx = "Y".equals(Env.getContext(context, windowNo, "IsSOTrx"))
    val priceListId = billPartner.getM_PriceList_ID
    if (priceListId <= 0) {
      val defaultPriceListId = Env.getContextAsInt(context, "#M_PriceList_ID");
      contract.setM_PriceList_ID(defaultPriceListId)
    } else {
      contract.setM_PriceList_ID(priceListId)
    }

    val billLocation = partnerBillLocations.last
    val billLocationId = billLocation.getC_BPartner_Location_ID
    if (billPartnerId == contract.getC_BPartner_ID) {
      val partnerLocationId = Env.getContextAsInt(context, windowNo, Env.TAB_INFO, "C_BPartner_Location_ID")
      contract.setBill_Location_ID(partnerLocationId)
      val contactId = Env.getContextAsInt(context, windowNo, Env.TAB_INFO, "AD_User_ID");
      if (contactId > 0) {
        contract.setBill_User_ID(contactId)
      } else {
        val contact = MUser.getOfBPartner(context, billPartner.get_ID(), null).toList.last
        contract.setBill_User_ID(contact.get_ID())
      }
    } else {
      contract.setBill_BPartner_ID(billLocationId)
    }

    if (isSOTrx) {
      val creditLimit = billPartner.getSO_CreditLimit
      if (creditLimit.signum() != 0) {
        val creditAvailable = billPartner.getSO_CreditLimit.subtract(billPartner.getSO_CreditUsed)
        if (creditAvailable.signum() < 0) {
          gridTab.fireDataStatusEEvent("CreditLimitOver", DisplayType.getNumberFormat(DisplayType.Amount).format(creditAvailable), false)
        }
      }
      val maybePOReference = Option(billPartner.getPOReference)
      maybePOReference.foreach(poReference => contract.setPOReference(poReference))
      val maybeDescription = Option(billPartner.getSO_Description)
      maybeDescription.foreach(description => contract.setDescription(description))
      contract.setIsDiscountPrinted(billPartner.isDiscountPrinted)
      val maybePaymentRule = if (isSOTrx) Option(billPartner.getPaymentRule) else Option(billPartner.getPaymentRulePO)
      contract.setPaymentRule(maybePaymentRule.getOrElse(X_S_Contract.PAYMENTRULE_OnCredit))
      val paymentTermId = if (isSOTrx) billPartner.getC_PaymentTerm_ID else billPartner.getPO_PaymentTerm_ID
      contract.setC_PaymentTerm_ID(paymentTermId)
      val maybeInvoiceRule = Option(if (isSOTrx) billPartner.getPaymentRule else billPartner.getPaymentRulePO)
      contract.setInvoiceRule(maybeInvoiceRule.getOrElse(X_S_Contract.INVOICERULE_AfterDelivery))
    }
    return ""
  }


  def priceList(context: Properties, windowNo: Int, gridTab: GridTab, gridField: GridField, value: Object): String = {
    val contract = GridTabWrapper.create(gridTab, classOf[I_S_Contract])
    val priceListId = contract.getM_PriceList_ID
    if (priceListId < 0)
      return ""

    val priceList = MPriceList.get(context, priceListId, null)
    val isTaxIncluded = priceList.isTaxIncluded
    val currencyId = priceList.getC_Currency_ID
    val contractDate = Env.getContextAsDate(context, windowNo, I_S_Contract.COLUMNNAME_DateContract)
    val priceListVersion = priceList.getPriceListVersion(contractDate)
    contract.setIsTaxIncluded(isTaxIncluded)
    contract.setC_Currency_ID(currencyId)
    Env.setContext(context, windowNo, "M_PriceList_Version_ID", priceListVersion.get_ID())
    ""
  }

  def tax(context: Properties, windowNo: Int, gridTab: GridTab, gridField: GridField, value: Object): String = {
    val contractLine = GridTabWrapper.create(gridTab, classOf[I_S_ContractLine])
    val column = gridField.getColumnName
    if (value == null)
      return ""
    val productId: Integer = if (column.equals("M_Product_ID")) value.asInstanceOf[Integer] else Env.getContextAsInt(context, windowNo, "M_Product_ID")
    val chargeId: Integer = if (column.equals("C_Charge_ID")) value.asInstanceOf[Integer] else Env.getContextAsInt(context, windowNo, "C_Charge_ID")
    //log.fine("Product=" + productId + ", C_Charge_ID=" + chargeId);
    if (productId == 0 && chargeId == 0)
      return amt(context, windowNo, gridTab, gridField, value)

    //	Check Partner Location
    val shipPartnerLocationId: Integer = if (column.equals("C_BPartner_Location_ID")) value.asInstanceOf[Integer] else Env.getContextAsInt(context, windowNo, "C_BPartner_Location_ID")
    if (shipPartnerLocationId == 0)
      return amt(context, windowNo, gridTab, gridField, value)
    //log.fine("Ship BP_Location=" + shipPartnerLocationId);
    val billDate = Env.getContextAsDate(context, windowNo, I_S_Contract.COLUMNNAME_DateContract)
    //log.fine("Bill Date=" + billDate);
    val shipDate = Env.getContextAsDate(context, windowNo, I_S_Contract.COLUMNNAME_DateDeadline)
    //log.fine("Ship Date=" + shipDate);
    val orgId = Env.getContextAsInt(context, windowNo, I_S_Contract.COLUMNNAME_AD_Org_ID)
    //log.fine("Org=" + orgId);
    val warehouseId = Env.getContextAsInt(context, windowNo, "M_Warehouse_ID")
    //log.fine("Warehouse=" + warehouseId);
    val billPartnerLocationId = Env.getContextAsInt(context, windowNo, I_S_Contract.COLUMNNAME_Bill_Location_ID)
    val isSOTrx = "Y".equals(Env.getContext(context, windowNo, "IsSOTrx"))
    //log.fine("Bill BP_Location=" + shipPartnerLocationId);
    val taxId = Tax.get(context, productId, chargeId, billDate, shipDate, orgId, warehouseId, if (billPartnerLocationId == 0) shipPartnerLocationId else billPartnerLocationId, shipPartnerLocationId, isSOTrx, null)
    //log.info("Tax ID=" + taxId);
    if (taxId == 0) gridTab.fireDataStatusEEvent(CLogger.retrieveError()) else contractLine.setC_Tax_ID(taxId)
    amt(context, windowNo, gridTab, gridField, value)
    ""
  }

  def charge(context: Properties, windowNo: Int, gridTab: GridTab, gridField: GridField, value: Object): String = {
    val contractLine = GridTabWrapper.create(gridTab, classOf[I_S_ContractLine])
    val chargeId = value.asInstanceOf[Integer]
    if (chargeId == null || chargeId <= 0)
      return ""
    if (contractLine.getM_Product_ID > 0) {
      contractLine.setC_Charge_ID(-1)
      return "ChargeExclusively"
    }

    contractLine.setM_AttributeSetInstance_ID(-1)
    contractLine.setS_ResourceAssignment_ID(-1)
    contractLine.setC_UOM_ID(100)

    Env.setContext(context, windowNo, "DiscountSchema", "N")
    val charge = MCharge.get(context, chargeId)
    contractLine.setPriceEntered(charge.getChargeAmt)
    contractLine.setPriceActual(charge.getChargeAmt)
    contractLine.setPriceLimit(Env.ZERO)
    contractLine.setPriceList(Env.ZERO)
    contractLine.setDiscount(Env.ZERO)
    tax(context, windowNo, gridTab, gridField, value)
  }

  def qty(context: Properties, windowNo: Int, gridTab: GridTab, gridField: GridField, value: Object): String = {
    val contractLine = GridTabWrapper.create(gridTab, classOf[I_S_ContractLine])
    if (isCalloutActive() || value == null)
      return ""
    val productId = Env.getContextAsInt(context, windowNo, "M_Product_ID")
    if (productId == 0) contractLine.setQtyOrdered(contractLine.getQtyEntered) else if (gridField.getColumnName.equals(I_S_ContractLine.COLUMNNAME_C_UOM_ID)) {
      val uomId = value.asInstanceOf[Integer]
      val precision = MUOM.getPrecision(context, uomId)
      val qtyEnteredRound = contractLine.getQtyEntered.setScale(precision, BigDecimal.ROUND_HALF_UP)
      if (contractLine.getQtyEntered.compareTo(qtyEnteredRound) != 0) {
        log.fine("Corrected QtyEntered Scale UOM=" + uomId + "; QtyEntered=" + contractLine.getQtyEntered + "->" + qtyEnteredRound)
        contractLine.setQtyEntered(qtyEnteredRound)
      }
      val qtyOrdered = Option(MUOMConversion.convertProductFrom(context, productId, uomId, contractLine.getQtyEntered)).getOrElse(contractLine.getQtyEntered)
      val conversion = contractLine.getQtyEntered.compareTo(qtyOrdered) != 0
      Env.setContext(context, windowNo, "UOMConversion", if (conversion) "Y" else "N")
      val priceEntered = Option(MUOMConversion.convertProductFrom(context, productId, uomId, contractLine.getPriceActual)).getOrElse(contractLine.getPriceActual)
      log.fine("UOM=" + uomId
        + ", QtyEntered/PriceActual=" + contractLine.getQtyEntered + "/" + contractLine.getPriceActual
        + " -> " + conversion
        + " QtyOrdered/PriceEntered=" + qtyOrdered + "/" + priceEntered)
      Env.setContext(context, windowNo, "UOMConversion", if (conversion) "Y" else "N")
      contractLine.setQtyOrdered(qtyOrdered)
      contractLine.setPriceEntered(priceEntered)
    } else if (gridField.getColumnName.equals(I_S_ContractLine.COLUMNNAME_QtyEntered)) {
      val uomId = Env.getContextAsInt(context, windowNo, I_S_ContractLine.COLUMNNAME_C_UOM_ID)
      val qtyEntered = value.asInstanceOf[BigDecimal]
      val qtyEnteredRound = qtyEntered.setScale(MUOM.getPrecision(context, uomId), BigDecimal.ROUND_HALF_UP)
      if (qtyEntered.compareTo(qtyEnteredRound) != 0) {
        log.fine("Corrected QtyEntered Scale UOM=" + uomId
          + "; QtyEntered=" + qtyEntered + "->" + qtyEnteredRound)
        contractLine.setQtyEntered(qtyEnteredRound)
      }
      val qtyOrdered = MUOMConversion.convertProductFrom(context, productId, uomId, contractLine.getQtyEntered)
      val conversion = qtyEntered.compareTo(contractLine.getQtyEntered) != 0
      log.fine("UOM=" + uomId
        + ", QtyEntered=" + qtyEntered
        + " -> " + conversion
        + " QtyOrdered=" + qtyOrdered)
      Env.setContext(context, windowNo, "UOMConversion", if (conversion) "Y" else "N")
      contractLine.setQtyOrdered(qtyOrdered)
    } else if (gridField.getColumnName.equals(I_S_ContractLine.COLUMNNAME_QtyOrdered)) {
      val uomId = Env.getContextAsInt(context, windowNo, I_S_ContractLine.COLUMNNAME_C_UOM_ID)
      val qtyOrdered = value.asInstanceOf[BigDecimal]
      val precision = MProduct.get(context, productId).getUOMPrecision()
      val qtyOrderedRound = qtyOrdered.setScale(precision, BigDecimal.ROUND_HALF_UP)
      if (qtyOrdered.compareTo(qtyOrderedRound) != 0) {
        log.fine("Corrected QtyOrdered Scale "
          + qtyOrdered + "->" + qtyOrderedRound)
        contractLine.setQtyOrdered(qtyOrderedRound)
      }
    }
    if (productId > 0 && Env.isSOTrx(context, windowNo) && contractLine.getQtyOrdered.signum() > 0) {
      val product = MProduct.get(context, productId)
      if (product.isStocked) {
        val warehouseId = Env.getContextAsInt(context, windowNo, I_S_ContractLine.COLUMNNAME_M_Warehouse_ID)
        val attributeSetInstanceId = Env.getContextAsInt(context, windowNo, I_S_ContractLine.COLUMNNAME_M_AttributeSetInstance_ID)
        val available = Option(MStorage.getQtyAvailable(warehouseId, 0, productId, attributeSetInstanceId, null)).getOrElse(BigDecimal.ZERO)
        if (available.signum() == 0) gridTab.fireDataStatusEEvent("NoQtyAvailable", "0", false) else if (available.compareTo(contractLine.getQtyOrdered) < 0) gridTab.fireDataStatusEEvent("InsufficientQtyAvailable", available.toString(), false) else {
          val contractLineId = Option(contractLine.getS_ContractLine_ID).getOrElse(0)
          // Todo: pending Implement reserved quantity for contract line
          val notReserved = Option(MOrderLine.getNotReserved(context, warehouseId, productId, attributeSetInstanceId, contractLineId)).getOrElse(BigDecimal.ZERO)
          val total = available.subtract(notReserved)
          if (total.compareTo(contractLine.getQtyOrdered) < 0) {
            val info = Msg.parseTranslation(context, "@QtyAvailable@=" + available + "  -  @QtyNotReserved@=" + notReserved + "  =  " + total)
            gridTab.fireDataStatusEEvent("InsufficientQtyAvailable", info, false)
          }
        }
      }
    }
    ""
  }

  def product(context: Properties, windowNo: Int, gridTab: GridTab, gridField: GridField, value: Object) = {
    val contractLine = GridTabWrapper.create(gridTab, classOf[I_S_ContractLine])
    val productId = value.asInstanceOf[Integer]
    if (productId == null || productId == 0) {
      contractLine.setM_AttributeSetInstance_ID(-1)
      contractLine.setPriceList(BigDecimal.ZERO)
      contractLine.setPriceLimit(BigDecimal.ZERO)
      contractLine.setPriceActual(BigDecimal.ZERO)
      contractLine.setPriceEntered(BigDecimal.ZERO)
      contractLine.setC_Currency_ID(-1)
      contractLine.setDiscount(BigDecimal.ZERO)
      contractLine.setC_UOM_ID(-1)
    }
    val product = MProduct.get(context, productId)
    val attributeSetInstance = product.getM_AttributeSetInstance
    contractLine.setC_Charge_ID(-1)

    val productWithAttributeSetInstance = MProduct.get(Env.getCtx(), productId)
    gridTab.setValue("M_AttributeSetInstance_ID", productWithAttributeSetInstance.getEnvAttributeSetInstance(context, windowNo))
    if (Env.getContextAsInt(context, windowNo, Env.TAB_INFO, "M_Product_ID") == productId
      && Env.getContextAsInt(context, windowNo, Env.TAB_INFO, "M_AttributeSetInstance_ID") != 0) gridTab.setValue("M_AttributeSetInstance_ID", Env.getContextAsInt(context, windowNo, Env.TAB_INFO, "M_AttributeSetInstance_ID")) else gridTab.setValue("M_AttributeSetInstance_ID", attributeSetInstance.getM_AttributeSetInstance_ID())

    val partnerId = Env.getContextAsInt(context, windowNo, "C_BPartner_ID")
    val quantityOrdered = contractLine.getQtyOrdered
    val IsSOTrx = Env.getContext(context, windowNo, "IsSOTrx").equals("Y")
    val productPricing = new MProductPricing(productId.intValue(), partnerId, quantityOrdered, IsSOTrx, null)
    val priceListId = Env.getContextAsInt(context, windowNo, "M_PriceList_ID")
    productPricing.setM_PriceList_ID(priceListId)
    val orderDate = contractLine.getDateStart
    val priceListVersionId = Env.getContextAsInt(context, windowNo, "M_PriceList_Version_ID")
    if (priceListVersionId == 0 && priceListId > 0) {
      val priceList = MPriceList.get(context, priceListId, null)
      val priceListVersion = priceList.getPriceListVersion(orderDate)
      if (priceListVersion.getM_PriceList_Version_ID > 0)
        Env.setContext(context, windowNo, "M_PriceList_Version_ID", priceListVersionId)
      productPricing.setM_PriceList_Version_ID(priceListVersion.getM_PriceList_Version_ID)
    }
    productPricing.setPriceDate(orderDate)
    contractLine.setPriceList(productPricing.getPriceList)
    contractLine.setPriceLimit(productPricing.getPriceLimit)
    contractLine.setPriceActual(productPricing.getPriceStd)
    contractLine.setPriceEntered(productPricing.getPriceStd)
    contractLine.setC_Currency_ID(productPricing.getC_Currency_ID)
    contractLine.setDiscount(productPricing.getDiscount)
    contractLine.setC_UOM_ID(productPricing.getC_UOM_ID)
    contractLine.setQtyEntered(contractLine.getQtyEntered)
    Env.setContext(context, windowNo, "DiscountSchema", if (productPricing.isDiscountSchema) "Y" else "N")
    if (Env.isSOTrx(context, windowNo)) if (product.isStocked) {
      val qtyOrdered = contractLine.getQtyOrdered
      val warehouseId = Env.getContextAsInt(context, windowNo, "M_Warehouse_ID")
      val attributeSetInstanceId = Env.getContextAsInt(context, windowNo, "M_AttributeSetInstance_ID")
      val available = Option(MStorage.getQtyAvailable(warehouseId, 0, productId, attributeSetInstanceId, null)).getOrElse(BigDecimal.ZERO)
      if (available.signum() == 0)
        gridTab.fireDataStatusEEvent("NoQtyAvailable", "0", false)
      else if (available.compareTo(qtyOrdered) < 0) gridTab.fireDataStatusEEvent("InsufficientQtyAvailable", available.toString(), false) else {
        val contractLineId = Option(contractLine.getS_ContractLine_ID).getOrElse(0)
        // Todo: pending Implement reserved quantity for contract line
        val notReserved = Option(MOrderLine.getNotReserved(context, warehouseId, productId, attributeSetInstanceId, contractLineId)).getOrElse(BigDecimal.ZERO)
        val total = available.subtract(notReserved)
        if (total.compareTo(contractLine.getQtyOrdered) < 0) {
          val info = Msg.parseTranslation(context, "@QtyAvailable@=" + available + "  -  @QtyNotReserved@=" + notReserved + "  =  " + total)
          gridTab.fireDataStatusEEvent("InsufficientQtyAvailable", info, false)
        }
      }
    }
    ""
  }

  def amt(context: Properties, windowNo: Int, gridTab: GridTab, gridField: GridField, value: Object): String = {
    if (isCalloutActive || value == null)
      return ""
    val amount = Option(value.asInstanceOf[BigDecimal])
    val contractLine = GridTabWrapper.create(gridTab, classOf[I_S_ContractLine])
    val uomToId = Env.getContextAsInt(context, windowNo, I_S_ContractLine.COLUMNNAME_C_UOM_ID)
    val productId = Env.getContextAsInt(context, windowNo, I_S_ContractLine.COLUMNNAME_M_Product_ID)
    val priceListId = Env.getContextAsInt(context, windowNo, "M_PriceList_ID")
    val precision = MPriceList.getStandardPrecision(context, priceListId)
    if (productId == 0) {
      // if price change sync price actual and entered
      // else ignore
      if (gridField.getColumnName.equals("PriceActual")) {
        val priceEntered = Option(value.asInstanceOf[BigDecimal]).getOrElse(BigDecimal.ZERO)
        contractLine.setPriceEntered(priceEntered)
      }
      else if (gridField.getColumnName.equals("PriceEntered")) {
        val priceActual = Option(value.asInstanceOf[BigDecimal]).getOrElse(BigDecimal.ZERO)
        contractLine.setPriceActual(priceActual)
      } else if ((gridField.getColumnName.equals("QtyOrdered")
        || gridField.getColumnName.equals("QtyEntered")
        || gridField.getColumnName.equals("C_UOM_ID")
        || gridField.getColumnName.equals("M_Product_ID"))
        && !"N".equals(Env.getContext(context, windowNo, "DiscountSchema"))) {
        val partnerId = Env.getContextAsInt(context, windowNo, "C_BPartner_ID")
        if (gridField.getColumnName.equals("QtyEntered")) {
          val qtyOrdered = Option(MUOMConversion.convertProductFrom(context, productId, uomToId, contractLine.getPriceEntered)).getOrElse(contractLine.getPriceEntered)
          val isSOTrx = Env.getContext(context, windowNo, "IsSOTrx").equals("Y")
          val productPrice = new MProductPricing(productId, partnerId, qtyOrdered, isSOTrx, null)
          productPrice.setM_PriceList_ID(priceListId)
          val priceListVersionId = Env.getContextAsInt(context, windowNo, "M_PriceList_Version_ID")
          productPrice.setM_PriceList_Version_ID(priceListVersionId)
          val dateOrdered = contractLine.getDateStart
          productPrice.setPriceDate(dateOrdered)
          val priceEntered = Option(MUOMConversion.convertProductFrom(context, productId, uomToId, productPrice.getPriceStd)).getOrElse(productPrice.getPriceStd)
          log.fine("QtyChanged -> PriceActual=" + productPrice.getPriceStd
            + ", PriceEntered=" + priceEntered + ", Discount=" + productPrice.getDiscount)
          val priceActual = productPrice.getPriceStd
          contractLine.setPriceActual(productPrice.getPriceStd)
          contractLine.setPriceActual(productPrice.getPriceStd)
          contractLine.setDiscount(productPrice.getDiscount)
          contractLine.setPriceEntered(priceEntered)
          Env.setContext(context, windowNo, "DiscountSchema", if (productPrice.isDiscountSchema) "Y" else "N")
        }
      } else if (gridField.getColumnName.equals("PriceActual")) {
        val priceActual = value.asInstanceOf[BigDecimal]
        val priceEntered = Option(MUOMConversion.convertProductFrom(context, productId, uomToId, priceActual)).getOrElse(priceActual)
        log.fine("PriceActual=" + priceActual
          + " -> PriceEntered=" + priceEntered)
        contractLine.setPriceEntered(priceEntered)
      } else if (gridField.getColumnName.equals("PriceEntered")) {
        val priceEntered = value.asInstanceOf[BigDecimal]
        val priceActual = Option(MUOMConversion.convertProductTo(context, productId, uomToId, priceEntered)).getOrElse(priceEntered)
        log.fine("PriceEntered=" + priceEntered
          + " -> PriceActual=" + priceActual)
        contractLine.setPriceActual(priceActual)
      }
      //  Discount entered - Calculate Actual/Entered
      if (gridField.getColumnName.equals("Discount")) {
        val priceList = contractLine.getPriceList
        val discount = contractLine.getDiscount
        if (priceList.signum() != 0) {
          val priceActual = new BigDecimal((100.0 - discount.doubleValue()) / 100.0 * priceList.doubleValue())
          if (priceActual.scale() > precision) {
            val priceActualRound = priceActual.setScale(precision, BigDecimal.ROUND_HALF_UP)
            val priceEntered = Option(MUOMConversion.convertProductFrom(context, productId, uomToId, priceActualRound)).getOrElse(priceActualRound)
            contractLine.setPriceActual(priceActualRound)
            contractLine.setPriceEntered(priceEntered)
          }
        }
      } else {
        val priceList = contractLine.getPriceList
        val priceActual = contractLine.getPriceActual
        if (priceList.signum() == 0) {
          val discount = new BigDecimal((priceList.doubleValue() - priceActual.doubleValue()) / priceList.doubleValue() * 100.0)
          contractLine.setDiscount(discount)
        } else {
          val discount = new BigDecimal((priceList.doubleValue() - priceActual.doubleValue()) / priceList.doubleValue() * 100.0)
          if (discount.scale() > 2) {
            val discountRound = discount.setScale(2, BigDecimal.ROUND_HALF_UP)
            contractLine.setDiscount(discountRound)
          }
        }
      }
      log.fine("PriceEntered=" + contractLine.getPriceEntered + ", Actual=" + contractLine.getPriceActual + ", Discount=" + contractLine.getDiscount)
      //	Check Price Limit?
      if (MPriceList.isCheckPriceLimit(priceListId) && contractLine.getPriceLimit.doubleValue() != 0.0
        && contractLine.getPriceActual.compareTo(contractLine.getPriceLimit) < 0) {
        val priceActual = contractLine.getPriceLimit
        val priceEntered = Option(MUOMConversion.convertProductFrom(context, productId, uomToId, contractLine.getPriceLimit)).getOrElse(contractLine.getPriceLimit)
        log.fine("(under) PriceEntered=" + priceActual + ", Actual" + priceActual)
        contractLine.setPriceActual(contractLine.getPriceLimit)
        contractLine.setPriceEntered(priceEntered)
        gridTab.fireDataStatusEEvent("UnderLimitPrice", "", false)
        //	Repeat Discount calc
        if (contractLine.getPriceList.signum() != 0) {
          val priceList = contractLine.getPriceList
          val priceActual = contractLine.getPriceActual
          val discount = new BigDecimal((priceList.doubleValue() - priceActual.doubleValue()) / priceList.doubleValue() * 100.0)
          if (discount.scale() > 2) {
            val discountRound = discount.setScale(2, BigDecimal.ROUND_HALF_UP)
            contractLine.setDiscount(discountRound)
          }
        }
      }
      //	Line Net Amt
      val lineNetAmt = contractLine.getQtyOrdered.multiply(contractLine.getPriceActual)
      if (lineNetAmt.scale() > precision) {
        val lineNetAmtRound = lineNetAmt.setScale(precision, BigDecimal.ROUND_HALF_UP)
        log.info("LineNetAmt=" + lineNetAmtRound)
        contractLine.setLineNetAmt(lineNetAmtRound)
      }
      return ""
    }
    ""
  }
}
