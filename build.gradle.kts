buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.40.5")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.6.21")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.0")
    }
}

task<Delete>("clean") {
    delete = setOf(rootProject.buildDir)
}
