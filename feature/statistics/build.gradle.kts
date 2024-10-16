plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_compiler
    }
    kotlinOptions {
        jvmTarget = AndroidConfig.javaVersion
    }
}

dependencies {
    implementation(project(Module.designsystem))
    implementation(project(Module.common))
    implementation(project(Module.database))
    implementation("androidx.work:work-runtime-ktx:2.9.1")
    implementation(project(mapOf("path" to ":feature:home")))


    // charts
    implementation("com.github.MahmoudIbrahim3:android-compose-charts:1.2.2")
}

ksp {
    arg("compose-destinations.mode", "destinations")
    arg("compose-destinations.moduleName", "statistics")
}
