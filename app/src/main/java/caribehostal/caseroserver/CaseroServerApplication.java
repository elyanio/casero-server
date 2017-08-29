package caribehostal.caseroserver;

import android.app.Application;

import com.facebook.soloader.SoLoader;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.squareup.leakcanary.LeakCanary;

import java.util.Locale;

/**
 * @author rainermf
 * @since 20/2/2017
 */
public class CaseroServerApplication extends Application {

    private static CaseroServerApplication instance;

    public static CaseroServerApplication instance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        instance = this;
        AndroidThreeTen.init(this);
        Locale.setDefault(new Locale("es"));

//        DatabaseSetup databaseSetup = new DatabaseSetup();
//        databaseSetup.mockDatabase();
//        databaseSetup.testExistAction();

        SoLoader.init(this, false);
    }
}
