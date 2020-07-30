package org.eevolution.context.kernel.domain.aggregate

import org.eevolution.context.kernel.domain.ubiquitouslanguage.Id

import scala.reflect.ClassTag

trait Aggregate {
  type AggregateType <: Aggregate
  type IdType <: AggregateId
  val id: IdType
  protected val tag: ClassTag[AggregateType]

  def canEqual(other: Any): Boolean = tag.runtimeClass.isInstance(other)

  override def equals(other: Any): Boolean = other match {
    case tag(that) => (that canEqual this) && id == that.id
    case _         => false
  }

  override def hashCode(): Int = 31 * id.##
}

trait AggregateId {
  type IdType
  val value: IdType
}

trait AggregateBigIntId extends AggregateId {

  override type IdType = Int

}