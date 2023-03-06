plugins {
    kotlin("jvm") version "1.6.21"
    application
    id("com.apollographql.apollo3").version("3.7.3")
    id("org.openjfx.javafxplugin").version("0.0.9")
}

group = "org.example"
version = "1.0-SNAPSHOT"
application {
    mainModule.set("graphqlKotlin")
    mainClass.set("org.example.MainFX")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.apollographql.apollo3:apollo-runtime:3.7.3")

    // optional: if you want to use the normalized cache
    implementation("com.apollographql.apollo3:apollo-normalized-cache-sqlite:3.7.3")
    //lombok
    implementation("org.projectlombok:lombok:1.18.22")
//    implementation("com.apollographql.apollo3:apollo-coroutines-support:3.7.3")
    // optional: if you just want the generated models and parsers and write your own HTTP code/cache code, you can remove apollo-runtime
    // and use apollo-api instead
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-javafx
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-javafx:1.6.4")

    implementation("org.openjfx:javafx-base:17")
    implementation("org.openjfx:javafx-fxml:17")

    implementation("com.apollographql.apollo3:apollo-api:3.7.3")
    testImplementation(kotlin("test"))

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

javafx {
    version = "17.0.1"
    modules = listOf("javafx.controls","javafx.fxml")
}

apollo {
    service("localhost") {
        sourceFolder.set("org/example/localhost")
        packageName.set("org.example.localhost")
    }
    // instruct the compiler to generate Kotlin models
    generateKotlinModels.set(true)
    generateModelBuilder.set(true)
}