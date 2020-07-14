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

trait TimeExpenseMapping {
	val queryTimeExpense : context.Quoted[context.EntityQuery[TimeExpense]] = quote {
			querySchema[TimeExpense] ("S_TimeExpense",
				_.clientId -> "AD_Client_ID",
				_.orgTrxId -> "AD_OrgTrx_ID",
				_.orgId -> "AD_Org_ID",
				_.approvalAmt -> "ApprovalAmt",
				_.activityId -> "C_Activity_ID",
				_.bPartnerId -> "C_BPartner_ID",
				_.campaignId -> "C_Campaign_ID",
				_.invoiceId -> "C_Invoice_ID",
				_.projectId -> "C_Project_ID",
				_.created -> "Created",
				_.createdBy -> "CreatedBy",
				_.dateReport -> "DateReport",
				_.description -> "Description",
				_.docAction -> "DocAction",
				_.docStatus -> "DocStatus",
				_.documentNo -> "DocumentNo",
				_.isActive -> "IsActive",
				_.isApproved -> "IsApproved",
				_.isSOTrx -> "IsSOTrx",
				_.priceListId -> "M_PriceList_ID",
				_.warehouseId -> "M_Warehouse_ID",
				_.calendarId -> "PP_Calendar_ID",
				_.periodDefinitionId -> "PP_PeriodDefinition_ID",
				_.periodId -> "PP_Period_ID",
				_.processed -> "Processed",
				_.processing -> "Processing",
				_.contractId -> "S_Contract_ID",
				_.timeExpenseId -> "S_TimeExpense_ID",
				_.uuid -> "UUID",
				_.updated -> "Updated",
				_.updatedBy -> "UpdatedBy",
				_.user1Id -> "User1_ID",
				_.user2Id -> "User2_ID",
				_.user3Id -> "User3_ID",
				_.user4Id -> "User4_ID")
	}
}