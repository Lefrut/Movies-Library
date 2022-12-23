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
    }
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}

