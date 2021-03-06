package mobilize.me.Services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

// Use IntentService which will queue each call to startService(Intent) through onHandleIntent and then shutdown
//
// NOTE that this implementation intentionally doesn't use PowerManager/WakeLock or deal with power issues
// (if the device is asleep, AlarmManager wakes up for BroadcastReceiver onReceive, but then might sleep again)
// (can use PowerManager and obtain WakeLock here, but STILL might not work, there is a gap)
// (this can be mitigated but for this example this complication is not needed)
// (it's not critical if user doesn't see new deals until phone is awake and notification is sent, both)
public class Service extends IntentService {

   //private static int count;

   private App app;

   public Service() {
      super("Deal Service");
   }

   @Override
   public void onStart(Intent intent, int startId) {
      super.onStart(intent, startId);
   }

   @Override
   public void onHandleIntent(Intent intent) {
      Log.i(Constants.LOG_TAG, "DealService invoked, checking for new deals (will notify if present)");
      this.app = (App) getApplication();
      if (app.connectionPresent()) {
//         // parse the feed
//         app.setSectionList(app.getParser().parse());
//
//         // get list of currentDealIds from first section (Daily Deals, always 4 items)
//         List<Long> currentDealIds = app.parseItemsIntoDealIds(app.getSectionList().get(0).getItems());
//
//         // previous deals - stored as prefs because it's easier than files for simple data
//         // and we need something persistent when service wakes up (previous app memory may not still be around)
//         List<Long> previousDealIds = app.getPreviousDealIds();
//
//         // store currentDealIds as PREVIOUS so we're up to date next time around
//         app.setPreviousDealIds(currentDealIds);
//
//         // do we have any NEW ids?
//         List<Long> newDealIdsList = this.checkForNewDeals(previousDealIds, currentDealIds);
//         if (!newDealIdsList.isEmpty()) {
//            this.sendNotification(this, newDealIdsList.size());
//         }

         // uncomment to force notification, new deals or not
         /*
         count++;
         if (count == 1) {
            SystemClock.sleep(5000);
            this.sendNotification(this, 1);
         }
         */
      } else {
         Log.w(Constants.LOG_TAG, "Network connection not available, not checking for new deals");
      }
   }

   // instead of using entire Item, use itemId, it's unique enough to know what's new         
   private List<Long> checkForNewDeals(List<Long> previousDealIds, List<Long> currentDealIds) {
      List<Long> newDealsList = new ArrayList<Long>();
      for (Long id : currentDealIds) {
         if ((id != 0) && !previousDealIds.contains(id)) {
            Log.d(Constants.LOG_TAG, "New deal found: " + id);
            newDealsList.add(id);
         }
      }
      return newDealsList;
   }

//   private void sendNotification(Context context, int numNewDeals) {
//      Intent notificationIntent = new Intent(context, List.class);
//      notificationIntent.putExtra(Constants.FORCE_RELOAD, true);
//      PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
//
//      NotificationManager notificationMgr =
//               (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//      Notification notification =
//               new Notification(android.R.drawable.star_on, getString(R.string.deal_service_ticker), System
//                        .currentTimeMillis());
//      notification.flags |= Notification.FLAG_AUTO_CANCEL;
//      notification.setLatestEventInfo(context, getResources().getString(R.string.deal_service_title), getResources()
//               .getQuantityString(R.plurals.deal_service_new_deal, numNewDeals, numNewDeals), contentIntent);
//      notificationMgr.notify(0, notification);
//   }
}
