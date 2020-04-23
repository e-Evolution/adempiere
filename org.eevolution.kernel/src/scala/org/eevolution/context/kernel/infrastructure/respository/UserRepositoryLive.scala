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

package org.eevolution.context.kernel.infrastructure.respository

import org.compiere.util.Env
import org.eevolution.context.kernel.api.repository.UserRepository
import org.eevolution.context.kernel.domain.model.User
import org.eevolution.context.kernel.domain.ubiquitouslanguage.Id
import org.eevolution.context.kernel.infrastructure.database.context._
import org.eevolution.context.kernel.infrastructure.persistence.model.MUser
import zio.Task

import scala.util.Try


object UserRepositoryLive extends UserRepository.Service with UserMapping {
  override def getById(id: Id): Task[Option[User]] = for {
    //ctx <- environment.get.getContext
    //trx <- environment.get.getTransaction
    user <- Task.fromTry {
      Try {
        //val result = prepare(quoteBankAccount.filter(_.bankAccountId == lift(id)))(trx.getConnection).executeQuery
        //val bank = new MBankAccount(ctx, result, trx.getTrxName)
        val user = run(quoteUser.filter(_.userId == lift(id))).headOption
        user
      }
    }
  } yield user


  override def getUser(partnerId: Id, userName: String, accountEmail: String): Task[Option[User]] = for {
    user <- Task.fromTry {
      Try {
        val user = run(quoteUser.filter(
          u => u.partnerId == lift(partnerId) &&
            u.name == lift(userName) &&
            u.email == lift(accountEmail)
        )).headOption
        user
      }
    }
  } yield user


  override def save(user: User): Task[Option[User]] = for {
    newUser <- Task.fromTry {
      Try {
        //val userModel = new MUser(ctx, user.userId, trx.getTrxName)
        val userModel = new MUser(Env.getCtx, user.userId, null)
        userModel.setAD_Org_ID(user.organizationId)
        userModel.setC_BPartner_ID(user.partnerId)
        userModel.setDescription(user.description)
        userModel.setName(user.name)
        userModel.setEMail(user.email)
        userModel.setPassword(user.password)
        val newUser = user.copy(
          userId = userModel.get_ID,
          uuid = userModel.getUUID,
          createdBy = userModel.getCreatedBy,
          created = userModel.getCreated.toLocalDateTime,
          updatedBy = userModel.getUpdatedBy,
          updated = userModel.getUpdated.toLocalDateTime
        )
        Option(newUser)
      }
    }
  } yield newUser


}