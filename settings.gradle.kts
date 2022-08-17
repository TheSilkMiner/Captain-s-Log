pluginManagement {
    repositories {
        gradlePluginPortal()
        maven {
            name = "Minecraft Forge Maven"
            url = uri("https://maven.minecraftforge.net")
        }
        maven {
            name = "FancyGradle"
            url = uri("https://gitlab.com/api/v4/projects/26758973/packages/maven")
        }
    }
}

rootProject.name = "CaptainsLog"
