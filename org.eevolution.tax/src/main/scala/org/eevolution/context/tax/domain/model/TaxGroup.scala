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

case class TaxGroup(
				clientId : TableDirect, // AD_Client_ID
				orgId : TableDirect, // AD_Org_ID
				taxGroupId : Id, // C_TaxGroup_ID
				created : DateTime, // Created
				createdBy : Table, // CreatedBy
				description : Option[String], // Description
				help : Option[String], // Help
				isActive : YesNo, // IsActive
				name : String, // Name
				uuid : Option[String], // UUID
				updated : DateTime, // Updated
				updatedBy : Table, // UpdatedBy
				value : String // Value
) extends DomainModel
with ActiveEnabled
with Identifiable
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	override type Identifiable = this.type
	override val entityName: String = "C_TaxGroup"
	override val identifier: String = "C_TaxGroup_ID"
	override val Id: Id = taxGroupId
}


object TaxGroup {
	def create(
			clientId : TableDirect, // AD_Client_ID
			orgId : TableDirect, // AD_Org_ID
			taxGroupId : Id, // C_TaxGroup_ID
			created : DateTime, // Created
			createdBy : Table, // CreatedBy
			description : Option[String], // Description
			help : Option[String], // Help
			isActive : YesNo, // IsActive
			name : String, // Name
			uuid : Option[String], // UUID
			updated : DateTime, // Updated
			updatedBy : Table, // UpdatedBy
			value : String// Value
	) : TaxGroup = TaxGroup(
						clientId, // AD_Client_ID
						orgId, // AD_Org_ID
						taxGroupId, // C_TaxGroup_ID
						created, // Created
						createdBy, // CreatedBy
						description, // Description
						help, // Help
						isActive, // IsActive
						name, // Name
						uuid, // UUID
						updated, // Updated
						updatedBy, // UpdatedBy
						value) // Value
}
