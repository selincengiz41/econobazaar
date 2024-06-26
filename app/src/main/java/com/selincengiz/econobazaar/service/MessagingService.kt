package com.selincengiz.econobazaar.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.selincengiz.econobazaar.presentation.MainActivity

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val title = message.notification?.title
        val desc = message.notification?.body

        if (title != null && desc != null) {
            showNotification(this, title, desc)
        }
    }

    private fun showNotification(context: Context, title: String, desc: String) {
        val builder: NotificationCompat.Builder

        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(context, MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(
            context,
            1,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val channelId = "CHANNEL_ID"
        val channelName = "CHANNEL_NAME"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            notificationManager.createNotificationChannel(channel)

            builder = NotificationCompat.Builder(context, channelId)
                .setContentTitle(title)
                .setContentText(desc)
                .setSmallIcon(com.selincengiz.core.common.R.drawable.logo)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        } else {
            builder = NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setContentText(desc)
                .setSmallIcon(com.selincengiz.core.common.R.drawable.logo)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
        }

        notificationManager.notify(1, builder.build())
    }
}