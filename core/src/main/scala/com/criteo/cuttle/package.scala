package com.criteo

import scala.concurrent._
import doobie.imports._
import cats.free._

package cuttle {
  sealed trait Completed
  case object Completed extends Completed
}

package object cuttle {

  type XA = Transactor[IOLite]
  private[cuttle] val NoUpdate: ConnectionIO[Int] = Free.pure(0)

  type SideEffect[S <: Scheduling] = (Execution[S]) => Future[Completed]

  implicit def scopedExecutionContext(implicit execution: Execution[_]) = execution.executionContext

}
