import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.utils.extendsFrom

plugins {
    java
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.lombok") version "2.0.0"
    id("io.freefair.lombok") version "8.6"
    `maven-publish`
    id("org.graalvm.buildtools.native") version "0.10.2"
}

graalvmNative {
    toolchainDetection.set(true)
}


repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
    mavenCentral()
}

val graal by sourceSets.creating


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.0.0")
    implementation("com.alibaba:fastjson:1.2.69_sec11")

    testImplementation("junit:junit:4.13.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:2.0.0")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
    testCompileOnly("org.projectlombok:lombok:1.18.34")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.34")

    "graalCompileOnly"("org.graalvm.nativeimage:svm:24.0.2")
    "graalCompileOnly"("org.graalvm.sdk:graal-sdk:24.0.2")
    nativeImageCompileOnly(graal.output.classesDirs)

}

configurations {
    getByName("graalImplementation").extendsFrom
}

graalvmNative {
    binaries {
        named("main") {
            imageName.set("almanac")
            mainClass.set("cn.huangdayu.almanac.AlmanacNative")
            buildArgs.add("-O4")
            buildArgs.add("--shared")
        }
        named("test") {
            buildArgs.add("-O0")
        }
    }
    binaries.all {
        buildArgs.add("--verbose")
    }
}


group = "cn.huangdayu"
version = "20211128"
description = "almanac"

java {
    withSourcesJar()
//    withJavadocJar()
}


val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "22"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "22"
}

tasks.withType<JavaExec> {
    jvmArgs = listOf("-Dsun.stdout.encoding=UTF-8", "-Dsun.stderr.encoding=UTF-8")
}


publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}


buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("io.freefair.gradle:lombok-plugin:8.6")
    }
}
