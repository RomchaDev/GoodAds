package org.romeo.goodads.koin_modules

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.romeo.goodads.navigation.AndroidNavigator
import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_presentation.core.main.BaseAdFullViewModel
import org.romeo.layer_presentation.main.home.HomeViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_presentation.core.navigation.commands.impl.*
import org.romeo.layer_presentation.core.navigation.commands.interfaces.*
import org.romeo.layer_presentation.main.ad_list_item_full.AdListItemFullViewModel
import org.romeo.layer_presentation.core.navigation.commands.impl.AnyToAdFullCommandImpl
import org.romeo.layer_presentation.core.navigation.commands.impl.AnyToChoseAdCommandImpl
import org.romeo.layer_presentation.core.navigation.commands.impl.AnyToCreateEditAdCommandImpl
import org.romeo.layer_presentation.core.navigation.commands.impl.LoginToHomeCommandImpl
import org.romeo.layer_presentation.core.navigation.commands.impl.RequestsToAdRequestCommandImpl
import org.romeo.layer_presentation.core.navigation.commands.interfaces.AnyToAdFullCommand
import org.romeo.layer_presentation.core.navigation.commands.interfaces.AnyToChoseAdCommand
import org.romeo.layer_presentation.core.navigation.commands.interfaces.AnyToCreateEditAdCommand
import org.romeo.layer_presentation.core.navigation.commands.interfaces.LoginToHomeCommand
import org.romeo.layer_presentation.core.navigation.commands.interfaces.RequestsToAdRequestCommand
import org.romeo.layer_presentation.main.ad_request_full.AdRequestViewModel
import org.romeo.layer_presentation.main.ad_screen.MyAdFullViewModel
import org.romeo.layer_presentation.main.ads.AdsViewModel
import org.romeo.layer_presentation.main.requests.RequestsViewModel
import org.romeo.layer_presentation.main.choose_ad_screen.ChooseAdViewModel
import org.romeo.layer_presentation.main.create_edit_ad.CreateEditAdViewModel
import org.romeo.layer_presentation.main.login.LoginViewModel
import org.romeo.layer_presentation.main.user_request_full.UserRequestViewModel
import org.romeo.layer_presentation.main.users.UsersViewModel

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { HomeViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { UsersViewModel(get(), get(), get(), get()) }
    viewModel { ChooseAdViewModel(get(), get()) }
    viewModel { (adId: String) -> MyAdFullViewModel(get(), get(), adId) }
    viewModel { RequestsViewModel(get(), get(), get(), get(), get()) }
    viewModel { (adId: String) -> AdRequestViewModel(get(), get(), adId) }
    viewModel { (userId: String) -> UserRequestViewModel(get(), get(), userId) }
    viewModel { AdsViewModel(get(), get(), get()) }
    viewModel { (ad: Ad) -> CreateEditAdViewModel(get(), ad) }
    viewModel { (adId: String) -> AdListItemFullViewModel(get(), get(), get(), adId) }
}

val navigationModule = module {
    single { AndroidNavigator() }
    single<AppNavigator> { get<AndroidNavigator>() }

    factory<LoginToHomeCommand> { LoginToHomeCommandImpl() }
    factory<AnyToChoseAdCommand> { AnyToChoseAdCommandImpl() }
    factory<AnyToAdFullCommand> { AnyToAdFullCommandImpl() }
    factory<AnyToCreateEditAdCommand> { AnyToCreateEditAdCommandImpl() }
    factory<RequestsToAdRequestCommand> { RequestsToAdRequestCommandImpl() }
    factory<RequestsToUserRequestCommand> { RequestsToUserRequestCommandImpl() }
    factory<AdsToAdListItemFullCommand> { AdsToAdListItemFullCommandImpl() }
}

val mapperModule = module {
}