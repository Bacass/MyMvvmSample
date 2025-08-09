package com.lee.mymvvmsample.data.repository;

import com.lee.mymvvmsample.data.mapper.ImageMapper;
import com.lee.mymvvmsample.data.network.ImageApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class ImageRepositoryImpl_Factory implements Factory<ImageRepositoryImpl> {
  private final Provider<ImageApiService> apiServiceProvider;

  private final Provider<ImageMapper> imageMapperProvider;

  public ImageRepositoryImpl_Factory(Provider<ImageApiService> apiServiceProvider,
      Provider<ImageMapper> imageMapperProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.imageMapperProvider = imageMapperProvider;
  }

  @Override
  public ImageRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), imageMapperProvider.get());
  }

  public static ImageRepositoryImpl_Factory create(Provider<ImageApiService> apiServiceProvider,
      Provider<ImageMapper> imageMapperProvider) {
    return new ImageRepositoryImpl_Factory(apiServiceProvider, imageMapperProvider);
  }

  public static ImageRepositoryImpl newInstance(ImageApiService apiService,
      ImageMapper imageMapper) {
    return new ImageRepositoryImpl(apiService, imageMapper);
  }
}
