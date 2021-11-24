package org.romeo.goodads.koin_modules

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.romeo.goodads.navigation.AndroidNavigator
import org.romeo.layer_presentation.main.home.HomeViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_presentation.core.navigation.commands.impl.AnyToAdFullCommandImpl
import org.romeo.layer_presentation.core.navigation.commands.impl.AnyToChoseAdCommandImpl
import org.romeo.layer_presentation.core.navigation.commands.impl.LoginToHomeCommandImpl
import org.romeo.layer_presentation.core.navigation.commands.interfaces.AnyToAdFullCommand
import org.romeo.layer_presentation.core.navigation.commands.interfaces.AnyToChoseAdCommand
import org.romeo.layer_presentation.core.navigation.commands.interfaces.LoginToHomeCommand
import org.romeo.layer_presentation.main.ad_screen.AdFullViewModel
import org.romeo.layer_presentation.main.choose_ad_screen.ChooseAdViewModel
import org.romeo.layer_presentation.main.login.LoginViewModel
import org.romeo.layer_presentation.main.users.UsersViewModel

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { HomeViewModel(get(), get(), get(), get(), get()) }
    viewModel { UsersViewModel(get(), get(), get()) }
    viewModel { ChooseAdViewModel(get(), get()) }
    viewModel { (adId: String) -> AdFullViewModel(get(), get(), adId) }
}

val navigationModule = module {
    single { AndroidNavigator() }
    single<AppNavigator> { get<AndroidNavigator>() }

    factory<LoginToHomeCommand> { LoginToHomeCommandImpl() }
    factory<AnyToChoseAdCommand> { AnyToChoseAdCommandImpl() }
    factory<AnyToAdFullCommand> { AnyToAdFullCommandImpl() }
}

val mapperModule = module {
}