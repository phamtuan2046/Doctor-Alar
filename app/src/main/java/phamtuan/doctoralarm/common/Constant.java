package phamtuan.doctoralarm.common;

/**
 * Created by DinhAnh-PC on 3/14/2016.
 */
public class Constant {
    // setup notification
    public static final String INTENT_FILTER_NOTIFICATION = "com.vn.ominext.medisafe.notification.fullwakeup";
    public static final String INTENT_FILTER_SHOW_DIALOG = "com.vn.ominext.medisafe.notification.showdialog";
    public static final String INTENT_FILTER_CHECK_EVERYDAY = "com.vn.ominext.medisafe.notification.checkeveryday";
    public static final String INTENT_FILTER_NOTIFICATION_INVENTORY = "com.vn.ominext.medisafe.notification.inventory";

    public static final String KEY_SEND_DATA_NOTIFICATION = "dataNotification";
    public static final String KEY_SEND_NOTIFICATION_OBJ = "objectNotification";
    public static final String KEY_SHOW_DIALOG_NOTIFICATION = "showDialogNotification";
    public static final long DEFAULT_TIME_REPEAT = 10 * 60 * 1000;
    public static final int REQUEST_CODE = 1000;


    public static final String TAG_WAKE_LOCK_APP = "MyLock";

    public static final int DEFAULT_HOUR = 10;
    public static final int DEFAULT_MINUTE = 50;
    public static final int DEFAULT_SECOND = 0;

    //type notification
    public static final int TYPE_MEDICATION = 100;
    public static final int TYPE_APPOINTMENT = 200;
    public static final int TYPE_INVENTORY = 300;

    // type notification of medication
    public static final int TYPE_EVERY_DAY = 0;
    public static final int EVERY_DAY_BY_DAY = 11;
    public static final int EVERY_DAY_BY_WEEK = 12;
    public static final int EVERY_DAY_BY_MONTH = 13;

    public static final int TYPE_SPECIFIC_DAY = 1;
    public static final int TYPE_BY_INVENTORY = 30;

    public static final String KEY_DAY = "D";
    public static final String KEY_WEED = "W";
    public static final String KEY_MONTH = "M";

    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int THURSDAY = 4;
    public static final int FRIDAY = 5;
    public static final int SATURDAY = 6;
    public static final int SUNDAY = 7;

    public static final String MON = "Mon";
    public static final String TUE = "Tue";
    public static final String WED = "Wed";
    public static final String THU = "Thu";
    public static final String FRI = "Fri";
    public static final String SAT = "Sat";
    public static final String SUN = "Sun";

//    public static final String START_DATE_TYPE_SPECIFIC_DATE = 0;
//    public static final String START_DATE_TYPE_WHEN_MEDICATION_FINISH = 1;
//
//    public static final String END_DATE_TYPE_SPECIFIC_DATE = 0;
//    public static final int END_DATE_TYPE_AFTER = 1;
//    public static final int END_DATE_TYPE_INFINITIVE = 2;

    /**
     * ngochm
     **/
    public static boolean DEBUG = false;

    public static final int PAGE_POSITION_LEFT = 0;
    public static final int PAGE_POSITION_CENTER = 1;
    public static final int PAGE_POSITION_RIGHT = 2;

    public static final int PAGE_COUNT = 3;

    public static final String SUPER_STATE = "super_state";
    public static final String ADAPTER_STATE = "adapter_state";

    public static final String LOG_TAG = "InfiniteViewPager";

    public static final String SHARE_PREFERENCE_NAME = "medication";
    public static final String KEY_CHECK_ASK_ADD = "check_group_add";
    public static final String KEY_CHECK_ASK_EDIT = "check_group_edit";
    public static final String KEY_IS_FIRST = "first_row";
    public static final String KEY_REMAINING_UNIT = "RemainingUnit";
    public static final String KEY_DOSAGE = "Dosage";
    public static final String KEY_REMAIN_WHEN = "RemainingWhen";
    public static final String KEY_CHECK_REMAIN_WHEN = "IsRemainingWhen";
    public static final String KEY_UNIT = "unit";
    public static final int ONEHOUR = 60 * 60 * 1000;


    public static final int ACTIVED_STATE = 0;
    public static final int DONE_STATE = 1;
    public static final int IGNORE = 2;
    public static final int SKIP_STATE = 3;
    public static final int HOLD_STATE = 4;
    public static final int MEDICATION_KEY = 0;
    public static final int SINGLE_INTAKE_KEY = 1;
    public static final int APPOIMENT_KEY = 2;
    public static final int SECTION_KEY = 3;
    public static final int MEDICATION_STATE_KEY = 4;

    //Trungnv update
    public static final String KEY_UNITS = "UNIT"; // key for get unit value
    public static final String KEY_MEDICATION_ID = "MEDICATION _ID";
    public static final String KEY_INVENTORY = "inventory";
    public static final String KEY_ACTIVITY = "KEY_ACTIVITY";
    public static final String KEY_TASK = "KEY_TASK";
    public static final String KEY_MEDICATION_STATE = "MEDICATION_STATE";
    public static final String FLAG_CREAT = "create";
    public static final String FLAG_EDIT = "edit";
    public static final String SPACSE = " ";
    public final static String SETTING_NOTIFY_HOUR = "hourNotify";
    public final static String SETTING_NOTIFY_MINUTE = "minuteNotify";
    public final static String SETTING_NOTIFY_SECOND = "secondNotify";
    public final static String SHARED_PREFERENCE_SETTING = "setting";

    // flag
    public final static String FLAG = "flag_startactivity";
    public final static String FLAG_SINGLEINTAKE = "from_singleintake";

    // result code
    public final static int RESULT_APPOINTMENT = 0;
    public final static int RESULT_SINGLEINTAKE_GROUP = 1;
    public final static int RESULT_SINGLEINTAKE_DOCTOR = 2;


    public final static int MEDICATION_SCHEDULE_EVERYDAY = 0;
    public final static int MEDICATION_SCHEDULE_SPECIFIX = 1;

}
