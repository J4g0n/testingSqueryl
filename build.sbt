name := "basic-project"

organization := "example"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.2"

crossScalaVersions := Seq("2.10.4", "2.11.2")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.11.5" % "test",
  "org.squeryl" % "squeryl_2.11" % "0.9.5-7",
  mysqlDriver
)

val mysqlDriver = "mysql" % "mysql-connector-java" % "5.1.10"

initialCommands := "import example._"
