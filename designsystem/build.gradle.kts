plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")

}

apply {
    from("$rootDir/shared_dependencies.gradle")
}

android {
    namespace = "com.brandyodhiambo.designsystem"
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
    kotlinOptions {
        jvmTarget = AndroidConfig.javaVersion
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_compiler
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation("androidx.compose.compiler:compiler:${Versions.compose_compiler}")
    implementation("androidx.compose.animation:animation:${Versions.compose_version}")
    implementation("androidx.compose.animation:animation-core:${Versions.compose_version}")

}
