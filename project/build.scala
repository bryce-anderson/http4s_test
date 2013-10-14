import sbt._
import Keys._

import sbtassembly.Plugin._
import AssemblyKeys._

object ApplicationBuild extends Build {

  lazy val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := "brycea",
    version := "0.0.1-SNAPSHOT",
    scalaVersion in ThisBuild := "2.10.3"
  )

  val main = Project("http4stest", 
                    new File("."),
                    settings = buildSettings ++ assemblySettings
    ).settings(
      mainClass in assembly := Some("http4stest.Main"),
      mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
        {
          case x if x.endsWith("MANIFEST.MF")     => 
            println("Found : " + x.getClass)
            MergeStrategy.discard
          case x if x.endsWith("rootdoc.txt")     => 
            println("Found : " + x)
            MergeStrategy.concat
          case x => old(x)
        }
      }
    )
   .dependsOn(http4s, http4sgrizzly)
   .dependsOn(pipeline)

   lazy val http4s = ProjectRef(file("../../Documents/GitHub/http4s"), "core")
   lazy val http4sgrizzly = ProjectRef(file("../../Documents/GitHub/http4s"), "grizzly")
   lazy val pipeline = RootProject(file("../../Documents/GitHub/play-iteratees-extras"))
}
