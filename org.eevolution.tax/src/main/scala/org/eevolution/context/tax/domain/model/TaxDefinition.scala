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

case class TaxDefinition(
				clientId : TableDirect, // AD_Client_ID
				orgTypeId : Option[TableDirect], // AD_OrgType_ID
				orgId : TableDirect, // AD_Org_ID
				bPGroupId : Option[TableDirect], // C_BP_Group_ID
				bPartnerId : Option[Search], // C_BPartner_ID
				taxBaseId : Option[TableDirect], // C_TaxBase_ID
				taxCategoryId : Option[TableDirect], // C_TaxCategory_ID
				taxDefinitionId : Id, // C_TaxDefinition_ID
				taxGroupId : Option[TableDirect], // C_TaxGroup_ID
				taxTypeId : Option[TableDirect], // C_TaxType_ID
				taxId : Option[TableDirect], // C_Tax_ID
				created : DateTime, // Created
				createdBy : Table, // CreatedBy
				description : Option[String], // Description
				help : Option[String], // Help
				isActive : YesNo, // IsActive
				isInvoiced : Option[YesNo], // IsInvoiced
				productCategoryId : Option[TableDirect], // M_Product_Category_ID
				productId : Option[Search], // M_Product_ID
				maxTaxable : Option[Number], // MaxTaxable
				minTaxable : Option[Number], // MinTaxable
				name : String, // Name
				seqNo : Option[Number], // SeqNo
				uuid : Option[String], // UUID
				updated : DateTime, // Updated
				updatedBy : Table, // UpdatedBy
				validFrom : Option[DateTime], // ValidFrom
				validTo : Option[DateTime], // ValidTo
				value : String // Value
) extends DomainModel
with ActiveEnabled
with Identifiable
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	override type Identifiable = this.type
	override val entityName: String = "C_TaxDefinition"
	override val identifier: String = "C_TaxDefinition_ID"
	override val Id: Id = taxDefinitionId
}


object TaxDefinition {
	def create(
			clientId : TableDirect, // AD_Client_ID
			orgTypeId : Option[TableDirect], // AD_OrgType_ID
			orgId : TableDirect, // AD_Org_ID
			bPGroupId : Option[TableDirect], // C_BP_Group_ID
			bPartnerId : Option[Search], // C_BPartner_ID
			taxBaseId : Option[TableDirect], // C_TaxBase_ID
			taxCategoryId : Option[TableDirect], // C_TaxCategory_ID
			taxDefinitionId : Id, // C_TaxDefinition_ID
			taxGroupId : Option[TableDirect], // C_TaxGroup_ID
			taxTypeId : Option[TableDirect], // C_TaxType_ID
			taxId : Option[TableDirect], // C_Tax_ID
			created : DateTime, // Created
			createdBy : Table, // CreatedBy
			description : Option[String], // Description
			help : Option[String], // Help
			isActive : YesNo, // IsActive
			isInvoiced : Option[YesNo], // IsInvoiced
			productCategoryId : Option[TableDirect], // M_Product_Category_ID
			productId : Option[Search], // M_Product_ID
			maxTaxable : Option[Number], // MaxTaxable
			minTaxable : Option[Number], // MinTaxable
			name : String, // Name
			seqNo : Option[Number], // SeqNo
			uuid : Option[String], // UUID
			updated : DateTime, // Updated
			updatedBy : Table, // UpdatedBy
			validFrom : Option[DateTime], // ValidFrom
			validTo : Option[DateTime], // ValidTo
			value : String// Value
	) : TaxDefinition = TaxDefinition(
						clientId, // AD_Client_ID
						orgTypeId, // AD_OrgType_ID
						orgId, // AD_Org_ID
						bPGroupId, // C_BP_Group_ID
						bPartnerId, // C_BPartner_ID
						taxBaseId, // C_TaxBase_ID
						taxCategoryId, // C_TaxCategory_ID
						taxDefinitionId, // C_TaxDefinition_ID
						taxGroupId, // C_TaxGroup_ID
						taxTypeId, // C_TaxType_ID
						taxId, // C_Tax_ID
						created, // Created
						createdBy, // CreatedBy
						description, // Description
						help, // Help
						isActive, // IsActive
						isInvoiced, // IsInvoiced
						productCategoryId, // M_Product_Category_ID
						productId, // M_Product_ID
						maxTaxable, // MaxTaxable
						minTaxable, // MinTaxable
						name, // Name
						seqNo, // SeqNo
						uuid, // UUID
						updated, // Updated
						updatedBy, // UpdatedBy
						validFrom, // ValidFrom
						validTo, // ValidTo
						value) // Value
}
