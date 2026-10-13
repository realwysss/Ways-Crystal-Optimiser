package com.wayysss.crystal;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

public class nsdmdmapi implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return p -> {
            ConfigBuilder b = ConfigBuilder.create().setParentScreen(p).setTitle(Text.literal("Ways Crystal Optimiser"));
            ConfigCategory configctgry = b.getOrCreateCategory(Text.literal("main"));
            ConfigEntryBuilder configIn = b.entryBuilder();
            configctgry.addEntry(configIn.startTextDescription(Text.literal("WARNING: Setting both delays to 0 might flag anticheats!")).build());
            configctgry.addEntry(configIn.startBooleanToggle(Text.literal("on"), cfg.on)
                .setDefaultValue(true)
                .setSaveConsumer(v -> cfg.on = v)
                .build());
            configctgry.addEntry(configIn.startIntSlider(Text.literal("min ticks"), cfg.min, 0, 4)
                .setDefaultValue(1)
                .setSaveConsumer(v -> cfg.min = v)
                .build());
            configctgry.addEntry(configIn.startIntSlider(Text.literal("max ticks"), cfg.max, 0, 4)
                .setDefaultValue(2)
                .setSaveConsumer(v -> cfg.max = v)
                .build());
            return b.build();
        };
    }
}
