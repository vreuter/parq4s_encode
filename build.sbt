ThisBuild / scalaVersion     := "3.1.0"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "vreuter"

lazy val root = (project in file("."))
  .settings(
    libraryDependencies ++= Seq(
      "org.apache.hadoop" % "hadoop-client" % "3.3.1", 
      "com.github.mjakubowski84" %% "parquet4s-core" % "2.1.0" , 
      "eu.timepit" %% "refined" % "0.9.28"
    )
  )
