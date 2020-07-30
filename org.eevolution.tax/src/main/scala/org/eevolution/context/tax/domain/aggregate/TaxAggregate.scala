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

package org.eevolution.context.tax.domain.aggregate

import java.time.LocalDateTime

import org.adempiere.exceptions.AdempiereException
import org.eevolution.context.kernel.domain.ubiquitouslanguage.{Amount, Id, _}
import org.eevolution.context.tax.api.service.TaxBaseService.TaxBaseService
import org.eevolution.context.tax.api.service.TaxDefinitionService.TaxDefinitionService
import org.eevolution.context.tax.api.service.TaxService.TaxService
import org.eevolution.context.tax.api.service.{TaxBaseService, TaxDefinitionService, TaxService}
import org.eevolution.context.tax.domain.model._
import zio.{RIO, Task, ZIO}

import scala.collection.mutable.ListBuffer
import scala.math.BigDecimal.RoundingMode

/*case class TaxId(value: Id) extends AggregateBigIntId

case class TaxAggregate(id: TaxId) extends Aggregate {
  override type AggregateType = TaxAggregate
  override type IdType = TaxId
  override protected val tag: ClassTag[TaxAggregate] = classTag[TaxAggregate]

}*/

case class TaxDocumentLine(taxId: Id
                           , isDocumentLevel: YesNo
                           , isTaxIncluded: YesNo
                           , taxRate: Amount
                           , taxMultiplier: Amount
                           , taxBaseAmount: Amount
                           , isTaxManuallyEntered: YesNo
                           , taxAmount: Amount)


case class TaxLine(taxId: Id
                   , isDocumentLevel: YesNo
                   , isTaxIncluded: YesNo
                   , taxRate: Amount
                   , taxMultiplier: Amount
                   , taxBaseAmount: Amount
                   , taxAmount: Amount)

object TaxAggregate {



  def getTaxes(taxDocumentLines: ListBuffer[TaxDocumentLine], scale: Int): Task[List[TaxLine]] = Task.effectTotal(
    taxDocumentLines.groupBy(taxDocumentLine => taxDocumentLine.taxId) // Group By Tax Id
    .map(taxDocumentLine => {
      val taxId = taxDocumentLine._2.head.taxId                     // Tax Id
      val isDocumentLevel = taxDocumentLine._2.head.isDocumentLevel // Is Document Level
      val isIncludedTax = taxDocumentLine._2.head.isDocumentLevel   // Is Included Tax
      val taxRate = taxDocumentLine._2.head.taxRate                 // Tax Rate
      val taxMultiplier = taxDocumentLine._2.head.taxMultiplier     // Tax Multiplier
      val taxBase = taxDocumentLine._2.map(_.taxBaseAmount).sum     // Tax Base Amount
      val taxAmount = taxDocumentLine._2.map(_.taxAmount).sum       // Tax Amount
      val taxLine = TaxLine(taxId
        , isDocumentLevel
        , isIncludedTax
        , taxRate
        , taxMultiplier
        , taxBase
        , if (isDocumentLevel) (taxBase * taxMultiplier).setScale(scale, RoundingMode.HALF_UP) else taxAmount)
      taxLine
    }
    ).toList
  )

  /**
   *
   * @param maybeTaxId
   * @param maybeIsSOTrx
   * @param maybeIsManual
   * @param maybeClientId
   * @param maybeOrgId
   * @param maybeOrgTypeId
   * @param maybePartnerGroupId
   * @param maybePartnerId
   * @param maybeProductCategoryId
   * @param maybeProductId
   * @param maybeTaxCategoryId
   * @param maybeTaxGroupId
   * @param maybeTaxTypeId
   * @param maybeIsTaxIncluded
   * @param maybeTaxManuallyEntered
   * @param maybePrice
   * @param maybeCost
   * @param maybeQuantity
   * @param maybeWeight
   * @param maybeTransactionDate
   * @param maybePartnerLocationId
   * @param scale
   * @return
   */
  def calculator(maybeTaxId: Option[TableDirect]
                 , maybeIsSOTrx: Option[YesNo]
                 , maybeIsManual: Option[YesNo]
                 , maybeClientId: Option[TableDirect]
                 , maybeOrgId: Option[TableDirect]
                 , maybeOrgTypeId: Option[TableDirect]
                 , maybePartnerGroupId: Option[TableDirect]
                 , maybePartnerId: Option[Search]
                 , maybeProductCategoryId: Option[TableDirect]
                 , maybeProductId: Option[Search]
                 , maybeTaxCategoryId: Option[TableDirect]
                 , maybeTaxGroupId: Option[TableDirect]
                 , maybeTaxTypeId: Option[TableDirect]
                 , maybeIsTaxIncluded: Option[YesNo]
                 , maybeTaxManuallyEntered: Option[Amount]
                 , maybePrice: Option[Amount]
                 , maybeCost: Option[Amount]
                 , maybeQuantity: Option[Amount]
                 , maybeWeight: Option[Amount]
                 , maybeTransactionDate: Option[DateTime]
                 , maybePartnerLocationId: Option[TableDirect]
                 , scale: Id): RIO[TaxDefinitionService with TaxBaseService with TaxService, ListBuffer[TaxDocumentLine]] = for {
    //Get Valid Tax Definition
    taxDefinition <- getValidTaxDefinition(maybeTaxId
      , maybeIsSOTrx
      , maybeIsManual
      , maybeClientId
      , maybeOrgId
      , maybeOrgTypeId
      , maybePartnerGroupId
      , maybePartnerId
      , maybeProductCategoryId
      , maybeProductId
      , maybeTaxCategoryId
      , maybeTaxGroupId
      , maybeTaxTypeId
      , maybeTransactionDate)
    //Get tax definition by combination
    tax <- getTax(taxDefinition)
    //Get Tax Calculation and check if Parent Tax or Normal tTx
    calculationResults <- if (tax.isSummary) {
      val taxDocumentLines = new ListBuffer[TaxDocumentLine]
      for {
        //Get Children Taxes
        taxes <- TaxService.getByParentTaxId(Option(tax.taxId))
        //Filling the calculation Results Buffer
        _ <- RIO.foreach(taxes) { tax =>
          calculator(Option(tax.taxId)
            , maybeIsSOTrx
            , maybeIsManual
            , maybeClientId
            , maybeOrgId
            , maybeOrgTypeId
            , maybePartnerGroupId
            , maybePartnerId
            , maybeProductCategoryId
            , maybeProductId
            , maybeTaxCategoryId
            , maybeTaxGroupId
            , maybeTaxTypeId
            , maybeIsTaxIncluded
            , maybeTaxManuallyEntered
            , maybePrice
            , maybeCost
            , maybeQuantity
            , maybeWeight
            , maybeTransactionDate
            , maybePartnerLocationId
            , scale)
            .map(calculationResult => taxDocumentLines ++: calculationResult)
        }
      } yield ()
      //Create effect with result
      Task.succeed(taxDocumentLines)
    } else for {
      //Get Tax Base to calculate tax base
      taxBase <- getTaxBase(taxDefinition)
      //Get Tax Base Amount
      taxBaseAmount <- getTaxBaseAmount(
        taxBase,
        maybePrice,
        maybeCost,
        maybeQuantity,
        maybeWeight)
      //get Calculation Results
      calculationResults <- getTaxDocumentLine(tax, tax.isDocumentLevel, maybeIsTaxIncluded.getOrElse(false), taxBaseAmount, maybeTaxManuallyEntered, scale)
    } yield calculationResults
  } yield calculationResults

  /**
   *
   * @param taxDefinition
   * @return
   */
  def getTax(taxDefinition: TaxDefinition): RIO[TaxService, Tax] = taxDefinition.taxId match {
    case Some(taxId) => for {
      maybeTax <- TaxService.getByTaxId(taxId)
      tax <- maybeTax match {
        case Some(tax) => Task.succeed(tax)
        case None => Task.fail(new AdempiereException("@C_Tax_ID@ @NotFound@"))
      }
    } yield tax
    case None => Task.fail(new AdempiereException("@C_Tax_ID@ @NotFound@"))
  }

  /**
   *
   * @param taxDefinition
   * @return
   */
  def getTaxBase(taxDefinition: TaxDefinition): RIO[TaxBaseService, TaxBase] = taxDefinition.taxBaseId match {
    case Some(taxBaseId) => for {
      maybeTaxBase <- TaxBaseService.getByTaxBaseId(taxBaseId)
      taxBase <- maybeTaxBase match {
        case Some(taxBase) => Task.succeed(taxBase)
        case None => Task.succeed(TaxBase.create(taxDefinition.clientId, taxDefinition.orgId, Option("P"), 0, LocalDateTime.now, 0, Option("Default"), Option("Default"), true, "Default", Option(BigDecimal.valueOf(100)), Option(""), LocalDateTime.now, 0, "Default"))
      }
    } yield taxBase
    case None => Task.succeed(TaxBase.create(taxDefinition.clientId, taxDefinition.orgId, Option("P"), 0, LocalDateTime.now, 0, Option("Default"), Option("Default"), true, "Default", Option(BigDecimal.valueOf(100)), Option(""), LocalDateTime.now, 0, "Default"))
  }

  /**
   *
   * @param maybeTaxId
   * @param maybeIsSOTrx
   * @param maybeIsManual
   * @param maybeClientId
   * @param maybeOrgId
   * @param maybeOrgTypeId
   * @param maybePartnerGroupId
   * @param maybePartnerId
   * @param maybeProductCategoryId
   * @param maybeProductId
   * @param maybeTaxCategoryId
   * @param maybeTaxGroupId
   * @param maybeTaxTypeId
   * @param maybeTransactionDate
   * @return
   */
  def getValidTaxDefinition(maybeTaxId: Option[TableDirect]
                            , maybeIsSOTrx: Option[YesNo]
                            , maybeIsManual: Option[YesNo]
                            , maybeClientId: Option[TableDirect]
                            , maybeOrgId: Option[TableDirect]
                            , maybeOrgTypeId: Option[TableDirect]
                            , maybePartnerGroupId: Option[TableDirect]
                            , maybePartnerId: Option[Search]
                            , maybeProductCategoryId: Option[TableDirect]
                            , maybeProductId: Option[Search]
                            , maybeTaxCategoryId: Option[TableDirect]
                            , maybeTaxGroupId: Option[TableDirect]
                            , maybeTaxTypeId: Option[TableDirect]
                            , maybeTransactionDate: Option[DateTime]): RIO[TaxDefinitionService, TaxDefinition] = for {
    taxDefinition <- for {
      //Get Tax Definition by this company
      taxDefinitions <- TaxDefinitionService.getAll(maybeClientId.get)
      //Get Tax Definition Valid with these criteria
      taxDefinition <- taxDefinitions.find(taxDefinition => existValidTaxDefinition(taxDefinition
        , maybeTaxId
        , maybeOrgId
        , maybeOrgTypeId
        , maybePartnerGroupId
        , maybePartnerId: Option[Search]
        , maybeProductCategoryId
        , maybeProductId
        , maybeTaxCategoryId
        , maybeTaxGroupId
        , maybeTaxTypeId
        , maybeTransactionDate)) match {
        case Some(taxDefinition) => ZIO.succeed(taxDefinition)
        case None => ZIO.fail(new AdempiereException("@C_TaxDefinition_ID@ @NotFound@"))
      }
    } yield taxDefinition
  } yield taxDefinition

  /**
   * Get Tax Line
   *
   * @param tax
   * @param isDocumentLevel
   * @param isTaxIncluded
   * @param taxBaseAmount
   * @param maybeTaxManuallyEntered
   * @param scale
   * @return
   */
  def getTaxDocumentLine(tax: Tax
                         , isDocumentLevel: YesNo
                         , isTaxIncluded: YesNo
                         , taxBaseAmount: Amount
                         , maybeTaxManuallyEntered: Option[Amount]
                         , scale: Int): Task[ListBuffer[TaxDocumentLine]] = {
    // get Tax Id
    val taxId = tax.taxId
    // Get tax rate
    val taxRate: Amount = tax.rate
    // Calculate Multiplier so that is based on %
    val taxMultiplier = tax.rate / 100 // Tax Multiplier
    // Get tax entry manually
    val isTaxManuallyEntered = if (maybeTaxManuallyEntered.getOrElse(BigDecimal.valueOf(0.0)) != 0) true else false
    // Get tax base if is tax included then tax base amount / (tax multiplier + 1 ) else tax base amount
    val taxBase: Amount = if (isTaxIncluded) taxBaseAmount / (taxMultiplier + 1).setScale(12, RoundingMode.HALF_UP) else taxBaseAmount
    // Get tax amount if is tax included then tax base amount - tax base else tax base * tax multiplier and set scale with  rounding mode half up
    val taxAmount: Amount =
    // if tax Amount Entry != 0 use tax Amount Entry
      if (isTaxManuallyEntered) maybeTaxManuallyEntered.getOrElse(BigDecimal.valueOf(0.0))
      // else if is Document Level
      else if (isDocumentLevel) 0.0
      // else if is tax include then tax = tax base amount - tax base
      else if (isTaxIncluded)
        taxBaseAmount - taxBase
      // else tax = tax base * tax multiplier set scale to scale rounding mode half up
      else (taxBase * taxMultiplier).setScale(scale, RoundingMode.HALF_UP)
    // Create List Buffer with the tuple tax id , is Document Level , is Tax Included  , tax rate , tax base , tax amount
    Task.succeed(ListBuffer(TaxDocumentLine(taxId, isDocumentLevel, isTaxIncluded, taxRate, taxMultiplier, taxBase, isTaxManuallyEntered, taxAmount)))
  }

  /**
   *
   * @param taxBase
   * @param maybePrice
   * @param maybeCost
   * @param maybeQuantity
   * @param maybeWeight
   * @return
   */
  def getTaxBaseAmount(taxBase: TaxBase
                       , maybePrice: Option[Amount]
                       , maybeCost: Option[Amount]
                       , maybeQuantity: Option[Amount]
                       , maybeWeight: Option[Amount]): ZIO[TaxBaseService, Throwable, Number] = {
    val basePercentage = taxBase.percentage.getOrElse(BigDecimal.valueOf(100))
    taxBase.base match {
      //Tax Base based on Price
      case Some(base) if "P" == base =>
        val taxBaseAmount = maybePrice.getOrElse(BigDecimal.valueOf(0)) % basePercentage
        Task.succeed(taxBaseAmount)
      //Tax Base based on Cost
      case Some(base) if "C" == base =>
        val taxBaseAmount = maybeCost.getOrElse(BigDecimal.valueOf(0)) % basePercentage
        Task.succeed(taxBaseAmount)
      //Tax Base based on Quantity
      case Some(base) if "Q" == base =>
        val taxBaseAmount = maybeQuantity.getOrElse(BigDecimal.valueOf(0)) % basePercentage
        Task.succeed(taxBaseAmount)
      //Tax Base based on Weight
      case Some(base) if "W" == base =>
        val taxBaseAmount = maybeWeight.getOrElse(BigDecimal.valueOf(0)) % basePercentage
        Task.succeed(taxBaseAmount)
      case None => Task.fail(new AdempiereException("@C_TaxBase_ID@ @Base@ @NotFound@"))
    }
  }

  /**
   *
   * @param taxDefinition
   * @param maybeOrgId
   * @param maybeOrgTypeId
   * @param maybePartnerGroupId
   * @param maybePartnerId
   * @param maybeProductCategoryId
   * @param maybeProductId
   * @param maybeTaxCategoryId
   * @param maybeTaxGroupId
   * @param maybeTaxTypeId
   * @param maybeTransactionDate
   * @return
   */
  def existValidTaxDefinition(taxDefinition: TaxDefinition
                              , maybeTaxId: Option[TableDirect]
                              , maybeOrgId: Option[TableDirect]
                              , maybeOrgTypeId: Option[TableDirect]
                              , maybePartnerGroupId: Option[TableDirect]
                              , maybePartnerId: Option[Search]
                              , maybeProductCategoryId: Option[TableDirect]
                              , maybeProductId: Option[Search]
                              , maybeTaxCategoryId: Option[TableDirect]
                              , maybeTaxGroupId: Option[TableDirect]
                              , maybeTaxTypeId: Option[TableDirect]
                              , maybeTransactionDate: Option[DateTime]): Boolean = {
    val isValidTax = maybeTaxId match {
      case Some(taxId) if (taxDefinition.taxId.isDefined && taxDefinition.taxId.get == taxId) => true
      case None => true
    }
    val isValidOrg = if (taxDefinition.orgId == 0 || taxDefinition.orgId == maybeOrgId.get) true else false
    val iseOrgType = maybeOrgTypeId match {
      case Some(orgTypeId) if taxDefinition.orgTypeId.isDefined && taxDefinition.orgTypeId.get == orgTypeId => true
      case None => true
    }
    val isValidPartnerGroup = maybePartnerGroupId match {
      case Some(partnerGroupId) if taxDefinition.bPGroupId.isDefined && taxDefinition.bPGroupId.get == partnerGroupId => true
      case None => true
    }
    val isValidPartner = maybePartnerId match {
      case Some(partnerId) if taxDefinition.bPartnerId.isDefined && taxDefinition.bPartnerId.get == partnerId => true
      case None => true
    }
    val isValidProductCategory = maybeProductCategoryId match {
      case Some(productCategoryId) if taxDefinition.productCategoryId.isDefined && taxDefinition.productCategoryId.get == productCategoryId => true
      case None => true
    }
    val isValidProduct = maybeProductId match {
      case Some(productId) if taxDefinition.productId.isDefined && taxDefinition.productId.get == productId => true
      case None => true
    }
    val isTaxCategory = maybeTaxCategoryId match {
      case Some(taxCategoryId) if taxDefinition.taxCategoryId.isDefined && taxDefinition.taxCategoryId.get == taxCategoryId => true
      case None => true
    }
    val isTaxGroup = maybeTaxGroupId match {
      case Some(taxGroupId) if taxDefinition.taxGroupId.isDefined && taxDefinition.taxGroupId.get == taxGroupId => true
      case None => true
    }
    val isTaxType = maybeTaxTypeId match {
      case Some(taxTypeId) if taxDefinition.taxTypeId.isDefined && taxDefinition.taxTypeId.get == taxTypeId => true
      case None => true
    }
    val isValidDates = maybeTransactionDate match {
      case Some(transactionDate) if (taxDefinition.validFrom.isDefined && taxDefinition.validFrom.get.isAfter(transactionDate))
        && (taxDefinition.validTo.isDefined && taxDefinition.validTo.get.isBefore(transactionDate)) => true
      case None => true
    }
    val isValid = isValidTax && isValidOrg && iseOrgType && isValidPartnerGroup && isValidPartner && isValidProductCategory &&
      isValidProduct && isTaxCategory && isTaxGroup && isTaxType && isValidDates
    isValid
  }
}
