package caribehostal.caseroserver;

import android.app.Application;

import com.facebook.soloader.SoLoader;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.util.Locale;

import caribehostal.caseroserver.dataaccess.DatabaseSetup;

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
        instance = this;
        AndroidThreeTen.init(this);
        Locale.setDefault(new Locale("es"));

//        DatabaseSetup databaseSetup = new DatabaseSetup();
//        databaseSetup.mockDatabase();
//        databaseSetup.testExistAction();

        SoLoader.init(this, false);
    }
}
