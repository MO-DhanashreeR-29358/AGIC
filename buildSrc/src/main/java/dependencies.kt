
object Versions1{
  const val compileSdkVersion= 29
  const val targetSdkVersion= 28
  const val minSdkVersion= 19
  const val buildToolsVersion= "29.0.2"

  const val androidVersion="3.5.1"
  const val kotlinVersion="1.3.50"
  const val fabric="1.30.0"
  const val googleServices="4.3.0"

  const val dexCountGradlePlugin="0.8.6"
  const val errorProne="0.0.13"
  const val okHttp= "3.12.3"
  const val okIo= "1.17.4"
  const val timber= "4.7.1"
  const val groupie= "2.3.0"
  const val retrofit= "2.6.0"
  const val dagger= "2.23.1"
  const val leakCanary= "1.6.3"
  const val rxBinding= "3.0.0-alpha2"
  const val room= "2.1.0"
  const val stetho= "1.5.1"
  const val wearable= "2.3.0"
  const val androidDataBinding= "3.4.2"
}

object Libs{
  const val androidBuildDeps="com.android.tools.build:gradle:${Versions.androidVersion}"
  const val googleServices="com.google.gms:google-services:${Versions.googleServices}"
  const val dexCountGradlePlugin="com.getkeepsafe.dexcount:dexcount-gradle-plugin:${Versions.dexCountGradlePlugin}"
  const val kotlinGradlePlugin="org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
  const val errorPronePlugin="net.ltgt.gradle:gradle-errorprone-plugin:${Versions.errorProne}"
  const val fabricPlugin="io.fabric.tools:gradle:${Versions.fabric}"

  const val androidApplication = "com.android.application"
  const val kotlinAndroid = "kotlin-android"
  const val kotlinAndroidExtensions = "kotlin-android-extensions"

  const val androidxAnnotation="androidx.annotation:annotation:1.1.0"
  const val androidxMaterial="com.google.android.material:material:1.1.0-beta01"
  const val androidxAppcompat="androidx.appcompat:appcompat:1.1.0-rc01"
  const val androidxConstraintlayout="androidx.constraintlayout:constraintlayout:1.1.3"
  const val androidxRecyclerview="androidx.recyclerview:recyclerview:1.1.0-beta01"
  const val androidxFragment="androidx.fragment:fragment:1.1.0-rc01"
  const val androidxCardview="androidx.cardview:cardview:1.0.0"
  const val androidxBrowser="androidx.browser:browser:1.0.0"
  const val androidxMultidex="androidx.multidex:multidex:2.0.1"
  const val androidxPreference="androidx.preference:preference:1.1.0-rc01"
  const val androidxLegacy="androidx.legacy:legacy-support-core-ui:1.0.0"
  const val androidxTestRunner="androidx.test:runner:1.2.0"
  const val androidxTestEspresso="androidx.test.espresso:espresso-core:3.2.0"
  const val androidxTestJunit="androidx.test.ext:junit:1.1.1"

  const val lifecycleCompiler="androidx.lifecycle:lifecycle-compiler:2.1.0-rc01"
  const val lifecycleExtension="androidx.lifecycle:lifecycle-extensions:2.1.0-rc01"

  const val ktxCore="androidx.core:core-ktx:1.1.0-rc01"
  const val ktxFragment="androidx.fragment:fragment-ktx:1.1.0-rc0"

  const val roomRuntime="androidx.room:room-runtime:${Versions.room}"
  const val roomCompiler="androidx.room:room-compiler:${Versions.room}"
  const val roomRx="androidx.room:room-rxjava2:${Versions.room}"

  const val wearable="com.google.android.support:wearable:${Versions.wearable}"
  const val wearableCompile="com.google.android.wearable:wearable:${Versions.wearable}"

  const val testingJunit="junit:junit:4.12"
  const val testingMockito="org.mockito:mockito-core:1.10.19"

  const val googlePlay="com.google.android.play:core:1.6.0"
  const val playServiceWearable="com.google.android.gms:play-services-wearable:17.0.0"

  const val firebaseCore="com.google.firebase:firebase-core:17.0.0"
  const val firebaseMessaging="com.google.firebase:firebase-messaging:19.0.1"
  const val firebasePerf="com.google.firebase:firebase-perf:18.0.1"
  const val firebaseRemoteConfig="com.google.firebase:firebase-config:18.0.0"
  const val firebaseCrashlytics="com.crashlytics.sdk.android:crashlytics:2.9.9"
  const val firebaseJobDispatcher="com.firebase:firebase-jobdispatcher:0.8.6"

  const val daggerCore="com.google.dagger:dagger:${Versions.dagger}"
  const val daggerCompiler="com.google.dagger:dagger-compiler:${Versions.dagger}"
  const val daggerAndroid="com.google.dagger:dagger-android-support:${Versions.dagger}"
  const val daggerAndroidCompiler="com.google.dagger:dagger-android-processor:${Versions.dagger}"

  const val timber="com.jakewharton.timber:timber:${Versions.timber}"
  const val groupieCore="com.xwray:groupie:${Versions.groupie}"
  const val groupieDataBinding="com.xwray:groupie-databinding:${Versions.groupie}"

  const val moshi="com.squareup.moshi:moshi-kotlin:1.8.0"
  const val moshiRetrofitConvertor="com.squareup.retrofit2:converter-moshi:2.4.0"
  const val moshiCodegen="com.squareup.moshi:moshi-kotlin-codegen:1.8.0"

  const val retrofitCore="com.squareup.retrofit2:retrofit:${Versions.retrofit}"
  const val retrofitMock="com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
  const val retrofitGson="com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
  const val retrofitRxJava="com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"

  const val rxJava="io.reactivex.rxjava2:rxjava:2.2.10"
  const val rxKotlin="io.reactivex.rxjava2:rxkotlin:2.3.0"
  const val rxAndroid="io.reactivex.rxjava2:rxandroid:2.1.1"
  const val rxRelay="com.jakewharton.rxrelay2:rxrelay:2.1.0"
  const val rxPreferences="com.f2prateek.rx.preferences2:rx-preferences:2.0.0"

  const val rxBindingPlatform="com.jakewharton.rxbinding3:rxbinding:${Versions.rxBinding}"
  const val rxBindingCore="com.jakewharton.rxbinding3:rxbinding-core:${Versions.rxBinding}"
  const val rxBindingSwiperefresh="com.jakewharton.rxbinding3:rxbinding-swiperefreshlayout:${Versions.rxBinding}"
  const val rxBindingViewpager="com.jakewharton.rxbinding3:rxbinding-viewpager:${Versions.rxBinding}"
  const val rxBindingAppcompat="com.jakewharton.rxbinding3:rxbinding-appcompat:${Versions.rxBinding}"

  const val okhttpOkio="com.squareup.okio:okio:${Versions.okIo}"
  const val okhttpCore="com.squareup.okhttp3:okhttp:${Versions.okHttp}"
  const val okhttpLogger="com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

  const val stethoCore="com.facebook.stetho:stetho:${Versions.stetho}"
  const val stethoOkHttp="com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"

  const val leakCanarydebug="com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
  const val leakCanaryrelease="com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakCanary}"

  const val brotli="org.brotli:dec:0.1.2"
  const val kotlin="org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
  const val mpChart="com.github.PhilJay:MPAndroidChart:v3.1.0"
  const val threeTen="com.jakewharton.threetenabp:threetenabp:1.2.1"
  const val appintro="com.github.AppIntro:AppIntro:5.1.0"
  const val processPhoenix="com.jakewharton:process-phoenix:2.0.0"
  const val dataBinding="androidx.databinding:databinding-compiler:${Versions.androidDataBinding}"

  const val gamoogaCore="com.gamooga.targetact:gamooga-targetact-client:1.9.3"
  const val gamoogapushAmplify="com.pushamplify.pushamplifylib:pushamplifylib:1.0.4"
  const val gamoogapushAmplifyBridge="com.gamooga.pushamplify.bridge:gamooga-pushamplify-bridge:1.0.3"

  const val javax="javax.inject:javax.inject:1"

  const val mofslArtifactsScanner="MofslArtifacts:scannerapi:1.0.6"
  const val mofslArtifactsWear="MofslArtifacts:wearapi:1.0.5"
  const val mofslArtifactsApiCore="MofslArtifacts:apicore:1.0.3"
  const val mofslArtifactsLoginApi="MofslArtifacts:loginapi:1.0.11"
  const val mofslArtifactsValidator="MofslArtifacts:validator:1.1.1"
  const val mofslArtifactsAnnotation="MofslArtifacts:annotations:1.0.2"
  const val mofslArtifactsMoslCommons="MofslArtifacts:moslcommons:1.1.0-alpha4"
  const val mofslArtifactsAppCore="MofslArtifacts:appcore:1.1.0-alpha6"
  const val mofslArtifactsNavigator="MofslArtifacts:navigator:1.0.7"
  const val mofslArtifactsAppCoreUI="MofslArtifacts:appcoreui:1.1.0-alpha3"
  const val mofslArtifactsTokenManager="MofslArtifacts:tokenmanager:1.1.0"
  const val mofslArtifactsGroupie="MofslArtifacts:groupie_extensions:1.0.8"
  const val mofslArtifactsTableView="MofslArtifacts:tableview:1.1.0"
  const val mofslArtifactsTableViewX="MofslArtifacts:tableview_extensions:1.1.0"
  const val mofslArtifactsTradingCore="MofslArtifacts:tradingcore:1.0.4"
  const val mofslArtifactsTradingCoreui="MofslArtifacts:tradingcoreui:1.0.3"
  const val mofslArtifactsDebugViewCore="MofslArtifacts:debugviewcore:1.0.2"
  const val mofslArtifactsContactPicker="MofslArtifacts:contactpicker:1.0.2"
  const val mofslArtifactsSockets="TradingArtifacts:sockets:1.2.0-alpha8"
  const val mofslArtifactsConceal="MofslArtifacts:conceal:1.0.0"
  const val mofslArtifactsDebugView="MofslArtifacts:debugview:1.0.6"
}