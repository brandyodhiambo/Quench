package com.brandyodhiambo.quench.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.brandyodhiambo.quench.R
import com.brandyodhiambo.quench.ui.MainActivity

private const val NOTIFICATION_ID = 33
private const val CHANNEL_ID = "ReminderChannel"

fun createChannel(context: Context) {
    val notificationChannel =
        NotificationChannel(CHANNEL_ID, "Channel1", NotificationManager.IMPORTANCE_HIGH)
    val notificationManager = context.getSystemService(NotificationManager::class.java)
    notificationManager.createNotificationChannel(notificationChannel)
}

fun NotificationManager.sendReminderNotification(
    context: Context,
    title: String,
    message: String,

) {
    // Opening the Notification
    val contentIntent = Intent(context, MainActivity::class.java)
    val contentPendingIntent = PendingIntent.getActivity(
        context,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
    )
    // Building the notification
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setContentTitle(title)
        .setContentText(message)
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(message),
        )
        .setSmallIcon(R.drawable.ic_notification)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
        .setContentIntent(contentPendingIntent)
        .build()

    this.notify(NOTIFICATION_ID, builder)
}
