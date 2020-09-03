// Generated by Dagger (https://dagger.dev).
package com.evgeny.goncharov.coreimpl.network;

import com.evgeny.goncharov.coreapi.rest.ApiCatSearch;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.Retrofit;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NetworkModule_ProvideApiSearchCatFromNameFactory implements Factory<ApiCatSearch> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideApiSearchCatFromNameFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ApiCatSearch get() {
    return provideApiSearchCatFromName(retrofitProvider.get());
  }

  public static NetworkModule_ProvideApiSearchCatFromNameFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideApiSearchCatFromNameFactory(retrofitProvider);
  }

  public static ApiCatSearch provideApiSearchCatFromName(Retrofit retrofit) {
    return Preconditions.checkNotNull(NetworkModule.provideApiSearchCatFromName(retrofit), "Cannot return null from a non-@Nullable @Provides method");
  }
}
