import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("AppKt")
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation(platform("org.http4k:http4k-bom:4.7.1.0")) {
        implementation("org.http4k:http4k-core")
        implementation("org.http4k:http4k-contract")
        implementation("org.http4k:http4k-format-jackson")
        implementation("org.http4k:http4k-server-jetty")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}
