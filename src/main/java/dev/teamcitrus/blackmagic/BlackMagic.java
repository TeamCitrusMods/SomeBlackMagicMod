package dev.teamcitrus.blackmagic;

import dev.shadowsoffire.placebo.tabs.TabFillingRegistry;
import dev.teamcitrus.blackmagic.data.SpellRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(BlackMagic.MODID)
public class BlackMagic {
    public static final String MODID = "blackmagic";
    public static final Logger LOGGER = LogManager.getLogger();

    public BlackMagic(IEventBus modEventBus) {
        modEventBus.addListener(this::setup);
        BMObjects.bootstrap(modEventBus);
    }

    @SubscribeEvent
    public void setup(FMLCommonSetupEvent event) {
        SpellRegistry.INSTANCE.registerToBus();
        event.enqueueWork(() -> {
            TabFillingRegistry.register(BMObjects.TAB_KEY, BMObjects.SPELL_ITEM);
        });
    }
}
