package dev.logickoder.expense_manager.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.dimensionResource
import dev.logickoder.expense_manager.R

@Composable
@ReadOnlyComposable
fun secondaryPadding() = primaryPadding() / 4 * 2

@Composable
@ReadOnlyComposable
fun primaryPadding() = dimensionResource(id = R.dimen.padding)
