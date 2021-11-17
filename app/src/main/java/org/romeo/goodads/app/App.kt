package org.romeo.goodads.app

import androidx.multidex.MultiDexApplication
import org.koin.core.context.startKoin
import org.romeo.goodads.koin_modules.*

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                viewModelModule,
                navigationModule,
                mapperModule,
                useCaseModule,
                dataSourceModule,
                preferencesModule,
                repositoryModule,
                retrofitModule
            )
        }
    }
}