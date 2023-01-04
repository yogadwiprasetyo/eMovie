import dependency.AppDependencies
import extensions.ExtensionDependencies.extImplementationProject

plugins {
    id(projectRoot.BuildPlugins.androidApplication)
    id(projectRoot.BuildPlugins.kotlinAndroid)
    id(projectRoot.BuildPlugins.kotlinParcelize)
    id(projectRoot.BuildPlugins.kotlinKapt)
    id(projectRoot.BuildPlugins.navigationArgs)
    id(projectRoot.BuildPlugins.googleServices)
    id(projectRoot.BuildPlugins.firebaseCrashlytics)
}

apply(from = "../shared_dependencies.gradle.kts")

android {
    compileSdk = projectRoot.AppConfig.compileSdk
    buildToolsVersion = projectRoot.AppConfig.buildToolsVersion

    defaultConfig {
        applicationId = "com.yogaprasetyo.capstone.emovie"
        minSdk = projectRoot.AppConfig.minSdk
        targetSdk = projectRoot.AppConfig.targetSdk
        versionCode = projectRoot.AppConfig.versionCode
        versionName = projectRoot.AppConfig.versionName

        testInstrumentationRunner = projectRoot.AppConfig.androidTestInstrumentation
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        // Enable proguard configuration in debug mode
        getByName("debug") {
            isMinifyEnabled = true
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures.viewBinding = true
    dynamicFeatures.addAll(setOf(":favorite", ":search"))
}

dependencies {
    // Include Project
    extImplementationProject(AppDependencies.pathProject)
}