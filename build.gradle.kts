plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.7"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.qqviaja.gradle.MybatisGenerator") version "2.5"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.4")
    implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:1.5.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.4")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
mybatisGenerator {
    verbose = true
    configFile = "${projectDir}/src/main/resources/generatorConfig.xml"
    mybatisProperties.putAll(mapOf(
        "driverClass" to "org.mariadb.jdbc.Driver",
        "connectionURL" to "jdbc:mariadb://127.0.0.1:3306/example",
        "username" to "root",
        "password" to "maria",
        //docker run --rm -d -e MARIADB_ROOT_PASSWORD=maria -p 3306:3306 --name mariadb mariadb:lts
        //の --name mariadb がパスワード
    ))
    dependencies {
        mybatisGenerator("org.mybatis.generator:mybatis-generator-core:1.4.2")
        mybatisGenerator("org.mariadb.jdbc:mariadb-java-client:3.5.3")
    }
}
