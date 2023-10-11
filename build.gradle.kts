buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    Plugins.apply {
        id(androidApplication) version buildToolsVersion apply false
        id(androidLibrary) version buildToolsVersion apply false
        id(kotlinGradle) version kotinGradleVersion apply false
        id(daggerHilt) version daggerHiltVersion apply false
        id(dependencyChecker) version dependencyCheckerVersion apply false
        id(ksp) version kspVersion apply false
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}