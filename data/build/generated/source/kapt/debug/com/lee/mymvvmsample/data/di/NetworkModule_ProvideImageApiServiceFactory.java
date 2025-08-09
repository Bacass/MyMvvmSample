package com.lee.mymvvmsample.data.di;

import com.lee.mymvvmsample.data.network.ImageApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
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
public final class NetworkModule_ProvideImageApiServiceFactory implements Factory<ImageApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideImageApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ImageApiService get() {
    return provideImageApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideImageApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideImageApiServiceFactory(retrofitProvider);
  }

  public static ImageApiService provideImageApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideImageApiService(retrofit));
  }
}
