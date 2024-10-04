plugins {
    alias(libs.plugins.android.library)
    id("io.deepmedia.tools.deployer") version "0.14.0"
    alias(libs.plugins.jetbrains.kotlin.android)
}

deployer {
    project.description = "A simple and highly customizable Jetpack Compose and Android Native RangeBar library."
    projectInfo {
        name = "Koranger"
        description =
            "A simple and highly customizable Jetpack Compose and Android Native RangeBar library."
        url = "https://github.com/muhammadzubair906/Koranger"
        groupId = "com.muhammadzubair.koranger"
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

    githubSpec {
        owner.set("muhammadzubair906")
        repository.set("Koranger")
        auth.user.set(secret("github.signing.username"))
        auth.token.set(secret("github.signing.keyId"))
        release.version = "0.0.1"
        release.description = "Basic Range bar Configured"
        release.tag.set("v0.0.1")

    }
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

