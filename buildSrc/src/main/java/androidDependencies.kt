object Versions {
    const val fabric = "1.31.0"
    const val dexCountGradlePlugin = "0.8.6"
    const val errorProne = "0.0.13"
    const val okIo = "1.17.4"
    const val leakCanary = "1.6.3"
    const val room = "2.1.0"
    const val wearable = "2.3.0"
    const val coroutines = "1.3.0"
    const val compileSdkVersion = 34
    const val targetSdkVersion = 34
    const val minSdkVersion = 21
    const val buildToolsVersion = "34.0.0"
    const val androidVersion = "8.1.0"
    const val kotlinVersion = "1.9.23"
    const val okHttp = "4.9.0"
    const val timber = "4.7.1"
    const val groupie = "2.3.0"
    const val retrofit = "2.9.0"
    const val dagger = "2.48"
    const val rxBinding = "3.0.0"
    const val stetho = "1.5.1"
    const val androidDataBinding = "4.0.1"
    const val lifeCycle = "2.2.0"
    const val sdp = "1.0.6"
    const val qrCode = "1.9.8"
    const val gson = "2.8.6"
    const val flashBar = "0.0.22"
    const val securityVersion = "1.1.0-alpha02"
    const val googleServices = "4.3.13"
    const val detektVersion = "1.10.0"
    const val firebaseCrashlytics = "2.9.1"
}

object Dependencies {
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val brotli = "org.brotli:dec:0.1.2"
    const val mpChart = "com.github.PhilJay:MPAndroidChart:v3.1.0"
    const val threeTen = "com.jakewharton.threetenabp:threetenabp:1.2.1"
    const val appIntro = "com.github.AppIntro:AppIntro:5.1.0"
    const val processPhoenix = "com.jakewharton:process-phoenix:2.0.0"
}

object PluginsDependencies {
    const val androidBuild = "com.android.tools.build:gradle:${Versions.androidVersion}"
    const val googleServices = "com.google.gms:google-services:${Versions.googleServices}"
    const val dexCountGradlePlugin =
        "com.getkeepsafe.dexcount:dexcount-gradle-plugin:${Versions.dexCountGradlePlugin}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val errorPronePlugin =
        "net.ltgt.gradle:gradle-errorprone-plugin:${Versions.errorProne}"
    const val fabricPlugin = "io.fabric.tools:gradle:${Versions.fabric}"

    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
}

object AndroidxDependencies {
    const val annotation = "androidx.annotation:annotation:1.1.0"
    const val material = "com.google.android.material:material:1.3.0"
    const val appcompat = "androidx.appcompat:appcompat:1.2.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0-beta01"
    const val fragment = "androidx.fragment:fragment:1.1.0-rc01"
    const val cardView = "androidx.cardview:cardview:1.0.0"
    const val browser = "androidx.browser:browser:1.0.0"
    const val multiDex = "androidx.multidex:multidex:2.0.1"
    const val preference = "androidx.preference:preference:1.1.0-rc01"
    const val preferenceKotlin = "androidx.preference:preference-ktx:1.1.0"
    const val legacy = "androidx.legacy:legacy-support-core-ui:1.0.0"
    const val testRunner = "androidx.test:runner:1.2.0"
    const val testEspresso = "androidx.test.espresso:espresso-core:3.2.0"
    const val testJunit = "androidx.test.ext:junit:1.1.1"
    const val dataBinding =
        "androidx.databinding:databinding-compiler:${Versions.androidDataBinding}"
}

object KotlinDependencies {
    const val ktxCore = "androidx.core:core-ktx:1.8.0"
    const val ktxFragment = "androidx.fragment:fragment-ktx:1.2.5"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"

}

object CoroutinesDependencies {
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

}

object LifeCycleDependencies {
    const val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifeCycle}"
    const val extension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycle}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycle}"
}


object FirebaseDependencies {
    const val core = "com.google.firebase:firebase-core:17.0.0"
    const val messaging = "com.google.firebase:firebase-messaging:19.0.1"
    const val perf = "com.google.firebase:firebase-perf:18.0.1"
    const val remoteConfig = "com.google.firebase:firebase-config:18.0.0"
    const val crashlytics = "com.crashlytics.sdk.android:crashlytics:2.10.1"
    const val jobDispatcher = "com.firebase:firebase-jobdispatcher:0.8.6"
}

object RetrofitDependencies {
    const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val mock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val rxJava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val protoBuf = "com.squareup.retrofit2:converter-protobuf:2.6.2"
}

object RoomDependencies {
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val rx = "androidx.room:room-rxjava2:${Versions.room}"
    const val ktx = "androidx.room:room-ktx:${Versions.room}"
}


object WearableDependencies {
    const val support = "com.google.android.support:wearable:${Versions.wearable}"
    const val compile = "com.google.android.wearable:wearable:${Versions.wearable}"
}

object TestingDependencies {
    const val junit = "junit:junit:4.12"
    const val mockito = "org.mockito:mockito-core:1.10.19"
}

object PlayServiceDependencies {
    const val core = "com.google.android.play:core:1.6.0"
    const val wearable = "com.google.android.gms:play-services-wearable:17.0.0"
}

object DaggerDependencies {
    const val core = "com.google.dagger:dagger:${Versions.dagger}"
    const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val android = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val androidCompiler =
        "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val javax = "javax.inject:javax.inject:1"
    const val javaAnnotations =  "javax.annotation:javax.annotation-api:1.3.2"
}

object GroupieDependencies {
    const val core = "com.xwray:groupie:${Versions.groupie}"
    const val dataBinding = "com.xwray:groupie-databinding:${Versions.groupie}"
}

object MoshiDependencies {
    const val core = "com.squareup.moshi:moshi-kotlin:1.8.0"
    const val retrofitConvertor = "com.squareup.retrofit2:converter-moshi:2.4.0"
    const val codegen = "com.squareup.moshi:moshi-kotlin-codegen:1.8.0"
}


object RxDependencies {
    const val java = "io.reactivex.rxjava2:rxjava:2.2.10"
    const val kotlin = "io.reactivex.rxjava2:rxkotlin:2.3.0"
    const val android = "io.reactivex.rxjava2:rxandroid:2.1.1"
    const val relay = "com.jakewharton.rxrelay2:rxrelay:2.1.0"
    const val preferences = "MofslArtifacts:rxpreferences:1.0.2"
//    const val preferences = "com.f2prateek.rx.preferences2:rx-preferences:2.0.0"
}

object RxBindingDependencies {
    const val bindingPlatform = "com.jakewharton.rxbinding3:rxbinding:${Versions.rxBinding}"
    const val bindingCore = "com.jakewharton.rxbinding3:rxbinding-core:${Versions.rxBinding}"
    const val bindingSwipeRefresh =
        "com.jakewharton.rxbinding3:rxbinding-swiperefreshlayout:${Versions.rxBinding}"
    const val bindingViewpager =
        "com.jakewharton.rxbinding3:rxbinding-viewpager:${Versions.rxBinding}"
    const val bindingAppcompat =
        "com.jakewharton.rxbinding3:rxbinding-appcompat:${Versions.rxBinding}"
    const val bindingMaterial =
        "com.jakewharton.rxbinding3:rxbinding-material:${Versions.rxBinding}"
}

object OkHttpDependencies {
    const val okio = "com.squareup.okio:okio:${Versions.okIo}"
    const val core = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val logger = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
}

object StethoDependencies {
    const val core = "com.facebook.stetho:stetho:${Versions.stetho}"
    const val okHttp = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"
}

object GsonDependencies {
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
}

object DimensionDependencies {
    const val sdp = "com.intuit.sdp:sdp-android:${Versions.sdp}"
    const val ssp = "com.intuit.ssp:ssp-android:${Versions.sdp}"
}

object GlideDependencies{
  const val glide = "com.github.bumptech.glide:glide:4.10.0"
}

object LeakCanaryDependencies {
    const val debug = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    const val release =
        "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakCanary}"
}


object GamoogaDependencies {
    const val core = "com.gamooga.targetact:gamooga-targetact-client:1.9.3"
    const val pushAmplify = "com.pushamplify.pushamplifylib:pushamplifylib:1.0.4"
    const val pushAmplifyBridge =
        "com.gamooga.pushamplify.bridge:gamooga-pushamplify-bridge:1.0.3"
}


object MofslArtifactDependencies {
    /*const val scanner = "MofslArtifacts:scannerapi:1.0.6"
    const val wear = "MofslArtifacts:wearapi:1.0.5"*/
    /*const val apiCore = "MofslArtifacts:apicore:1.0.3"
    const val loginApi = "MofslArtifacts:loginapi:1.0.11"
    const val validator = "MofslArtifacts:validator:1.1.1"
    const val annotation = "MofslArtifacts:annotations:1.0.2"
    const val moslCommons = "MofslArtifacts:moslcommons:1.1.0-alpha4"
    const val appCore = "MofslArtifacts:appcore:1.1.0-alpha6"
    const val navigator = "MofslArtifacts:navigator:1.0.9"
    const val appCoreUI = "MofslArtifacts:appcoreui:1.1.1-alpha2"
    const val tokenManager = "MofslArtifacts:tokenmanager:1.1.0"
    const val groupie = "MofslArtifacts:groupie_extensions:1.0.8"
    const val tableView = "MofslArtifacts:tableview:1.1.0"
    const val tableViewX = "MofslArtifacts:tableview_extensions:1.1.0"
   *//* const val tradingCore = "MofslArtifacts:tradingcore:1.0.4"
    const val tradingCoreUi = "MofslArtifacts:tradingcoreui:1.0.3-alpha1"*//*
    const val debugViewCore = "MofslArtifacts:debugviewcore:1.0.2"
    const val contactPicker = "MofslArtifacts:contactpicker:1.0.2"
   *//* const val sockets = "TradingArtifacts:sockets:1.2.0-alpha8"
    const val conceal = "MofslArtifacts:conceal:1.0.0"*//*
    const val debugView = "MofslArtifacts:debugview:1.0.7"
    const val protoModels = "MofslArtifacts:protomodels:0.0.1"*/
}

/*
object QrCodeDependencies {
    const val qrCode = "me.dm7.barcodescanner:zxing:${Versions.qrCode}"
}*/
