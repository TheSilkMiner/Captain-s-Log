import wtf.gofancy.fancygradle.script.extensions.createDebugLoggingRunConfig
import wtf.gofancy.fancygradle.script.extensions.curse
import wtf.gofancy.fancygradle.script.extensions.curseForge
import wtf.gofancy.fancygradle.script.extensions.deobf

import java.time.format.DateTimeFormatter
import java.time.Instant

plugins {
    idea
    kotlin("jvm") version "1.7.10"
    id("net.minecraftforge.gradle") version "5.1.+"
    id("wtf.gofancy.fancygradle") version "1.1.+"
}

version = "1.0.0"
group = "net.thesilkminer.mc.captainslog"

minecraft {
    mappings("stable", "39-1.12")

    runs {
        createDebugLoggingRunConfig("client")
        createDebugLoggingRunConfig("server") { args("nogui") }
    }
}

fancyGradle {
    patches {
        asm
        coremods
        mergetool
        resources
    }
}

idea {
    module {
        inheritOutputDirs = true
        excludeDirs.addAll(listOf(file("run_client"), file("run_server")))
    }
}

repositories {
    mavenCentral()
    curseForge()
    maven("https://dvs1.progwml6.com/files/maven/") {
        name = "Progwml6"
    }
    maven("https://maven.shadowfacts.net/") {
        name = "ShadowFacts"
    }
    maven("https://modmaven.k-4u.nl") {
        name = "ModMaven"
    }
}

dependencies {
    minecraft(group = "net.minecraftforge", name = "forge", version = "1.12.2-14.23.5.2860")

    implementation(fg.deobf(curse(mod = "fermion-lib", projectId = 345538L, fileId = 3186519L)))
    implementation(fg.deobf(curse(mod = "boson", projectId = 346228L, fileId = 3250759L)))
    implementation(group = "net.shadowfacts", name = "Forgelin", version = "1.8.4") // No need to deobfuscate
    compileOnly(fg.deobf(group = "mezz.jei", name = "jei_1.12.2", version = "4.16.1.302", classifier = "api"))
    runtimeOnly(fg.deobf(group = "mezz.jei", name = "jei_1.12.2", version = "4.16.1.302"))
}

sourceSets.main {
    resources.destinationDirectory.set(java.destinationDirectory)
}

tasks {
    withType<Jar> {
        archiveBaseName.set("captains-log")
        finalizedBy("reobfJar")

        manifest {
            attributes(
                "Specification-Title" to project.name,
                "Specification-Version" to project.version,
                "Specification-Vendor" to "TheSilkMiner",
                "Implementation-Title" to "${project.group}.${project.name.toLowerCase().replace(' ', '_')}",
                "Implementation-Version" to project.version,
                "Implementation-Vendor" to "TheSilkMiner",
                "Implementation-Timestamp" to DateTimeFormatter.ISO_INSTANT.format(Instant.now())
            )
        }
    }

    withType<JavaCompile> {
        sourceCompatibility = JavaVersion.VERSION_1_8.toString()
        targetCompatibility = JavaVersion.VERSION_1_8.toString()
    }

    withType<Wrapper> {
        gradleVersion = "7.5.1"
        distributionType = Wrapper.DistributionType.ALL
    }
}
