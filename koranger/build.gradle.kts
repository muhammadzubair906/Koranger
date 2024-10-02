plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
    id("signing")
}

android {
    signingConfigs {
        create("release") {
            storeFile = file(project.findProperty("storeFile") as String? ?: "")
            storePassword = project.findProperty("storePassword") as String? ?: ""
            keyAlias = project.findProperty("storeAlias") as String? ?: ""
            keyPassword = project.findProperty("storeAliasPassword") as String? ?: ""
        }
    }
    namespace = "com.muhammadzubair.koranger"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
}


publishing {
    publications {
        create<MavenPublication>("release") {
            from(components.findByName("release"))

            groupId = "com.muhammadzubair.koranger"
            artifactId = "koranger"
            version = "0.0.1"
            description = "A simple and highly customizable Jetpack Compose and Android Native RangeBar library."


            pom {
                name.set("Koranger")
                description.set("A highly customizable Jetpack Compose RangeBar library.")
                url.set("https://github.com/muhammadzubair906/Koranger")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("muhammadzubair906")
                        name.set("Muhammad Zubair")
                        email.set("info@muhammadzubair.com")
                    }
                }
                scm {
                    connection.set("scm:git:github.com/muhammadzubair906/Koranger.git")
                    developerConnection.set("scm:git:ssh://github.com/muhammadzubair906/Koranger.git")
                    url.set("https://github.com/muhammadzubair906/Koranger")
                }
            }




        }
    }

    repositories {
        maven {
            name = "MavenCentral"
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")

            credentials {  // Credentials for authentication
                username = project.findProperty("ossrhUsername") as String? ?: ""
                password = project.findProperty("ossrhPassword") as String? ?: ""
            }
        }
    }

    signing {
        useGpgCmd()
        sign(publishing.publications["release"])
    }
}

