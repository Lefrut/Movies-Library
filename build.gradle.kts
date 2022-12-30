// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath(Dependencies.ClassPath.gradlePlugin)
        classpath(Dependencies.ClassPath.kotlinGradlePlugin)
        classpath(Dependencies.ClassPath.navigationSafeArgsPlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath(Dependencies.ClassPath.googleServicesPlugin)
    }
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}

