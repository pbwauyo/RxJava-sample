package com.example.rxjavatest.apiClient

import android.content.Context
import com.example.rxjavatest.constants.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    companion object{
        private const val REQUEST_TIMEOUT: Long = 60

        fun getClient(): Retrofit{
            val okHttpClient: OkHttpClient = initOkHttp()

            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }


        private fun initOkHttp(): OkHttpClient {

            val httpClient: OkHttpClient.Builder = OkHttpClient().newBuilder()
            httpClient.connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                      .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                      .addInterceptor(object : Interceptor {
                          override fun intercept(chain: Interceptor.Chain): Response {
                              val original: Request = chain.request()
                              val requestBuilder: Request.Builder = original.newBuilder()
                              requestBuilder.addHeader("Accept", "application/json")
                              requestBuilder.addHeader("Content-Type", "application/json")

                              val request: Request = requestBuilder.build()
                              return chain.proceed(request)
                          }
                      })

            return httpClient.build()
        }
    }
}