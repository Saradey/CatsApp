// Generated by Dagger (https://dagger.dev).
package com.evgeny.goncharov.coreimpl.network;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NetworkModule_ProvideHttpClientFactory implements Factory<OkHttpClient> {
  private final Provider<HttpLoggingInterceptor> loggingInterceptorProvider;

  private final Provider<Context> contextProvider;

  private final Provider<Boolean> buildVariantsProvider;

  public NetworkModule_ProvideHttpClientFactory(
      Provider<HttpLoggingInterceptor> loggingInterceptorProvider,
      Provider<Context> contextProvider, Provider<Boolean> buildVariantsProvider) {
    this.loggingInterceptorProvider = loggingInterceptorProvider;
    this.contextProvider = contextProvider;
    this.buildVariantsProvider = buildVariantsProvider;
  }

  @Override
  public OkHttpClient get() {
    return provideHttpClient(loggingInterceptorProvider.get(), contextProvider.get(), buildVariantsProvider.get());
  }

  public static NetworkModule_ProvideHttpClientFactory create(
      Provider<HttpLoggingInterceptor> loggingInterceptorProvider,
      Provider<Context> contextProvider, Provider<Boolean> buildVariantsProvider) {
    return new NetworkModule_ProvideHttpClientFactory(loggingInterceptorProvider, contextProvider, buildVariantsProvider);
  }

  public static OkHttpClient provideHttpClient(HttpLoggingInterceptor loggingInterceptor,
      Context context, boolean buildVariants) {
    return Preconditions.checkNotNull(NetworkModule.provideHttpClient(loggingInterceptor, context, buildVariants), "Cannot return null from a non-@Nullable @Provides method");
  }
}
