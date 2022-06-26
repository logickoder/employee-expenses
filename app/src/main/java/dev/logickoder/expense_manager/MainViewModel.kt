package dev.logickoder.expense_manager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev.logickoder.expense_manager.data.repository.DataRepository
import dev.logickoder.expense_manager.data.repository.UserRepository
import dev.logickoder.expense_manager.di.Provider
import dev.logickoder.expense_manager.ui.screens.home.HomeState
import dev.logickoder.expense_manager.ui.screens.login.LoginState
import dev.logickoder.expense_manager.ui.screens.profile.ProfileState
import dev.logickoder.expense_manager.ui.screens.shared.filter_form.FilterFormState

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
