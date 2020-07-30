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

case class TaxPostal(
				clientId : TableDirect, // AD_Client_ID
				orgId : TableDirect, // AD_Org_ID
				taxPostalId : Id, // C_TaxPostal_ID
				taxId : TableDirect, // C_Tax_ID
				created : DateTime, // Created
				createdBy : Table, // CreatedBy
				isActive : YesNo = true, // IsActive
				postal : String, // Postal
				postalTo : Option[String], // Postal_To
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
	override val entityName: String = "C_TaxPostal"
	override val identifier: String = "C_TaxPostal_ID"
	override val Id: Id = taxPostalId
}


object TaxPostal {
	def create(
			clientId : TableDirect, // AD_Client_ID
			orgId : TableDirect, // AD_Org_ID
			taxPostalId : Id, // C_TaxPostal_ID
			taxId : TableDirect, // C_Tax_ID
			created : DateTime, // Created
			createdBy : Table, // CreatedBy
			isActive : YesNo = true, // IsActive
			postal : String, // Postal
			postalTo : Option[String], // Postal_To
			uuid : Option[String], // UUID
			updated : DateTime, // Updated
			updatedBy : Table// UpdatedBy
	) : TaxPostal = TaxPostal(
						clientId, // AD_Client_ID
						orgId, // AD_Org_ID
						taxPostalId, // C_TaxPostal_ID
						taxId, // C_Tax_ID
						created, // Created
						createdBy, // CreatedBy
						isActive, // IsActive
						postal, // Postal
						postalTo, // Postal_To
						uuid, // UUID
						updated, // Updated
						updatedBy) // UpdatedBy
}
