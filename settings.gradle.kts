include(":app")
//pluginManagement {
//    repositories {
//        gradlePluginPortal()
//        google()
//        mavenCentral()
//    }
//}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "ViewApp"
include(":features")
include(":features:library")
include(":features:profile")
include(":features:unknown")
include(":core")
include(":core:data")
include(":core:domain")
include(":utility")
include(":features:authorization")
include(":navigation")
