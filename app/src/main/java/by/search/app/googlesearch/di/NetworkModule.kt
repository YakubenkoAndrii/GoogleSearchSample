package by.search.app.googlesearch.di

import by.search.app.googlesearch.BuildConfig
import by.search.app.googlesearch.common.BASE_URL
import by.search.app.googlesearch.common.utils.Networking
import by.search.app.googlesearch.data.api.GoogleSearchService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val networkModule: Module = module {

    single {
        val logger = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logger.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logger.level = HttpLoggingInterceptor.Level.NONE
        }
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logger)
    }

    single {
        GsonBuilder()
            .setLenient()
            .create()
    }

    single {
        Networking(
            BASE_URL,
            okHttpClient = get(),
            gson = get())
            .create(GoogleSearchService::class.java)
    }

}