# https://github.com/krschultz/android-proguard-snippets/blob/master/libraries/proguard-square-okio.pro
# Okio (Updated-04/11/2016)
# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# https://github.com/square/okhttp/blob/master/okhttp/src/main/resources/META-INF/proguard/okhttp3.pro
# OkHttp (Updated-15/10/2018)
# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform

# Other App related rules

-keep class com.mosl.mobile.login.UserType {*;}

-keep class  com.gamooga.pushamplify.bridge.BridgePushHandler
-keepclassmembers enum * { *; }
-keep class com.mosl.mobile.appuser.model.User
-keep class com.mosl.mobile.domain.** { *; }
-keep class com.mosl.mobile.data.** { *; }

# Keep Flashbar classes
-keep class com.mosl.mobile.flashbar.** { *; }

# Keep OkHttp platform classes (optional dependencies)
-dontwarn org.bouncycastle.jsse.**
-dontwarn org.conscrypt.**
-dontwarn org.openjsse.**

# Keep SSL classes that might be used reflectively
-keep class org.bouncycastle.jsse.** { *; }
-keep class org.conscrypt.** { *; }
-keep class org.openjsse.** { *; }

# Firebase Remote Config
-keep class com.google.firebase.remoteconfig.** { *; }
-keep class com.google.android.gms.internal.firebase_remote_config.** { *; }
-keepclassmembers class com.google.android.gms.internal.firebase_remote_config.** { *; }
-dontwarn com.google.firebase.remoteconfig.**
