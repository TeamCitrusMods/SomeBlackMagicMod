package dev.teamcitrus.blackmagic.item;

import dev.shadowsoffire.placebo.reload.DynamicHolder;
import dev.shadowsoffire.placebo.tabs.ITabFiller;
import dev.teamcitrus.blackmagic.data.Spell;
import dev.teamcitrus.blackmagic.data.SpellRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.fml.loading.FMLEnvironment;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class SpellItem extends Item implements ITabFiller {
    public SpellItem(Properties pProperties) {
        super(pProperties);
    }

    public static void setSpell(ItemStack stack, Spell spell) {
        stack.getOrCreateTag().putString("spell", SpellRegistry.INSTANCE.getKey(spell).toString());
    }

    public static DynamicHolder<Spell> getSpell(ItemStack spellItem) {
        return SpellRegistry.INSTANCE.holder(new ResourceLocation(spellItem.getOrCreateTag().getString("spell")));
    }

    @Override
    public Component getName(ItemStack stack) {
        if (stack.hasCustomHoverName()) return super.getName(stack);
        DynamicHolder<Spell> spell = getSpell(stack);
        if (spell.isBound()) return Component.translatable("blackmagic.spell_item",
                Component.translatable(spell.getId().toString().replace(':', '.'))
        );
        return super.getName(stack);
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
            tooltip.add(Component.literal("Spell is not registered, please make a bug report."));
        }
        else if (FMLEnvironment.dist.isClient()) {
            tooltip.add(
                    Component.literal(" Level " + holder.get().maxLevel())
            );
            tooltip.add(Component.translatable("blackmagic.school",
                    Component.translatable("blackmagic.school." + holder.get().school().toString().toLowerCase(Locale.ROOT))
                            .withColor(holder.get().school().getColor())
                    ).withStyle(ChatFormatting.DARK_GRAY)
            );
            if (Screen.hasShiftDown()) {
                tooltip.add(Component.translatable("blackmagic." + holder.getId().getPath() + ".desc")
                        .withStyle(ChatFormatting.DARK_GRAY)
                );
            }
        }
    }
}
