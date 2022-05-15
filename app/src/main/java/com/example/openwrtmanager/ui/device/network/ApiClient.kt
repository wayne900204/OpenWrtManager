package com.example.openwrtmanager.com.example.openwrtmanager.ui.device.network

import com.example.openwrtmanager.com.example.openwrtmanager.utils.MyLogger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


class ApiClient(var url: String = "http://192.168.56.4:80/") {
    private val TAG = ApiClient::class.qualifiedName

    var apiService: ApiService = getRetrofit().create(ApiService::class.java)

    fun getRetrofit(): Retrofit {
        var ua = System.getProperty("http.agent")
        ua = if (ua !== null) {
            ua.toString()
        } else {
            ""
        }
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original: Request = chain.request()
                    val request: Request = original.newBuilder()
                        .header("Content-Type", "application/json; charset=utf-8")
                        .header("User-Agent", ua)
                        .build()

                    val response: Response = chain.proceed(request)
                    when (response.code()) {
                        401 -> {
                            MyLogger.e(TAG + "Unauthorized-->", "$response")
                        }
                        400, 403, 404 -> {
                            MyLogger.e(TAG + "Error--> ", "$response")
                        }
                        500, 503 -> {
                            MyLogger.e(TAG + "ServerError-->", "$response")
                        }
                    }
                    return response
                }
            })
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.HEADERS
                level = HttpLoggingInterceptor.Level.BODY
            })
            .followRedirects(false)
            .followSslRedirects(false)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}

