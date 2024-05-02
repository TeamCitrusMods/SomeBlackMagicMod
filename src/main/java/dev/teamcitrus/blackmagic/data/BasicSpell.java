package dev.teamcitrus.blackmagic.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record BasicSpell(SpellType spellType, School school, int maxLevel) implements Spell {
    public static Codec<BasicSpell> CODEC = RecordCodecBuilder.create(inst -> inst
            .group(
                    SpellType.CODEC.fieldOf("spellType").forGetter(BasicSpell::spellType),
                    School.CODEC.fieldOf("school").forGetter(BasicSpell::school),
                    Codec.INT.fieldOf("maxLevel").forGetter(BasicSpell::maxLevel)
            ).apply(inst, BasicSpell::new));

    @Override
    public Codec<? extends Spell> getCodec() {
        return CODEC;
    }
}
