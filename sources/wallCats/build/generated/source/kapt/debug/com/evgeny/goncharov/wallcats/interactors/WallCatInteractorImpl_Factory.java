// Generated by Dagger (https://dagger.dev).
package com.evgeny.goncharov.wallcats.interactors;

import com.evgeny.goncharov.wallcats.gateways.WallCatGateway;
import dagger.internal.Factory;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class WallCatInteractorImpl_Factory implements Factory<WallCatInteractorImpl> {
  private final Provider<WallCatGateway> repositoryProvider;

  public WallCatInteractorImpl_Factory(Provider<WallCatGateway> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public WallCatInteractorImpl get() {
    return newInstance(repositoryProvider.get());
  }

  public static WallCatInteractorImpl_Factory create(Provider<WallCatGateway> repositoryProvider) {
    return new WallCatInteractorImpl_Factory(repositoryProvider);
  }

  public static WallCatInteractorImpl newInstance(WallCatGateway repository) {
    return new WallCatInteractorImpl(repository);
  }
}
