package com.evgeny.goncharov.wallcats.di.components;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u0000 \b2\u00020\u0001:\u0002\b\tJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\n"}, d2 = {"Lcom/evgeny/goncharov/wallcats/di/components/WallCatsComponent;", "", "inject", "", "fragment", "Lcom/evgeny/goncharov/wallcats/ui/WallCatsFragment;", "viewModel", "Lcom/evgeny/goncharov/wallcats/view/model/WallCatsViewModel;", "Companion", "Factory", "wallCats_debug"})
@dagger.Component(dependencies = {com.evgeny.goncharov.coreapi.providers.ProviderFacade.class}, modules = {com.evgeny.goncharov.wallcats.di.modules.WallCatsBindsModule.class, com.evgeny.goncharov.wallcats.di.modules.WallCatsProvidesModule.class})
@javax.inject.Singleton()
public abstract interface WallCatsComponent {
    public static final com.evgeny.goncharov.wallcats.di.components.WallCatsComponent.Companion Companion = null;
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.evgeny.goncharov.wallcats.ui.WallCatsFragment fragment);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.evgeny.goncharov.wallcats.view.model.WallCatsViewModel viewModel);
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/evgeny/goncharov/wallcats/di/components/WallCatsComponent$Factory;", "", "plus", "Lcom/evgeny/goncharov/wallcats/di/components/WallCatsComponent;", "fragment", "Lcom/evgeny/goncharov/wallcats/ui/WallCatsFragment;", "provide", "Lcom/evgeny/goncharov/coreapi/providers/ProviderFacade;", "wallCats_debug"})
    @dagger.Component.Factory()
    public static abstract interface Factory {
        
        @org.jetbrains.annotations.NotNull()
        public abstract com.evgeny.goncharov.wallcats.di.components.WallCatsComponent plus(@org.jetbrains.annotations.NotNull()
        @dagger.BindsInstance()
        com.evgeny.goncharov.wallcats.ui.WallCatsFragment fragment, @org.jetbrains.annotations.NotNull()
        com.evgeny.goncharov.coreapi.providers.ProviderFacade provide);
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u000e"}, d2 = {"Lcom/evgeny/goncharov/wallcats/di/components/WallCatsComponent$Companion;", "", "()V", "component", "Lcom/evgeny/goncharov/wallcats/di/components/WallCatsComponent;", "getComponent", "()Lcom/evgeny/goncharov/wallcats/di/components/WallCatsComponent;", "setComponent", "(Lcom/evgeny/goncharov/wallcats/di/components/WallCatsComponent;)V", "init", "provide", "Lcom/evgeny/goncharov/coreapi/providers/ProviderFacade;", "fragment", "Lcom/evgeny/goncharov/wallcats/ui/WallCatsFragment;", "wallCats_debug"})
    public static final class Companion {
        @org.jetbrains.annotations.Nullable()
        private static com.evgeny.goncharov.wallcats.di.components.WallCatsComponent component;
        
        @org.jetbrains.annotations.Nullable()
        public final com.evgeny.goncharov.wallcats.di.components.WallCatsComponent getComponent() {
            return null;
        }
        
        public final void setComponent(@org.jetbrains.annotations.Nullable()
        com.evgeny.goncharov.wallcats.di.components.WallCatsComponent p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.evgeny.goncharov.wallcats.di.components.WallCatsComponent init(@org.jetbrains.annotations.NotNull()
        com.evgeny.goncharov.coreapi.providers.ProviderFacade provide, @org.jetbrains.annotations.NotNull()
        com.evgeny.goncharov.wallcats.ui.WallCatsFragment fragment) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}