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

package org.eevolution.context.tax.api.repository

import org.eevolution.context.tax.domain.model._
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import zio.{Has, RIO, ZLayer}

object TaxBaseRepository {

	type TaxBaseRepository = Has[TaxBaseRepository.Service]

	trait Service {
		def getByDescription(description: Option[String]): RIO[Any,List[TaxBase]]

		def getByName(name: String): RIO[Any, Option[TaxBase]]

		def getByValue(value: String): RIO[Any, Option[TaxBase]]

		def getByTaxBaseId(taxBaseId: Id): RIO[Any, Option[TaxBase]]

		def getAll: RIO[Any,List[TaxBase]]

		def getAll(clientId: TableDirect): RIO[Any,List[TaxBase]]

	}

	def live(service: Service): ZLayer[Any, Throwable, Has[Service]] = ZLayer.succeed(service)

	def getByDescription(description: Option[String]): RIO[TaxBaseRepository,List[TaxBase]]
	= RIO.accessM(_.get.getByDescription(description))

	def getByName(name: String): RIO[TaxBaseRepository, Option[TaxBase]]
	= RIO.accessM(_.get.getByName(name))

	def getByValue(value: String): RIO[TaxBaseRepository, Option[TaxBase]]
	= RIO.accessM(_.get.getByValue(value))

	def getByTaxBaseId(taxBaseId: Id): RIO[TaxBaseRepository, Option[TaxBase]]
	= RIO.accessM(_.get.getByTaxBaseId(taxBaseId))

	def getAll: RIO[TaxBaseRepository,List[TaxBase]]
	= RIO.accessM(_.get.getAll)

	def getAll(clientId: TableDirect): RIO[TaxBaseRepository,List[TaxBase]]
	= RIO.accessM(_.get.getAll(clientId))
}