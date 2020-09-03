// Generated by Dagger (https://dagger.dev).
package com.evgeny.goncharov.coreimpl.mangers;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class LanguageManagerImpl_Factory implements Factory<LanguageManagerImpl> {
  private final Provider<Context> contextProvider;

  public LanguageManagerImpl_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LanguageManagerImpl get() {
    return newInstance(contextProvider.get());
  }

  public static LanguageManagerImpl_Factory create(Provider<Context> contextProvider) {
    return new LanguageManagerImpl_Factory(contextProvider);
  }

  public static LanguageManagerImpl newInstance(Context context) {
    return new LanguageManagerImpl(context);
  }
}
