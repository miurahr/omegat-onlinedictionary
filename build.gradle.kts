plugins {
    groovy
    java
    checkstyle
    jacoco
    distribution
    id("com.diffplug.spotless") version "5.17.0"
    id("com.github.kt3k.coveralls") version "2.12.0"
    id("org.omegat.gradle") version "1.5.3"
    id("com.github.nbaztec.coveralls-jacoco") version "1.2.13"
    id("com.palantir.git-version") version "0.12.3"
}

// calculate version string from git tag, hash and commit distance
fun getVersionDetails(): com.palantir.gradle.gitversion.VersionDetails = (extra["versionDetails"] as groovy.lang.Closure<*>)() as com.palantir.gradle.gitversion.VersionDetails
if (getVersionDetails().isCleanTag) {
    version = getVersionDetails().lastTag.substring(1)
} else {
    version = getVersionDetails().lastTag.substring(1) + "-" + getVersionDetails().commitDistance + "-" + getVersionDetails().gitHash + "-SNAPSHOT"
}

group = "tokyo.northside"
configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

omegat {
    version = "5.5.0"
    pluginClass = "tokyo.northside.omegat.onlinedictionary.OnlineDictionaryPlugin"
    // projectDir = File(project.projectDir, "test-project").toString()
}

dependencies {
    packIntoJar("org.slf4j:slf4j-api:1.7.25")
    packIntoJar("org.apache.httpcomponents.client5:httpclient5:5.1.1")
    packIntoJar("com.fasterxml.jackson.core:jackson-core:2.13.0")
    packIntoJar("com.fasterxml.jackson.core:jackson-databind:2.13.0")
    packIntoJar("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.13.0")
    implementation("commons-io:commons-io:2.7")
    implementation("commons-lang:commons-lang:2.6")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("commons-io:commons-io:2.7")
    testImplementation("org.codehaus.groovy:groovy-all:3.0.9")
}

tasks {
    // Enable xml for coveralls.
    "jacocoTestReport"(JacocoReport::class) {
        reports {
            // To be read by humans
            html.isEnabled = true
            // To be read by Coveralls etc.
            xml.isEnabled = true
            xml.destination = file("$buildDir/reports/jacoco/test/jacocoTestReport.xml")
        }

    }

    // Trying to run tests every time.
    val test by tasks
    val cleanTest by tasks
    test.dependsOn(cleanTest)

    // Use the built-in JUnit support of Gradle.
    "test"(Test::class) {
        useJUnitPlatform()
    }

    // Sorry, I have no idea.
    Unit
}

distributions {
    main {
        contents {
            from(tasks["jar"], "README.md", "CHANGELOG.md", "COPYING")
        }
    }
}
