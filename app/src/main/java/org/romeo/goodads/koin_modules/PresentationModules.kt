package org.romeo.goodads.koin_modules

import org.koin.dsl.module
import org.romeo.layer_presentation.core.navigation.AndroidNavigator
import org.romeo.layer_presentation.core.navigation.AppNavigator

val viewModelModule = module {
}

val navigationModule = module {
    single<AppNavigator> { AndroidNavigator() }
}

val mapperModule = module {
}