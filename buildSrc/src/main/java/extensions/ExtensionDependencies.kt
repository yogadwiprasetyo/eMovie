package extensions

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object ExtensionDependencies {
    private const val kaptName = "kapt"
    private const val apiName = "api"
    private const val implementationName = "implementation"
    private const val debugImplementationName = "debugImplementation"
    private const val testImplementationName = "testImplementation"
    private const val androidTestImplementationName = "androidTestImplementation"

    fun DependencyHandler.extKapt(list: List<String>) {
        list.forEach { dependency -> add(kaptName, dependency) }
    }

    fun DependencyHandler.extApi(list: List<String>) {
        list.forEach { dependency -> add(apiName, dependency) }
    }

    fun DependencyHandler.extImplementation(list: List<String>) {
        list.forEach { dependency -> add(implementationName, dependency) }
    }

    fun DependencyHandler.extImplementationProject(list: List<String>) {
        list.forEach { path -> add(implementationName, project(path)) }
    }

    fun DependencyHandler.extTestImplementation(list: List<String>) {
        list.forEach { dependency -> add(testImplementationName, dependency) }
    }

    fun DependencyHandler.extAndroidTestImplementation(list: List<String>) {
        list.forEach { dependency -> add(androidTestImplementationName, dependency) }
    }

    fun DependencyHandler.extDebugImplementation(list: List<String>) {
        list.forEach { dependency -> add(debugImplementationName, dependency) }
    }
}