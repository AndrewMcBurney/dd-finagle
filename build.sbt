name := "datadog-sbt"

version := "1.0"

scalaVersion := "2.12.4"

Compile / run / mainClass := Some("datadog.sbt.Server")
Compile / run / fork := true
Compile / run / javaOptions := Seq("-javaagent:dd-java-agent.jar")

libraryDependencies ++= Seq(
  "com.twitter" %% "finagle-http" % "18.2.0",
  "io.opentracing" % "opentracing-api"  % "0.31.0",
  "io.opentracing" % "opentracing-util" % "0.31.0",
  "com.datadoghq" % "dd-trace-api" % "0.3.0"
)
