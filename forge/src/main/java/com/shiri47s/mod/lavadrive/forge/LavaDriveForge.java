package com.shiri47s.mod.lavadrive.forge;

import dev.architectury.platform.forge.EventBuses;
import com.shiri47s.mod.lavadrive.LavaDrive;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@SuppressWarnings("unused")
@Mod(LavaDrive.MOD_ID)
public class LavaDriveForge {
    public LavaDriveForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(LavaDrive.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        LavaDrive.init(new ForgePlatform());
    }
}