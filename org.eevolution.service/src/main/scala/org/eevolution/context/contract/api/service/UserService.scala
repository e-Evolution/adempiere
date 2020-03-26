/**
  * Copyright (C) 2003-2020, e-Evolution Consultants S.A. , http://www.e-evolution.com
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

package org.eevolution.context.contract.api.service


import org.eevolution.context.contract.api.Context
import org.eevolution.context.contract.api.Context.Context
import org.eevolution.context.contract.api.repository.UserRepository
import org.eevolution.context.contract.api.repository.UserRepository.Service
import org.eevolution.context.contract.domain.ubiquitouslanguage.{Id, Partner, User}
import zio.{Has, ZIO, ZLayer}

/**
  * Standard Implementation for Domain User Domain
  */
object UserService {

  type UserService = Has[Service]

  trait Service {
    def getById(id: Id): ZIO[Context, Throwable, User]
    def getUser(partnerId: Id, userName: String, accountEmail: String, password: String): ZIO[Context, Throwable, User]
    def create(partner : Partner , userName : String , accountEmail : String , userPassword : String) : ZIO[Context, Any, User]
    def save (user:User) : ZIO[Context, Throwable, User]
  }

  //def live : ZLayer[Has[Context.Service] with Has[UserRepository.Service], Nothing, Has[Service]] = ZLayer.fromServices[Context.Service ,UserRepository.Service, Service] { (context, userRepository) =>UserServiceLive (context, userRepository)}

}

case class UserServiceLive (context : Context.Service, userRepository : UserRepository.Service) extends Service{
  def getById(id: Id): ZIO[Context, Throwable, User] = ???
  def getUser(partnerId: Id, userName: String, accountEmail: String, password: String): ZIO[Context, Throwable, User] = ???
  def create(partner : Partner , userName : String , accountEmail : String , userPassword : String) : ZIO[Context, Any, User] = ???
  def save (user:User) : ZIO[Context, Throwable, User] = ???
}
