# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

## Begin: proguard configuration for Navigation
-keepnames class androidx.navigation.dynamicfeatures.fragment.DynamicNavHostFragment


## Begin: proguard configuration for args navigation
-keepnames class com.yogaprasetyo.capstone.emovie.core.domain.model.Movie
-keepnames class com.yogaprasetyo.capstone.emovie.core.utils.TypeMovie


## Begin: proguard configuration for SQLCipher
-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }


## Begin: proguard configuration for avoid missing warning gradle
-dontwarn okhttp3.internal.platform.*
-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.conscrypt.Conscrypt


## Begin: proguard configuration for Response POJO file
-keep class com.yogaprasetyo.capstone.emovie.core.data.source.remote.response.** {*;}