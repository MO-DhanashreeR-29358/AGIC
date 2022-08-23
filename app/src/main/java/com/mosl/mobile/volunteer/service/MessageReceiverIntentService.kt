package com.mosl.mobile.volunteer.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.f2prateek.rx.preferences2.Preference
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.mosl.annotations.FcmToken
import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.core.MOSLCommonActivity
import com.mosl.mobile.volunteer.dashboard.ActivityNavigationHelper.Companion.ARG_NOTIFICATION_CATEGORY
import dagger.android.AndroidInjection
import timber.log.Timber
import java.util.*
import javax.inject.Inject


class MessageReceiverIntentService : FirebaseMessagingService() {

    var TAG = "FirebaseMessagingService"
    var notificationCount = 0

    @Inject
    @field:FcmToken
    lateinit var fcmtokenPref: Preference<String>

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onNewToken(token: String?) {
        Log.d(TAG, "Refreshed token: " + token!!)
        FirebaseMessaging.getInstance().subscribeToTopic("agic");
        if (token.isNullOrBlank()) {
            fcmtokenPref.delete()
        } else {
            fcmtokenPref.set(token)
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage);


        val extras = Bundle()

        for ((key, value) in remoteMessage.data) {
            extras.putString(key, value)
        }

        showNotificationIfPossible(extras)
    }

    private fun showNotificationIfPossible(extras: Bundle) {

        val message = extras.getString("message")
        val title = extras.getString("title")

        // Send a notification if there is a message or title, otherwise just send data

        Timber.d("message =[$message]")
        Timber.d("title =[$title]")

        if (message != null && message.length != 0 || title != null && title.length != 0) {
            Timber.d("create notification")
            notificationCount++
            createNotification(extras)
        }
    }

    private fun createNotification(extras: Bundle) {
        val requestCode = Random().nextInt()
        val mNotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "my_channel_01"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            /* Create or update. */
            val mChannel = NotificationChannel(
                channelId, "Channel human readable title",
                NotificationManager.IMPORTANCE_HIGH
            )
            mChannel.enableLights(true)
            // Sets the notification light color for notifications posted to this
            // channel, if the device supports this feature.
            mChannel.lightColor = Color.RED
            mChannel.enableVibration(true)
            mNotificationManager.createNotificationChannel(mChannel)
        }

        val notificationIntent = Intent(this, MOSLCommonActivity::class.java)
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        notificationIntent.putExtra(ARG_NOTIFICATION_CATEGORY, extras.getString("category"))

        val contentIntent = PendingIntent.getActivity(
            this, requestCode, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val mBuilder =
            NotificationCompat.Builder(this, channelId).setSmallIcon(R.mipmap.ic_launcher)
                .setColor(ContextCompat.getColor(this, R.color.blue))
                .setContentTitle(extras.getString("title"))
                .setTicker(extras.getString("title"))
                .setContentText(extras.getString("message"))
                .setContentIntent(contentIntent)
                .setAutoCancel(true)


        mBuilder.build().flags =
            mBuilder.build().flags or Notification.FLAG_AUTO_CANCEL

        mBuilder.setDefaults(Notification.DEFAULT_ALL)
        mNotificationManager.notify(notificationCount, mBuilder.build())
    }
}