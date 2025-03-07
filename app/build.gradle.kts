plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "pengyuw007.wagemate"
    compileSdk = 34

    defaultConfig {
        applicationId = "pengyuw007.wagemate"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform() // Tells Gradle to use JUnit 5

    // Configure test logging
    testLogging {
        events("passed", "skipped", "failed")
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    testImplementation(libs.junit.jupiter.v5100)
    testImplementation (libs.junit.platform.suite.api) // Platform API
    testImplementation(libs.jupiter.junit.jupiter.engine)
    testImplementation(libs.jupiter.junit.jupiter.api)
    testRuntimeOnly(libs.platform.junit.platform.launcher) // JUnit Platform Launcher
    testRuntimeOnly (libs.platform.junit.platform.suite.engine)

    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("com.mysql:mysql-connector-j:8.1.0")
}