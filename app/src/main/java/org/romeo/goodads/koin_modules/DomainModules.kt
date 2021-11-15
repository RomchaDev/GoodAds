package org.romeo.goodads.koin_modules

import org.koin.dsl.module
import org.romeo.layer_domain.use_cases.LoginUseCase
import org.romeo.layer_domain.use_cases.LoginUseCaseImpl

val useCaseModule = module {
    single<LoginUseCase> { LoginUseCaseImpl(get()) }
}