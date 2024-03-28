package com.shiri47s.mod.lavadrive.fabric;

import com.shiri47s.mod.lavadrive.LavaDrive;
import net.fabricmc.api.ModInitializer;

public class LavaDriveFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        LavaDrive.init(new FabricPlatform());
    }
}