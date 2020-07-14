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
import org.eevolution.context.contract.api.repository._
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.kernel.infrastructure.database.context._
import zio.RIO

import scala.util.Try

case class AgreementTypeRepositoryLive() extends AgreementTypeRepository.Service with AgreementTypeMapping {
	override def getByAgreementTypeId(agreementTypeId: Id): RIO[Any, Option[AgreementType]] =
		for {
			agreementType <- RIO.fromTry {
				Try {
					val agreementType = run(query[AgreementType].filter(_.agreementTypeId == lift(agreementTypeId))).headOption
					agreementType
				}
			}
		} yield agreementType

	override def getByDescription(description: Option[String]): RIO[Any,List[AgreementType]] =
		for {
			agreementTypeList <- RIO.fromTry {
				Try {
					val agreementTypeList = run(query[AgreementType].filter(_.description == lift(description)))
					agreementTypeList
			}
		}
	} yield agreementTypeList

	override def getByName(name: String): RIO[Any, Option[AgreementType]] =
		for {
			agreementType <- RIO.fromTry {
				Try {
					val agreementType = run(query[AgreementType].filter(_.name == lift(name))).headOption
					agreementType
				}
			}
		} yield agreementType

	override def getByValue(value: Option[String]): RIO[Any, Option[AgreementType]] =
		for {
			agreementType <- RIO.fromTry {
				Try {
					val agreementType = run(query[AgreementType].filter(_.value == lift(value))).headOption
					agreementType
				}
			}
		} yield agreementType

	override def getAll: RIO[Any,List[AgreementType]] =
		for {
			agreementTypeList <- RIO.fromTry {
				Try {
					val agreementTypeList = run(query[AgreementType])
					agreementTypeList
			}
		}
	} yield agreementTypeList

	override def getAll(clientId: TableDirect): RIO[Any,List[AgreementType]] =
		for {
			agreementTypeList <- RIO.fromTry {
				Try {
					val agreementTypeList = run(query[AgreementType].filter(_.clientId == lift(clientId)))
					agreementTypeList
			}
		}
	} yield agreementTypeList

}