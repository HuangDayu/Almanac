import org.jetbrains.kotlin.gradle.dsl.JvmTarget
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

group = "cn.huangdayu"
version = "20211128"
description = "almanac"

graalvmNative {
    toolchainDetection.set(true)
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

java {
    charset("UTF-8")
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
    withSourcesJar()
    // withJavadocJar()
}

tasks.withType<JavaExec> {
    defaultCharacterEncoding = "UTF-8"
    jvmArgs = listOf(
        "-Dfile.encoding=UTF-8", "-Dsun.stdout.encoding=UTF-8", "-Dsun.stderr.encoding=UTF-8"
    )
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_22)
    }
}


repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
    mavenCentral()
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

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}