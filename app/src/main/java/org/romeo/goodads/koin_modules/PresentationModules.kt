package org.romeo.goodads.koin_modules

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.romeo.goodads.navigation.AndroidNavigator
import org.romeo.layer_presentation.core.main.home.HomeViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_presentation.core.navigation.commands.impl.LoginToHomeCommandImpl
import org.romeo.layer_presentation.core.navigation.commands.interfaces.LoginToHomeCommand
import org.romeo.layer_presentation.core.main.login.LoginViewModel

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { HomeViewModel(get()) }
}

val navigationModule = module {
    single { AndroidNavigator() }
    single<AppNavigator> { get<AndroidNavigator>() }

    factory<LoginToHomeCommand> { LoginToHomeCommandImpl() }
}

val mapperModule = module {
}