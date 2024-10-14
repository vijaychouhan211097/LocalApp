plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.localapp"
    compileSdk = 34

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.example.localapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.legacy.support.v4)
    implementation(libs.litert.support.api)
    implementation(libs.play.services.analytics.impl)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    /* for loading images from internet */
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.13.0")

    /* For Search Bar -  */
    implementation ("com.makeramen:roundedimageview:2.3.0")
    implementation ("com.github.mancj:MaterialSearchBar:0.8.5")

    /* For Slider - start */
    // Material Components for Android. Replace the version with the latest version of Material Components library.
    implementation ("com.google.android.material:material:1.5.0")
    // Circle Indicator (To fix the xml preview "Missing classes" error)
    implementation ("me.relex:circleindicator:2.1.6")
    implementation ("org.imaginativeworld.whynotimagecarousel:whynotimagecarousel:2.1.0")
    /* For Slider - end */

}