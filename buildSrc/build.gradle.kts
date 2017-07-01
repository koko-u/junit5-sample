plugins {
    id("org.jetbrains.kotlin.jvm") version "1.1.3"
}

repositories {
    mavenCentral()
}

dependencies {
    compile(group = "org.jetbrains.kotlin", name = "kotlin-stdlib-jre8")
    compile(group = "org.junit.platform", name = "junit-platform-gradle-plugin", version = "1.0.0-M4")
}
