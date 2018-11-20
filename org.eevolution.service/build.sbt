import sbt.Keys.{unmanagedJars, unmanagedSourceDirectories}

name := "ServiceManagement"

version := "1.0"

scalaVersion := "2.12.6"
crossPaths := false

scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation", "-encoding" , "utf8")
javacOptions ++= Seq("-encoding", "UTF-8")

val sourceAdempiere = "/Users/e-Evolution/Develop/ADempiere/391"

unmanagedJars in Compile ++= (file(sourceAdempiere + "/zkwebui/WEB-INF/lib") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/tools/lib") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/lib") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/packages") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/zkpackages") * "*.jar").classpath

scalaSource in Compile := baseDirectory.value / "src" / "main" / "scala"
scalaSource in Test := baseDirectory.value / "src" / "test" / "scala"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.last
}

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = true, includeDependency = false)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.last
}


assemblyJarName in assembly := "ServiceManagement.jar"
