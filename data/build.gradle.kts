@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.ddd.ansayo.data"
    compileSdk = libs.versions.compileSdk.get().toInt()
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core-model"))
    implementation(libs.hilt.android.core)
    kapt(libs.hilt.compiler)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.bundles.okhttp)
    implementation(libs.bundles.retrofit)
    implementation(libs.androidx.datastore)
    implementation(libs.logger) {
        exclude(group = "com.android.support", module = "support-annotations")
    }
    implementation(libs.gson)
}
