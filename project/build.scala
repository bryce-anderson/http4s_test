import sbt._
import Keys._

object ApplicationBuild extends Build {

  val main = Project("http4stest", new File(".")).settings(
    libraryDependencies ++= Seq(),
    organization := "brycea",
    version := "0.0.1-SNAPSHOT",
    scalaVersion in ThisBuild := "2.10.0"
  )
   .dependsOn(http4s, http4sgrizzly)
   .dependsOn(pipeline)

   lazy val http4s = ProjectRef(file("../../Documents/GitHub/http4s"), "core")
   lazy val http4sgrizzly = ProjectRef(file("../../Documents/GitHub/http4s"), "grizzly")
   lazy val pipeline = RootProject(file("../../Documents/GitHub/play-iteratees-extras"))
}
