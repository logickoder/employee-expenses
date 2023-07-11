package dev.logickoder.expensemanager.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily

val Typography: Typography
    get() {
        val typography = Typography()
        val fontFamily = FontFamily.SansSerif

        return typography.copy(
            displayLarge = typography.displayLarge.copy(fontFamily = fontFamily),
            displayMedium = typography.displayMedium.copy(fontFamily = fontFamily),
            displaySmall = typography.displaySmall.copy(fontFamily = fontFamily),
            headlineLarge = typography.headlineLarge.copy(fontFamily = fontFamily),
            headlineMedium = typography.headlineMedium.copy(fontFamily = fontFamily),
            headlineSmall = typography.headlineMedium.copy(fontFamily = fontFamily),
            titleLarge = typography.titleLarge.copy(fontFamily = fontFamily),
            titleMedium = typography.titleMedium.copy(fontFamily = fontFamily),
            titleSmall = typography.titleSmall.copy(fontFamily = fontFamily),
            bodyLarge = typography.bodyLarge.copy(fontFamily = fontFamily),
            bodyMedium = typography.bodyMedium.copy(fontFamily = fontFamily),
            bodySmall = typography.bodySmall.copy(fontFamily = fontFamily),
            labelLarge = typography.labelLarge.copy(fontFamily = fontFamily),
            labelMedium = typography.labelMedium.copy(fontFamily = fontFamily),
            labelSmall = typography.labelSmall.copy(fontFamily = fontFamily),
        )
    }

@Composable
fun ErrorText(error: String) {
    Text(
        error,
        style = MaterialTheme.typography.labelSmall.copy(
            color = MaterialTheme.colorScheme.error,
        )
    )
}