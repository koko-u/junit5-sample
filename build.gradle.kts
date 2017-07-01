
import org.junit.platform.gradle.plugin.JUnitPlatformExtension
import org.junit.platform.gradle.plugin.JUnitPlatformPlugin

import jp.co.kokou.junit5.ext.engines
import jp.co.kokou.junit5.ext.filters
import java.nio.charset.StandardCharsets

buildscript {

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.0-M4")
    }
}

subprojects {

    apply {
        plugin<JavaPlugin>()
        plugin<JUnitPlatformPlugin>()
    }

    configure<JUnitPlatformExtension> {
        enableStandardTestTask = true

        filters {
            engines {
                include("junit-jupiter")
            }
            includeClassNamePatterns(".*Test", ".*Tests")
        }
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = JavaVersion.VERSION_1_8.majorVersion

        options.encoding = StandardCharsets.UTF_8.name()
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        testCompileOnly("org.projectlombok:lombok:1.16.16")

        testCompile("org.junit.jupiter:junit-jupiter-api:5.0.0-M4")
        testCompile("org.junit.jupiter:junit-jupiter-params:5.0.0-M4")
        testRuntime("org.junit.jupiter:junit-jupiter-engine:5.0.0-M4")

        testCompile("org.assertj:assertj-core:3.8.0")
    }
}