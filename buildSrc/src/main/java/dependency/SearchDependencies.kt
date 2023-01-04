package dependency

import versions.Versions

object SearchDependencies {
    // RxJava3
    private val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.rxjava}"
    private val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxjava_android}"

    // Path project
    private val corePath = ":core"
    private val appPath = ":app"

    val thirdPartyLibs = arrayListOf<String>().apply {
        add(rxJava)
        add(rxAndroid)
    }

    val pathProject = arrayListOf<String>().apply {
        add(corePath)
        add(appPath)
    }
}