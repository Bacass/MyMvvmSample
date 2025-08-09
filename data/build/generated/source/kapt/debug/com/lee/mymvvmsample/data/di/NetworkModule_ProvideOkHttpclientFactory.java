package com.lee.mymvvmsample.data.di;

import com.lee.mymvvmsample.data.local.CookieStorage;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class NetworkModule_ProvideOkHttpclientFactory implements Factory<OkHttpClient> {
  private final Provider<CookieStorage> cookieStorageProvider;

  public NetworkModule_ProvideOkHttpclientFactory(Provider<CookieStorage> cookieStorageProvider) {
    this.cookieStorageProvider = cookieStorageProvider;
  }

  @Override
  public OkHttpClient get() {
    return provideOkHttpclient(cookieStorageProvider.get());
  }

  public static NetworkModule_ProvideOkHttpclientFactory create(
      Provider<CookieStorage> cookieStorageProvider) {
    return new NetworkModule_ProvideOkHttpclientFactory(cookieStorageProvider);
  }

  public static OkHttpClient provideOkHttpclient(CookieStorage cookieStorage) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOkHttpclient(cookieStorage));
  }
}
