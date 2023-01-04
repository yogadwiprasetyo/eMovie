package dependency

import versions.Versions

object CoreDependencies {
    // Android Library: Room
    private val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    private val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    private val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    // External Library: Retrofit, OkHttp3
    private val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private val retrofitConvertorGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    private val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}"

    // External Library: Coroutine
    private val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine_core}"
    private val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine_android}"

    // Encryption Database
    private val androidSqlChiper = "net.zetetic:android-database-sqlcipher:${Versions.sqlchiper}"
    private val sqliteKtx = "androidx.sqlite:sqlite-ktx:${Versions.sqlite}"

    // Lifecycle & Navigation
    private val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    private val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    private val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

    private val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    private val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    private val navigationDynamic = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}"

    val androidLibs = arrayListOf<String>().apply {
        add(roomKtx)
        add(roomRuntime)
        add(sqliteKtx)
    }

    val thirdPartyLibs = arrayListOf<String>().apply {
        add(retrofit)
        add(retrofitConvertorGson)
        add(okhttpInterceptor)
        add(coroutineCore)
        add(coroutineAndroid)
        add(androidSqlChiper)
    }

    val apiLibs = arrayListOf<String>().apply {
        add(viewModel)
        add(liveData)
        add(lifecycleKtx)
        add(navigationFragment)
        add(navigationUi)
        add(navigationDynamic)
    }

    val kaptLibs = arrayListOf<String>().apply {
        add(roomCompiler)
    }
}