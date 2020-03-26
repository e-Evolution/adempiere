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

package org.eevolution.context.contract.api.repository


import org.eevolution.context.contract.api.Context.Context
import org.eevolution.context.contract.api.repository.TimeExpenseLineRepository.Service
import org.eevolution.context.contract.domain.ubiquitouslanguage.{ContractLine, Id, InvoiceLine, TimeExpenseLine}
import zio.{Has, ZIO, ZLayer}

/**
 * Time Expense Line Repository contract to implement the infrastructure application for Time Expense Line Entity
 */

object TimeExpenseLineRepository {

  type TimeExpenseLineRepository = Has[Service]
  trait Service {

    def newInstance(): ZIO[Context, Throwable, TimeExpenseLine]

    def createFromContractLine(contractLine: ContractLine): ZIO[Context, Throwable, TimeExpenseLine]

    def save(timeExpenseLine: TimeExpenseLine): ZIO[Context, Throwable, TimeExpenseLine]

    def update(timeExpenseLine: TimeExpenseLine, invoiceLine: InvoiceLine): ZIO[Context, Throwable, TimeExpenseLine]

    def getById(id: Id): ZIO[Context, Throwable, TimeExpenseLine]

    def getByTimeExpense(timeExpenseId: Id): ZIO[Context, Throwable, List[TimeExpenseLine]]

    def query(whereClause: String, parameters: List[TimeExpenseLine]): ZIO[Context, Throwable, List[TimeExpenseLine]]
  }

  def live: ZLayer[Has[Context], Nothing, Has[Service]] = ZLayer.fromService[Context, Service] { contextService => TimeExpenseLineRepositoryLive(contextService)}
}

case class TimeExpenseLineRepositoryLive(contextService: Context) extends Service {
  override def newInstance(): ZIO[Context, Throwable, TimeExpenseLine] = ???

  override def createFromContractLine(contractLine: ContractLine): ZIO[Context, Throwable, TimeExpenseLine] = ???

  override def save(timeExpenseLine: TimeExpenseLine): ZIO[Context, Throwable, TimeExpenseLine] = ???

  override def update(timeExpenseLine: TimeExpenseLine, invoiceLine: InvoiceLine): ZIO[Context, Throwable, TimeExpenseLine] = ???

  override def getById(id: Id): ZIO[Context, Throwable, TimeExpenseLine] = ???

  override def getByTimeExpense(timeExpenseId: Id): ZIO[Context, Throwable, List[TimeExpenseLine]] = ???

  override def query(whereClause: String, parameters: List[TimeExpenseLine]): ZIO[Context, Throwable, List[TimeExpenseLine]] = ???
}