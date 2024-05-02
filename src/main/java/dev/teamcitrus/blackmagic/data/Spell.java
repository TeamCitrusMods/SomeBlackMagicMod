package dev.teamcitrus.blackmagic.data;

import com.mojang.serialization.Codec;
import dev.shadowsoffire.placebo.codec.CodecProvider;
import dev.shadowsoffire.placebo.codec.PlaceboCodecs;

public interface Spell extends CodecProvider<Spell> {
    SpellType spellType();

    School school();

    int maxLevel();

    enum SpellType {
        SELF, PROJECTILE;

        public static final Codec<SpellType> CODEC = PlaceboCodecs.enumCodec(SpellType.class);
    }

    enum School {
        FIRE(0xFF0000), WATER(0x0000FF), EARTH(0x964B00), AIR(0xFFFF00),
        DIVINE(0xFDFD96), SUMMONING(0xA020F0);

        private final int color;

        School(int color) {
            this.color = color;
        }

        public static final Codec<School> CODEC = PlaceboCodecs.enumCodec(School.class);

        public int getColor() {
            return color;
        }
    }
}
