plugins {
    alias(libs.plugins.androidDynamicFeature)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "gyanani.harish.facedetector"
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
    implementation(fileTree(mapOf(
        "dir" to "libs",
        "include" to listOf("*.aar", "*.jar"),
    )))
    //implementation(files("$projectDir/libs/mylibraryml-release.aar"))
    //implementation(name = "mylibraryml-release", ext = "aar")
    //implementation(modules(":dynamicfeatureml"))

    //implementation(project(":app"))
    implementation(libs.play.services.mlkit.face.detection) {
        exclude(group = "androidx.exifinterface", module = "exifinterface")
        exclude(group = "com.google.mlkit", module = "vision-common")
        exclude(group = "com.google.mlkit", module = "vision-interfaces")
        exclude(group = "com.google.android.odml", module = "image")
    }
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    //implementation(project(":mymllib"))
    //implementation("mylibraryml-release:version@aar")
    implementation(project(":app"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}