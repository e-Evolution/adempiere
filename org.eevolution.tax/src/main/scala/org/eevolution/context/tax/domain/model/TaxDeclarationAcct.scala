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

case class TaxDeclarationAcct(
				clientId : TableDirect, // AD_Client_ID
				orgId : TableDirect, // AD_Org_ID
				accountId : Option[Table], // Account_ID
				amtAcctCr : Option[Amount], // AmtAcctCr
				amtAcctDr : Option[Amount], // AmtAcctDr
				amtSourceCr : Option[Amount], // AmtSourceCr
				amtSourceDr : Option[Amount], // AmtSourceDr
				acctSchemaId : TableDirect, // C_AcctSchema_ID
				bPartnerId : Option[Search], // C_BPartner_ID
				currencyId : Option[Search], // C_Currency_ID
				taxDeclarationAcctId : Id, // C_TaxDeclarationAcct_ID
				taxDeclarationId : TableDirect, // C_TaxDeclaration_ID
				taxId : Option[Search], // C_Tax_ID
				created : DateTime, // Created
				createdBy : Table, // CreatedBy
				dateAcct : Option[DateTime], // DateAcct
				description : Option[String], // Description
				factAcctId : Search, // Fact_Acct_ID
				isActive : YesNo, // IsActive
				line : Option[Number], // Line
				uuid : Option[String], // UUID
				updated : DateTime, // Updated
				updatedBy : Table // UpdatedBy
) extends DomainModel
with ActiveEnabled
with Identifiable
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	override type Identifiable = this.type
	override val entityName: String = "C_TaxDeclarationAcct"
	override val identifier: String = "C_TaxDeclarationAcct_ID"
	override val Id: Id = taxDeclarationAcctId
}


object TaxDeclarationAcct {
	def create(
			clientId : TableDirect, // AD_Client_ID
			orgId : TableDirect, // AD_Org_ID
			accountId : Option[Table], // Account_ID
			amtAcctCr : Option[Amount], // AmtAcctCr
			amtAcctDr : Option[Amount], // AmtAcctDr
			amtSourceCr : Option[Amount], // AmtSourceCr
			amtSourceDr : Option[Amount], // AmtSourceDr
			acctSchemaId : TableDirect, // C_AcctSchema_ID
			bPartnerId : Option[Search], // C_BPartner_ID
			currencyId : Option[Search], // C_Currency_ID
			taxDeclarationAcctId : Id, // C_TaxDeclarationAcct_ID
			taxDeclarationId : TableDirect, // C_TaxDeclaration_ID
			taxId : Option[Search], // C_Tax_ID
			created : DateTime, // Created
			createdBy : Table, // CreatedBy
			dateAcct : Option[DateTime], // DateAcct
			description : Option[String], // Description
			factAcctId : Search, // Fact_Acct_ID
			isActive : YesNo, // IsActive
			line : Option[Number], // Line
			uuid : Option[String], // UUID
			updated : DateTime, // Updated
			updatedBy : Table// UpdatedBy
	) : TaxDeclarationAcct = TaxDeclarationAcct(
						clientId, // AD_Client_ID
						orgId, // AD_Org_ID
						accountId, // Account_ID
						amtAcctCr, // AmtAcctCr
						amtAcctDr, // AmtAcctDr
						amtSourceCr, // AmtSourceCr
						amtSourceDr, // AmtSourceDr
						acctSchemaId, // C_AcctSchema_ID
						bPartnerId, // C_BPartner_ID
						currencyId, // C_Currency_ID
						taxDeclarationAcctId, // C_TaxDeclarationAcct_ID
						taxDeclarationId, // C_TaxDeclaration_ID
						taxId, // C_Tax_ID
						created, // Created
						createdBy, // CreatedBy
						dateAcct, // DateAcct
						description, // Description
						factAcctId, // Fact_Acct_ID
						isActive, // IsActive
						line, // Line
						uuid, // UUID
						updated, // Updated
						updatedBy) // UpdatedBy
}
