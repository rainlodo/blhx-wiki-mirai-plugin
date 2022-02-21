plugins {
    val kotlinVersion = "1.5.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.9.0-M1"
}

group = "org.iris.wiki"
version = "0.3.5"

repositories {
    mavenLocal()
    maven("https://maven.aliyun.com/repository/public") // 阿里云国内代理仓库
    mavenCentral()
}
dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jsoup:jsoup:1.10.3")
    implementation("com.google.code.gson:gson:2.8.5")
}