/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
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
 * Email: emeris.hernandez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by emeris.hernandez@e-evolution.com , www.e-evolution.com
 **/

package org.eevolution.context.contract.domain.model

import org.eevolution.context.kernel.domain.model._
import org.eevolution.context.kernel.domain.ubiquitouslanguage._

import java.time.LocalDateTime

case class Contract(
				clientId : TableDirect, // AD_Client_ID
				orgTrxId : Option[Table], // AD_OrgTrx_ID
				orgId : TableDirect, // AD_Org_ID
				userId : Option[TableDirect], // AD_User_ID
				billBPartnerId : Option[Table], // Bill_BPartner_ID
				billLocationId : Option[Table], // Bill_Location_ID
				billUserId : Option[Table], // Bill_User_ID
				activityId : Option[TableDirect], // C_Activity_ID
				bPartnerId : Search, // C_BPartner_ID
				bPartnerLocationId : TableDirect, // C_BPartner_Location_ID
				campaignId : Option[TableDirect], // C_Campaign_ID
				chargeId : Option[Table], // C_Charge_ID
				conversionTypeId : Option[TableDirect], // C_ConversionType_ID
				currencyId : TableDirect, // C_Currency_ID
				docTypeId : TableDirect, // C_DocType_ID
				opportunityId : Option[Search], // C_Opportunity_ID
				paymentTermId : TableDirect, // C_PaymentTerm_ID
				projectId : Option[TableDirect], // C_Project_ID
				chargeAmt : Option[Amount], // ChargeAmt
				committedAmt : Option[Amount], // CommittedAmt
				committedQty : Option[Quantity], // CommittedQty
				copyFrom : Option[String], // CopyFrom
				created : DateTime, // Created
				createdBy : Table, // CreatedBy
				dateAcct : DateTime = java.time.LocalDateTime.now, // DateAcct
				dateApproved : Option[DateTime], // DateApproved
				dateContract : DateTime = java.time.LocalDateTime.now, // DateContract
				dateDeadline : Option[DateTime], // DateDeadline
				dateDoc : DateTime = java.time.LocalDateTime.now, // DateDoc
				dateFinish : Option[DateTime], // DateFinish
				dateFinishSchedule : Option[DateTime], // DateFinishSchedule
				dateGrace : Option[DateTime], // DateGrace
				datePrinted : Option[DateTime], // DatePrinted
				dateSigned : Option[DateTime], // DateSigned
				dateStart : Option[DateTime], // DateStart
				dateStartSchedule : Option[DateTime], // DateStartSchedule
				daysToExpiry : Option[Number], // DaysToExpiry
				deliveryRule : Option[ListType], // DeliveryRule
				deliveryViaRule : ListType = "P", // DeliveryViaRule
				description : Option[String], // Description
				docAction : String = "CO", // DocAction
				docStatus : ListType = "DR", // DocStatus
				documentNo : String, // DocumentNo
				dropShipBPartnerId : Option[Search], // DropShip_BPartner_ID
				dropShipLocationId : Option[Table], // DropShip_Location_ID
				dropShipUserId : Option[Table], // DropShip_User_ID
				duration : Option[Number], // Duration
				durationGrace : Option[Number], // DurationGrace
				durationUnit : Option[ListType], // DurationUnit
				durationUnitGrace : Option[ListType], // DurationUnitGrace
				freightAmt : Option[Amount], // FreightAmt
				freightCostRule : ListType = "I", // FreightCostRule
				grandTotal : Amount, // GrandTotal
				invoiceRule : ListType = "I", // InvoiceRule
				isActive : YesNo = true, // IsActive
				isApproved : YesNo = false, // IsApproved
				isCreditApproved : YesNo, // IsCreditApproved
				isDelivered : YesNo = false, // IsDelivered
				isDiscountPrinted : YesNo = false, // IsDiscountPrinted
				isDropShip : YesNo = false, // IsDropShip
				isIndefinite : Option[YesNo], // IsIndefinite
				isInvoiced : YesNo, // IsInvoiced
				isPrinted : YesNo = false, // IsPrinted
				isSOTrx : YesNo, // IsSOTrx
				isSelected : YesNo = false, // IsSelected
				isSelfService : YesNo = false, // IsSelfService
				isSummary : Option[YesNo], // IsSummary
				isTaxIncluded : YesNo = false, // IsTaxIncluded
				isTransferred : YesNo = false, // IsTransferred
				linkContractId : Option[Table], // Link_Contract_ID
				freightCategoryId : Option[TableDirect], // M_FreightCategory_ID
				priceListId : TableDirect, // M_PriceList_ID
				shipperId : Option[TableDirect], // M_Shipper_ID
				warehouseId : TableDirect, // M_Warehouse_ID
				pOReference : Option[String], // POReference
				calendarId : Option[TableDirect], // PP_Calendar_ID
				periodDefinitionId : Option[TableDirect], // PP_PeriodDefinition_ID
				payBPartnerId : Option[Table], // Pay_BPartner_ID
				payLocationId : Option[Table], // Pay_Location_ID
				paymentRule : ListType = "B", // PaymentRule
				plannedAmt : Option[Amount], // PlannedAmt
				plannedQty : Option[Quantity], // PlannedQty
				posted : Option[String] = Option("N"), // Posted
				priorityRule : ListType = "S", // PriorityRule
				processed : YesNo = false, // Processed
				processedOn : Option[Number], // ProcessedOn
				processing : Option[YesNo] = Option(false), // Processing
				promotionCode : Option[String], // PromotionCode
				refContractId : Option[Search], // Ref_Contract_ID
				contractId : Id, // S_Contract_ID
				salesRepId : Table, // SalesRep_ID
				sendEMail : YesNo = false, // SendEMail
				totalLines : Amount, // TotalLines
				uuid : Option[String], // UUID
				updated : DateTime, // Updated
				updatedBy : Table, // UpdatedBy
				user1Id : Option[Table], // User1_ID
				user2Id : Option[Table], // User2_ID
				user3Id : Option[Table], // User3_ID
				user4Id : Option[Table], // User4_ID
				volume : Option[Number], // Volume
				weight : Option[Number] // Weight
) extends DomainModel
with ActiveEnabled
with Identifiable
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	override type Identifiable = this.type
	override val entityName: String = "S_Contract"
	override val identifier: String = "S_Contract_ID"
	override val Id: Id = contractId
}


object Contract {
	def create(
			clientId : TableDirect, // AD_Client_ID
			orgTrxId : Option[Table], // AD_OrgTrx_ID
			orgId : TableDirect, // AD_Org_ID
			userId : Option[TableDirect], // AD_User_ID
			billBPartnerId : Option[Table], // Bill_BPartner_ID
			billLocationId : Option[Table], // Bill_Location_ID
			billUserId : Option[Table], // Bill_User_ID
			activityId : Option[TableDirect], // C_Activity_ID
			bPartnerId : Search, // C_BPartner_ID
			bPartnerLocationId : TableDirect, // C_BPartner_Location_ID
			campaignId : Option[TableDirect], // C_Campaign_ID
			chargeId : Option[Table], // C_Charge_ID
			conversionTypeId : Option[TableDirect], // C_ConversionType_ID
			currencyId : TableDirect, // C_Currency_ID
			docTypeId : TableDirect, // C_DocType_ID
			opportunityId : Option[Search], // C_Opportunity_ID
			paymentTermId : TableDirect, // C_PaymentTerm_ID
			projectId : Option[TableDirect], // C_Project_ID
			chargeAmt : Option[Amount], // ChargeAmt
			committedAmt : Option[Amount], // CommittedAmt
			committedQty : Option[Quantity], // CommittedQty
			copyFrom : Option[String], // CopyFrom
			created : DateTime, // Created
			createdBy : Table, // CreatedBy
			dateAcct : DateTime = java.time.LocalDateTime.now, // DateAcct
			dateApproved : Option[DateTime], // DateApproved
			dateContract : DateTime = java.time.LocalDateTime.now, // DateContract
			dateDeadline : Option[DateTime], // DateDeadline
			dateDoc : DateTime = java.time.LocalDateTime.now, // DateDoc
			dateFinish : Option[DateTime], // DateFinish
			dateFinishSchedule : Option[DateTime], // DateFinishSchedule
			dateGrace : Option[DateTime], // DateGrace
			datePrinted : Option[DateTime], // DatePrinted
			dateSigned : Option[DateTime], // DateSigned
			dateStart : Option[DateTime], // DateStart
			dateStartSchedule : Option[DateTime], // DateStartSchedule
			daysToExpiry : Option[Number], // DaysToExpiry
			deliveryRule : Option[ListType], // DeliveryRule
			deliveryViaRule : ListType = "P", // DeliveryViaRule
			description : Option[String], // Description
			docAction : String = "CO", // DocAction
			docStatus : ListType = "DR", // DocStatus
			documentNo : String, // DocumentNo
			dropShipBPartnerId : Option[Search], // DropShip_BPartner_ID
			dropShipLocationId : Option[Table], // DropShip_Location_ID
			dropShipUserId : Option[Table], // DropShip_User_ID
			duration : Option[Number], // Duration
			durationGrace : Option[Number], // DurationGrace
			durationUnit : Option[ListType], // DurationUnit
			durationUnitGrace : Option[ListType], // DurationUnitGrace
			freightAmt : Option[Amount], // FreightAmt
			freightCostRule : ListType = "I", // FreightCostRule
			grandTotal : Amount, // GrandTotal
			invoiceRule : ListType = "I", // InvoiceRule
			isActive : YesNo = true, // IsActive
			isApproved : YesNo = false, // IsApproved
			isCreditApproved : YesNo, // IsCreditApproved
			isDelivered : YesNo = false, // IsDelivered
			isDiscountPrinted : YesNo = false, // IsDiscountPrinted
			isDropShip : YesNo = false, // IsDropShip
			isIndefinite : Option[YesNo], // IsIndefinite
			isInvoiced : YesNo, // IsInvoiced
			isPrinted : YesNo = false, // IsPrinted
			isSOTrx : YesNo, // IsSOTrx
			isSelected : YesNo = false, // IsSelected
			isSelfService : YesNo = false, // IsSelfService
			isSummary : Option[YesNo], // IsSummary
			isTaxIncluded : YesNo = false, // IsTaxIncluded
			isTransferred : YesNo = false, // IsTransferred
			linkContractId : Option[Table], // Link_Contract_ID
			freightCategoryId : Option[TableDirect], // M_FreightCategory_ID
			priceListId : TableDirect, // M_PriceList_ID
			shipperId : Option[TableDirect], // M_Shipper_ID
			warehouseId : TableDirect, // M_Warehouse_ID
			pOReference : Option[String], // POReference
			calendarId : Option[TableDirect], // PP_Calendar_ID
			periodDefinitionId : Option[TableDirect], // PP_PeriodDefinition_ID
			payBPartnerId : Option[Table], // Pay_BPartner_ID
			payLocationId : Option[Table], // Pay_Location_ID
			paymentRule : ListType = "B", // PaymentRule
			plannedAmt : Option[Amount], // PlannedAmt
			plannedQty : Option[Quantity], // PlannedQty
			posted : Option[String] = Option("N"), // Posted
			priorityRule : ListType = "S", // PriorityRule
			processed : YesNo = false, // Processed
			processedOn : Option[Number], // ProcessedOn
			processing : Option[YesNo] = Option(false), // Processing
			promotionCode : Option[String], // PromotionCode
			refContractId : Option[Search], // Ref_Contract_ID
			contractId : Id, // S_Contract_ID
			salesRepId : Table, // SalesRep_ID
			sendEMail : YesNo = false, // SendEMail
			totalLines : Amount, // TotalLines
			uuid : Option[String], // UUID
			updated : DateTime, // Updated
			updatedBy : Table, // UpdatedBy
			user1Id : Option[Table], // User1_ID
			user2Id : Option[Table], // User2_ID
			user3Id : Option[Table], // User3_ID
			user4Id : Option[Table], // User4_ID
			volume : Option[Number], // Volume
			weight : Option[Number]// Weight
	) : Contract = Contract(
						clientId, // AD_Client_ID
						orgTrxId, // AD_OrgTrx_ID
						orgId, // AD_Org_ID
						userId, // AD_User_ID
						billBPartnerId, // Bill_BPartner_ID
						billLocationId, // Bill_Location_ID
						billUserId, // Bill_User_ID
						activityId, // C_Activity_ID
						bPartnerId, // C_BPartner_ID
						bPartnerLocationId, // C_BPartner_Location_ID
						campaignId, // C_Campaign_ID
						chargeId, // C_Charge_ID
						conversionTypeId, // C_ConversionType_ID
						currencyId, // C_Currency_ID
						docTypeId, // C_DocType_ID
						opportunityId, // C_Opportunity_ID
						paymentTermId, // C_PaymentTerm_ID
						projectId, // C_Project_ID
						chargeAmt, // ChargeAmt
						committedAmt, // CommittedAmt
						committedQty, // CommittedQty
						copyFrom, // CopyFrom
						created, // Created
						createdBy, // CreatedBy
						dateAcct, // DateAcct
						dateApproved, // DateApproved
						dateContract, // DateContract
						dateDeadline, // DateDeadline
						dateDoc, // DateDoc
						dateFinish, // DateFinish
						dateFinishSchedule, // DateFinishSchedule
						dateGrace, // DateGrace
						datePrinted, // DatePrinted
						dateSigned, // DateSigned
						dateStart, // DateStart
						dateStartSchedule, // DateStartSchedule
						daysToExpiry, // DaysToExpiry
						deliveryRule, // DeliveryRule
						deliveryViaRule, // DeliveryViaRule
						description, // Description
						docAction, // DocAction
						docStatus, // DocStatus
						documentNo, // DocumentNo
						dropShipBPartnerId, // DropShip_BPartner_ID
						dropShipLocationId, // DropShip_Location_ID
						dropShipUserId, // DropShip_User_ID
						duration, // Duration
						durationGrace, // DurationGrace
						durationUnit, // DurationUnit
						durationUnitGrace, // DurationUnitGrace
						freightAmt, // FreightAmt
						freightCostRule, // FreightCostRule
						grandTotal, // GrandTotal
						invoiceRule, // InvoiceRule
						isActive, // IsActive
						isApproved, // IsApproved
						isCreditApproved, // IsCreditApproved
						isDelivered, // IsDelivered
						isDiscountPrinted, // IsDiscountPrinted
						isDropShip, // IsDropShip
						isIndefinite, // IsIndefinite
						isInvoiced, // IsInvoiced
						isPrinted, // IsPrinted
						isSOTrx, // IsSOTrx
						isSelected, // IsSelected
						isSelfService, // IsSelfService
						isSummary, // IsSummary
						isTaxIncluded, // IsTaxIncluded
						isTransferred, // IsTransferred
						linkContractId, // Link_Contract_ID
						freightCategoryId, // M_FreightCategory_ID
						priceListId, // M_PriceList_ID
						shipperId, // M_Shipper_ID
						warehouseId, // M_Warehouse_ID
						pOReference, // POReference
						calendarId, // PP_Calendar_ID
						periodDefinitionId, // PP_PeriodDefinition_ID
						payBPartnerId, // Pay_BPartner_ID
						payLocationId, // Pay_Location_ID
						paymentRule, // PaymentRule
						plannedAmt, // PlannedAmt
						plannedQty, // PlannedQty
						posted, // Posted
						priorityRule, // PriorityRule
						processed, // Processed
						processedOn, // ProcessedOn
						processing, // Processing
						promotionCode, // PromotionCode
						refContractId, // Ref_Contract_ID
						contractId, // S_Contract_ID
						salesRepId, // SalesRep_ID
						sendEMail, // SendEMail
						totalLines, // TotalLines
						uuid, // UUID
						updated, // Updated
						updatedBy, // UpdatedBy
						user1Id, // User1_ID
						user2Id, // User2_ID
						user3Id, // User3_ID
						user4Id, // User4_ID
						volume, // Volume
						weight) // Weight
}
