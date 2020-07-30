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
import org.eevolution.context.tax.api.repository._
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.kernel.infrastructure.database.context._
import zio.RIO

import scala.util.Try

case class TaxDefinitionRepositoryLive() extends TaxDefinitionRepository.Service with TaxDefinitionMapping {
	override def getByDescription(description: Option[String]): RIO[Any,List[TaxDefinition]] =
		for {
			taxDefinitionList <- RIO.fromTry {
				Try {
					val taxDefinitionList = run(query[TaxDefinition].filter(_.description == lift(description)))
					taxDefinitionList
			}
		}
	} yield taxDefinitionList

	override def getByName(name: String): RIO[Any, Option[TaxDefinition]] =
		for {
			taxDefinition <- RIO.fromTry {
				Try {
					val taxDefinition = run(query[TaxDefinition].filter(_.name == lift(name))).headOption
					taxDefinition
				}
			}
		} yield taxDefinition

	override def getByValue(value: String): RIO[Any, Option[TaxDefinition]] =
		for {
			taxDefinition <- RIO.fromTry {
				Try {
					val taxDefinition = run(query[TaxDefinition].filter(_.value == lift(value))).headOption
					taxDefinition
				}
			}
		} yield taxDefinition

	override def getByTaxDefinitionId(taxDefinitionId: Id): RIO[Any, Option[TaxDefinition]] =
		for {
			taxDefinition <- RIO.fromTry {
				Try {
					val taxDefinition = run(query[TaxDefinition].filter(_.taxDefinitionId == lift(taxDefinitionId))).headOption
					taxDefinition
				}
			}
		} yield taxDefinition

	override def getAll: RIO[Any,List[TaxDefinition]] =
		for {
			taxDefinitionList <- RIO.fromTry {
				Try {
					val taxDefinitionList = run(query[TaxDefinition])
					taxDefinitionList
			}
		}
	} yield taxDefinitionList

	override def getAll(clientId: TableDirect): RIO[Any,List[TaxDefinition]] =
		for {
			taxDefinitionList <- RIO.fromTry {
				Try {
					val taxDefinitionList = run(query[TaxDefinition].filter(_.clientId == lift(clientId)))
					taxDefinitionList
			}
		}
	} yield taxDefinitionList

}