import dependency.FavoriteDependencies
import extensions.ExtensionDependencies.extImplementationProject

plugins {
    id(projectRoot.BuildPlugins.androidDynamicFeature)
    id(projectRoot.BuildPlugins.kotlinAndroid)
    id(projectRoot.BuildPlugins.kotlinParcelize)
    id(projectRoot.BuildPlugins.kotlinKapt)
    id(projectRoot.BuildPlugins.googleServices)
    id(projectRoot.BuildPlugins.firebaseCrashlytics)
}

apply(from = "../shared_dependencies.gradle.kts")

android {
    compileSdk = projectRoot.AppConfig.compileSdk
    buildToolsVersion = projectRoot.AppConfig.buildToolsVersion

    defaultConfig {
        minSdk = projectRoot.AppConfig.minSdk
        testInstrumentationRunner = projectRoot.AppConfig.androidTestInstrumentation
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions.jvmTarget = "1.8"
    buildFeatures.viewBinding = true
}

dependencies {
    // Include project
    extImplementationProject(FavoriteDependencies.pathProject)
}