package dependency

import versions.Versions

object SharedDependencies {
    // Default
    private val coreKtx = "androidx.core:core-ktx:${Versions.core_ktx}"
    private val legacySupportV4 = "androidx.legacy:legacy-support-v4:${Versions.legacy_support}"
    private val appCompat = "androidx.appcompat:appcompat:${Versions.app_compat}"
    private val material = "com.google.android.material:material:${Versions.material}"
    private val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"

    // Android Library: RecyclerView, CardView
    private val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recycler_view}"
    private val cardView = "androidx.cardview:cardview:${Versions.card_view}"

    // External Library: Koin, Glide
    private val koinAndroid = "io.insert-koin:koin-android:${Versions.koin_android}"
    private val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    // External Library: Firebase Analytics & Crashlytics
    private val firebaseCrashlyticsKtx = "com.google.firebase:firebase-crashlytics-ktx:${Versions.firebase_crashlytics}"
    private val firebaseAnalyticsKtx = "com.google.firebase:firebase-analytics-ktx:${Versions.firebase_analytics}"

    // LeakCanary
    private val leakCanaryAndroid = "com.squareup.leakcanary:leakcanary-android:${Versions.leak_canary}"

    // Testing
    private val junit = "junit:junit:${Versions.junit}"
    private val mockitoCore = "org.mockito:mockito-core:${Versions.mockito_core}"
    private val mockitoInline = "org.mockito:mockito-inline:${Versions.mockito_inline}"
    private val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutine_test}" // Coroutine Test
    private val coreTesting = "androidx.arch.core:core-testing:${Versions.core_testing}" // InstantTaskExecutorRule

    private val extJunit = "androidx.test.ext:junit:${Versions.junit_ext}"
    private val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"

    val defaultLibs = arrayListOf<String>().apply {
        add(coreKtx)
        add(legacySupportV4)
        add(appCompat)
        add(material)
        add(constraintLayout)
    }

    val androidLibs = arrayListOf<String>().apply {
        add(recyclerView)
        add(cardView)
    }

    val thirdPartyLibs = arrayListOf<String>().apply {
        add(koinAndroid)
        add(glide)
        add(firebaseAnalyticsKtx)
        add(firebaseCrashlyticsKtx)
    }

    val onlyDebugLibs = arrayListOf<String>().apply { add(leakCanaryAndroid) }

    val testingLibs = arrayListOf<String>().apply {
        add(junit)
        add(mockitoCore)
        add(mockitoInline)
        add(coroutineTest)
        add(coreTesting)
    }

    val androidTestLibs = arrayListOf<String>().apply {
        add(extJunit)
        add(espressoCore)
    }
}