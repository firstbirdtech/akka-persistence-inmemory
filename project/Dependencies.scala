import sbt._
import sbt.librarymanagement.ModuleID

object Dependencies {

  private val AkkaVersion      = "2.6.21"
  private val ScalazVersion    = "7.3.8"
  private val ScalaTestVersion = "3.2.19"
  private val LogbackVersion   = "1.5.8"

  val root: Seq[ModuleID] = Seq(
    "com.typesafe.akka" %% "akka-actor"             % AkkaVersion,
    "com.typesafe.akka" %% "akka-persistence"       % AkkaVersion,
    "com.typesafe.akka" %% "akka-persistence-query" % AkkaVersion,
    "com.typesafe.akka" %% "akka-stream"            % AkkaVersion,
    "org.scalaz"        %% "scalaz-core"            % ScalazVersion,
    "ch.qos.logback"     % "logback-classic"        % LogbackVersion   % Test,
    "com.typesafe.akka" %% "akka-slf4j"             % AkkaVersion      % Test,
    "com.typesafe.akka" %% "akka-persistence-tck"   % AkkaVersion      % Test,
    "com.typesafe.akka" %% "akka-stream-testkit"    % AkkaVersion      % Test,
    "com.typesafe.akka" %% "akka-testkit"           % AkkaVersion      % Test,
    "org.scalatest"     %% "scalatest"              % ScalaTestVersion % Test
  )
}
