package org.eevolution.context.contract.api

import java.util.Properties

import org.compiere.util.Trx
import org.eevolution.context.contract.api.Context.{Context, Service}
import org.eevolution.context.contract.api.service.UserService.UserService
import org.eevolution.context.contract.api.service.{OrganizationService, TenantService, UserService}
import org.eevolution.context.contract.domain.ubiquitouslanguage.{Organization, Tenant, User}
import zio.{Has, ZIO, ZLayer}

/**
 * API Singleton Object Context Application
 */
object Context {

  type Context = Has[Service]

  trait Service {
    def getContext: ZIO[Any, Throwable, Properties]

    def getTransaction: ZIO[Context, Throwable, Trx]

    def getTenant: ZIO[Context, Throwable, Tenant]

    def getOrganization: ZIO[Context, Throwable, Organization]

    def getUser: ZIO[Context, Throwable, User]
  }

  def live : ZLayer[Has[TenantService.Service] with Has[OrganizationService.Service] with Has[UserService.Service], Nothing, Has[Service]] = ZLayer.fromServices[TenantService.Service,OrganizationService.Service, UserService.Service , Service] {
    (tenantService , organizationService , userService ) => ContextLive(tenantService , organizationService , userService)
  }
 }

case class ContextLive (tenantService : TenantService.Service, organizationService : OrganizationService.Service , userService : UserService.Service) extends Service {
  override def getContext: ZIO[Any, Throwable, Properties] = ???

  override def getTransaction: ZIO[Context, Throwable, Trx] = ???

  override def getTenant: ZIO[Context, Throwable, Tenant] = ???

  override def getOrganization: ZIO[Context, Throwable, Organization] = ???

  override def getUser: ZIO[Context, Throwable, User] = ???
}
