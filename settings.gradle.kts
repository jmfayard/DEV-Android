pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenLocal()
    }
}
apply(from = "plugins.gradle.kts")
rootProject.name = "DEV-Android"
include(":app", ":baseui", ":data")
