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

case class Tax(
				clientId : TableDirect, // AD_Client_ID
				orgId : TableDirect, // AD_Org_ID
				ruleId : Option[TableDirect], // AD_Rule_ID
				countryId : Option[Table], // C_Country_ID
				regionId : Option[Table], // C_Region_ID
				taxCategoryId : TableDirect, // C_TaxCategory_ID
				taxId : Id, // C_Tax_ID
				created : DateTime, // Created
				createdBy : Table, // CreatedBy
				description : Option[String], // Description
				isActive : YesNo = true, // IsActive
				isDefault : YesNo, // IsDefault
				isDocumentLevel : YesNo, // IsDocumentLevel
				isSalesTax : YesNo = false, // IsSalesTax
				isSummary : YesNo, // IsSummary
				isTaxExempt : YesNo, // IsTaxExempt
				name : String, // Name
				parentTaxId : Option[Table], // Parent_Tax_ID
				rate : Number, // Rate
				requiresTaxCertificate : YesNo, // RequiresTaxCertificate
				sOPOType : ListType = "B", // SOPOType
				taxIndicator : Option[String], // TaxIndicator
				toCountryId : Option[Table], // To_Country_ID
				toRegionId : Option[Table], // To_Region_ID
				uuid : Option[String], // UUID
				updated : DateTime, // Updated
				updatedBy : Table, // UpdatedBy
				validFrom : DateTime // ValidFrom
) extends DomainModel
with ActiveEnabled
with Identifiable
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	override type Identifiable = this.type
	override val entityName: String = "C_Tax"
	override val identifier: String = "C_Tax_ID"
	override val Id: Id = taxId
}


object Tax {
	def create(
			clientId : TableDirect, // AD_Client_ID
			orgId : TableDirect, // AD_Org_ID
			ruleId : Option[TableDirect], // AD_Rule_ID
			countryId : Option[Table], // C_Country_ID
			regionId : Option[Table], // C_Region_ID
			taxCategoryId : TableDirect, // C_TaxCategory_ID
			taxId : Id, // C_Tax_ID
			created : DateTime, // Created
			createdBy : Table, // CreatedBy
			description : Option[String], // Description
			isActive : YesNo = true, // IsActive
			isDefault : YesNo, // IsDefault
			isDocumentLevel : YesNo, // IsDocumentLevel
			isSalesTax : YesNo = false, // IsSalesTax
			isSummary : YesNo, // IsSummary
			isTaxExempt : YesNo, // IsTaxExempt
			name : String, // Name
			parentTaxId : Option[Table], // Parent_Tax_ID
			rate : Number, // Rate
			requiresTaxCertificate : YesNo, // RequiresTaxCertificate
			sOPOType : ListType = "B", // SOPOType
			taxIndicator : Option[String], // TaxIndicator
			toCountryId : Option[Table], // To_Country_ID
			toRegionId : Option[Table], // To_Region_ID
			uuid : Option[String], // UUID
			updated : DateTime, // Updated
			updatedBy : Table, // UpdatedBy
			validFrom : DateTime// ValidFrom
	) : Tax = Tax(
						clientId, // AD_Client_ID
						orgId, // AD_Org_ID
						ruleId, // AD_Rule_ID
						countryId, // C_Country_ID
						regionId, // C_Region_ID
						taxCategoryId, // C_TaxCategory_ID
						taxId, // C_Tax_ID
						created, // Created
						createdBy, // CreatedBy
						description, // Description
						isActive, // IsActive
						isDefault, // IsDefault
						isDocumentLevel, // IsDocumentLevel
						isSalesTax, // IsSalesTax
						isSummary, // IsSummary
						isTaxExempt, // IsTaxExempt
						name, // Name
						parentTaxId, // Parent_Tax_ID
						rate, // Rate
						requiresTaxCertificate, // RequiresTaxCertificate
						sOPOType, // SOPOType
						taxIndicator, // TaxIndicator
						toCountryId, // To_Country_ID
						toRegionId, // To_Region_ID
						uuid, // UUID
						updated, // Updated
						updatedBy, // UpdatedBy
						validFrom) // ValidFrom
}
