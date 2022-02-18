import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "ldgame"
version = "0.0.1"

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":common"))
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<ShadowJar> {
    archiveBaseName.set("CoronaAuth")
    archiveClassifier.set("")
}