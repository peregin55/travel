lazy val root = (project in file(".")).
  settings(
    name := "travel",
    organization  := "com.travel",
    version :=  "0.1",
    scalaVersion := "2.11.6",
    libraryDependencies ++= Seq("joda-time"     % "joda-time"       % "2.7",
                                "org.joda"      % "joda-convert"    % "1.7",
                                "org.scalatest" % "scalatest_2.11"  % "2.2.4" % "test")
  )
