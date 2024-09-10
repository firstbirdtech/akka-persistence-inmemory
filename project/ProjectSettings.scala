import sbt._
import sbt.Keys._
import scalariform.formatter.preferences._
import com.typesafe.sbt.SbtScalariform

object ProjectSettings extends AutoPlugin {
  final val AkkaVersion = "2.6.21"
  final val ScalazVersion = "7.3.8"
  final val ScalaTestVersion = "3.2.19"
  final val LogbackVersion = "1.5.8"

  override def requires = plugins.JvmPlugin && SbtScalariform
  override def trigger = allRequirements

  override def projectSettings = Seq(
    name := "akka-persistence-inmemory",
    organization := "com.github.dnvriend",
    organizationName := "Dennis Vriend",
    description := "A plugin for storing events in an event journal akka-persistence-inmemory",
    startYear := Some(2014),

    scalaVersion := "2.13.14",
    crossScalaVersions := Seq("2.13.14", "3.3.3"),
    crossVersion := CrossVersion.binary,

    licenses := Seq(("Apache-2.0", new URL("https://www.apache.org/licenses/LICENSE-2.0.txt"))),

  ) ++ compilerSettings ++ scalariFormSettings ++ resolverSettings ++ librarySettings ++ testSettings

  lazy val librarySettings = Seq(
    libraryDependencies += "com.typesafe.akka" %% "akka-actor" % AkkaVersion,
    libraryDependencies += "com.typesafe.akka" %% "akka-persistence" % AkkaVersion,
    libraryDependencies += "com.typesafe.akka" %% "akka-persistence-query" % AkkaVersion,
    libraryDependencies += "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
    libraryDependencies += "org.scalaz" %% "scalaz-core" % ScalazVersion,
    libraryDependencies += "ch.qos.logback" % "logback-classic" % LogbackVersion % Test,
    libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % AkkaVersion % Test,
    libraryDependencies += "com.typesafe.akka" %% "akka-persistence-tck" % AkkaVersion % Test,
    libraryDependencies += "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion % Test,
    libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % AkkaVersion % Test,
    libraryDependencies += "org.scalatest" %% "scalatest" % ScalaTestVersion % Test,
  )

  lazy val testSettings = Seq(
    Test / fork := true,
    Test / logBuffered := false,
    Test / parallelExecution := false,
    // show full stack traces and test case durations
    Test / testOptions += Tests.Argument("-oDF"),
  )

  lazy val scalariFormSettings = Seq(
    SbtScalariform.autoImport.scalariformPreferences := {
      SbtScalariform.autoImport.scalariformPreferences.value
        .setPreference(AlignSingleLineCaseStatements, true)
        .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)
        .setPreference(DoubleIndentConstructorArguments, true)
        .setPreference(DanglingCloseParenthesis, Preserve)
    }
  )

  lazy val resolverSettings = Seq(
    resolvers ++= Resolver.sonatypeOssRepos("public"),
    resolvers += Resolver.typesafeRepo("releases"),
    resolvers += Resolver.jcenterRepo,
  )

  lazy val compilerSettings = Seq(
    scalacOptions ++= {
      val commonOptions = Seq(
      "-encoding",
      "UTF-8",
      "-deprecation",
      "-feature",
      "-unchecked",
      "-language:higherKinds",
      "-language:implicitConversions"
    )

    if (ScalaArtifacts.isScala3(scalaVersion.value)) {
      commonOptions
    } else {
      commonOptions :+ "-Xlog-reflective-calls"
    }
  }
  )
}
