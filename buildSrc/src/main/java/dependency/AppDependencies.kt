package dependency

object AppDependencies {
    private const val corePath = ":core"

    val pathProject = arrayListOf<String>().apply {
        add(corePath)
    }
}