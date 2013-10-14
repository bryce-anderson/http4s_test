package http4stest

import org.http4s.grizzly.SimpleGrizzlyServer

/**
 * @author Bryce Anderson
 *         Created on 10/13/13
 */
object Main {
  def main(args: Array[String]) {
    val server = SimpleGrizzlyServer(serverRoot = "/*")(Route.route)
  }
}
