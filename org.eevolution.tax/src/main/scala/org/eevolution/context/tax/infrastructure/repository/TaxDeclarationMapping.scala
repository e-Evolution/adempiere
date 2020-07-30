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

trait TaxDeclarationMapping {
	implicit val TaxDeclarationSchemaMeta : context.SchemaMeta[TaxDeclaration] = 
			schemaMeta[TaxDeclaration] ("C_TaxDeclaration",
				_.clientId -> "AD_Client_ID",
				_.orgId -> "AD_Org_ID",
				_.taxDeclarationId -> "C_TaxDeclaration_ID",
				_.created -> "Created",
				_.createdBy -> "CreatedBy",
				_.dateFrom -> "DateFrom",
				_.dateTo -> "DateTo",
				_.dateTrx -> "DateTrx",
				_.description -> "Description",
				_.isActive -> "IsActive",
				_.name -> "Name",
				_.processed -> "Processed",
				_.processing -> "Processing",
				_.uuid -> "UUID",
				_.updated -> "Updated",
				_.updatedBy -> "UpdatedBy")

}