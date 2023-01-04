import dependency.SharedDependencies
import extensions.ExtensionDependencies.extAndroidTestImplementation
import extensions.ExtensionDependencies.extDebugImplementation
import extensions.ExtensionDependencies.extImplementation
import extensions.ExtensionDependencies.extTestImplementation

dependencies {
    // Default
    extImplementation(SharedDependencies.defaultLibs)

    // Android Library
    extImplementation(SharedDependencies.androidLibs)

    // External Library
    extImplementation(SharedDependencies.thirdPartyLibs)

    // Testing
    extTestImplementation(SharedDependencies.testingLibs)

    // Android Testing
    extAndroidTestImplementation(SharedDependencies.androidTestLibs)

    // Only Debug Library
    extDebugImplementation(SharedDependencies.onlyDebugLibs)
}