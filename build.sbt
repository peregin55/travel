name := """travel"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "joda-time"     % "joda-time"       % "2.7",
  "org.joda"      % "joda-convert"    % "1.7",
  "org.scalatest" % "scalatest_2.11"  % "2.2.4" % "test"
)
