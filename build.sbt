name := "comet"

version := "1.0"

scalaVersion := "2.10.0"

resolvers += "spray repo" at "http://repo.spray.io"

resolvers += "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"

libraryDependencies += "io.spray" % "spray-can" % "1.1-M8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.2-M3",
  "com.typesafe.akka" %% "akka-testkit" % "2.2-M3",
  "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test",
  "junit" % "junit" % "4.11" % "test",
  "com.novocode" % "junit-interface" % "0.7" % "test->default",
  "joda-time"         % "joda-time"           % "2.1",
  "org.joda" % "joda-convert" % "1.2",
  "com.typesafe.slick" %% "slick" % "1.0.1",
  "org.slf4j" % "slf4j-nop" % "1.6.4"
)

// Note: These settings are defaults for activator, and reorganize your source directories.
Seq(
  scalaSource in Compile <<= baseDirectory / "src",
  javaSource in Compile <<= baseDirectory / "src",
  sourceDirectory in Compile <<= baseDirectory / "src",
  scalaSource in Test <<= baseDirectory / "test",
  javaSource in Test <<= baseDirectory / "test",
  sourceDirectory in Test <<= baseDirectory / "test",
  resourceDirectory in Compile <<= baseDirectory / "conf"
)
