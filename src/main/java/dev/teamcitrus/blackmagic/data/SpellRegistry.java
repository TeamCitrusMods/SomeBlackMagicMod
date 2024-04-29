package dev.teamcitrus.blackmagic.data;

import dev.shadowsoffire.placebo.reload.DynamicRegistry;
import dev.teamcitrus.blackmagic.BlackMagic;
import net.minecraft.resources.ResourceLocation;

public class SpellRegistry extends DynamicRegistry<Spell> {
    public static final SpellRegistry INSTANCE = new SpellRegistry();

    private SpellRegistry() {
        super(BlackMagic.LOGGER, "blackmagic", true, true);
    }

    @Override
    protected void registerBuiltinCodecs() {
        this.registerDefaultCodec(new ResourceLocation(BlackMagic.MODID, "spells"), BasicSpell.CODEC);
    }
}
