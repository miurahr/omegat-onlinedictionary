plugins {
    groovy
    java
    checkstyle
    jacoco
    distribution
    id("com.diffplug.gradle.spotless") version "3.27.1"
    id("com.github.kt3k.coveralls") version "2.10.2"
    id("org.omegat.gradle") version "1.4.2"
    id("com.github.nbaztec.coveralls-jacoco") version "1.2.5"
    id("com.sarhanm.versioner") version "4.0.2"
}

group = "tokyo.northside"
versioner {
    snapshot=false
    omitBranchMetadata=true
    disableHotfixVersioning=true
}

omegat {
    version = "5.4.1"
    pluginClass = "tokyo.northside.omegat.onlinedictionary.OnlineDictionaryPlugin"
    // projectDir = File(project.projectDir, "test-project").toString()
}

repositories {
    jcenter()
}

dependencies {
    packIntoJar("org.slf4j:slf4j-api:1.7.25")
    packIntoJar("org.apache.httpcomponents.client5:httpclient5:5.0.3")
    packIntoJar("com.fasterxml.jackson.core:jackson-core:2.12.0")
    packIntoJar("com.fasterxml.jackson.core:jackson-databind:2.12.0")
    packIntoJar("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.12.0")
    implementation("commons-io:commons-io:2.5")
    implementation("commons-lang:commons-lang:2.6")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.0.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.0.0")
    testImplementation("commons-io:commons-io:2.5")
    testImplementation("org.codehaus.groovy:groovy-all:3.0.1")
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

tasks.distTar {
  compression = Compression.GZIP
}

distributions {
    main {
        contents {
            from(tasks["jar"], "README.md", "CHANGELOG.md", "COPYING")
        }
    }
}
