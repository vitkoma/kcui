plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.10"
    id("org.jetbrains.kotlin.kapt") version "1.6.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.10"
    id("groovy")
    id("com.github.johnrengelman.shadow") version "7.1.1"
    id("io.micronaut.application") version "3.2.0"
    id("com.google.cloud.tools.jib") version "2.8.0"
    id("com.github.node-gradle.node") version "3.2.1"
}

version = "0.1.0-4-SNAPSHOT"
group = "cloud.koma.kcui"

val kotlinVersion = project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

dependencies {
    kapt("io.micronaut:micronaut-http-validation")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-validation")
    implementation("io.projectreactor:reactor-core")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

}


application {
    mainClass.set("cloud.koma.kcui.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("17")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
    jib {
        to {
            image = "vkoma/kcui"
        }
    }
    processResources {
        dependsOn("copyVueResources")
    }
    dockerBuild {
        images.set(listOf("vkoma/kcui:${project.version}"))
    }
    dockerBuildNative {
        images.set(listOf("vkoma/kcui:${project.version}-native"))
    }
}
graalvmNative.toolchainDetection.set(false)
micronaut {
    runtime("netty")
    testRuntime("spock2")
    processing {
        incremental(true)
        annotations("cloud.koma.kcui.*")
    }
}

tasks.register<com.github.gradle.node.npm.task.NpmTask>("buildVue") {
    workingDir.set(file("src/main/vue"))
    args.set(listOf("run", "build"))
}

tasks.register<Copy>("copyVueResources") {
    from("src/main/vue/dist")
    into("${buildDir}/resources/main/public")
    dependsOn("buildVue")
}
