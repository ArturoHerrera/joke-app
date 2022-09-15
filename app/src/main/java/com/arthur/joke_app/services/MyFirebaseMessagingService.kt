package com.arthur.joke_app.services

import android.util.Log
import com.arthur.joke_app.data.model.MyNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    val TAG: String = "FCMToken"

    override fun onNewToken(token: String) {
        Log.d(TAG, "FCMToken: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
        }

        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            val noti = MyNotification(applicationContext,it.title.toString(),it.body.toString())
            noti.fireNotification()
        }

    }
}

