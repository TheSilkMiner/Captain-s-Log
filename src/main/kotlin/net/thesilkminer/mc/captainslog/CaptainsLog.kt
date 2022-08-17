package net.thesilkminer.mc.captainslog

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.thesilkminer.mc.boson.api.log.L

@Mod(
    modid = CaptainsLog.MOD_ID,
    name = "Captain's Log",
    version = "1.0.0",
    dependencies = "required-after:forge@[14.23.5.2860,);required-after:fermion@[1.0.2,);required-after:forgelin@[1.8.4,);required-after:boson@[0.1.3,)",
    acceptedMinecraftVersions = "1.12.2",
    modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter",
    modLanguage = "kotlin"
)
object CaptainsLog {
    @Suppress("SpellCheckingInspection") const val MOD_ID = "captainslog"
    private val l = L(MOD_ID, "Main")

    @Mod.EventHandler
    fun onPreInit(event: FMLPreInitializationEvent) = Unit
}
