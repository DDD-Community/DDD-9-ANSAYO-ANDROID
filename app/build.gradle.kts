@file:Suppress("DSL_SCOPE_VIOLATION")
import java.util.Properties
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}

val properties = Properties().apply {
    load(project.rootProject.file("local.properties").inputStream())
}

    android {
        namespace = "com.ddd.ansayo"
        compileSdk = libs.versions.compileSdk.get().toInt()

        defaultConfig {
            applicationId = "com.ddd.ansayo"
            minSdk = libs.versions.minSdk.get().toInt()
            targetSdk = libs.versions.targetSdk.get().toInt()
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            buildConfigField("String", "KAKAO_NATIVE_KEY", properties.getProperty("kakao_native_key"))
            buildConfigField("String", "NAVER_CLIENT_ID", properties.getProperty("naver_client_id"))
            buildConfigField("String", "NAVER_CLIENT_SECRET", properties.getProperty("naver_client_secret"))
            buildConfigField("String", "NAVER_CLIENT_MAPS_ID", properties.getProperty("naver_client_maps_id"))
            manifestPlaceholders["KAKAO_SCHEME"] = properties.getProperty("kakao_scheme")
            manifestPlaceholders["NAVER_MAPS_ID"] = properties.getProperty("naver_client_maps_id")
        }

        configurations {
            implementation.get().exclude(group = "org.jetbrains", module = "annotations")
        }

        buildTypes {
            release {
                isDebuggable = false
                isMinifyEnabled = true
                manifestPlaceholders["KAKAO_SCHEME"] = properties.getProperty("kakao_scheme")

                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }

            debug {
                isDebuggable = true
                isMinifyEnabled = false
                manifestPlaceholders["KAKAO_SCHEME"] = properties.getProperty("kakao_scheme")

            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        kotlinOptions {
            jvmTarget = "1.8"
        }

        buildFeatures {
            viewBinding = true
            dataBinding = true
        }
        // Allow references to generated code
        kapt {
            correctErrorTypes = true
        }

        hilt {
            enableAggregatingTask = true
        }

    }

    dependencies {
        implementation(project(":presentation"))
        implementation(project(":data"))
        implementation(project(":domain"))
        implementation(project(":remote"))
        implementation(project(":local"))
        implementation(project(":core-design"))
        implementation(project(":core-model"))
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.androidx.constraintlayout)
        implementation(libs.androidx.recyclerview)
        implementation(libs.android.google.material)
        implementation(libs.activity.ktx)
        implementation(libs.fragment.ktx)
        testImplementation(libs.junit4)
        androidTestImplementation(libs.androidx.test.ext)
        androidTestImplementation(libs.androidx.test.espresso.core)

        implementation(libs.hilt.android.core)
        kapt(libs.hilt.compiler)
        implementation(libs.logger) {
            exclude(group = "com.android.support", module = "support-annotations")
        }
        implementation(libs.kakao.talk)
        implementation(libs.kakao.user)
        implementation(libs.orbit.viewmodel)
        implementation(libs.imagePicker)
        implementation(libs.tedPermission)
        implementation(libs.glide)
        implementation(libs.naver.oauth)
        implementation(libs.naver.maps)

}