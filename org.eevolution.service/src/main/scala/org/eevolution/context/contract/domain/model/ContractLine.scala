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

case class ContractLine(
				clientId : TableDirect, // AD_Client_ID
				orgTrxId : Option[Table], // AD_OrgTrx_ID
				orgId : TableDirect, // AD_Org_ID
				activityId : Option[TableDirect], // C_Activity_ID
				bPartnerId : Option[Search], // C_BPartner_ID
				bPartnerLocationId : TableDirect, // C_BPartner_Location_ID
				campaignId : Option[TableDirect], // C_Campaign_ID
				chargeId : Option[TableDirect], // C_Charge_ID
				currencyId : TableDirect, // C_Currency_ID
				projectPhaseId : Option[TableDirect], // C_ProjectPhase_ID
				projectTaskId : Option[TableDirect], // C_ProjectTask_ID
				projectId : Option[TableDirect], // C_Project_ID
				serviceLevelId : Option[TableDirect], // C_ServiceLevel_ID
				taxId : TableDirect, // C_Tax_ID
				uOMId : TableDirect, // C_UOM_ID
				created : DateTime, // Created
				createdBy : Table, // CreatedBy
				dateDelivered : Option[DateTime], // DateDelivered
				dateFinish : Option[DateTime], // DateFinish
				dateFinishSchedule : Option[DateTime], // DateFinishSchedule
				dateInvoiced : Option[DateTime], // DateInvoiced
				dateLastRun : Option[DateTime], // DateLastRun
				dateNextRun : Option[DateTime], // DateNextRun
				dateStart : Option[DateTime], // DateStart
				dateStartSchedule : Option[DateTime], // DateStartSchedule
				description : Option[String], // Description
				discount : Option[Number], // Discount
				duration : Option[Number], // Duration
				durationEstimated : Option[Number], // DurationEstimated
				durationRequired : Option[Number], // DurationRequired
				durationUnit : Option[ListType], // DurationUnit
				freightAmt : Amount, // FreightAmt
				frequency : Option[Number], // Frequency
				frequencyType : Option[ListType], // FrequencyType
				isActive : YesNo = true, // IsActive
				isConsumesForecast : Option[YesNo] = Option(false), // IsConsumesForecast
				isDescription : Option[YesNo] = Option(false), // IsDescription
				isIndefinite : Option[YesNo] = Option(false), // IsIndefinite
				isRecurrent : Option[YesNo] = Option(false), // IsRecurrent
				line : Number, // Line
				lineNetAmt : Amount, // LineNetAmt
				linkContractLineId : Option[Table], // Link_ContractLine_ID
				attributeSetInstanceId : Option[AttributeSetInstance], // M_AttributeSetInstance_ID
				freightCategoryId : Option[TableDirect], // M_FreightCategory_ID
				productId : Option[Search], // M_Product_ID
				promotionId : Option[TableDirect], // M_Promotion_ID
				shipperId : Option[TableDirect], // M_Shipper_ID
				warehouseId : Option[TableDirect], // M_Warehouse_ID
				periodId : Option[TableDirect], // PP_Period_ID
				pickedQty : Option[Quantity] = Some(BigDecimal(0)), // PickedQty
				priceActual : CostPrice, // PriceActual
				priceCost : Option[CostPrice], // PriceCost
				priceEntered : CostPrice, // PriceEntered
				priceLimit : Option[CostPrice], // PriceLimit
				priceList : CostPrice, // PriceList
				processed : YesNo = false, // Processed
				qtyDelivered : Quantity, // QtyDelivered
				qtyEntered : Quantity = BigDecimal(1), // QtyEntered
				qtyInvoiced : Option[Quantity], // QtyInvoiced
				qtyLostSales : Quantity = BigDecimal(0), // QtyLostSales
				qtyOrdered : Quantity = BigDecimal(1), // QtyOrdered
				qtyReserved : Quantity = BigDecimal(0), // QtyReserved
				rRAmt : Option[Amount], // RRAmt
				rRStartDate : Option[DateTime], // RRStartDate
				standardRequestTypeId : Option[TableDirect], // R_StandardRequestType_ID
				refContractLineId : Option[Table], // Ref_ContractLine_ID
				runsMax : Option[Number], // RunsMax
				runsRemaining : Option[Number], // RunsRemaining
				contractLineId : Id, // S_ContractLine_ID
				contractId : TableDirect, // S_Contract_ID
				resourceAssignmentId : Option[ResourceAssignment], // S_ResourceAssignment_ID
				serviceTypeId : Option[TableDirect], // S_ServiceType_ID
				uuid : Option[String], // UUID
				updated : DateTime, // Updated
				updatedBy : Table, // UpdatedBy
				user1Id : Option[Table], // User1_ID
				user2Id : Option[Table], // User2_ID
				user3Id : Option[Table], // User3_ID
				user4Id : Option[Table] // User4_ID
) extends DomainModel
with ActiveEnabled
with Identifiable
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	override type Identifiable = this.type
	override val entityName: String = "S_ContractLine"
	override val identifier: String = "S_ContractLine_ID"
	override val Id: Id = contractLineId
}


object ContractLine {
	def create(
			clientId : TableDirect, // AD_Client_ID
			orgTrxId : Option[Table], // AD_OrgTrx_ID
			orgId : TableDirect, // AD_Org_ID
			activityId : Option[TableDirect], // C_Activity_ID
			bPartnerId : Option[Search], // C_BPartner_ID
			bPartnerLocationId : TableDirect, // C_BPartner_Location_ID
			campaignId : Option[TableDirect], // C_Campaign_ID
			chargeId : Option[TableDirect], // C_Charge_ID
			currencyId : TableDirect, // C_Currency_ID
			projectPhaseId : Option[TableDirect], // C_ProjectPhase_ID
			projectTaskId : Option[TableDirect], // C_ProjectTask_ID
			projectId : Option[TableDirect], // C_Project_ID
			serviceLevelId : Option[TableDirect], // C_ServiceLevel_ID
			taxId : TableDirect, // C_Tax_ID
			uOMId : TableDirect, // C_UOM_ID
			created : DateTime, // Created
			createdBy : Table, // CreatedBy
			dateDelivered : Option[DateTime], // DateDelivered
			dateFinish : Option[DateTime], // DateFinish
			dateFinishSchedule : Option[DateTime], // DateFinishSchedule
			dateInvoiced : Option[DateTime], // DateInvoiced
			dateLastRun : Option[DateTime], // DateLastRun
			dateNextRun : Option[DateTime], // DateNextRun
			dateStart : Option[DateTime], // DateStart
			dateStartSchedule : Option[DateTime], // DateStartSchedule
			description : Option[String], // Description
			discount : Option[Number], // Discount
			duration : Option[Number], // Duration
			durationEstimated : Option[Number], // DurationEstimated
			durationRequired : Option[Number], // DurationRequired
			durationUnit : Option[ListType], // DurationUnit
			freightAmt : Amount, // FreightAmt
			frequency : Option[Number], // Frequency
			frequencyType : Option[ListType], // FrequencyType
			isActive : YesNo = true, // IsActive
			isConsumesForecast : Option[YesNo] = Option(false), // IsConsumesForecast
			isDescription : Option[YesNo] = Option(false), // IsDescription
			isIndefinite : Option[YesNo] = Option(false), // IsIndefinite
			isRecurrent : Option[YesNo] = Option(false), // IsRecurrent
			line : Number, // Line
			lineNetAmt : Amount, // LineNetAmt
			linkContractLineId : Option[Table], // Link_ContractLine_ID
			attributeSetInstanceId : Option[AttributeSetInstance], // M_AttributeSetInstance_ID
			freightCategoryId : Option[TableDirect], // M_FreightCategory_ID
			productId : Option[Search], // M_Product_ID
			promotionId : Option[TableDirect], // M_Promotion_ID
			shipperId : Option[TableDirect], // M_Shipper_ID
			warehouseId : Option[TableDirect], // M_Warehouse_ID
			periodId : Option[TableDirect], // PP_Period_ID
			pickedQty : Option[Quantity] = Some(BigDecimal(0)), // PickedQty
			priceActual : CostPrice, // PriceActual
			priceCost : Option[CostPrice], // PriceCost
			priceEntered : CostPrice, // PriceEntered
			priceLimit : Option[CostPrice], // PriceLimit
			priceList : CostPrice, // PriceList
			processed : YesNo = false, // Processed
			qtyDelivered : Quantity, // QtyDelivered
			qtyEntered : Quantity = BigDecimal(1), // QtyEntered
			qtyInvoiced : Option[Quantity], // QtyInvoiced
			qtyLostSales : Quantity = BigDecimal(0), // QtyLostSales
			qtyOrdered : Quantity = BigDecimal(1), // QtyOrdered
			qtyReserved : Quantity = BigDecimal(0), // QtyReserved
			rRAmt : Option[Amount], // RRAmt
			rRStartDate : Option[DateTime], // RRStartDate
			standardRequestTypeId : Option[TableDirect], // R_StandardRequestType_ID
			refContractLineId : Option[Table], // Ref_ContractLine_ID
			runsMax : Option[Number], // RunsMax
			runsRemaining : Option[Number], // RunsRemaining
			contractLineId : Id, // S_ContractLine_ID
			contractId : TableDirect, // S_Contract_ID
			resourceAssignmentId : Option[ResourceAssignment], // S_ResourceAssignment_ID
			serviceTypeId : Option[TableDirect], // S_ServiceType_ID
			uuid : Option[String], // UUID
			updated : DateTime, // Updated
			updatedBy : Table, // UpdatedBy
			user1Id : Option[Table], // User1_ID
			user2Id : Option[Table], // User2_ID
			user3Id : Option[Table], // User3_ID
			user4Id : Option[Table]// User4_ID
	) : ContractLine = ContractLine(
						clientId, // AD_Client_ID
						orgTrxId, // AD_OrgTrx_ID
						orgId, // AD_Org_ID
						activityId, // C_Activity_ID
						bPartnerId, // C_BPartner_ID
						bPartnerLocationId, // C_BPartner_Location_ID
						campaignId, // C_Campaign_ID
						chargeId, // C_Charge_ID
						currencyId, // C_Currency_ID
						projectPhaseId, // C_ProjectPhase_ID
						projectTaskId, // C_ProjectTask_ID
						projectId, // C_Project_ID
						serviceLevelId, // C_ServiceLevel_ID
						taxId, // C_Tax_ID
						uOMId, // C_UOM_ID
						created, // Created
						createdBy, // CreatedBy
						dateDelivered, // DateDelivered
						dateFinish, // DateFinish
						dateFinishSchedule, // DateFinishSchedule
						dateInvoiced, // DateInvoiced
						dateLastRun, // DateLastRun
						dateNextRun, // DateNextRun
						dateStart, // DateStart
						dateStartSchedule, // DateStartSchedule
						description, // Description
						discount, // Discount
						duration, // Duration
						durationEstimated, // DurationEstimated
						durationRequired, // DurationRequired
						durationUnit, // DurationUnit
						freightAmt, // FreightAmt
						frequency, // Frequency
						frequencyType, // FrequencyType
						isActive, // IsActive
						isConsumesForecast, // IsConsumesForecast
						isDescription, // IsDescription
						isIndefinite, // IsIndefinite
						isRecurrent, // IsRecurrent
						line, // Line
						lineNetAmt, // LineNetAmt
						linkContractLineId, // Link_ContractLine_ID
						attributeSetInstanceId, // M_AttributeSetInstance_ID
						freightCategoryId, // M_FreightCategory_ID
						productId, // M_Product_ID
						promotionId, // M_Promotion_ID
						shipperId, // M_Shipper_ID
						warehouseId, // M_Warehouse_ID
						periodId, // PP_Period_ID
						pickedQty, // PickedQty
						priceActual, // PriceActual
						priceCost, // PriceCost
						priceEntered, // PriceEntered
						priceLimit, // PriceLimit
						priceList, // PriceList
						processed, // Processed
						qtyDelivered, // QtyDelivered
						qtyEntered, // QtyEntered
						qtyInvoiced, // QtyInvoiced
						qtyLostSales, // QtyLostSales
						qtyOrdered, // QtyOrdered
						qtyReserved, // QtyReserved
						rRAmt, // RRAmt
						rRStartDate, // RRStartDate
						standardRequestTypeId, // R_StandardRequestType_ID
						refContractLineId, // Ref_ContractLine_ID
						runsMax, // RunsMax
						runsRemaining, // RunsRemaining
						contractLineId, // S_ContractLine_ID
						contractId, // S_Contract_ID
						resourceAssignmentId, // S_ResourceAssignment_ID
						serviceTypeId, // S_ServiceType_ID
						uuid, // UUID
						updated, // Updated
						updatedBy, // UpdatedBy
						user1Id, // User1_ID
						user2Id, // User2_ID
						user3Id, // User3_ID
						user4Id) // User4_ID
}
