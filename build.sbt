val ScalaVersion = "2.11.0"
val Organization = "com.commodityvectors"

val sharedSettings = Seq(
  organization := Organization,
  scalaVersion := ScalaVersion,
  parallelExecution in Test := false,
  bintrayOrganization := Some("commodityvectors"),
  bintrayRepository := "commodityvectors-releases",
  licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
  pomExtra := (
    <scm>
      <url>git@github.com:commodityvectors/scalatest-snapshot-matcher.git</url>
      <connection>scm:git:git@github.com:commodityvectors/scalatest-snapshot-matcher.git</connection>
    </scm>
  )
)

lazy val core =
  Project("scalatest-snapshot-matcher-core", file("scalatest-snapshot-matcher-core"))
    .settings(name := "scalatest-snapshot-matcher-core")
    .settings(sharedSettings: _*)
    .settings(libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "2.2.6",
      "com.googlecode.java-diff-utils" % "diffutils" % "1.2.1",
      "commons-io" % "commons-io" % "2.4" % "test"
    ))

lazy val playJson =
  Project("scalatest-snapshot-matcher-play-json", file("scalatest-snapshot-matcher-play-json"))
    .settings(name := "scalatest-snapshot-matcher-play-json")
    .settings(sharedSettings: _*)
    .settings(libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play-json" % "2.6.0-M6",
      "commons-io" % "commons-io" % "2.4" % "test"
    )).dependsOn(core)