package org.romeo.goodads.koin_modules

import org.koin.dsl.module
import org.romeo.layer_domain.use_cases.GetAdUserUseCase
import org.romeo.layer_domain.use_cases.GetMyAdsUseCase
import org.romeo.layer_domain.use_cases.GetUserAdsUseCase

val useCaseModule = module {

    single { GetUserAdsUseCase(get(), get()) }
    single { GetAdUserUseCase(get(), get()) }
    single { GetMyAdsUseCase(get(), get()) }
}