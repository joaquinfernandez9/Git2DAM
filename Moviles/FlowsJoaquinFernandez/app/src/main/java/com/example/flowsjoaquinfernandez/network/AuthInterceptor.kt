package com.example.flowsjoaquinfernandez.network

import com.example.flowsjoaquinfernandez.utils.Constants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor (private val apiKey: String):Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val httpUrl = original.url.newBuilder()
            .addQueryParameter(Constants.API_KEY, apiKey)
            .build()

        val requestBuilder: Request.Builder = original.newBuilder()
            .url(httpUrl)

        return chain.proceed(requestBuilder.build())
    }
}