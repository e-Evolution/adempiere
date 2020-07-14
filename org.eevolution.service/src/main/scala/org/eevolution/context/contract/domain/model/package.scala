package org.eevolution.context.contract.domain


import java.time.Instant
import java.util.UUID

import org.eevolution.context.contract.api.Context.Context
import org.eevolution.context.contract.domain.ubiquitouslanguage.DateTime
import org.eevolution.context.kernel.domain.model.{Organization, Tenant}
import zio.ZIO



/**
 * Package with API for Domain Model
 */
package object model {


  /**
   * Define Domain Model type
   */
  abstract trait DomainModel {
    type DomainModel
  }

  /**
   * Define Active Enabled Type
   */
  abstract trait ActiveEnabled {

    type ActiveEnabled

    val isActive: Boolean
  }

  /**
   * Define Tenant Enabled Type
   */
  abstract trait TenantEnabled {

    type TenantEnabled

    val tenant: Tenant

    def getTenant = tenant
  }

  /**
   * Define Identifiable Type
   */
  abstract trait Identifiable {

    type Identifiable

    val entityName: String
    val identifier: String

    def Id: Int

    def EntityName: String = entityName

    def Identifier: String = identifier
  }

  /**
   * Define Organization Enabled type
   */
  abstract trait OrganizationEnabled {

    type OrganizationEnabled

    val organization: Organization

    def getOrganization = organization
  }

  /**
   * Define Traceable Type
   */
  abstract trait Traceable {

    type Traceable

    val updatedBy: Int
    val createdBy: Int
    val created: DateTime
    val updated: DateTime

    def getCreatedBy: Int = createdBy

    def getCreated: DateTime = created

    def getUpdatedBy: Int = updatedBy

    def getUpdated: DateTime = updated

  }


  /**
   * Generic Service trait
   *
   * @tparam DomainModel Domain Model
   * @tparam idType      id Type
   */
  trait Repository[DomainModel, idType] {

    type Repository

    def getById(id: idType): ZIO[Context, Throwable, DomainModel]

    def getByUUID(uuid: UUID): ZIO[Context, Throwable, DomainModel]

    def getAllByPage(page: Int, pageSize: Int): ZIO[Context, Throwable, DomainModel]

    def getAll: ZIO[Context, Throwable, List[DomainModel]]
  }


  /**
   * Generic Service trait
   *
   * @tparam DomainModel Domain Model
   * @tparam idType      id Type
   */
  trait Service[DomainModel, idType] {

    type Service

    def getById(id: idType): Object

    def getByUUID(uuid: UUID): Object

    def getAll: Object

    def getAllByPage(page: Option[Int], pageSize: Option[Int]): Object

  }


  /** Display Type and Application Dictionary Reference  */
  object DisplayType {

    /** Display Type 10	String	 */
    val String: Int = 10
    /** Display Type 11	Integer	 */
    val Integer: Int = 11
    /** Display Type 12	Amount	 */
    val Amount: Int = 12
    /** Display Type 13	ID	 */
    val ID: Int = 13
    /** Display Type 14	Text	 */
    val Text: Int = 14
    /** Display Type 15	Date	 */
    val Date: Int = 15
    /** Display Type 16	DateTime	 */
    val DateTime: Int = 16
    /** Display Type 17	List	 */
    val List: Int = 17
    /** Display Type 18	Table	 */
    val Table: Int = 18
    /** Display Type 19	TableDir	 */
    val TableDir: Int = 19
    /** Display Type 20	YN	 */
    val YesNo: Int = 20
    /** Display Type 21	Location	 */
    val Location: Int = 21
    /** Display Type 22	Number	 */
    val Number: Int = 22
    /** Display Type 23	BLOB	 */
    val Binary: Int = 23
    /** Display Type 24	Time	 */
    val Time: Int = 24
    /** Display Type 25	Account	 */
    val Account: Int = 25
    /** Display Type 26	RowID	 */
    val RowID: Int = 26
    /** Display Type 27	Color   */
    val Color: Int = 27
    /** Display Type 28	Button	 */
    val Button: Int = 28
    /** Display Type 29	Quantity	 */
    val Quantity: Int = 29
    /** Display Type 30	Search	 */
    val Search: Int = 30
    /** Display Type 31	Locator	 */
    val Locator: Int = 31
    /** Display Type 32 Image	 */
    val Image: Int = 32
    /** Display Type 33 Assignment	 */
    val Assignment: Int = 33
    /** Display Type 34	Memo	 */
    val Memo: Int = 34
    /** Display Type 35	PAttribute	 */
    val PAttribute: Int = 35
    /** Display Type 36	CLOB	 */
    val TextLong: Int = 36
    /** Display Type 37	CostPrice	 */
    val CostPrice: Int = 37
    /** Display Type 38	File Path	 */
    val FilePath: Int = 38
    /** Display Type 39 File Name	 */
    val FileName: Int = 39
    /** Display Type 40	URL	 */
    val URL: Int = 40
    /** Display Type 42	PrinterName	 */
    val PrinterName: Int = 42
    /** Display Type 53370 Chart */
    val Chart: Int = 53370

    /**
     * Get Class for Reference
     *
     * @param reference      Reference
     * @param yesNoAsBoolean Yes No As Boolean
     * @return
     */
    def geClass(reference: Int, yesNoAsBoolean: Boolean): String = {
      if (isText(reference) || reference == List) "String"
      else if (isId(reference) || reference == Integer) "String"
      else if (isNumeric(reference)) "BigDecimal"
      else if (isDate(reference)) "DateTime"
      else if (reference == YesNo) if (yesNoAsBoolean) "Boolean" else "String"
      else if (reference == Button) "String"
      else if (isLob(reference)) "Byte"
      else "AnyRef"
    }

    /**
     * Check if Is a Text
     *
     * @param reference Reference
     * @return
     */
    def isText(reference: Int): Boolean = reference match {
      case String => true
      case Text => true
      case TextLong => true
      case Memo => true
      case FilePath => true
      case FileName => true
      case URL => true
      case PrinterName => true
      case _ => false
    }

    /**
     * Check if Is a Date
     *
     * @param reference Reference
     * @return
     */
    def isDate(reference: Int): Boolean = reference match {
      case Date => true
      case DateTime => true
      case _ => false
    }

    /**
     * Check if Is Lookup
     *
     * @param reference Reference
     * @return
     */
    def isLookup(reference: Int): Boolean = reference match {
      case List => true
      case Table => true
      case TableDir => true
      case Search => true
      case _ => false
    }

    /**
     * Check if is Lob
     *
     * @param reference Reference
     * @return
     */
    def isLob(reference: Int): Boolean = reference match {
      case Binary => true
      case TextLong => true
      case _ => false
    }

    /**
     * Check if is Numeric
     *
     * @param reference Reference
     * @return
     */
    def isNumeric(reference: Int): Boolean = reference match {
      case Amount => true
      case Number => true
      case CostPrice => true
      case Integer => true
      case Quantity => true
      case _ => false
    }

    /**
     * Check if is an Id
     *
     * @param reference Reference
     * @return
     */
    def isId(reference: Int): Boolean = reference match {
      case ID => true
      case Table => true
      case TableDir => true
      case Search => true
      case Location => true
      case Locator => true
      case Account => true
      case Assignment => true
      case PAttribute => true
      case Image => true
      case Color => true
      case _ => false
    }

  }

}

