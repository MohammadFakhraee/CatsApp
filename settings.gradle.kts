pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core")
include(":data:local")
include(":data:remote")
include(":data:repository_impl")
include(":domain:models")
include(":domain:repository")
include(":domain:use_case")
include(":feature:core_ui")
include(":feature:cats_list")
include(":feature:cat_details")
