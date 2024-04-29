package dev.teamcitrus.blackmagic.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record BasicSpell(SpellType spellType) implements Spell {
    public static Codec<BasicSpell> CODEC = RecordCodecBuilder.create(inst -> inst
            .group(
                    SpellType.CODEC.fieldOf("spellType").forGetter(BasicSpell::spellType)
            ).apply(inst, BasicSpell::new));

    @Override
    public Codec<? extends Spell> getCodec() {
        return CODEC;
    }
}
