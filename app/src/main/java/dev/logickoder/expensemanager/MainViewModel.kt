package dev.logickoder.expensemanager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev.logickoder.expensemanager.data.repository.DataRepository
import dev.logickoder.expensemanager.data.repository.UserRepository
import dev.logickoder.expensemanager.di.Provider
import dev.logickoder.expensemanager.ui.screens.home.HomeState
import dev.logickoder.expensemanager.ui.screens.login.LoginState
import dev.logickoder.expensemanager.ui.screens.profile.ProfileState
import dev.logickoder.expensemanager.ui.screens.shared.filter_form.FilterFormState

class MainViewModel(app: Application) : AndroidViewModel(app) {
    val loginState: LoginState = LoginState()
    val profileState: ProfileState
    val homeState: HomeState

    init {
        val provider = app as Provider
        val userRepository = provider.provider[UserRepository::class.java]!!
        val dataRepository = provider.provider[DataRepository::class.java]!!
        homeState = HomeState(
            repository = dataRepository,
            filterFormState = FilterFormState(dataRepository),
        )
        profileState = ProfileState(userRepository, viewModelScope)
    }
}
