package com.example.myapplication.FCM;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.core.app.NotificationCompat;

//import com.firebase.jobdispatcher.Constraint;
//import com.firebase.jobdispatcher.FirebaseJobDispatcher;
//import com.firebase.jobdispatcher.GooglePlayDriver;
//import com.firebase.jobdispatcher.Job;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    Bitmap bitmap = null;
    String CHANNEL_ID = "my_channel_01";
    CharSequence name = "my_channel";
    String Description = "This is my channel";
    String title = "", message = "", imageUri = "";

//    Solve same price display in recent item,favorite items and cart items,
//    For FCM notification

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
//        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
//        if (remoteMessage.getData().size() > 0) {
//            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
//
//            if (/* Check if data needs to be processed by long running job */ true) {
//                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
//                scheduleJob();
//            } else {
//                // Handle message within 10 seconds
//                handleNow();
//            }
//
//        }

        // Check if message contains a notification payload.
//        if (remoteMessage.getNotification() != null) {
//            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
////            sendNotification(remoteMessage.getNotification().getBody());
//        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.


        //title will contain the Push Message for Scheduel notification
        if (remoteMessage.getData().get("title") != null) {
            title = remoteMessage.getData().get("title");
        }

        //message will contain the Push Message title for FCM or it's description for Scheduel notification
        if (remoteMessage.getData().get("message") != null) {
            message = remoteMessage.getData().get("message");
        }

        //imageUri will contain URL of the image to be displayed with Notification
        if (remoteMessage.getData().get("image") != null) {
            imageUri = remoteMessage.getData().get("image");
        }

        //If the key AnotherActivity has  value as True then when the user taps on notification, in the app AnotherActivity will be opened.
        //If the key AnotherActivity has  value as False then when the user taps on notification, in the app MainActivityNew will be opened.
//        String TrueOrFlase = remoteMessage.getData().get("AnotherActivity");

        //To get a Bitmap image from the URL received
        if (imageUri != null && !imageUri.equals("null") && !imageUri.equals("")) {
            bitmap = getBitmapfromUrl(imageUri);
        }

        sendNotification(title, message, bitmap);

    }
    // [END receive_message]

    /**
     * Schedule a job using FirebaseJobDispatcher.
     */
    private void scheduleJob() {
        // [START dispatch_job]
//        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
//        Job myJob = dispatcher.newJobBuilder()
//                .setService(MyJobService.class)
//                .setTag("my-job-tag")
//                .build();
//        dispatcher.schedule(myJob);
        // [END dispatch_job]
    }

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.app_name);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("FCM Message")
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     */

    private void sendNotification(String mTitle, String messageBody, Bitmap image) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.putExtra("AnotherActivity", TrueOrFalse);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);

//        Bitmap rawBitmap = BitmapFactory.decodeResource(getResources(),
//                R.mipmap.ic_launcher);
//                .setLargeIcon(image)/*Notification icon image*/
//        notificationBuilder.setSmallIcon(getNotificationIcon(notificationBuilder))


//        Bitmap big_bitmap_image = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                mChannel.setDescription(Description);
                mChannel.enableLights(true);
                mChannel.setLightColor(Color.RED);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                mChannel.setShowBadge(false);
                notificationManager.createNotificationChannel(mChannel);
            }

            if (image != null && !image.equals("null") && !image.equals("")) {
                NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle().bigPicture(image);

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle(mTitle)
                            .setContentText(messageBody)
                            .setStyle(style)/*Notification with Image*/
                            .setAutoCancel(true)
//                .setLargeIcon(image)
                            .setSound(defaultSoundUri)
                            .setContentIntent(pendingIntent);
                } else {
                    notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle(mTitle)
                            .setContentText(messageBody)
                            .setStyle(style)/*Notification with Image*/
                            .setAutoCancel(true)
//                .setLargeIcon(image)
                            .setSound(defaultSoundUri)
                            .setContentIntent(pendingIntent);
                }
            } else {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle(mTitle)
                            .setContentText(messageBody)
                            .setAutoCancel(true)
                            .setSound(defaultSoundUri)
                            .setContentIntent(pendingIntent);
                } else {
                    notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle(mTitle)
                            .setContentText(messageBody)
                            .setAutoCancel(true)
                            .setSound(defaultSoundUri)
                            .setContentIntent(pendingIntent);
                }
            }


            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private int getNotificationIcon(NotificationCompat.Builder notificationBuilder) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int color = 0x008000;
            notificationBuilder.setColor(color);
            return R.mipmap.ic_launcher;

        }
        return R.mipmap.ic_launcher;
    }

    /*
     *To get a Bitmap image from the URL received
     * */
    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }
}
