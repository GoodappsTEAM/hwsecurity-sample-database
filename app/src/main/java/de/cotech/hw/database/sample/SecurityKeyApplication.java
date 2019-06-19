package de.cotech.hw.database.sample;


import android.app.Application;

import de.cotech.hw.SecurityKeyManager;
import de.cotech.hw.SecurityKeyManagerConfig;
import de.cotech.hw.openpgp.OpenPgpSecurityKeyConnectionMode;
import de.cotech.hw.openpgp.OpenPgpSecurityKeyConnectionModeConfig;
import timber.log.Timber;

public class SecurityKeyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        OpenPgpSecurityKeyConnectionModeConfig pgpConfig = new OpenPgpSecurityKeyConnectionModeConfig.Builder()
                .build();
        OpenPgpSecurityKeyConnectionMode.setDefaultConfig(pgpConfig);

        SecurityKeyManager securityKeyManager = SecurityKeyManager.getInstance();
        SecurityKeyManagerConfig.Builder configBuilder = new SecurityKeyManagerConfig.Builder();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            configBuilder.setEnableDebugLogging(true);
        }

        securityKeyManager.init(this, configBuilder.build());
    }
}