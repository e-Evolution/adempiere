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

case class ContractRepositoryLive() extends ContractRepository.Service with ContractMapping {
	override def getByBPartnerId(bPartnerId: Search): RIO[Any,List[Contract]] =
		for {
			contractList <- RIO.fromTry {
				Try {
					val contractList = run(query[Contract].filter(_.bPartnerId == lift(bPartnerId)))
					contractList
			}
		}
	} yield contractList

	override def getByContractId(contractId: Id): RIO[Any, Option[Contract]] =
		for {
			contract <- RIO.fromTry {
				Try {
					val contract = run(query[Contract].filter(_.contractId == lift(contractId))).headOption
					contract
				}
			}
		} yield contract

	override def getByDateContract(dateContract: DateTime = java.time.LocalDateTime.now): RIO[Any,List[Contract]] =
		for {
			contractList <- RIO.fromTry {
				Try {
					val contractList = run(query[Contract].filter(_.dateContract == lift(dateContract)))
					contractList
			}
		}
	} yield contractList

	override def getByDateStart(dateStart: Option[DateTime]): RIO[Any,List[Contract]] =
		for {
			contractList <- RIO.fromTry {
				Try {
					val contractList = run(query[Contract].filter(_.dateStart == lift(dateStart)))
					contractList
			}
		}
	} yield contractList

	override def getByDocumentNo(documentNo: String): RIO[Any,List[Contract]] =
		for {
			contractList <- RIO.fromTry {
				Try {
					val contractList = run(query[Contract].filter(_.documentNo == lift(documentNo)))
					contractList
			}
		}
	} yield contractList

	override def getByDocTypeId(docTypeId: TableDirect): RIO[Any,List[Contract]] =
		for {
			contractList <- RIO.fromTry {
				Try {
					val contractList = run(query[Contract].filter(_.docTypeId == lift(docTypeId)))
					contractList
			}
		}
	} yield contractList

	override def getByDateFinish(dateFinish: Option[DateTime]): RIO[Any,List[Contract]] =
		for {
			contractList <- RIO.fromTry {
				Try {
					val contractList = run(query[Contract].filter(_.dateFinish == lift(dateFinish)))
					contractList
			}
		}
	} yield contractList

	override def getByUserId(userId: Option[TableDirect]): RIO[Any,List[Contract]] =
		for {
			contractList <- RIO.fromTry {
				Try {
					val contractList = run(query[Contract].filter(_.userId == lift(userId)))
					contractList
			}
		}
	} yield contractList

	override def getAll: RIO[Any,List[Contract]] =
		for {
			contractList <- RIO.fromTry {
				Try {
					val contractList = run(query[Contract])
					contractList
			}
		}
	} yield contractList

	override def getAll(clientId: TableDirect): RIO[Any,List[Contract]] =
		for {
			contractList <- RIO.fromTry {
				Try {
					val contractList = run(query[Contract].filter(_.clientId == lift(clientId)))
					contractList
			}
		}
	} yield contractList

}