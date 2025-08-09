package com.lee.mymvvmsample.common.di

import com.lee.mymvvmsample.common.utils.CookieStorageImpl
import com.lee.mymvvmsample.common.utils.UserPreferencesImpl
import com.lee.mymvvmsample.data.local.CookieStorage
import com.lee.mymvvmsample.domain.preferences.UserPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindCookieStorage(impl: CookieStorageImpl): CookieStorage

    @Binds
    @Singleton
    abstract fun bindUserPreferences(impl: UserPreferencesImpl): UserPreferences
}
