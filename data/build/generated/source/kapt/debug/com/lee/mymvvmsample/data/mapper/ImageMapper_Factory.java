package com.lee.mymvvmsample.data.mapper;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class ImageMapper_Factory implements Factory<ImageMapper> {
  @Override
  public ImageMapper get() {
    return newInstance();
  }

  public static ImageMapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ImageMapper newInstance() {
    return new ImageMapper();
  }

  private static final class InstanceHolder {
    private static final ImageMapper_Factory INSTANCE = new ImageMapper_Factory();
  }
}
