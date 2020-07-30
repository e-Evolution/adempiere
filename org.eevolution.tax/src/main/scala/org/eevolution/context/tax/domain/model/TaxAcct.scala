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
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 **/

package org.eevolution.context.tax.domain.model

import org.eevolution.context.kernel.domain.model._
import org.eevolution.context.kernel.domain.ubiquitouslanguage._

case class TaxAcct(
				clientId : TableDirect, // AD_Client_ID
				orgId : TableDirect, // AD_Org_ID
				acctSchemaId : TableDirect, // C_AcctSchema_ID
				taxId : TableDirect, // C_Tax_ID
				created : DateTime, // Created
				createdBy : Table, // CreatedBy
				isActive : YesNo = true, // IsActive
				creditAcct : TableDirect, // T_Credit_Acct
				dueAcct : TableDirect, // T_Due_Acct
				expenseAcct : TableDirect, // T_Expense_Acct
				liabilityAcct : TableDirect, // T_Liability_Acct
				receivablesAcct : TableDirect, // T_Receivables_Acct
				uuid : Option[String], // UUID
				updated : DateTime, // Updated
				updatedBy : Table // UpdatedBy
) extends DomainModel
with ActiveEnabled
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	val entityName: String = "C_Tax_Acct"
}


object TaxAcct {
	def create(
			clientId : TableDirect, // AD_Client_ID
			orgId : TableDirect, // AD_Org_ID
			acctSchemaId : TableDirect, // C_AcctSchema_ID
			taxId : TableDirect, // C_Tax_ID
			created : DateTime, // Created
			createdBy : Table, // CreatedBy
			isActive : YesNo = true, // IsActive
			creditAcct : TableDirect, // T_Credit_Acct
			dueAcct : TableDirect, // T_Due_Acct
			expenseAcct : TableDirect, // T_Expense_Acct
			liabilityAcct : TableDirect, // T_Liability_Acct
			receivablesAcct : TableDirect, // T_Receivables_Acct
			uuid : Option[String], // UUID
			updated : DateTime, // Updated
			updatedBy : Table// UpdatedBy
	) : TaxAcct = TaxAcct(
						clientId, // AD_Client_ID
						orgId, // AD_Org_ID
						acctSchemaId, // C_AcctSchema_ID
						taxId, // C_Tax_ID
						created, // Created
						createdBy, // CreatedBy
						isActive, // IsActive
						creditAcct, // T_Credit_Acct
						dueAcct, // T_Due_Acct
						expenseAcct, // T_Expense_Acct
						liabilityAcct, // T_Liability_Acct
						receivablesAcct, // T_Receivables_Acct
						uuid, // UUID
						updated, // Updated
						updatedBy) // UpdatedBy
}
