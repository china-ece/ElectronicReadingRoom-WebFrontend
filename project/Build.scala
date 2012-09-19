import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "ElectronicReadingRoom"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
	"mysql" % "mysql-connector-java" % "5.1.21",
	"net.sourceforge.jtds" % "jtds" % "1.2.4"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      // Add your own project settings here      
    )

}
