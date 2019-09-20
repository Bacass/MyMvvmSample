package com.lee.mymvvmsample.common

import android.app.Application
import android.content.Context
import com.chibatching.kotpref.Kotpref
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.lee.mymvvmsample.BuildConfig
import com.lee.mymvvmsample.ui.main.MainViewModel
import com.lee.mymvvmsample.network.AddCookieInterceptor
import com.lee.mymvvmsample.network.SampleRepository
import com.lee.mymvvmsample.network.SampleService
import com.lee.mymvvmsample.network.ReceivedCookieInterceptor
import com.lee.mymvvmsample.ui.main.gallery.GalleryViewModel
import com.lee.mymvvmsample.ui.main.home.HomeViewModel
import com.lee.mymvvmsample.ui.main.send.SendViewModel
import com.lee.mymvvmsample.ui.main.share.ShareViewModel
import com.lee.mymvvmsample.ui.main.slideshow.SlideshowViewModel
import com.lee.mymvvmsample.ui.main.tools.ToolsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MyApplication: Application() {
    companion object {
        var mContext: Context? = null
    }

    private val appModules = module {
        single<SampleService> {
            Retrofit.Builder().baseUrl(BuildConfig.SERVER_HTTP_URL).client(OkHttpClient.Builder().apply {
                connectTimeout(30, TimeUnit.SECONDS)
                writeTimeout(30, TimeUnit.SECONDS)
                readTimeout(30, TimeUnit.SECONDS)
                addInterceptor(AddCookieInterceptor())
                addInterceptor(ReceivedCookieInterceptor())
                addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                    if (!it.startsWith("{") && !it.startsWith("[")) {
                        Timber.tag("OkHttp").d(it)
                        return@Logger
                    }
                    try {
                        Timber.tag("OkHttp").d(
                            GsonBuilder().setPrettyPrinting().create().toJson(
                                JsonParser().parse(it)))
                    } catch (m: JsonSyntaxException) {
                        Timber.tag("OkHttp").d(it)
                    }
                }).apply { level = HttpLoggingInterceptor.Level.BODY })
            }.build()).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(
                GsonConverterFactory.create()).build().create()
        }

        single {
            SampleRepository(get())
        }

        single { MainViewModel(get()) } // 싱글톤 뷰모델 생성.
        viewModel { HomeViewModel(get()) }
        viewModel { SendViewModel(get()) }
        viewModel { ShareViewModel(get()) }
        viewModel { ToolsViewModel(get()) }
        viewModel { SlideshowViewModel(get()) }
        viewModel { GalleryViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        mContext = this

        /**
         * 로그를 표시함.
         */
        if (BuildConfig.SHOW_LOG) {
            Timber.plant(Timber.DebugTree())
        }

        Kotpref.init(this)

        // start Koin
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModules)
        }
    }
}