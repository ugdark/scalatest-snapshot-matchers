val ScalaVersion = "2.13.6"
val Organization = "com.kokodayo"

val sharedSettings = Seq(
  organization := Organization,
  scalaVersion := ScalaVersion,
  //scalafmtOnCompile := true,
  Test / parallelExecution := false,
  //  releaseCrossBuild := true,
  //  crossScalaVersions := Seq("2.11.8", ScalaVersion),
  //  bintrayOrganization := Some("commodityvectors"),
  //  bintrayRepository := "commodityvectors-releases",
  licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
  //  pomExtra := (
  //    <scm>
  //      <url>git@github.com:commodityvectors/scalatest-snapshot-matcher.git</url>
  //      <connection>scm:git:git@github.com:commodityvectors/scalatest-snapshot-matcher.git</connection>
  //    </scm>
  //  ),
  //  releaseTagComment := s"[${name.value}] Releasing ${version.value}",
  //  releaseCommitMessage := s"[${name.value}] Setting version to ${version.value}",
  //  releaseTagName := s"${name.value}v${version.value}"
)

lazy val root = Project("scalatest-snapshot-matcher", file("."))
  //  .settings(publish := {})
  //  .settings(publishArtifact := false)
  .settings(name := "scalatest-snapshot-matcher")
  .settings(sharedSettings: _*)
  .aggregate(core)

lazy val core =
  Project("scalatest-snapshot-matcher-core", file("scalatest-snapshot-matcher-core"))
    .settings(name := "scalatest-snapshot-matcher-core")
    .settings(sharedSettings: _*)
    .settings(libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.1.4",
      "com.googlecode.java-diff-utils" % "diffutils" % "1.3.0",
      "com.typesafe" % "config" % "1.3.2",
      "commons-io" % "commons-io" % "2.6" % "test"
    ))
