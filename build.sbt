import com.lihaoyi.workbench.Plugin._

enablePlugins(ScalaJSPlugin)

workbenchSettings

name := "Example"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.7"



libraryDependencies ++= Seq(
  "io.spray"            %%  "spray-can"     % "1.3.3",
  "io.spray"            %%  "spray-routing" % "1.3.3",
  "io.spray"            %%  "spray-json" % "1.3.2",
  "io.spray"            %%  "spray-util" % "1.3.3",
  "io.spray"            %%  "spray-caching" % "1.3.3",
  "io.spray"            %%  "spray-testkit" % "1.3.3"  % "test",
  "com.typesafe.akka"   %%  "akka-actor"    % "2.3.9",
  "com.typesafe.akka"   %%  "akka-testkit"  % "2.3.9"   % "test",
  "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.5",
  "org.scala-js" %%% "scalajs-dom" % "0.8.2",
  "com.lihaoyi" %%% "scalatags" % "0.5.4",
  "com.typesafe.slick" %% "slick" % "3.0.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "org.ccil.cowan.tagsoup" % "tagsoup" % "1.2.1",
  "com.h2database" % "h2" % "1.4.192"
)

bootSnippet := "com.fijimf.deepfij.ui.BookUI.main(document.getElementById('deepfij-main'));"

updateBrowsers <<= updateBrowsers.triggeredBy(fastOptJS in Compile)

scalacOptions ++= Seq("-Ylog-classpath", "-deprecation")

