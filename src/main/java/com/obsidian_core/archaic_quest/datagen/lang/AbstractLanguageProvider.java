package com.obsidian_core.archaic_quest.datagen.lang;

import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.Text;
import net.minecraft.network.chat.TextContents;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.world.biome.Biome;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public abstract class AbstractLanguageProvider extends LanguageProvider {

    public AbstractLanguageProvider(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    protected void addItemGroup(CreativeModeTab creativeTab, String localized) {
        this.add("itemGroup." + creativeTab.getRecipeFolderName(), "Archaic Quest - " + localized);
    }

    protected void addBiome(RegistryObject<Biome> biome, String localized) {
        this.add("biome.archaic_quest." + biome.getId().getPath(), localized);
    }

    protected void addJeiInfo(Item item, String localized) {
        this.add(item.getDescriptionId() + ".jei_desc", localized);
    }

    protected void addDamageSource(DamageSource damageSource, String death, String combatloggedDath) {
        String deathString = "death.attack." + damageSource.getMsgId();
        String combatloggedDeathString = deathString + ".player";
        this.add(deathString, death);
        this.add(combatloggedDeathString, combatloggedDath);
    }

    protected void addTranslationText(Text component, String localized) {
        if (component.getContents() instanceof TranslatableContents translatableContents) {
            add(translatableContents.getKey(), localized);
        }
    }
}
