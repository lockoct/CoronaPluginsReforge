import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm")
}

group = "ldgame"
version = "0.0.1"

repositories {
    maven {
        url = uri("https://repo.codemc.io/repository/maven-snapshots/")
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":common"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("net.wesjd:anvilgui:1.5.3-SNAPSHOT")

}

tasks.withType<ShadowJar> {
    archiveBaseName.set("CoronaAuth")
    archiveClassifier.set("")
}
