package com.teslaa.butterknife;

import android.app.Application;
import butterknife.ButterKnife;

public class SimpleApp extends Application {
  @Override public void onCreate() {
    super.onCreate();
    ButterKnife.setDebug(BuildConfig.DEBUG);
  }
}
