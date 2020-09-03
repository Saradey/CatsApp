package com.evgeny.goncharov.settings.di.modules;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0007"}, d2 = {"Lcom/evgeny/goncharov/settings/di/modules/SettingsProvidesModule;", "", "()V", "provideSettingsViewModel", "Lcom/evgeny/goncharov/settings/view/model/SettingsViewModel;", "fragment", "Lcom/evgeny/goncharov/settings/ui/SettingsFragment;", "settings_debug"})
@dagger.Module()
public final class SettingsProvidesModule {
    public static final com.evgeny.goncharov.settings.di.modules.SettingsProvidesModule INSTANCE = null;
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public static final com.evgeny.goncharov.settings.view.model.SettingsViewModel provideSettingsViewModel(@org.jetbrains.annotations.NotNull()
    com.evgeny.goncharov.settings.ui.SettingsFragment fragment) {
        return null;
    }
    
    private SettingsProvidesModule() {
        super();
    }
}