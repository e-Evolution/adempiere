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

case class TaxCategory(
				clientId : TableDirect, // AD_Client_ID
				orgId : TableDirect, // AD_Org_ID
				taxCategoryId : Id, // C_TaxCategory_ID
				commodityCode : Option[String], // CommodityCode
				created : DateTime, // Created
				createdBy : Table, // CreatedBy
				description : Option[String], // Description
				isActive : YesNo = true, // IsActive
				isDefault : YesNo, // IsDefault
				name : String, // Name
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
	override val entityName: String = "C_TaxCategory"
	override val identifier: String = "C_TaxCategory_ID"
	override val Id: Id = taxCategoryId
}


object TaxCategory {
	def create(
			clientId : TableDirect, // AD_Client_ID
			orgId : TableDirect, // AD_Org_ID
			taxCategoryId : Id, // C_TaxCategory_ID
			commodityCode : Option[String], // CommodityCode
			created : DateTime, // Created
			createdBy : Table, // CreatedBy
			description : Option[String], // Description
			isActive : YesNo = true, // IsActive
			isDefault : YesNo, // IsDefault
			name : String, // Name
			uuid : Option[String], // UUID
			updated : DateTime, // Updated
			updatedBy : Table// UpdatedBy
	) : TaxCategory = TaxCategory(
						clientId, // AD_Client_ID
						orgId, // AD_Org_ID
						taxCategoryId, // C_TaxCategory_ID
						commodityCode, // CommodityCode
						created, // Created
						createdBy, // CreatedBy
						description, // Description
						isActive, // IsActive
						isDefault, // IsDefault
						name, // Name
						uuid, // UUID
						updated, // Updated
						updatedBy) // UpdatedBy
}
