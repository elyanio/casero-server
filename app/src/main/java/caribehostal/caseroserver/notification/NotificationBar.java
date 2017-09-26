package caribehostal.caseroserver.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import caribehostal.caseroserver.view.action.ActionShow;

/**
 * Created by asio on 2/24/2017.
 */
public class NotificationBar {

    public void notificRegisterClient(Context context_emisor, int id_notification, String title, String text, String code, String bigText) {

        long[] pattern = new long[]{0, 2000, 0};
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context_emisor)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setContentTitle(title)
                .setContentText(text)
                .setTicker(code)
                .setAutoCancel(true)
                .setSound(defaultSound)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(bigText))
                .setVibrate(pattern);

        Intent resultIntent = new Intent(context_emisor, ActionShow.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context_emisor);
        stackBuilder.addParentStack(ActionShow.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context_emisor.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(id_notification, mBuilder.build());
    }

    public void notificRegisterOwner(Context context_emisor, int id_notification, String title, String text, String bigText) {

        long[] pattern = new long[]{0, 2000, 0};
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context_emisor)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true)
                .setSound(defaultSound)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(bigText))
                .setVibrate(pattern);

        Intent resultIntent = new Intent(context_emisor, ActionShow.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context_emisor);
        stackBuilder.addParentStack(ActionShow.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context_emisor.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(id_notification, mBuilder.build());
    }
}
