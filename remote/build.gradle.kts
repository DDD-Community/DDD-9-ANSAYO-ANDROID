@file:Suppress("DSL_SCOPE_VIOLATION")

import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}

val localProperties = Properties()
localProperties.load(project.rootProject.file("local.properties").inputStream())

android {
    namespace = "com.ddd.ansayo.remote"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        buildConfigField("String", "X_FUNCTION_KEY", localProperties.getProperty("x_function_key"))
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":core-model"))
    implementation(libs.hilt.android.core)
    kapt(libs.hilt.compiler)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.bundles.okhttp)
    implementation(libs.bundles.retrofit)
    implementation(libs.logger) {
        exclude(group = "com.android.support", module = "support-annotations")
    }
    implementation(libs.sandwich)
}
