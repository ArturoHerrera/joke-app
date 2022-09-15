package com.arthur.joke_app.ui.screens.fcm

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arthur.joke_app.data.model.MyNotification

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
fun FirebasePushNotificationScreen(
    upPress: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        OutlinedButton(
            border = BorderStroke(2.dp, Color.Black),
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = Color.White,
                contentColor = Color.Black,
                disabledContentColor = Color.LightGray
            ),
            elevation = ButtonDefaults.elevation(8.dp),
            shape = RoundedCornerShape(6.dp),
            onClick = {
                val noti = MyNotification(context, "FCM", "Notificacion de prueba.")
                noti.fireNotification()
            }
        ) {
            Text(text = "Fire Notification", fontSize = 16.sp)
        }
    }
}