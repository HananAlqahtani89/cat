apply {
    from("$rootDir/feature-module.gradle")
}

dependencies {
    "implementation"(project(DataCenter.cat))
    "implementation"(project(Modules.coreUi))
}