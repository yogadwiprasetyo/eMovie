import dependency.CoreDependencies
import extensions.ExtensionDependencies.extApi
import extensions.ExtensionDependencies.extImplementation
import extensions.ExtensionDependencies.extKapt

plugins {
    id(projectRoot.BuildPlugins.androidLibrary)
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
        targetSdk = projectRoot.AppConfig.targetSdk

        testInstrumentationRunner = projectRoot.AppConfig.androidTestInstrumentation

        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "IMG_BASE_URL", "\"https://image.tmdb.org/t/p/original\"")
        buildConfigField("String", "SECRET_PASSPHRASE", "\"movie-projects\"")
        buildConfigField(
            "String",
            "CERTIFICATE_PINNING",
            "\"sha256/oD/WAoRPvbez1Y2dfYfuo4yujAcYHXdv1Ivb2v2MOKk=\""
        )
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

    kotlinOptions.jvmTarget = "1.8"
    buildFeatures.viewBinding = true
}

dependencies {
    // Android Libraries
    extImplementation(CoreDependencies.androidLibs)

    // Third Party Libraries
    extImplementation(CoreDependencies.thirdPartyLibs)

    // Use kapt (Room compiler)
    extKapt(CoreDependencies.kaptLibs)

    // Use api (Lifecycle & Navigation)
    extApi(CoreDependencies.apiLibs)
}