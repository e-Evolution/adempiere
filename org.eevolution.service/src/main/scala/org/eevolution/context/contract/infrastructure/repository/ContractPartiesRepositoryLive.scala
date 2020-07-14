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

case class ContractPartiesRepositoryLive() extends ContractPartiesRepository.Service with ContractPartiesMapping {
	override def getByBPName(bPName: Option[String]): RIO[Any,List[ContractParties]] =
		for {
			contractPartiesList <- RIO.fromTry {
				Try {
					val contractPartiesList = run(query[ContractParties].filter(_.bPName == lift(bPName)))
					contractPartiesList
			}
		}
	} yield contractPartiesList

	override def getByContractId(contractId: Option[TableDirect]): RIO[Any,List[ContractParties]] =
		for {
			contractPartiesList <- RIO.fromTry {
				Try {
					val contractPartiesList = run(query[ContractParties].filter(_.contractId == lift(contractId)))
					contractPartiesList
			}
		}
	} yield contractPartiesList

	override def getByContractPartiesId(contractPartiesId: Id): RIO[Any, Option[ContractParties]] =
		for {
			contractParties <- RIO.fromTry {
				Try {
					val contractParties = run(query[ContractParties].filter(_.contractPartiesId == lift(contractPartiesId))).headOption
					contractParties
				}
			}
		} yield contractParties

	override def getByContractPartiesTypeId(contractPartiesTypeId: Option[TableDirect]): RIO[Any,List[ContractParties]] =
		for {
			contractPartiesList <- RIO.fromTry {
				Try {
					val contractPartiesList = run(query[ContractParties].filter(_.contractPartiesTypeId == lift(contractPartiesTypeId)))
					contractPartiesList
			}
		}
	} yield contractPartiesList

	override def getByNotificationType(notificationType: Option[ListType]): RIO[Any,List[ContractParties]] =
		for {
			contractPartiesList <- RIO.fromTry {
				Try {
					val contractPartiesList = run(query[ContractParties].filter(_.notificationType == lift(notificationType)))
					contractPartiesList
			}
		}
	} yield contractPartiesList

	override def getByUserId(userId: Option[TableDirect]): RIO[Any,List[ContractParties]] =
		for {
			contractPartiesList <- RIO.fromTry {
				Try {
					val contractPartiesList = run(query[ContractParties].filter(_.userId == lift(userId)))
					contractPartiesList
			}
		}
	} yield contractPartiesList

	override def getAll: RIO[Any,List[ContractParties]] =
		for {
			contractPartiesList <- RIO.fromTry {
				Try {
					val contractPartiesList = run(query[ContractParties])
					contractPartiesList
			}
		}
	} yield contractPartiesList

	override def getAll(clientId: TableDirect): RIO[Any,List[ContractParties]] =
		for {
			contractPartiesList <- RIO.fromTry {
				Try {
					val contractPartiesList = run(query[ContractParties].filter(_.clientId == lift(clientId)))
					contractPartiesList
			}
		}
	} yield contractPartiesList

}