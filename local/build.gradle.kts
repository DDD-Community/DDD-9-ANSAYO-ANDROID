plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.ddd.ansayo.local"
    compileSdk = libs.versions.compileSdk.get().toInt()
}

dependencies {
    implementation(project(":data"))
}
