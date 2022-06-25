package dev.logickoder.expense_manager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dev.logickoder.expense_manager.data.repository.DataRepository
import dev.logickoder.expense_manager.di.Provider
import dev.logickoder.expense_manager.ui.screens.home.HomeState
import dev.logickoder.expense_manager.ui.screens.login.LoginState
import dev.logickoder.expense_manager.ui.screens.profile.ProfileState
import dev.logickoder.expense_manager.ui.screens.shared.filter_form.FilterFormState

class MainViewModel(app: Application) : AndroidViewModel(app) {
    val loginState = LoginState()
    val profileState = ProfileState()
    val homeState = HomeState(
        repository = (app as Provider).provider[DataRepository::class.java]!!,
        filterFormState = FilterFormState(),
    )
}
