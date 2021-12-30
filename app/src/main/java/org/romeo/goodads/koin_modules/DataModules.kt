package org.romeo.goodads.koin_modules

import android.content.Context
import android.content.SharedPreferences
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import org.romeo.layer_data.api.ApiService
import org.romeo.layer_data.api.MAIN_API_URL
import org.romeo.layer_data.api.MainInterceptor
import org.romeo.layer_data.data_sources.ApiDataSource
import org.romeo.layer_data.data_sources.FakeApiDataSource
import org.romeo.layer_data.data_sources.preferences.TokenUserDataSourceLocal
import org.romeo.layer_data.data_sources.preferences.TokenUserDataSourceLocalImpl
import org.romeo.layer_data.repository.*
import org.romeo.layer_domain.repository_bounderies.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataSourceModule = module {
    //single<ApiDataSource> { ApiDataSourceImpl(get()) }
    single<ApiDataSource> { FakeApiDataSource() }
    single<TokenUserDataSourceLocal> { TokenUserDataSourceLocalImpl(get()) }
}

val preferencesModule = module {
    single<SharedPreferences> {
        get<Context>().getSharedPreferences(
            APP_PREFERENCES,
            Context.MODE_PRIVATE
        )
    }
}

val repositoryModule = module {
    single<AdsRepository> { AdsRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<DistributionRepository> { DistributionRepositoryImpl(get()) }
    single<AdvertisingRequestsRepository> { AdvertisingRequestsRepositoryImpl(get()) }
    single<PaymentRepository> { PaymentRepositoryImpl(get()) }
}

val retrofitModule = module {
    single {

        val client = OkHttpClient.Builder()
            .addInterceptor(MainInterceptor(get()))
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        Retrofit.Builder()
            .baseUrl(MAIN_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build().create(ApiService::class.java)
    }
}

private const val APP_PREFERENCES = "APP_PREFERENCES"