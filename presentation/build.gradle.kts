@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.ddd.ansayo.presentation"
    compileSdk = libs.versions.compileSdk.get().toInt()
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core-model"))

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)

    implementation(libs.hilt.android.core)
    kapt(libs.hilt.compiler)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.logger) {
        exclude(group = "com.android.support", module = "support-annotations")
    }
    implementation(libs.orbit.viewmodel)
}
