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

package org.eevolution.context.contract.infrastructure.repository

import org.eevolution.context.contract.domain.model._
import org.eevolution.context.kernel.infrastructure.database.context
import org.eevolution.context.kernel.infrastructure.database.context._

trait ContractLineMapping {
	implicit val ContractLineSchemaMeta : context.SchemaMeta[ContractLine] = 
			schemaMeta[ContractLine] ("S_ContractLine",
				_.clientId -> "AD_Client_ID",
				_.orgTrxId -> "AD_OrgTrx_ID",
				_.orgId -> "AD_Org_ID",
				_.activityId -> "C_Activity_ID",
				_.bPartnerId -> "C_BPartner_ID",
				_.bPartnerLocationId -> "C_BPartner_Location_ID",
				_.campaignId -> "C_Campaign_ID",
				_.chargeId -> "C_Charge_ID",
				_.currencyId -> "C_Currency_ID",
				_.projectPhaseId -> "C_ProjectPhase_ID",
				_.projectTaskId -> "C_ProjectTask_ID",
				_.projectId -> "C_Project_ID",
				_.serviceLevelId -> "C_ServiceLevel_ID",
				_.taxId -> "C_Tax_ID",
				_.uOMId -> "C_UOM_ID",
				_.created -> "Created",
				_.createdBy -> "CreatedBy",
				_.dateDelivered -> "DateDelivered",
				_.dateFinish -> "DateFinish",
				_.dateFinishSchedule -> "DateFinishSchedule",
				_.dateInvoiced -> "DateInvoiced",
				_.dateLastRun -> "DateLastRun",
				_.dateNextRun -> "DateNextRun",
				_.dateStart -> "DateStart",
				_.dateStartSchedule -> "DateStartSchedule",
				_.description -> "Description",
				_.discount -> "Discount",
				_.duration -> "Duration",
				_.durationEstimated -> "DurationEstimated",
				_.durationRequired -> "DurationRequired",
				_.durationUnit -> "DurationUnit",
				_.freightAmt -> "FreightAmt",
				_.frequency -> "Frequency",
				_.frequencyType -> "FrequencyType",
				_.isActive -> "IsActive",
				_.isConsumesForecast -> "IsConsumesForecast",
				_.isDescription -> "IsDescription",
				_.isIndefinite -> "IsIndefinite",
				_.isRecurrent -> "IsRecurrent",
				_.line -> "Line",
				_.lineNetAmt -> "LineNetAmt",
				_.linkContractLineId -> "Link_ContractLine_ID",
				_.attributeSetInstanceId -> "M_AttributeSetInstance_ID",
				_.freightCategoryId -> "M_FreightCategory_ID",
				_.productId -> "M_Product_ID",
				_.promotionId -> "M_Promotion_ID",
				_.shipperId -> "M_Shipper_ID",
				_.warehouseId -> "M_Warehouse_ID",
				_.periodId -> "PP_Period_ID",
				_.pickedQty -> "PickedQty",
				_.priceActual -> "PriceActual",
				_.priceCost -> "PriceCost",
				_.priceEntered -> "PriceEntered",
				_.priceLimit -> "PriceLimit",
				_.priceList -> "PriceList",
				_.processed -> "Processed",
				_.qtyDelivered -> "QtyDelivered",
				_.qtyEntered -> "QtyEntered",
				_.qtyInvoiced -> "QtyInvoiced",
				_.qtyLostSales -> "QtyLostSales",
				_.qtyOrdered -> "QtyOrdered",
				_.qtyReserved -> "QtyReserved",
				_.rRAmt -> "RRAmt",
				_.rRStartDate -> "RRStartDate",
				_.standardRequestTypeId -> "R_StandardRequestType_ID",
				_.refContractLineId -> "Ref_ContractLine_ID",
				_.runsMax -> "RunsMax",
				_.runsRemaining -> "RunsRemaining",
				_.contractLineId -> "S_ContractLine_ID",
				_.contractId -> "S_Contract_ID",
				_.resourceAssignmentId -> "S_ResourceAssignment_ID",
				_.serviceTypeId -> "S_ServiceType_ID",
				_.uuid -> "UUID",
				_.updated -> "Updated",
				_.updatedBy -> "UpdatedBy",
				_.user1Id -> "User1_ID",
				_.user2Id -> "User2_ID",
				_.user3Id -> "User3_ID",
				_.user4Id -> "User4_ID")

}