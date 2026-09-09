import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.jvm.tasks.Jar

plugins {
    val kotlinVersion = "1.9.22"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.13.4"
}

group = "org.iris.wiki"
version = "0.4.6"

repositories {
    mavenLocal()
    maven("https://maven.aliyun.com/repository/public") // 阿里云国内代理仓库
    maven("https://maven.aliyun.com/repository/google")
    maven("https://maven.aliyun.com/repository/jcenter")
    mavenCentral()
}
dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jsoup:jsoup:1.17.2")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.google.code.gson:gson:2.9.1")
    implementation("org.yaml:snakeyaml:2.2")
}

tasks.named<ShadowJar>("shadowJar") {
    onlyIf { true }
    archiveClassifier.set("all")
    configurations = listOf(project.configurations.runtimeClasspath.get())
    mergeServiceFiles()
}

val fatJar by tasks.registering(Jar::class) {
    group = "build"
    description = "Assembles a fat jar archive containing the plugin and all runtime dependencies."
    archiveClassifier.set("all")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get()
            .filter { it.name.endsWith(".jar") }
            .map { zipTree(it) }
    })

    exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA")
}

tasks.named("build") {
    dependsOn(fatJar)
}
