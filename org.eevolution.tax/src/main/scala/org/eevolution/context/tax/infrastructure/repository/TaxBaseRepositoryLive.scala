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

case class TaxBaseRepositoryLive() extends TaxBaseRepository.Service with TaxBaseMapping {
	override def getByDescription(description: Option[String]): RIO[Any,List[TaxBase]] =
		for {
			taxBaseList <- RIO.fromTry {
				Try {
					val taxBaseList = run(query[TaxBase].filter(_.description == lift(description)))
					taxBaseList
			}
		}
	} yield taxBaseList

	override def getByName(name: String): RIO[Any, Option[TaxBase]] =
		for {
			taxBase <- RIO.fromTry {
				Try {
					val taxBase = run(query[TaxBase].filter(_.name == lift(name))).headOption
					taxBase
				}
			}
		} yield taxBase

	override def getByValue(value: String): RIO[Any, Option[TaxBase]] =
		for {
			taxBase <- RIO.fromTry {
				Try {
					val taxBase = run(query[TaxBase].filter(_.value == lift(value))).headOption
					taxBase
				}
			}
		} yield taxBase

	override def getByTaxBaseId(taxBaseId: Id): RIO[Any, Option[TaxBase]] =
		for {
			taxBase <- RIO.fromTry {
				Try {
					val taxBase = run(query[TaxBase].filter(_.taxBaseId == lift(taxBaseId))).headOption
					taxBase
				}
			}
		} yield taxBase

	override def getAll: RIO[Any,List[TaxBase]] =
		for {
			taxBaseList <- RIO.fromTry {
				Try {
					val taxBaseList = run(query[TaxBase])
					taxBaseList
			}
		}
	} yield taxBaseList

	override def getAll(clientId: TableDirect): RIO[Any,List[TaxBase]] =
		for {
			taxBaseList <- RIO.fromTry {
				Try {
					val taxBaseList = run(query[TaxBase].filter(_.clientId == lift(clientId)))
					taxBaseList
			}
		}
	} yield taxBaseList

}