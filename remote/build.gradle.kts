plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.ddd.ansayo.remote"
    compileSdk = libs.versions.compileSdk.get().toInt()
}

dependencies {
    implementation(project(":data"))
    implementation(libs.hilt.android.core)
    kapt(libs.hilt.compiler)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.bundles.okhttp)
    implementation(libs.bundles.retrofit)

}