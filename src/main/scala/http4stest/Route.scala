package http4stest

import org.http4s._
import play.api.libs.iteratee.{Done, Enumeratee}
import play.api.libs.iteratee.Concurrent.Channel
import akka.util.ByteString
import org.http4s.HttpHeaders.{TransferEncoding}

/**
 * @author Bryce Anderson
 *         Created on 10/13/13
 */
object Route {
  import Writable._
  import Status._
  import concurrent.ExecutionContext.Implicits.global
  implicit val formats =  org.json4s.DefaultFormats

  case class ID(name: String, id: Int)

  val route: org.http4s.Route = {
    case Get -> Root / "hello" =>
      Ok("Hello world.")

    case Post -> Root / "jsonstream" =>
      val pipe = Enumeratee.map[HttpChunk]{
                          case BodyChunk(s) => new String(s.toArray)
                          case _ => ""                                  } ><>
                       play.extras.iteratees.JsonPipeline.arrayParser[ID] ><>
                       Enumeratee.map(id => s"Hello ${id.name}. Your ID is ${id.id}")
      Ok(pipe).addHeader(TransferEncoding(HttpEncodings.chunked))
  }


}
