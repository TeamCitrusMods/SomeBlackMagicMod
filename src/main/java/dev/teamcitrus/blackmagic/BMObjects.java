package dev.teamcitrus.blackmagic;

import dev.shadowsoffire.placebo.registry.DeferredHelper;
import dev.teamcitrus.blackmagic.item.SpellItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.ApiStatus;

public class BMObjects {
    public static final DeferredHelper R = DeferredHelper.create(BlackMagic.MODID);

    public static final DeferredItem<SpellItem> SPELL_ITEM = R.item("spell_item", () -> new SpellItem(new Item.Properties()));

    public static final ResourceKey<CreativeModeTab> TAB_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(BlackMagic.MODID, "tab"));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = R.tab("tab", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.blackmagic")).icon(Items.BOOK::getDefaultInstance).build());

    @ApiStatus.Internal
    static void bootstrap(IEventBus bus) {
        bus.register(R);
    }
}
