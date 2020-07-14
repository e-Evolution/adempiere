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

package org.eevolution.context.contract.infrastructure.repository

import org.eevolution.context.contract.domain.model._
import org.eevolution.context.kernel.infrastructure.database.context
import org.eevolution.context.kernel.infrastructure.database.context._

trait ContractTermConditionMapping {
	implicit val ContractTermConditionSchemaMeta : context.SchemaMeta[ContractTermCondition] = 
			schemaMeta[ContractTermCondition] ("S_ContractTermCondition",
				_.clientId -> "AD_Client_ID",
				_.orgId -> "AD_Org_ID",
				_.createFrom -> "CreateFrom",
				_.created -> "Created",
				_.createdBy -> "CreatedBy",
				_.isActive -> "IsActive",
				_.agreementTypeId -> "S_AgreementType_ID",
				_.contractTermConditionId -> "S_ContractTermCondition_ID",
				_.contractId -> "S_Contract_ID",
				_.termsAndConditions -> "TermsAndConditions",
				_.uuid -> "UUID",
				_.updated -> "Updated",
				_.updatedBy -> "UpdatedBy",
				_.versionNo -> "VersionNo")

}