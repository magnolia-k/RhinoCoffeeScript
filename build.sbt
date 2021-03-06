organization := "tv.cntt"
name         := "rhinocoffeescript"
version      := "1.10.0"

// Remove Scala dependency
autoScalaLibrary := false

// Remove Scala version in output paths and artifacts
crossPaths := false

javacOptions ++= Seq("-source", "1.6", "-target", "1.6", "-Xlint:deprecation")

javacOptions in doc := Seq("-source", "1.6")

// https://developer.mozilla.org/en-US/docs/Mozilla/Projects/Rhino/Download_Rhino
libraryDependencies += "org.mozilla" % "rhino" % "1.7.7.1"

// Replace CoffeeScript.class after compilation
compile in Compile <<= (compile in Compile) map { any =>
  val src = new File("tv/cntt/rhinocoffeescript/CoffeeScript.class")
  if (src.exists)
    IO.copyFile(
      file("tv/cntt/rhinocoffeescript/CoffeeScript.class"),
      file("target/classes/tv/cntt/rhinocoffeescript/CoffeeScript.class")
    )
  any
}
