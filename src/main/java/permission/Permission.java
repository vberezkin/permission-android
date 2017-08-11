package permission;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by berezkin on 14/02/16.
 */
public class Permission {

    @SuppressWarnings("WeakerAccess")
    static public boolean isPermissionGranted(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    static public boolean isLocationPermissionGranted(Context context) {
        return isPermissionGranted(context, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    static public boolean isReadPhoneStatePermissionGranted(Context context) {
        return isPermissionGranted(context, Manifest.permission.READ_PHONE_STATE);
    }

    static public boolean isRecordAudioPermissionGranted(Context context) {
        return isPermissionGranted(context, Manifest.permission.RECORD_AUDIO);
    }

    static public boolean isWriteExternalStoragePermissionGranted(Context context) {
        return isPermissionGranted(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }
}
