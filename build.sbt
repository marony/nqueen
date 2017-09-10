lazy val root = (project in file(".")).
  aggregate(app).
  settings(inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.3"
    )),
    name := "NQueen-root"
  )

lazy val app = (project in file("app")).
  settings(
    name := "NQueen"
  )
