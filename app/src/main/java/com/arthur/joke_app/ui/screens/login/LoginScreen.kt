package com.arthur.joke_app.ui.screens.login

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arthur.joke_app.R
import com.arthur.joke_app.ui.screens.home.HomeViewModel
import com.arthur.joke_app.ui.theme.QuickSand
import com.arthur.joke_app.ui.theme.SuperWhite
import com.arthur.joke_app.utils.ExtFunctions.gradientBackground
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.random.Random

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun LoginScreen(
    navigateToHome: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val uiState by viewModel.uiState.collectAsState()

    val acceptTerms = remember { mutableStateOf(false) }

    Scaffold(
        scaffoldState = scaffoldState
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Surface() {
                if (uiState.loading) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                        color = MaterialTheme.colors.secondary
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .gradientBackground(
                        listOf(
                            Color(
                                Random.nextInt(256),
                                Random.nextInt(256),
                                Random.nextInt(256)
                            ), Color(
                                Random.nextInt(256),
                                Random.nextInt(256),
                                Random.nextInt(256)
                            )
                        ),
                        angle = 45f
                    )
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        modifier = Modifier.padding(bottom = 16.dp),
                        fontFamily = QuickSand,
                        fontWeight = FontWeight.Black,
                        fontSize = 48.sp,
                        style = MaterialTheme.typography.h1.copy(
                            shadow = Shadow(
                                color = colorResource(id = R.color.black),
                                offset = Offset(x = 2f, y = 4f),
                                blurRadius = 0.1f
                            )
                        ),
                        color = SuperWhite
                    )
                    Image(
                        painter = painterResource(
                            id = R.drawable.ic_laughing_logo
                        ),
                        contentDescription = null,
                        modifier = Modifier.padding(start = 120.dp, end = 120.dp, bottom = 32.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(bottom = 16.dp, start = 0.dp, end = 0.dp)
                            .fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = acceptTerms.value,
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.Black,
                                checkmarkColor = Color.White,
                            ),
                            onCheckedChange = { acceptTerms.value = it }
                        )
                        Text(
                            text = stringResource(id = R.string.terms_condition),
                            style = MaterialTheme.typography.body2.copy(
                                shadow = Shadow(
                                    color = colorResource(id = R.color.black),
                                    offset = Offset(x = 2f, y = 4f),
                                    blurRadius = 0.1f
                                )
                            ),
                            fontFamily = QuickSand,
                            color = SuperWhite,
                            fontWeight = FontWeight.Black,
                            textAlign = TextAlign.Start
                        )
                    }
                    OutlinedButton(
                        border = BorderStroke(2.dp, Color.Black),
                        modifier = Modifier.fillMaxWidth(),
                        enabled = acceptTerms.value,
                        colors = ButtonDefaults.outlinedButtonColors(
                            backgroundColor = Color.White,
                            contentColor = Color.Black,
                            disabledContentColor = Color.LightGray
                        ),
                        elevation = ButtonDefaults.elevation(8.dp),
                        shape = RoundedCornerShape(6.dp),
                        onClick = navigateToHome
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.ic_google_logo
                            ),
                            contentDescription = null
                        )
                        Text(
                            text = stringResource(id = R.string.sign_in_google),
                            modifier = Modifier.padding(6.dp),
                            fontFamily = QuickSand,
                            fontWeight = FontWeight.Black,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}