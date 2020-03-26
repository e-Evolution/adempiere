package org.eevolution.context.contract.api.repository

import org.compiere.util.Trx
import org.eevolution.context.contract.api.Context
import org.eevolution.context.contract.api.Context.Context
import org.eevolution.context.contract.api.repository.Transaction.Service
import zio.{Has, ZIO, ZLayer}

/**
 * API Singleton Object Transaction Management
 */
object Transaction {

  type Transaction = Has[Service]

  trait Service {
    def create () : ZIO[Any, Throwable, Trx]
    def getTrxName() :  ZIO[Any, Throwable, String]
    def get() :  ZIO[Any, Throwable, Trx]
    def setTransaction(trxName : String) : ZIO[Any, Throwable, Trx]
    def commit() : ZIO[Any, Throwable, Trx]
    def Rollback() : ZIO[Any, Throwable, Trx]
  }

  def live: ZLayer[Has[Context.Service], Nothing, Has[Transaction.Service]] = ZLayer.fromService[Context.Service, Service] { contextService => TransactionLive(contextService)}

}

case class TransactionLive(contextService: Context.Service) extends Service {
  override def create(): ZIO[Any, Throwable, Trx] = ???

  override def getTrxName(): ZIO[Any, Throwable, String] = ???

  override def get(): ZIO[Any, Throwable, Trx] = ???

  override def setTransaction(trxName: String): ZIO[Any, Throwable, Trx] = ???

  override def commit(): ZIO[Any, Throwable, Trx] = ???

  override def Rollback(): ZIO[Any, Throwable, Trx] = ???
}

