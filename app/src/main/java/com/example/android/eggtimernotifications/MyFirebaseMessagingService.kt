package com.example.android.eggtimernotifications


import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        Log.d(TAG, "From: ${message?.from}")

        // TODO Setup 3.5 check messages for data
        // Check if message contains a data payload.
        message?.data?.let {
            Log.d(TAG, "Message data payload: " + message.data)
        }


        //TODO Setup 3.6 check messages for notification and call sendNotification
        message?.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            sendNotification(it.body!!)
        }


    }

    // TODO: Step 3.2 log registration token
    // [START on_new_token]
    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    override fun onNewToken(token: String) {
        Log.d("Tag on new token", "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String?) {

    }
    // [END on_new_token]

    private fun sendNotification(messageBody: String) {
        val notificationManager = ContextCompat.getSystemService(
            applicationContext,
        NotificationManager::class.java) as NotificationManager
        notificationManager.sendNotification(messageBody, applicationContext)

    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }

}