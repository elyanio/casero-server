package caribehostal.caseroserver.dataaccess;

import android.content.Context;

import java.io.File;

import caribehostal.caseroserver.CaseroServerApplication;
import caribehostal.caseroserver.datamodel.Models;
import io.requery.Persistable;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.sql.Configuration;
import io.requery.sql.EntityDataStore;

/**
 * @author asio
 */
public enum DataStoreHolder {
    INSTANCE;

    private EntityDataStore<Persistable> entityDataStore;
    private File dbFile = new File(Resources.INSTANCE.getDirectory() + "/caseroServer.db");
    private static final int DB_VERSION = 1;

    public EntityDataStore<Persistable> getDataStore() {
        if (entityDataStore == null) {
            entityDataStore = createDataStore(CaseroServerApplication.instance(), dbFile);
        }
        return entityDataStore;
    }

    private EntityDataStore<Persistable> createDataStore(Context context, File dbFile) {
        String dbName = dbFile.getAbsolutePath();
        Configuration configuration;
        DatabaseSource source = new DatabaseSource(context, Models.DEFAULT, dbName, DB_VERSION);
        configuration = source.getConfiguration();
        return new EntityDataStore<>(configuration);
    }

    public boolean existsDbFile() {
        return dbFile.exists();
    }

    public File getDbFile() {
        return dbFile;
    }
}
