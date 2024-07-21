// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.dagger.hilt.plugin.android) apply false
    alias(libs.plugins.navigation.safeargs.kotlin) apply false
    kotlin("kapt") version "1.9.24" apply false
}