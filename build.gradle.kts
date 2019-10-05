group = "zzy"
version = "1.0-SNAPSHOT"


plugins {
    java
    scala
    application
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    //mavenCentral()
    jcenter()
}

sourceSets {
    //为了实现Java和Scala相互调用,将source set都移动到了scala侧,避免compileJava阶段找不到Scala代码的情况
    main {
        withConvention(ScalaSourceSet::class) {
            scala {
                setSrcDirs(listOf("src/main/scala", "src/main/java"))
            }
        }
        java {
            setSrcDirs(emptyList<String>())
        }
    }
    test {
        withConvention(ScalaSourceSet::class) {
            scala {
                setSrcDirs(listOf("src/test/scala", "src/test/java"))
            }
        }
        java {
            setSrcDirs(emptyList<String>())
        }
    }
}

val scalaV = "2.11"
fun String.scv(): String = "${this}_$scalaV"

dependencies {
    compile("org.scala-lang:scala-library:$scalaV.12")
    compileOnly("ch.qos.logback", "logback-classic", "1.2.3")
    compile("com.typesafe", "config", "1.3.0")
    compile("mysql:mysql-connector-java:8.0.16")
    compile("com.lihaoyi", "requests".scv(), "0.1.9")
    compile("com.lihaoyi", "os-lib".scv(),"0.2.9")
    compile("org.apache.spark", "spark-core".scv(), "2.3.1")
    compile("org.apache.spark", "spark-sql".scv(), "2.3.1")
    compile("org.apache.spark", "spark-sql".scv(), "2.3.1")
    compile("org.scalatest", "scalatest".scv(), "3.0.5")
    compile("org.scalactic", "scalactic".scv(), "3.0.5")
    testRuntime("org.pegdown:pegdown:1.4.2")
}