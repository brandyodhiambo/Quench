plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")

}

apply {
    from("$rootDir/shared_dependencies.gradle")
}

android {
    namespace = "com.brandyodhiambo.database"
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
}

dependencies {

    implementation(project(Module.common))

    // Room
    implementation("androidx.room:room-runtime:${Versions.room_version}")
    ksp("androidx.room:room-compiler:${Versions.room_version}")

    // Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:${Versions.room_version}")
}
