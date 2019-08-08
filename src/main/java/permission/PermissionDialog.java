package permission;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import androidx.annotation.StringRes;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by berezkin on 23/02/16.
 */
public class PermissionDialog {

    public PermissionDialog(Activity activity,
                            int permissionRequest,
                            String[] permissions,
                            @StringRes int permissionRationalTitle,
                            @StringRes int permissionRationalMessage,
                            boolean forceRational) {
        this.activity = activity;
        this.permissionRequest = permissionRequest;
        this.permissions = permissions;
        this.permissionRationalTitle = permissionRationalTitle;
        this.permissionRationalMessage = permissionRationalMessage;
        this.forceRational = forceRational;
    }

    public void startRequestPermission() {
        List<String> forRationale = new ArrayList<>();
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                forRationale.add(permission);
            }
        }
        if (!forRationale.isEmpty() || forceRational) {
            showRequestPermissionRationale();
        } else {
            requestPermission();
        }
    }

    public List<String> onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        List<String> granted = new ArrayList<>();
        if (requestCode == permissionRequest) {
            int i = 0;
            for (String permission : permissions) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    granted.add(permission);
                }
                ++i;
            }
        }
        return granted;
    }

    private void showRequestPermissionRationale() {
        new AlertDialog.Builder(activity).setTitle(permissionRationalTitle).setMessage(permissionRationalMessage).
                setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermission();
                    }
                }).show();
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(activity, permissions, permissionRequest);
    }

    private final Activity activity;
    private final int permissionRequest;
    private final String[] permissions;
    @StringRes
    private final int permissionRationalTitle;
    @StringRes
    private final int permissionRationalMessage;
    private final boolean forceRational;
}
