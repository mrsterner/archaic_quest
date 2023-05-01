package com.obsidian_core.archaic_quest.api.impl;

import com.obsidian_core.archaic_quest.api.ArchaicQuestApi;
import com.obsidian_core.archaic_quest.api.IRegistryHelper;

public class ArchaicQuestAPI implements ArchaicQuestApi {

    private final IRegistryHelper registryHelper = new RegistryHelper();

    @Override
    public IRegistryHelper getRegistryHelper() {
        return null;
    }
}
