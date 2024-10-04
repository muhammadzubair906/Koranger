plugins {
    alias(libs.plugins.android.library)
    id("io.deepmedia.tools.deployer") version "0.14.0"
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
    id("signing")
}

deployer {
    project.description =
        "A simple and highly customizable Jetpack Compose and Android Native RangeBar library."
    projectInfo {
        name = "Koranger"
        description =
            "A simple and highly customizable Jetpack Compose and Android Native RangeBar library."
        url = "https://github.com/muhammadzubair906/Koranger"
        groupId = "com.muhammadzubair"
        artifactId = "koranger"
        license(apache2)
        license(MIT)
        developer("Muhammad Zubair", "info@muhammadzubair.com")
    }

    centralPortalSpec {
        auth.user.set(secret("ossrhUsername"))
        auth.password.set(secret("ossrhPassword"))

        // Signing is required
        signing.key.set(secret("signing.keyId"))
        signing.password.set(secret("signing.password"))
    }
}

android {
    version = "0.0.1"
    group = "com.muhammadzubair"
    namespace = "com.muhammadzubair.koranger"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        aarMetadata {

            version = "0.0.1"
            group = "com.muhammadzubair"
            namespace = "com.muhammadzubair.koranger"
            compileSdk = 34
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    publishing {
        singleVariant("koranger") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("koranger") {
            artifactId = "koranger"
            version = "0.0.1"
            groupId = "com.muhammadzubair"

            pom {
                name = "Koranger"
                description =
                    "A simple yet powerful rangebar for Jetpack Compose and Android Native."
                url = "https://github.com/muhammadzubair906/Koranger"
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }

                    license {
                        name = "MIT License"
                        url = "https://opensource.org/licenses/MIT"
                    }
                }
                developers {
                    developer {
                        id = "muhammadzubair906"
                        name = "Muhammad Zubair"
                        email = "info@muhammadzubair.com"
                    }
                }
                scm {
                    connection = "scm:git:git://muhammadzubair906/Koranger.git"
                    developerConnection = "scm:git:ssh://com:muhammadzubair906/Koranger.git"
                    url = "https://github.com/muhammadzubair906/Koranger"
                }
            }

            repositories {
                maven {
                    name = "GitHubPackages"
                    url = uri("https://maven.pkg.github.com/muhammadzubair906/Koranger")
                    credentials {
                        username = project.findProperty("GITHUB_USERNAME") as String? ?: ""
                        password = project.findProperty("GITHUB_TOKEN") as String? ?: ""
                    }
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["koranger"])
}


dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
}

