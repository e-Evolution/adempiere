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

case class TimeExpenseLine(
				clientId : TableDirect, // AD_Client_ID
				orgTrxId : Option[Table], // AD_OrgTrx_ID
				orgId : TableDirect, // AD_Org_ID
				allocatedAmt : Option[Amount], // AllocatedAmt
				activityId : Option[TableDirect], // C_Activity_ID
				bPartnerId : Option[Search], // C_BPartner_ID
				campaignId : Option[TableDirect], // C_Campaign_ID
				currencyId : Option[TableDirect], // C_Currency_ID
				invoiceLineId : Option[Search], // C_InvoiceLine_ID
				orderLineId : Option[Search], // C_OrderLine_ID
				projectPhaseId : Option[TableDirect], // C_ProjectPhase_ID
				projectTaskId : Option[TableDirect], // C_ProjectTask_ID
				projectId : Option[TableDirect], // C_Project_ID
				taxId : Option[TableDirect], // C_Tax_ID
				uOMId : Option[TableDirect], // C_UOM_ID
				convertedAmt : Option[Amount], // ConvertedAmt
				created : DateTime, // Created
				createdBy : Table, // CreatedBy
				dateExpense : DateTime, // DateExpense
				description : Option[String], // Description
				discountAmt : Option[Amount] = Some(BigDecimal(0)), // DiscountAmt
				expenseAmt : Option[Amount], // ExpenseAmt
				invoicePrice : Option[CostPrice], // InvoicePrice
				isActive : YesNo = true, // IsActive
				isInvoiced : YesNo, // IsInvoiced
				isPaid : Option[YesNo] = Option(false), // IsPaid
				isTimeReport : YesNo, // IsTimeReport
				line : Number, // Line
				lineNetAmt : Option[Amount], // LineNetAmt
				lineTotalAmt : Option[Amount], // LineTotalAmt
				productId : Option[Search], // M_Product_ID
				note : Option[String], // Note
				calendarId : Option[TableDirect], // PP_Calendar_ID
				periodDefinitionId : Option[TableDirect], // PP_PeriodDefinition_ID
				periodId : Option[TableDirect], // PP_Period_ID
				priceInvoiced : Option[CostPrice], // PriceInvoiced
				priceReimbursed : Option[CostPrice], // PriceReimbursed
				processed : YesNo, // Processed
				qty : Option[Quantity] = Some(BigDecimal(0)), // Qty
				qtyInvoiced : Option[Quantity], // QtyInvoiced
				qtyReimbursed : Option[Quantity], // QtyReimbursed
				contractLineId : Option[TableDirect], // S_ContractLine_ID
				resourceAssignmentId : Option[ResourceAssignment], // S_ResourceAssignment_ID
				timeExpenseLineId : Id, // S_TimeExpenseLine_ID
				timeExpenseId : TableDirect, // S_TimeExpense_ID
				timeTypeId : Option[TableDirect], // S_TimeType_ID
				taxAmt : Option[Amount], // TaxAmt
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
	override val entityName: String = "S_TimeExpenseLine"
	override val identifier: String = "S_TimeExpenseLine_ID"
	override val Id: Id = timeExpenseLineId
}


object TimeExpenseLine {
	def create(
			clientId : TableDirect, // AD_Client_ID
			orgTrxId : Option[Table], // AD_OrgTrx_ID
			orgId : TableDirect, // AD_Org_ID
			allocatedAmt : Option[Amount], // AllocatedAmt
			activityId : Option[TableDirect], // C_Activity_ID
			bPartnerId : Option[Search], // C_BPartner_ID
			campaignId : Option[TableDirect], // C_Campaign_ID
			currencyId : Option[TableDirect], // C_Currency_ID
			invoiceLineId : Option[Search], // C_InvoiceLine_ID
			orderLineId : Option[Search], // C_OrderLine_ID
			projectPhaseId : Option[TableDirect], // C_ProjectPhase_ID
			projectTaskId : Option[TableDirect], // C_ProjectTask_ID
			projectId : Option[TableDirect], // C_Project_ID
			taxId : Option[TableDirect], // C_Tax_ID
			uOMId : Option[TableDirect], // C_UOM_ID
			convertedAmt : Option[Amount], // ConvertedAmt
			created : DateTime, // Created
			createdBy : Table, // CreatedBy
			dateExpense : DateTime, // DateExpense
			description : Option[String], // Description
			discountAmt : Option[Amount] = Some(BigDecimal(0)), // DiscountAmt
			expenseAmt : Option[Amount], // ExpenseAmt
			invoicePrice : Option[CostPrice], // InvoicePrice
			isActive : YesNo = true, // IsActive
			isInvoiced : YesNo, // IsInvoiced
			isPaid : Option[YesNo] = Option(false), // IsPaid
			isTimeReport : YesNo, // IsTimeReport
			line : Number, // Line
			lineNetAmt : Option[Amount], // LineNetAmt
			lineTotalAmt : Option[Amount], // LineTotalAmt
			productId : Option[Search], // M_Product_ID
			note : Option[String], // Note
			calendarId : Option[TableDirect], // PP_Calendar_ID
			periodDefinitionId : Option[TableDirect], // PP_PeriodDefinition_ID
			periodId : Option[TableDirect], // PP_Period_ID
			priceInvoiced : Option[CostPrice], // PriceInvoiced
			priceReimbursed : Option[CostPrice], // PriceReimbursed
			processed : YesNo, // Processed
			qty : Option[Quantity] = Some(BigDecimal(0)), // Qty
			qtyInvoiced : Option[Quantity], // QtyInvoiced
			qtyReimbursed : Option[Quantity], // QtyReimbursed
			contractLineId : Option[TableDirect], // S_ContractLine_ID
			resourceAssignmentId : Option[ResourceAssignment], // S_ResourceAssignment_ID
			timeExpenseLineId : Id, // S_TimeExpenseLine_ID
			timeExpenseId : TableDirect, // S_TimeExpense_ID
			timeTypeId : Option[TableDirect], // S_TimeType_ID
			taxAmt : Option[Amount], // TaxAmt
			uuid : Option[String], // UUID
			updated : DateTime, // Updated
			updatedBy : Table, // UpdatedBy
			user1Id : Option[Table], // User1_ID
			user2Id : Option[Table], // User2_ID
			user3Id : Option[Table], // User3_ID
			user4Id : Option[Table]// User4_ID
	) : TimeExpenseLine = TimeExpenseLine(
						clientId, // AD_Client_ID
						orgTrxId, // AD_OrgTrx_ID
						orgId, // AD_Org_ID
						allocatedAmt, // AllocatedAmt
						activityId, // C_Activity_ID
						bPartnerId, // C_BPartner_ID
						campaignId, // C_Campaign_ID
						currencyId, // C_Currency_ID
						invoiceLineId, // C_InvoiceLine_ID
						orderLineId, // C_OrderLine_ID
						projectPhaseId, // C_ProjectPhase_ID
						projectTaskId, // C_ProjectTask_ID
						projectId, // C_Project_ID
						taxId, // C_Tax_ID
						uOMId, // C_UOM_ID
						convertedAmt, // ConvertedAmt
						created, // Created
						createdBy, // CreatedBy
						dateExpense, // DateExpense
						description, // Description
						discountAmt, // DiscountAmt
						expenseAmt, // ExpenseAmt
						invoicePrice, // InvoicePrice
						isActive, // IsActive
						isInvoiced, // IsInvoiced
						isPaid, // IsPaid
						isTimeReport, // IsTimeReport
						line, // Line
						lineNetAmt, // LineNetAmt
						lineTotalAmt, // LineTotalAmt
						productId, // M_Product_ID
						note, // Note
						calendarId, // PP_Calendar_ID
						periodDefinitionId, // PP_PeriodDefinition_ID
						periodId, // PP_Period_ID
						priceInvoiced, // PriceInvoiced
						priceReimbursed, // PriceReimbursed
						processed, // Processed
						qty, // Qty
						qtyInvoiced, // QtyInvoiced
						qtyReimbursed, // QtyReimbursed
						contractLineId, // S_ContractLine_ID
						resourceAssignmentId, // S_ResourceAssignment_ID
						timeExpenseLineId, // S_TimeExpenseLine_ID
						timeExpenseId, // S_TimeExpense_ID
						timeTypeId, // S_TimeType_ID
						taxAmt, // TaxAmt
						uuid, // UUID
						updated, // Updated
						updatedBy, // UpdatedBy
						user1Id, // User1_ID
						user2Id, // User2_ID
						user3Id, // User3_ID
						user4Id) // User4_ID
}
