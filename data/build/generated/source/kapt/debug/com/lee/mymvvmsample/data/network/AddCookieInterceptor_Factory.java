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
public final class AddCookieInterceptor_Factory implements Factory<AddCookieInterceptor> {
  private final Provider<CookieStorage> cookieStorageProvider;

  public AddCookieInterceptor_Factory(Provider<CookieStorage> cookieStorageProvider) {
    this.cookieStorageProvider = cookieStorageProvider;
  }

  @Override
  public AddCookieInterceptor get() {
    return newInstance(cookieStorageProvider.get());
  }

  public static AddCookieInterceptor_Factory create(Provider<CookieStorage> cookieStorageProvider) {
    return new AddCookieInterceptor_Factory(cookieStorageProvider);
  }

  public static AddCookieInterceptor newInstance(CookieStorage cookieStorage) {
    return new AddCookieInterceptor(cookieStorage);
  }
}
