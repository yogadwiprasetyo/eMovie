package dependency

object FavoriteDependencies {
    // Path project
    private val corePath = ":core"
    private val appPath = ":app"

    val pathProject = arrayListOf<String>().apply {
        add(corePath)
        add(appPath)
    }
}