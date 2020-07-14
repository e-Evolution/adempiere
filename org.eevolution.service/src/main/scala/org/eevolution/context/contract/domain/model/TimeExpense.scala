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

case class TimeExpense(
				clientId : TableDirect, // AD_Client_ID
				orgTrxId : Option[Table], // AD_OrgTrx_ID
				orgId : TableDirect, // AD_Org_ID
				approvalAmt : Option[Amount], // ApprovalAmt
				activityId : Option[Table], // C_Activity_ID
				bPartnerId : Table, // C_BPartner_ID
				campaignId : Option[Table], // C_Campaign_ID
				invoiceId : Option[TableDirect], // C_Invoice_ID
				projectId : Option[TableDirect], // C_Project_ID
				created : DateTime, // Created
				createdBy : Table, // CreatedBy
				dateReport : DateTime = java.time.LocalDateTime.now, // DateReport
				description : Option[String], // Description
				docAction : String = "CO", // DocAction
				docStatus : ListType = "DR", // DocStatus
				documentNo : String, // DocumentNo
				isActive : YesNo = true, // IsActive
				isApproved : YesNo, // IsApproved
				isSOTrx : Option[YesNo] = Option(true), // IsSOTrx
				priceListId : TableDirect, // M_PriceList_ID
				warehouseId : TableDirect, // M_Warehouse_ID
				calendarId : Option[TableDirect], // PP_Calendar_ID
				periodDefinitionId : Option[TableDirect], // PP_PeriodDefinition_ID
				periodId : Option[TableDirect], // PP_Period_ID
				processed : YesNo, // Processed
				processing : Option[YesNo], // Processing
				contractId : Option[TableDirect], // S_Contract_ID
				timeExpenseId : Id, // S_TimeExpense_ID
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
	override val entityName: String = "S_TimeExpense"
	override val identifier: String = "S_TimeExpense_ID"
	override val Id: Id = timeExpenseId
}


object TimeExpense {
	def create(
			clientId : TableDirect, // AD_Client_ID
			orgTrxId : Option[Table], // AD_OrgTrx_ID
			orgId : TableDirect, // AD_Org_ID
			approvalAmt : Option[Amount], // ApprovalAmt
			activityId : Option[Table], // C_Activity_ID
			bPartnerId : Table, // C_BPartner_ID
			campaignId : Option[Table], // C_Campaign_ID
			invoiceId : Option[TableDirect], // C_Invoice_ID
			projectId : Option[TableDirect], // C_Project_ID
			created : DateTime, // Created
			createdBy : Table, // CreatedBy
			dateReport : DateTime = java.time.LocalDateTime.now, // DateReport
			description : Option[String], // Description
			docAction : String = "CO", // DocAction
			docStatus : ListType = "DR", // DocStatus
			documentNo : String, // DocumentNo
			isActive : YesNo = true, // IsActive
			isApproved : YesNo, // IsApproved
			isSOTrx : Option[YesNo] = Option(true), // IsSOTrx
			priceListId : TableDirect, // M_PriceList_ID
			warehouseId : TableDirect, // M_Warehouse_ID
			calendarId : Option[TableDirect], // PP_Calendar_ID
			periodDefinitionId : Option[TableDirect], // PP_PeriodDefinition_ID
			periodId : Option[TableDirect], // PP_Period_ID
			processed : YesNo, // Processed
			processing : Option[YesNo], // Processing
			contractId : Option[TableDirect], // S_Contract_ID
			timeExpenseId : Id, // S_TimeExpense_ID
			uuid : Option[String], // UUID
			updated : DateTime, // Updated
			updatedBy : Table, // UpdatedBy
			user1Id : Option[Table], // User1_ID
			user2Id : Option[Table], // User2_ID
			user3Id : Option[Table], // User3_ID
			user4Id : Option[Table]// User4_ID
	) : TimeExpense = TimeExpense(
						clientId, // AD_Client_ID
						orgTrxId, // AD_OrgTrx_ID
						orgId, // AD_Org_ID
						approvalAmt, // ApprovalAmt
						activityId, // C_Activity_ID
						bPartnerId, // C_BPartner_ID
						campaignId, // C_Campaign_ID
						invoiceId, // C_Invoice_ID
						projectId, // C_Project_ID
						created, // Created
						createdBy, // CreatedBy
						dateReport, // DateReport
						description, // Description
						docAction, // DocAction
						docStatus, // DocStatus
						documentNo, // DocumentNo
						isActive, // IsActive
						isApproved, // IsApproved
						isSOTrx, // IsSOTrx
						priceListId, // M_PriceList_ID
						warehouseId, // M_Warehouse_ID
						calendarId, // PP_Calendar_ID
						periodDefinitionId, // PP_PeriodDefinition_ID
						periodId, // PP_Period_ID
						processed, // Processed
						processing, // Processing
						contractId, // S_Contract_ID
						timeExpenseId, // S_TimeExpense_ID
						uuid, // UUID
						updated, // Updated
						updatedBy, // UpdatedBy
						user1Id, // User1_ID
						user2Id, // User2_ID
						user3Id, // User3_ID
						user4Id) // User4_ID
}
