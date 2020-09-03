// Generated by Dagger (https://dagger.dev).
package com.evgeny.goncharov.wallcats.view.model;

import com.evgeny.goncharov.wallcats.interactors.CatDescriptionInteractor;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class CatDescriptionViewModelImpl_MembersInjector implements MembersInjector<CatDescriptionViewModelImpl> {
  private final Provider<CatDescriptionInteractor> interactorProvider;

  public CatDescriptionViewModelImpl_MembersInjector(
      Provider<CatDescriptionInteractor> interactorProvider) {
    this.interactorProvider = interactorProvider;
  }

  public static MembersInjector<CatDescriptionViewModelImpl> create(
      Provider<CatDescriptionInteractor> interactorProvider) {
    return new CatDescriptionViewModelImpl_MembersInjector(interactorProvider);}

  @Override
  public void injectMembers(CatDescriptionViewModelImpl instance) {
    injectInteractor(instance, interactorProvider.get());
  }

  @InjectedFieldSignature("com.evgeny.goncharov.wallcats.view.model.CatDescriptionViewModelImpl.interactor")
  public static void injectInteractor(CatDescriptionViewModelImpl instance,
      CatDescriptionInteractor interactor) {
    instance.interactor = interactor;
  }
}
