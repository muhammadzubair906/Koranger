plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
    id("signing")
    id("org.jetbrains.dokka") version "1.9.20"
}

lateinit var sourcesArtifact: PublishArtifact
lateinit var javadocArtifact: PublishArtifact


tasks {
    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(android.sourceSets["main"].java.srcDirs)
    }
    val dokkaHtml by getting(org.jetbrains.dokka.gradle.DokkaTask::class)
    val javadocJar by creating(Jar::class) {
        dependsOn(dokkaHtml)
        archiveClassifier.set("javadoc")
        from(dokkaHtml.outputDirectory)
    }
    artifacts {
        sourcesArtifact = archives(sourcesJar)
        javadocArtifact = archives(javadocJar)
    }
}

android {
    version = "0.0.1c"
    group = "com.muhammadzubair"
    namespace = "com.muhammadzubair.koranger"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        aarMetadata {
            version = "0.0.1c"
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    publishing {
        singleVariant("Koranger") {
            withSourcesJar()
            withJavadocJar()
        }
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
        register<MavenPublication>("Koranger") {
            artifactId = "koranger"
            version = "0.0.1c"
            groupId = "com.muhammadzubair"
            artifact(sourcesArtifact)
            artifact(javadocArtifact)

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

signing{
    sign(publishing.publications["Koranger"])
}
