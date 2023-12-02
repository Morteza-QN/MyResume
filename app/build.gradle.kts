@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "me.mqn.myportfolio"
    compileSdk = 34

    defaultConfig {
        applicationId = "me.mqn.myportfolio"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "0.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = JavaVersion.VERSION_17.toString() }
    hilt { enableAggregatingTask = true }
    buildFeatures {
        buildConfig = true
        viewBinding = true
        // prefab = true
        // resValues = true
        // shaders = true
    }
    lint {
        checkDependencies = true
        warningsAsErrors = false
        abortOnError = true
        //        baseline = file("lint-baseline.xml")
    }
    sourceSets {
        findByName("main")?.java?.srcDirs(project.file("src/main/kotlin"))
    }
}

kapt {
    showProcessorStats = true
    correctErrorTypes = true
    includeCompileClasspath = false
    // Increase the max count of errors from annotation processors.
    javacOptions { option("-Xmaxerrs", 500) } // vault is 100.
    arguments { arg("room.schemaLocation", "$projectDir/schemas") }
}

dependencies {
    // import lib jars
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(libs.kotlin.stdlib)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // KTX
    implementation(libs.core.ktx)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    // hilt - dependency injection
    // noinspection GradleDependency
    implementation(libs.hilt.android)
    // noinspection GradleDependency
    kapt(libs.hilt.android.compiler)
    //    kapt("com.google.dagger:hilt-compiler:$hiltVersion")
    //    kapt("androidx.hilt:hilt-compiler:1.0.0")
    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    // Retrofit - networking
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.gson)
    // Okhttp3 - networking
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    testImplementation(libs.mockwebserver)
    // Chucker https logger
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chuckern)
    // Datastore
    implementation(libs.androidx.datastore.preferences)
    // Room database
    val roomVersion = "2.5.2"
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    // noinspection KaptUsageInsteadOfKsp
    kapt(libs.androidx.room.compiler)
    // To use Kotlin Symbol Processing (KSP)
    //    ksp("androidx.room:room-compiler:$roomVersion")
    // region navigation component
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    // Timber - Logger
    implementation(libs.timber)
    // xdp responsible dimensions
    implementation(libs.sdp.android)
    implementation(libs.ssp.android)
    // Coil - image loading
    implementation(libs.coil)
    implementation(libs.coil.gif)
    implementation(libs.coil.svg)
    // Global font set
    implementation(libs.calligraphy3)
    implementation(libs.viewpump)
    // EventBus
    implementation(libs.eventbus)

    /*
    implementation("io.sentry:sentry-android:6.29.0")
    implementation("io.sentry:sentry-android-fragment:6.29.0")
    implementation("io.sentry:sentry-android-navigation:6.29.0")
    implementation("io.sentry:sentry-android-timber:6.29.0")
    implementation("io.sentry:sentry-android-okhttp:6.29.0")
    implementation("io.sentry:sentry-android-sqlite:6.29.0")
    */
}