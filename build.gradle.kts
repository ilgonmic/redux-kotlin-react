plugins {
    kotlin("js") version "1.4.255-SNAPSHOT"
}
group = "me.user"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}
dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.2")
    implementation("org.jetbrains:kotlin-react:16.13.1-pre.124-kotlin-1.4.10")
    implementation("org.jetbrains:kotlin-react-dom:16.13.1-pre.124-kotlin-1.4.10")
    implementation("org.reduxkotlin:redux-kotlin:0.5.5")

    testImplementation(kotlin("test-js"))
}
kotlin {
    js {
        browser {
            binaries.executable()
        }
    }
}