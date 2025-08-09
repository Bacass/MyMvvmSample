package com.lee.mymvvmsample.common.di

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.lee.mymvvmsample.BuildConfig
import com.lee.mymvvmsample.data.network.AddCookieInterceptor
import com.lee.mymvvmsample.data.network.ImageApiService
import com.lee.mymvvmsample.data.network.ReceivedCookieInterceptor
import com.lee.mymvvmsample.data.repository.ImageRepositoryImpl
import com.lee.mymvvmsample.domain.repository.ImageRepository
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindImageRepository(imageRepositoryImpl: ImageRepositoryImpl): ImageRepository
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideOkHttpclient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            addInterceptor(AddCookieInterceptor())
            addInterceptor(ReceivedCookieInterceptor())
            /**
             * 200309
             * addInterceptor 을 수정함.
             * okhttp 4.3.1 버전으로 수정되면서 아래 내용을 수정했다.
             * 기존 내용은 주석처리함.
             */
            addInterceptor(
                HttpLoggingInterceptor(
                    object : HttpLoggingInterceptor.Logger {
                        override fun log(message: String) {
                            if (!message.startsWith("{") && !message.startsWith("[")) {
                                Timber.tag("OkHttp").d(message)
                                return
                            }
                            try {
                                val element: JsonElement =
                                    runCatching { JsonParser.parseString(message) }
                                        .getOrNull() ?: return
                                val pretty = GsonBuilder().setPrettyPrinting().create().toJson(element)
                                Timber.tag("OkHttp").d(pretty)
                            } catch (_: JsonSyntaxException) {
                                Timber.tag("OkHttp").d(message)
                            }
                        }
                    },
                ).apply {
                    level = HttpLoggingInterceptor.Level.BODY
                },
            )
        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_HTTP_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideImageApiService(retrofit: Retrofit): ImageApiService {
        return retrofit.create(ImageApiService::class.java)
    }
}
