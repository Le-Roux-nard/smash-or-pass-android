package bzh.lerouxard.smashorpass;

import android.app.Application;
import android.content.Context;
import java.io.File;

public class MyApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
        File cacheDir = getCacheDir();
        File imageCacheDir = new File(cacheDir, "images");
        if (imageCacheDir.exists()) {
            imageCacheDir.delete();
        }
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}