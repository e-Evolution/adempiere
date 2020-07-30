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

package org.eevolution.context.tax.infrastructure.repository

import org.eevolution.context.tax.domain.model._
import org.eevolution.context.kernel.infrastructure.database.context
import org.eevolution.context.kernel.infrastructure.database.context._

trait TaxMapping {
	implicit val TaxSchemaMeta : context.SchemaMeta[Tax] = 
			schemaMeta[Tax] ("C_Tax",
				_.clientId -> "AD_Client_ID",
				_.orgId -> "AD_Org_ID",
				_.ruleId -> "AD_Rule_ID",
				_.countryId -> "C_Country_ID",
				_.regionId -> "C_Region_ID",
				_.taxCategoryId -> "C_TaxCategory_ID",
				_.taxId -> "C_Tax_ID",
				_.created -> "Created",
				_.createdBy -> "CreatedBy",
				_.description -> "Description",
				_.isActive -> "IsActive",
				_.isDefault -> "IsDefault",
				_.isDocumentLevel -> "IsDocumentLevel",
				_.isSalesTax -> "IsSalesTax",
				_.isSummary -> "IsSummary",
				_.isTaxExempt -> "IsTaxExempt",
				_.name -> "Name",
				_.parentTaxId -> "Parent_Tax_ID",
				_.rate -> "Rate",
				_.requiresTaxCertificate -> "RequiresTaxCertificate",
				_.sOPOType -> "SOPOType",
				_.taxIndicator -> "TaxIndicator",
				_.toCountryId -> "To_Country_ID",
				_.toRegionId -> "To_Region_ID",
				_.uuid -> "UUID",
				_.updated -> "Updated",
				_.updatedBy -> "UpdatedBy",
				_.validFrom -> "ValidFrom")

}