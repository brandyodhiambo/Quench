buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.diffplug.spotless:spotless-plugin-gradle:6.25.0")
    }
} // Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version ("8.3.1") apply false
    id("com.android.library") version ("8.3.1") apply false
    id("org.jetbrains.kotlin.android") version ("1.9.22") apply false
    id("com.google.dagger.hilt.android") version "2.51" apply false
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0" apply false
    id("com.diffplug.spotless") version "6.25.0" apply false
}

subprojects {
    afterEvaluate {
        project.apply("$rootDir/spotless.gradle")
        project.apply("$rootDir/ktlint.gradle")
    }
}
