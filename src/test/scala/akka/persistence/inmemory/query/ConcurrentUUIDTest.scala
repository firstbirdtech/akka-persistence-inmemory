package akka.persistence.inmemory.query

import akka.persistence.inmemory.TestSpec
import org.scalatest.Ignore

import java.util.UUID

import scala.concurrent.Future

@Ignore
class ConcurrentUUIDTest extends TestSpec {
  def getNow(): Future[UUID] = Future(akka.persistence.inmemory.nowUuid)
  it should "get uuids concurrently" in {
    Future.sequence((1 to 1000).map(_ => getNow())).futureValue
  }
}
