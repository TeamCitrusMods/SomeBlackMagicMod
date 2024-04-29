package dev.teamcitrus.blackmagic.data;

import com.mojang.serialization.Codec;
import dev.shadowsoffire.placebo.codec.CodecProvider;
import dev.shadowsoffire.placebo.codec.PlaceboCodecs;

public interface Spell extends CodecProvider<Spell> {
    SpellType spellType();

    public static enum SpellType {
        SELF;

        public static final Codec<SpellType> CODEC = PlaceboCodecs.enumCodec(SpellType.class);
    }
}
