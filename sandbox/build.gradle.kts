import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
}

group = "me.mikun"
version = "unspecified"

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_21)
                }
            }
        }
    }

    jvm {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_21)
                }
            }
        }
    }

//    listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach {
//        it.binaries.framework {
//            baseName = "shared"
//            isStatic = true
//        }
//    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)

            implementation(projects.mylibrary)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        jvmMain.dependencies {

        }
    }

    compilerOptions.freeCompilerArgs.add("-Xexpect-actual-classes")

}

android {
    namespace = "me.mikun.sandbox"
    compileSdk = 36
    defaultConfig {
        applicationId = "me.mikun.sandbox"
        minSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

compose.desktop {
    application {
        mainClass = "me.mikun.sandbox.MainKt"

        nativeDistributions {
            includeAllModules = true
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "sandbox"
            packageVersion = "1.0.0"
        }
    }
}




