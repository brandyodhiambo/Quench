pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}
rootProject.name = "Quench"
include(":app")
include(":designsystem")
include(":core")
include(":core:common")
include(":core:database")
include(":feature")
include(":feature:home")
include(":feature:settings")
include(":feature:statistics")
