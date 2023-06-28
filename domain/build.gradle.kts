plugins {
    id("kotlin")
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(libs.kotlin.native)
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.android)

}