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

case class ServiceTypeRepositoryLive() extends ServiceTypeRepository.Service with ServiceTypeMapping {
	override def getByDescription(description: Option[String]): RIO[Any,List[ServiceType]] =
		for {
			serviceTypeList <- RIO.fromTry {
				Try {
					val serviceTypeList = run(query[ServiceType].filter(_.description == lift(description)))
					serviceTypeList
			}
		}
	} yield serviceTypeList

	override def getByName(name: String): RIO[Any, Option[ServiceType]] =
		for {
			serviceType <- RIO.fromTry {
				Try {
					val serviceType = run(query[ServiceType].filter(_.name == lift(name))).headOption
					serviceType
				}
			}
		} yield serviceType

	override def getByValue(value: Option[String]): RIO[Any, Option[ServiceType]] =
		for {
			serviceType <- RIO.fromTry {
				Try {
					val serviceType = run(query[ServiceType].filter(_.value == lift(value))).headOption
					serviceType
				}
			}
		} yield serviceType

	override def getByServiceTypeId(serviceTypeId: Id): RIO[Any, Option[ServiceType]] =
		for {
			serviceType <- RIO.fromTry {
				Try {
					val serviceType = run(query[ServiceType].filter(_.serviceTypeId == lift(serviceTypeId))).headOption
					serviceType
				}
			}
		} yield serviceType

	override def getAll: RIO[Any,List[ServiceType]] =
		for {
			serviceTypeList <- RIO.fromTry {
				Try {
					val serviceTypeList = run(query[ServiceType])
					serviceTypeList
			}
		}
	} yield serviceTypeList

	override def getAll(clientId: TableDirect): RIO[Any,List[ServiceType]] =
		for {
			serviceTypeList <- RIO.fromTry {
				Try {
					val serviceTypeList = run(query[ServiceType].filter(_.clientId == lift(clientId)))
					serviceTypeList
			}
		}
	} yield serviceTypeList

}