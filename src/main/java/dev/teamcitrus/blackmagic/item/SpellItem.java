package dev.teamcitrus.blackmagic.item;

import dev.shadowsoffire.placebo.reload.DynamicHolder;
import dev.shadowsoffire.placebo.tabs.ITabFiller;
import dev.teamcitrus.blackmagic.data.Spell;
import dev.teamcitrus.blackmagic.data.SpellRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.Comparator;
import java.util.List;

public class SpellItem extends Item implements ITabFiller {
    public SpellItem(Properties pProperties) {
        super(pProperties);
    }

    public static void setSpell(ItemStack opener, Spell gate) {
        opener.getOrCreateTag().putString("spell", SpellRegistry.INSTANCE.getKey(gate).toString());
    }

    public static DynamicHolder<Spell> getSpell(ItemStack spellItem) {
        return SpellRegistry.INSTANCE.holder(new ResourceLocation(spellItem.getOrCreateTag().getString("spell")));
    }

    @Override
    public Component getName(ItemStack pStack) {
        if (pStack.hasCustomHoverName()) return super.getName(pStack);
        var spell = getSpell(pStack);
        if (spell.isBound()) return Component.translatable("blackmagic.spell", Component.translatable(spell.getId().toString().replace(":", ".")));
        return super.getName(pStack);
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, CreativeModeTab.Output output) {
        SpellRegistry.INSTANCE.getValues().stream().sorted(Comparator.comparing(Spell::spellType).thenComparing(SpellRegistry.INSTANCE::getKey)).forEach(spell -> {
            ItemStack stack = new ItemStack(this);
            setSpell(stack, spell);
            output.accept(stack);
        });
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        DynamicHolder<Spell> holder = getSpell(stack);
        if (!holder.isBound()) {
            tooltip.add(Component.literal("Errored Spell, file a bug report detailing how you obtained this."));
        }
    }
}
