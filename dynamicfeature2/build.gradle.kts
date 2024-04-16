plugins {
    alias(libs.plugins.androidDynamicFeature)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}
android {
    namespace = "gyanani.harish.dynamicfeature2"
    compileSdk = 34
    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    //implementation(project(":mymllib"))
    implementation(project(":app"))
    //implementation(project(":app"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.play.services.mlkit.barcode.scanning) {
        exclude(group = "androidx.exifinterface", module = "exifinterface")
        exclude(group = "com.google.mlkit", module = "vision-common")
        exclude(group = "com.google.mlkit", module = "vision-interfaces")
        exclude(group = "com.google.android.odml", module = "image")
    }
}