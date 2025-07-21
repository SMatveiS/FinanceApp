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

rootProject.name = "My Finance"
include(":app")
include(":core")
include(":core:data")
include(":core:domain")
include(":feature")
include(":feature:account")
include(":feature:analysis")
include(":feature:categories")
include(":feature:change_transaction")
include(":feature:expenses")
include(":feature:incomes")
include(":feature:settings")
include(":feature:transactions_history")
include(":core:ui")
include(":core:model")
