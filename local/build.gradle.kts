@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.ddd.ansayo.local"
    compileSdk = libs.versions.compileSdk.get().toInt()
}

dependencies {
    implementation(project(":data"))
    implementation(project(":core-model"))
    implementation(libs.hilt.android.core)
    kapt(libs.hilt.compiler)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.bundles.room)
    implementation(libs.androidx.datastore)
    implementation(libs.logger) {
        exclude(group = "com.android.support", module = "support-annotations")
    }
}
