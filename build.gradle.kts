import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
}

group = "ldgame"
version = "0.0.1"

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        maven {
            url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")

            // As of Gradle 5.1, you can limit this to only those
            // dependencies you expect from it
            content {
                includeGroup("org.bukkit")
                includeGroup("org.spigotmc")
            }
        }

        mavenCentral()
    }

    dependencies {
        testImplementation(kotlin("test"))
        implementation(kotlin("stdlib-jdk8"))
        compileOnly("org.spigotmc:spigot-api:1.17-R0.1-SNAPSHOT")
        testImplementation("com.github.seeseemelk:MockBukkit-v1.17:1.7.0")
        testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")
        implementation("com.github.ajalt.clikt:clikt:3.2.0")
    }

    tasks.test {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
    val compileKotlin: KotlinCompile by tasks
    compileKotlin.kotlinOptions {
        jvmTarget = "1.8"
    }
    val compileTestKotlin: KotlinCompile by tasks
    compileTestKotlin.kotlinOptions {
        jvmTarget = "1.8"
    }
}
