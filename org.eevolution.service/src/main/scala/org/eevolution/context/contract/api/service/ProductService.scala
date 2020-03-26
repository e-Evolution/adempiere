/**
 * Copyright (C) 2003-2018, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software, you can redistribute it and/or modify it
 * under the terms version 2 of the GNU General Public License as published
 * or (at your option) any later version.
 * by the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along
 * with this program, if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 * For the text or an alternative of this public license, you may reach us
 * or via info@adempiere.net or http://www.adempiere.net/license.html
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.eevolution.context.contract.api.service

import org.eevolution.context.contract.api.Context
import org.eevolution.context.contract.api.Context.Context
import org.eevolution.context.contract.api.repository.ProductRepository
import org.eevolution.context.contract.api.service.ProductService.Service
import org.eevolution.context.contract.domain.ubiquitouslanguage.{Id, Product}
import zio.{Has, ZIO, ZLayer}

/**
 * Product Domain Service contract to implement the business logic for Product Entity
 */
trait ProductService {
  def productService: ProductService.Service
}

object ProductService {

  type ProductService = Has[Service]

  trait Service {
    def getById(id: Id): ZIO[Context, Throwable, Product]

    def getByServiceTypeId(serviceTypeId: Id): ZIO[Context, Throwable, Product]
  }
  def live : ZLayer[Has[Context.Service] with Has[ProductRepository.Service], Nothing , Has[Service]] = ZLayer.fromServices[Context.Service, ProductRepository.Service , Service] {(contextService , productRepository) => ProductServiceLive (contextService , productRepository)}

}

case class ProductServiceLive(contextService: Context.Service, productRepository: ProductRepository.Service)  extends Service {
  override def getById(id: Id): ZIO[Context, Throwable, Product] = ???

  override def getByServiceTypeId(serviceTypeId: Id): ZIO[Context, Throwable, Product] = ???
}

