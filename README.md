# permission-android
Wrapper for Android permission API

## Usage
Make sure that your root project build.gradle file has this section

    ext {
        compileSdkVersion = 25
        buildToolsVersion = '25.0.3'
        androidSupportVersion = '25.4.0'
    }
   
Add this code to your Activity:

    private val PERMISSIONS_REQUEST = 1
    private var permissionDialog: PermissionDialog? = null    

    //  Put this in some place where user is ready to grant permission
    {
        permissionDialog = PermissionDialog(this,
                PERMISSIONS_REQUEST,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO),
                R.string.app_name,  //  Permission rationale dialog title
                R.string.permission_rationale,  //  Permission rationale dialog message
                false //  should we force permission rationale dialog show
        )
        permissionDialog?.startRequestPermission()        
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //  Delegate this callback to process result
        val granted = permissionDialog?.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (granted?.isNotEmpty() ?: false) {
            //  Here granted permissions come
        }
    }

