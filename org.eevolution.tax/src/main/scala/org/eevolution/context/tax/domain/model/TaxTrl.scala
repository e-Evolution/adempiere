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

case class TaxTrl(
				clientId : TableDirect, // AD_Client_ID
				language : Table, // AD_Language
				orgId : TableDirect, // AD_Org_ID
				taxId : TableDirect, // C_Tax_ID
				created : DateTime, // Created
				createdBy : Table, // CreatedBy
				description : Option[String], // Description
				isActive : YesNo = true, // IsActive
				isTranslated : YesNo, // IsTranslated
				name : String, // Name
				taxIndicator : Option[String], // TaxIndicator
				uuid : Option[String], // UUID
				updated : DateTime, // Updated
				updatedBy : Table // UpdatedBy
) extends DomainModel
with ActiveEnabled
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	val entityName: String = "C_Tax_Trl"
}


object TaxTrl {
	def create(
			clientId : TableDirect, // AD_Client_ID
			language : Table, // AD_Language
			orgId : TableDirect, // AD_Org_ID
			taxId : TableDirect, // C_Tax_ID
			created : DateTime, // Created
			createdBy : Table, // CreatedBy
			description : Option[String], // Description
			isActive : YesNo = true, // IsActive
			isTranslated : YesNo, // IsTranslated
			name : String, // Name
			taxIndicator : Option[String], // TaxIndicator
			uuid : Option[String], // UUID
			updated : DateTime, // Updated
			updatedBy : Table// UpdatedBy
	) : TaxTrl = TaxTrl(
						clientId, // AD_Client_ID
						language, // AD_Language
						orgId, // AD_Org_ID
						taxId, // C_Tax_ID
						created, // Created
						createdBy, // CreatedBy
						description, // Description
						isActive, // IsActive
						isTranslated, // IsTranslated
						name, // Name
						taxIndicator, // TaxIndicator
						uuid, // UUID
						updated, // Updated
						updatedBy) // UpdatedBy
}
