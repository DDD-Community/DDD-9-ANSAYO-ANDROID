pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://devrepo.kakao.com/nexus/content/groups/public/")

    }
}
rootProject.name = "DDD-9-ANSAYO-ANDROID"
include(":app")
include(":presentation")
include(":data")
include(":remote")
include(":local")
include(":domain")
