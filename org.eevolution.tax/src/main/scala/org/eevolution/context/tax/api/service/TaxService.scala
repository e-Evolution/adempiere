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

package org.eevolution.context.tax.api.service

import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.tax.domain.model._
import org.eevolution.context.tax.api.repository._
import org.eevolution.context.tax.domain.service._
import zio.{Has, RIO, ZLayer}

object TaxService {

	type TaxService = Has[Service]

	trait Service {
		def getByDescription(description: Option[String]): RIO[Any,List[Tax]]

		def getByName(name: String): RIO[Any, Option[Tax]]

		def getByTaxId(taxId: Id): RIO[Any, Option[Tax]]

		def getAll: RIO[Any,List[Tax]]

		def getAll(clientId: TableDirect): RIO[Any,List[Tax]]

	}

	def live: ZLayer[TaxRepository.TaxRepository, Nothing, Has[Service]]
	= ZLayer.fromService[TaxRepository.Service, Service] { taxRepository => TaxServiceLive(taxRepository) }

	def getByDescription(description: Option[String]): RIO[TaxService,List[Tax]]
	= RIO.accessM(_.get.getByDescription(description))

	def getByName(name: String): RIO[TaxService, Option[Tax]]
	= RIO.accessM(_.get.getByName(name))

	def getByTaxId(taxId: Id): RIO[TaxService, Option[Tax]]
	= RIO.accessM(_.get.getByTaxId(taxId))

	def getAll: RIO[TaxService,List[Tax]]
	= RIO.accessM(_.get.getAll)

	def getAll(clientId: TableDirect): RIO[TaxService,List[Tax]]
	= RIO.accessM(_.get.getAll(clientId))

}