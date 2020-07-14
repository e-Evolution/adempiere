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

case class ServiceType(
				clientId : TableDirect, // AD_Client_ID
				orgId : TableDirect, // AD_Org_ID
				taxCategoryId : TableDirect, // C_TaxCategory_ID
				taxTypeId : Option[TableDirect], // C_TaxType_ID
				uOMId : TableDirect, // C_UOM_ID
				created : DateTime, // Created
				createdBy : Table, // CreatedBy
				description : Option[String], // Description
				isActive : YesNo = true, // IsActive
				isInvoiced : Option[YesNo], // IsInvoiced
				productCategoryId : TableDirect, // M_Product_Category_ID
				name : String, // Name
				serviceTypeId : Id, // S_ServiceType_ID
				uuid : Option[String], // UUID
				updated : DateTime, // Updated
				updatedBy : Table, // UpdatedBy
				value : Option[String] // Value
) extends DomainModel
with ActiveEnabled
with Identifiable
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	override type Identifiable = this.type
	override val entityName: String = "S_ServiceType"
	override val identifier: String = "S_ServiceType_ID"
	override val Id: Id = serviceTypeId
}


object ServiceType {
	def create(
			clientId : TableDirect, // AD_Client_ID
			orgId : TableDirect, // AD_Org_ID
			taxCategoryId : TableDirect, // C_TaxCategory_ID
			taxTypeId : Option[TableDirect], // C_TaxType_ID
			uOMId : TableDirect, // C_UOM_ID
			created : DateTime, // Created
			createdBy : Table, // CreatedBy
			description : Option[String], // Description
			isActive : YesNo = true, // IsActive
			isInvoiced : Option[YesNo], // IsInvoiced
			productCategoryId : TableDirect, // M_Product_Category_ID
			name : String, // Name
			serviceTypeId : Id, // S_ServiceType_ID
			uuid : Option[String], // UUID
			updated : DateTime, // Updated
			updatedBy : Table, // UpdatedBy
			value : Option[String]// Value
	) : ServiceType = ServiceType(
						clientId, // AD_Client_ID
						orgId, // AD_Org_ID
						taxCategoryId, // C_TaxCategory_ID
						taxTypeId, // C_TaxType_ID
						uOMId, // C_UOM_ID
						created, // Created
						createdBy, // CreatedBy
						description, // Description
						isActive, // IsActive
						isInvoiced, // IsInvoiced
						productCategoryId, // M_Product_Category_ID
						name, // Name
						serviceTypeId, // S_ServiceType_ID
						uuid, // UUID
						updated, // Updated
						updatedBy, // UpdatedBy
						value) // Value
}
