apply {
    from("$rootDir/feature-module.gradle")
}

dependencies {
    "implementation"(project(DataCenter.cat))
}