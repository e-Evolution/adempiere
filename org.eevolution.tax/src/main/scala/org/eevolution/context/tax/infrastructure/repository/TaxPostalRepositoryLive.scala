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

case class TaxPostalRepositoryLive() extends TaxPostalRepository.Service with TaxPostalMapping {
	override def getByTaxId(taxId: TableDirect): RIO[Any,List[TaxPostal]] =
		for {
			taxPostalList <- RIO.fromTry {
				Try {
					val taxPostalList = run(query[TaxPostal].filter(_.taxId == lift(taxId)))
					taxPostalList
			}
		}
	} yield taxPostalList

	override def getByTaxPostalId(taxPostalId: Id): RIO[Any, Option[TaxPostal]] =
		for {
			taxPostal <- RIO.fromTry {
				Try {
					val taxPostal = run(query[TaxPostal].filter(_.taxPostalId == lift(taxPostalId))).headOption
					taxPostal
				}
			}
		} yield taxPostal

	override def getByPostal(postal: String): RIO[Any,List[TaxPostal]] =
		for {
			taxPostalList <- RIO.fromTry {
				Try {
					val taxPostalList = run(query[TaxPostal].filter(_.postal == lift(postal)))
					taxPostalList
			}
		}
	} yield taxPostalList

	override def getByPostalTo(postalTo: Option[String]): RIO[Any,List[TaxPostal]] =
		for {
			taxPostalList <- RIO.fromTry {
				Try {
					val taxPostalList = run(query[TaxPostal].filter(_.postalTo == lift(postalTo)))
					taxPostalList
			}
		}
	} yield taxPostalList

	override def getAll: RIO[Any,List[TaxPostal]] =
		for {
			taxPostalList <- RIO.fromTry {
				Try {
					val taxPostalList = run(query[TaxPostal])
					taxPostalList
			}
		}
	} yield taxPostalList

	override def getAll(clientId: TableDirect): RIO[Any,List[TaxPostal]] =
		for {
			taxPostalList <- RIO.fromTry {
				Try {
					val taxPostalList = run(query[TaxPostal].filter(_.clientId == lift(clientId)))
					taxPostalList
			}
		}
	} yield taxPostalList

}