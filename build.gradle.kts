// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        val kotlinVersion = findProperty("version.org.jetbrains.kotlin") as String
        val agpVersion = findProperty("version.com.android.tools.build..gradle") as String
        classpath("com.android.tools.build:gradle:$agpVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
plugins {
    id("com.louiscad.splitties").version("0.1.3")
    id("de.fayard.refreshVersions").version("0.8.0")
    detekt
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

/** Update Gradle with: $ ./gradlew buildSrcVersions && ./gradlew wrapper   ***/
tasks.wrapper {
    gradleVersion = Versions.gradleLatestVersion
    distributionType = Wrapper.DistributionType.ALL
}

task("clean") {
    group = "custom"
    description = "Delete directory build"
    delete(rootProject.buildDir)
}
tasks.register("hello") {
    group = "custom"
    description = "Hello World task - useful to solve build problems"
}
tasks.register("install") {
    group = "custom"
    description = "Build and install the app"
    dependsOn(":app:installDebug")
}
tasks.register("test") {
    group = "custom"
    description = "Run the unit tests"
    dependsOn(":app:testDebugUnitTest")
}
tasks.register("androidTest") {
    group = "custom"
    description = "Run android instrumentation tests"
    dependsOn(":app:connectedDebugAndroidTest")
}

// Plugin documentation available at https://github.com/jmfayard/buildSrcVersions/issues/53
refreshVersions {

}

detekt {
    input = files("$projectDir/app/src/main/java")
    config = files("$projectDir/config/detekt/detekt.yml")
    reports {
        xml {
            enabled = true
            destination = file("$projectDir/reports/detekt.xml")
        }
        html {
            enabled = true
            destination = file("$projectDir/reports/detekt.html")
        }
    }
    parallel = true
}


