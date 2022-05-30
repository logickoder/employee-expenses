package dev.logickoder.employee_expenses.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import dev.logickoder.employee_expenses.R
import dev.logickoder.employee_expenses.ui.screens.shared.AppBar
import dev.logickoder.employee_expenses.ui.screens.shared.AppBarIconButton
import dev.logickoder.employee_expenses.ui.screens.shared.InputWithField
import dev.logickoder.employee_expenses.ui.theme.Theme
import dev.logickoder.employee_expenses.ui.theme.primaryPadding
import dev.logickoder.employee_expenses.ui.theme.secondaryPadding

@Composable
fun ProfileScreen(
    profileState: ProfileState,
    modifier: Modifier = Modifier,
    sectionSpacing: Dp = secondaryPadding(),
) = with(profileState) {

    if (showGallery) {
        SelectAvatar()
    }

    Scaffold(
        modifier = modifier,
        backgroundColor = Theme.colors.surface,
        topBar = {
            AppBar(
                title = stringResource(id = R.string.app_name),
                navigateBack = navigateToHomeScreen,
                actions = {
                    AppBarIconButton(
                        icon = Icons.Outlined.Logout,
                        onClick = logout
                    )
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(primaryPadding()),
                verticalArrangement = Arrangement.spacedBy(
                    sectionSpacing,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    BoxWithConstraints(
                        modifier = Modifier
                            .clickable {
                                showGallery = true
                            }
                            .clipToBounds(),
                        content = {
                            val imageModifier = Modifier
                                .size(maxWidth / 3)
                                .clip(CircleShape)
                            if (avatar == null) {
                                Icon(
                                    modifier = imageModifier,
                                    imageVector = Icons.Outlined.Person,
                                    contentDescription = stringResource(id = R.string.profile),
                                    tint = Color.Black,
                                )
                            } else {
                                Image(
                                    modifier = imageModifier,
                                    painter = rememberAsyncImagePainter(photo.uri),
                                    contentScale = ContentScale.Crop,
                                    contentDescription = null
                                )
                            }
                            Icon(
                                modifier = Modifier.align(Alignment.BottomEnd),
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = null,
                                tint = Color.Black,
                            )
                        }
                    )
                    InputWithField(
                        title = stringResource(id = R.string.name),
                        value = name,
                        onValueChanged = {
                            name = it
                        },
                    )
                    InputWithField(
                        title = stringResource(id = R.string.job_description),
                        value = jobDescription,
                        onValueChanged = {
                            jobDescription = it
                        },
                    )
                    InputWithField(
                        title = stringResource(id = R.string.department),
                        value = department,
                        onValueChanged = {
                            department = it
                        },
                    )
                    InputWithField(
                        title = stringResource(id = R.string.location),
                        value = location,
                        onValueChanged = {
                            location = it
                        },
                    )
                }
            )
        }
    )
}
