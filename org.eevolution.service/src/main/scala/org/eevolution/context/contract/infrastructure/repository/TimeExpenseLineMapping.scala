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

trait TimeExpenseLineMapping {
	val queryTimeExpenseLine : context.Quoted[context.EntityQuery[TimeExpenseLine]] = quote {
			querySchema[TimeExpenseLine] ("S_TimeExpenseLine",
				_.clientId -> "AD_Client_ID",
				_.orgTrxId -> "AD_OrgTrx_ID",
				_.orgId -> "AD_Org_ID",
				_.allocatedAmt -> "AllocatedAmt",
				_.activityId -> "C_Activity_ID",
				_.bPartnerId -> "C_BPartner_ID",
				_.campaignId -> "C_Campaign_ID",
				_.currencyId -> "C_Currency_ID",
				_.invoiceLineId -> "C_InvoiceLine_ID",
				_.orderLineId -> "C_OrderLine_ID",
				_.projectPhaseId -> "C_ProjectPhase_ID",
				_.projectTaskId -> "C_ProjectTask_ID",
				_.projectId -> "C_Project_ID",
				_.taxId -> "C_Tax_ID",
				_.uOMId -> "C_UOM_ID",
				_.convertedAmt -> "ConvertedAmt",
				_.created -> "Created",
				_.createdBy -> "CreatedBy",
				_.dateExpense -> "DateExpense",
				_.description -> "Description",
				_.discountAmt -> "DiscountAmt",
				_.expenseAmt -> "ExpenseAmt",
				_.invoicePrice -> "InvoicePrice",
				_.isActive -> "IsActive",
				_.isInvoiced -> "IsInvoiced",
				_.isPaid -> "IsPaid",
				_.isTimeReport -> "IsTimeReport",
				_.line -> "Line",
				_.lineNetAmt -> "LineNetAmt",
				_.lineTotalAmt -> "LineTotalAmt",
				_.productId -> "M_Product_ID",
				_.note -> "Note",
				_.calendarId -> "PP_Calendar_ID",
				_.periodDefinitionId -> "PP_PeriodDefinition_ID",
				_.periodId -> "PP_Period_ID",
				_.priceInvoiced -> "PriceInvoiced",
				_.priceReimbursed -> "PriceReimbursed",
				_.processed -> "Processed",
				_.qty -> "Qty",
				_.qtyInvoiced -> "QtyInvoiced",
				_.qtyReimbursed -> "QtyReimbursed",
				_.contractLineId -> "S_ContractLine_ID",
				_.resourceAssignmentId -> "S_ResourceAssignment_ID",
				_.timeExpenseLineId -> "S_TimeExpenseLine_ID",
				_.timeExpenseId -> "S_TimeExpense_ID",
				_.timeTypeId -> "S_TimeType_ID",
				_.taxAmt -> "TaxAmt",
				_.uuid -> "UUID",
				_.updated -> "Updated",
				_.updatedBy -> "UpdatedBy",
				_.user1Id -> "User1_ID",
				_.user2Id -> "User2_ID",
				_.user3Id -> "User3_ID",
				_.user4Id -> "User4_ID")
	}
}