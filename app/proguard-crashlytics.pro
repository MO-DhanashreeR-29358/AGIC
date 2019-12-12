# Crashlytics (Updated-04/11/2016)
## https://docs.fabric.io/android/crashlytics/dex-and-proguard.html

# https://firebase.google.com/docs/crashlytics/get-deobfuscated-reports
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception

# To let Crashlytics automatically upload the ProGuard or DexGuard mapping file, remove this line from the config file
# -printmapping mapping.txt

-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**