package br.com.thiengo.superplacar.extras;

import android.os.SystemClock;

import java.lang.ref.WeakReference;

import br.com.thiengo.superplacar.MainActivity;


public class Worker extends Thread {
    final private WeakReference<MainActivity> mActivity;

    public Worker(MainActivity activity) {
        this.mActivity = new WeakReference<>(activity);
    }

    @Override
    public void run() {
        super.run();
        while (mActivity.get() != null) {
            new FifaRequest(mActivity.get()).execute();
            SystemClock.sleep(60000);
        }
    }
}