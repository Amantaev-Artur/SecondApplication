package com.example.secondapplication.datalayer

import com.google.gson.GsonBuilder
import okhttp3.Cache
import android.content.Context
import android.net.ConnectivityManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider(private val context: Context) {
    private val baseUrl = "https://cataas.com"
    private val gson = GsonBuilder().create()

    //Создаем клиент OkHttp с использованием кэша
    private val cacheSize = 10 * 1024 * 1024 // 10 MB
    private val cache = Cache(context.cacheDir, cacheSize.toLong())
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun hasNetwork(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun provide(): Retrofit {
        val cacheSize = 10 * 1024 * 1024 // 10 MB
        val cache = Cache(context.cacheDir, cacheSize.toLong())

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder().apply {
            cache(cache)
            addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork())
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()

                chain.proceed(request)
            }
            addNetworkInterceptor(loggingInterceptor)
        }.build()

        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create(gson))
            client(okHttpClient)
            baseUrl(baseUrl)
        }.build()
    }
}