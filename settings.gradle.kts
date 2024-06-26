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
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "EconoBazaar"
include(":app")
include(":core:network")
include(":home:domain")
include(":home:data")
include(":home:presentation")
include(":categories:domain")
include(":categories:data")
include(":categories:presentation")
include(":search:domain")
include(":search:data")
include(":search:presentation")
include(":favorite:domain")
include(":favorite:data")
include(":favorite:presentation")
include(":cart:domain")
include(":cart:data")
include(":cart:presentation")
include(":profile:data")
include(":profile:domain")
include(":profile:presentation")
include(":onboarding")
include(":onboarding:data")
include(":onboarding:domain")
include(":onboarding:presentation")
include(":detail:domain")
include(":detail:data")
include(":detail:presentation")
include(":core:common")
include(":login:presentation")
include(":login:domain")
include(":login:data")
include(":core:local")
