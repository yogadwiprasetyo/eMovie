// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:${versions.Versions.gradle}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.Versions.kotlin}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${versions.Versions.navigation}")
        classpath("com.google.gms:google-services:${versions.Versions.googleServices}")
        classpath("com.google.firebase:firebase-crashlytics-gradle:${versions.Versions.firebaseCrashlyticsGradle}")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}