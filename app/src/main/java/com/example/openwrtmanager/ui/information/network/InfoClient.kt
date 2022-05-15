package com.example.openwrtmanager.com.example.openwrtmanager.ui.identity.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class InfoClient() {
    private val TAG = InfoClient::class.qualifiedName
    fun getRetrofit(): Retrofit {
        var ua = System.getProperty("http.agent")
        ua = if (ua !== null) {
            ua.toString()
        } else {
            ""
        }
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
//            .addInterceptor(object : Interceptor {
//                @Throws(IOException::class)
//                override fun intercept(chain: Interceptor.Chain): Response {
//                    val original: Request = chain.request()
//                    val request: Request = original.newBuilder()
//                        .header("Content-Type", "application/json; charset=utf-8")
//                        .header("User-Agent", ua)
//                        .build()
//
//                    val response: Response = chain.proceed(request)
//                    when (response.code()) {
//                        401 -> {
//                            MyLogger.e(TAG + "Unauthorized-->", "$response")
//                        }
//                        400, 403, 404 -> {
//                            MyLogger.e(TAG + "Error--> ", "$response")
//                        }
//                        500, 503 -> {
//                            MyLogger.e(TAG + "ServerError-->", "$response")
//                        }
//                    }
//                    return response
//                }
//            })
//            .addInterceptor(HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.HEADERS
//                level = HttpLoggingInterceptor.Level.BODY
//            })
            .followRedirects(false)
            .followSslRedirects(false)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl("http://192.168.56.4:80/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun getInfoRetrofit(): Retrofit {

        var ua = System.getProperty("http.agent")
        ua = if (ua !== null) {
            ua.toString()
        } else {
            ""
        }
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.HEADERS
                level = HttpLoggingInterceptor.Level.BODY
            })
//            .addInterceptor(object : Interceptor {
//                @Throws(IOException::class)
//                override fun intercept(chain: Interceptor.Chain): Response {
//                    val original: Request = chain.request()
//                    val request: Request = original.newBuilder()
//                        .header("Content-Type", "application/json; charset=utf-8")
//                        .header("User-Agent", ua)
//                        .build()
//
//                    val response: Response = chain.proceed(request)
//                    when (response.code()) {
//                        401 -> {
//                            MyLogger.e(TAG + "Unauthorized-->", "$response")
//                        }
//                        400, 403, 404 -> {
//                            MyLogger.e(TAG + "Error--> ", "$response")
//                        }
//                        500, 503 -> {
//                            MyLogger.e(TAG + "ServerError-->", "$response")
//                        }
//                    }
//                    return response
//                }
//            })
            .followRedirects(false)
            .followSslRedirects(false)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()


        return Retrofit.Builder()
            .baseUrl("http://192.168.56.4:80/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}
//    fun putMethod(cookie: String): ResponseBody? {
//        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
//            .addInterceptor(HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.HEADERS
//                level = HttpLoggingInterceptor.Level.BODY
//            })
//            .followRedirects(false)
//            .followSslRedirects(false)
//            .connectTimeout(10, TimeUnit.SECONDS)
//            .writeTimeout(10, TimeUnit.SECONDS)
//            .readTimeout(10, TimeUnit.SECONDS)
//            .build()
//        // Create Retrofit
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://192.168.56.4:80/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .build()

// Create Service
//        val service = retrofit.create(InfoService::class.java)
//        val rrrrr = listOf(
//            ResquestJSONElement(
//                id = 1,
//                params = listOf<Any>(
//                    cookie, "system", "info", ParamClass
//                )
//            ),
//            ResquestJSONElement(
//                id = 2,
//                params = listOf<Any>(
//                    cookie, "system", "board", ParamClass()
//                )
//            ),
//            ResquestJSONElement(
//                id = 3,
//                params = listOf<Any>(cookie, "luci-rpc", "getDHCPLeases", ParamClass())
//            ),
//            ResquestJSONElement(
//                id = 4,
//                params = listOf<Any>(
//                    cookie, "luci-rpc", "getNetworkDevices", ParamClass()
//                )
//            ),
//            ResquestJSONElement(
//                id = 1,
//                params = listOf<Any>(
//                    cookie, "network.interface", "dump", ParamClass()
//                )
//            ),
//        )

//        CoroutineScope(Dispatchers.IO).launch {
//            // Do the PUT request and get response
//            val response = service.getInformation(rrrrr)
//
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//                    return response.body()
//                } else {
//
//                    Log.e("RETROFIT_ERROR", response.code().toString())
//
//                }
//            }
//        }
//
//    }
//}
