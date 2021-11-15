package org.romeo.goodads.koin_modules

import org.koin.dsl.module
import org.romeo.layer_presentation.core.navigation.AndroidNavigator
import org.romeo.layer_presentation.core.navigation.INavigator

val viewModelModule = module {
}

val navigationModule = module {
    single<INavigator> { AndroidNavigator() }
}

val mapperModule = module {
}