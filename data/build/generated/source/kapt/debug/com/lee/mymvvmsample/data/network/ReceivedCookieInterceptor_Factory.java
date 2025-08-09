package com.lee.mymvvmsample.data.network;

import com.lee.mymvvmsample.data.local.CookieStorage;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class ReceivedCookieInterceptor_Factory implements Factory<ReceivedCookieInterceptor> {
  private final Provider<CookieStorage> cookieStorageProvider;

  public ReceivedCookieInterceptor_Factory(Provider<CookieStorage> cookieStorageProvider) {
    this.cookieStorageProvider = cookieStorageProvider;
  }

  @Override
  public ReceivedCookieInterceptor get() {
    return newInstance(cookieStorageProvider.get());
  }

  public static ReceivedCookieInterceptor_Factory create(
      Provider<CookieStorage> cookieStorageProvider) {
    return new ReceivedCookieInterceptor_Factory(cookieStorageProvider);
  }

  public static ReceivedCookieInterceptor newInstance(CookieStorage cookieStorage) {
    return new ReceivedCookieInterceptor(cookieStorage);
  }
}
