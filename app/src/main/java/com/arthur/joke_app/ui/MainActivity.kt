package com.arthur.joke_app.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arthur.joke_app.data.model.MyNotification
import com.arthur.joke_app.navigation.Destinations
import com.arthur.joke_app.navigation.JokeAppNavGraph
import com.arthur.joke_app.ui.screens.login.LoginViewModel
import com.arthur.joke_app.ui.theme.JokeAppTheme
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val viewModel by viewModels<LoginViewModel>()

    companion object {
        lateinit var message: MutableState<String>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            JokeAppTheme {
                navController = rememberNavController()
                message = remember { mutableStateOf("") }

                requestFirebaseMessagingToken()

                Scaffold {
                    JokeAppNavGraph(navController = navController)
                    checkAuthState()
                }
            }
        }
    }

    private fun checkAuthState() {
        if(viewModel.isUserAuthenticated) {
            navController.navigate(Destinations.HOME_SCREEN)
        }
    }

    private fun requestFirebaseMessagingToken(){
        FirebaseMessaging.getInstance().token
            .addOnSuccessListener(this@MainActivity) { instanceIdResult ->
                val updatedToken: String = instanceIdResult
                //TODO Save token to handle notifications. Rooms, shared
                Log.e("FCMToken", "Token from MainActivity: $updatedToken" )

                val noti = MyNotification(this, "Token:", "$updatedToken")
                noti.fireNotification()

                try {
                    val clipboard = this?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip: ClipData = ClipData.newPlainText("token", updatedToken)
                    clipboard.setPrimaryClip(clip)
                    Toast.makeText(this,"Token copiado al portapapeles", Toast.LENGTH_LONG).show()
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }
    }
}
