package com.lee.mymvvmsample.data.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
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
  @Override
  public OkHttpClient get() {
    return provideOkHttpclient();
  }

  public static NetworkModule_ProvideOkHttpclientFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static OkHttpClient provideOkHttpclient() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOkHttpclient());
  }

  private static final class InstanceHolder {
    private static final NetworkModule_ProvideOkHttpclientFactory INSTANCE = new NetworkModule_ProvideOkHttpclientFactory();
  }
}
