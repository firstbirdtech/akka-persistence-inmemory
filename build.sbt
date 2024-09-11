addCommandAlias("codeFmt", ";scalafmtAll;scalafmtSbt;scalafixAll")
addCommandAlias("codeVerify", ";scalafmtCheckAll;scalafmtSbtCheck;scalafixAll --check")

lazy val akkaPersistenceInmemory = project
  .in(file("."))
  .settings(
    organization        := "com.firstbird",
    organizationName    := "Firstbird GmbH",
    sonatypeProfileName := "com.firstbird",
    description         := "A plugin for storing events in an event journal akka-persistence-inmemory",
    homepage            := Some(url("https://github.com/firstbirdtech/akka-persistence-inmemory")),
    licenses += ("Apache-2.0", new URL("https://www.apache.org/licenses/LICENSE-2.0.txt")),
    headerLicense := Some(HeaderLicense.ALv2("2024", "akka-persistence-inmemory contributors")),
    startYear     := Some(2014),
    scmInfo := Some(
      ScmInfo(homepage.value.get, "scm:git:https://github.com/firstbirdtech/akka-persistence-inmemory.git")
    ),
    developers += Developer(
      "contributors",
      "Contributors",
      "hello@firstbird.com",
      url("https://github.com/firstbirdtech/akka-persistence-inmemory/graphs/contributors")
    ),

    // Compiler Settings
    scalaVersion       := "2.13.14",
    crossScalaVersions := Seq("2.13.14", "3.3.3"),
    semanticdbEnabled  := true,
    semanticdbVersion  := scalafixSemanticdb.revision,
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
        commonOptions :+ "-Wunused:all"
      } else {
        commonOptions ++ Seq(
          "-Wunused",
          "-Xlog-reflective-calls",
          "-Xsource:3",
          "-Wdead-code"
        )
      }
    },

    // Test Options
    Test / fork              := true,
    Test / logBuffered       := false,
    Test / parallelExecution := false,
    // show full stack traces and test case durations
    Test / testOptions += Tests.Argument("-oDF"),

    // Dependencies
    libraryDependencies ++= Dependencies.root
  )
