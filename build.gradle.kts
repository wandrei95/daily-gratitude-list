buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.43.2")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.7.10")
    }
}

task<Delete>("clean") {
    delete = setOf(rootProject.buildDir)
}
