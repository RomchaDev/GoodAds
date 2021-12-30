package org.romeo.goodads.koin_modules

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.romeo.goodads.navigation.AndroidNavigator
import org.romeo.layer_domain.entity.ad.Ad
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
import org.romeo.layer_presentation.main.other_ads.OtherAdsViewModel
import org.romeo.layer_presentation.main.requests.AdvertisingRequestsViewModel
import org.romeo.layer_presentation.main.choose_ad_screen.ChooseAdViewModel
import org.romeo.layer_presentation.main.create_distribution.CreateDistributionViewModel
import org.romeo.layer_presentation.main.create_edit_ad.CreateEditAdViewModel
import org.romeo.layer_presentation.main.login.LoginViewModel
import org.romeo.layer_presentation.main.user_request_full.UserRequestViewModel
import org.romeo.layer_presentation.main.advertisers.AdvertisersViewModel

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { HomeViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { AdvertisersViewModel(get(), get(), get(), get()) }
    viewModel { ChooseAdViewModel(get(), get()) }
    viewModel { (adId: String) -> MyAdFullViewModel(get(), get(), get(), adId) }
    viewModel { AdvertisingRequestsViewModel(get(), get(), get(), get()) }
    viewModel { (adId: String) -> AdRequestViewModel(get(), get(), get(), get(), adId) }
    viewModel { (userId: String) -> UserRequestViewModel(get(), get(), userId) }
    viewModel { OtherAdsViewModel(get(), get(), get()) }
    viewModel { (ad: Ad) -> CreateEditAdViewModel(get(), ad) }
    viewModel { (adId: String) -> AdListItemFullViewModel(get(), get(), get(), get(), adId) }
    viewModel { (adId: String) -> CreateDistributionViewModel(get(), get(), adId) }
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
    factory<AdFullToDistributionCommand> { AdFullToDistributionCommandImpl() }
}

val mapperModule = module {
}