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

trait ContractMapping {
	implicit val ContractSchemaMeta : context.SchemaMeta[Contract] = 
			schemaMeta[Contract] ("S_Contract",
				_.clientId -> "AD_Client_ID",
				_.orgTrxId -> "AD_OrgTrx_ID",
				_.orgId -> "AD_Org_ID",
				_.userId -> "AD_User_ID",
				_.billBPartnerId -> "Bill_BPartner_ID",
				_.billLocationId -> "Bill_Location_ID",
				_.billUserId -> "Bill_User_ID",
				_.activityId -> "C_Activity_ID",
				_.bPartnerId -> "C_BPartner_ID",
				_.bPartnerLocationId -> "C_BPartner_Location_ID",
				_.campaignId -> "C_Campaign_ID",
				_.chargeId -> "C_Charge_ID",
				_.conversionTypeId -> "C_ConversionType_ID",
				_.currencyId -> "C_Currency_ID",
				_.docTypeId -> "C_DocType_ID",
				_.opportunityId -> "C_Opportunity_ID",
				_.paymentTermId -> "C_PaymentTerm_ID",
				_.projectId -> "C_Project_ID",
				_.chargeAmt -> "ChargeAmt",
				_.committedAmt -> "CommittedAmt",
				_.committedQty -> "CommittedQty",
				_.copyFrom -> "CopyFrom",
				_.created -> "Created",
				_.createdBy -> "CreatedBy",
				_.dateAcct -> "DateAcct",
				_.dateApproved -> "DateApproved",
				_.dateContract -> "DateContract",
				_.dateDeadline -> "DateDeadline",
				_.dateDoc -> "DateDoc",
				_.dateFinish -> "DateFinish",
				_.dateFinishSchedule -> "DateFinishSchedule",
				_.dateGrace -> "DateGrace",
				_.datePrinted -> "DatePrinted",
				_.dateSigned -> "DateSigned",
				_.dateStart -> "DateStart",
				_.dateStartSchedule -> "DateStartSchedule",
				_.daysToExpiry -> "DaysToExpiry",
				_.deliveryRule -> "DeliveryRule",
				_.deliveryViaRule -> "DeliveryViaRule",
				_.description -> "Description",
				_.docAction -> "DocAction",
				_.docStatus -> "DocStatus",
				_.documentNo -> "DocumentNo",
				_.dropShipBPartnerId -> "DropShip_BPartner_ID",
				_.dropShipLocationId -> "DropShip_Location_ID",
				_.dropShipUserId -> "DropShip_User_ID",
				_.duration -> "Duration",
				_.durationGrace -> "DurationGrace",
				_.durationUnit -> "DurationUnit",
				_.durationUnitGrace -> "DurationUnitGrace",
				_.freightAmt -> "FreightAmt",
				_.freightCostRule -> "FreightCostRule",
				_.grandTotal -> "GrandTotal",
				_.invoiceRule -> "InvoiceRule",
				_.isActive -> "IsActive",
				_.isApproved -> "IsApproved",
				_.isCreditApproved -> "IsCreditApproved",
				_.isDelivered -> "IsDelivered",
				_.isDiscountPrinted -> "IsDiscountPrinted",
				_.isDropShip -> "IsDropShip",
				_.isIndefinite -> "IsIndefinite",
				_.isInvoiced -> "IsInvoiced",
				_.isPrinted -> "IsPrinted",
				_.isSOTrx -> "IsSOTrx",
				_.isSelected -> "IsSelected",
				_.isSelfService -> "IsSelfService",
				_.isSummary -> "IsSummary",
				_.isTaxIncluded -> "IsTaxIncluded",
				_.isTransferred -> "IsTransferred",
				_.linkContractId -> "Link_Contract_ID",
				_.freightCategoryId -> "M_FreightCategory_ID",
				_.priceListId -> "M_PriceList_ID",
				_.shipperId -> "M_Shipper_ID",
				_.warehouseId -> "M_Warehouse_ID",
				_.pOReference -> "POReference",
				_.calendarId -> "PP_Calendar_ID",
				_.periodDefinitionId -> "PP_PeriodDefinition_ID",
				_.payBPartnerId -> "Pay_BPartner_ID",
				_.payLocationId -> "Pay_Location_ID",
				_.paymentRule -> "PaymentRule",
				_.plannedAmt -> "PlannedAmt",
				_.plannedQty -> "PlannedQty",
				_.posted -> "Posted",
				_.priorityRule -> "PriorityRule",
				_.processed -> "Processed",
				_.processedOn -> "ProcessedOn",
				_.processing -> "Processing",
				_.promotionCode -> "PromotionCode",
				_.refContractId -> "Ref_Contract_ID",
				_.contractId -> "S_Contract_ID",
				_.salesRepId -> "SalesRep_ID",
				_.sendEMail -> "SendEMail",
				_.totalLines -> "TotalLines",
				_.uuid -> "UUID",
				_.updated -> "Updated",
				_.updatedBy -> "UpdatedBy",
				_.user1Id -> "User1_ID",
				_.user2Id -> "User2_ID",
				_.user3Id -> "User3_ID",
				_.user4Id -> "User4_ID",
				_.volume -> "Volume",
				_.weight -> "Weight")

}