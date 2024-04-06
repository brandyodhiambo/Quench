plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp") version ("1.9.23-1.0.20")
}

apply {
    from("$rootDir/shared_dependencies.gradle")
}

android {
    namespace = "com.brandyodhiambo.statistics"
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_compiler
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(Module.designsystem))
    implementation(project(Module.common))
    implementation(project(Module.database))

    // RamCosta Navigation
    implementation("io.github.raamcosta.compose-destinations:core:1.10.2")
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation(project(mapOf("path" to ":feature:home")))
    ksp("io.github.raamcosta.compose-destinations:ksp:1.10.2")

    // Navigation animation
    implementation("com.google.accompanist:accompanist-navigation-animation:0.34.0")

    // charts
    implementation("com.github.MahmoudIbrahim3:android-compose-charts:1.2.2")
}

kotlin {
    sourceSets {
        debug {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
        release {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}

ksp {
    arg("compose-destinations.mode", "destinations")
    arg("compose-destinations.moduleName", "statistics")
}
