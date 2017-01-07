package com.example.igor.sample;

import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SampleActivity extends AppCompatActivity {

    private static final String LOG_TAG = "SampleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // First time init, create the UI.
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container,
                    UiFragment.newInstance()).commit();
        }
    }

    public static class UiFragment extends Fragment implements View.OnClickListener {

        public static UiFragment newInstance() {
            UiFragment fragment = new UiFragment();
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment, container, false);
            view.findViewById(R.id.runThreads).setOnClickListener(this);
            return view;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.runThreads:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Process.setThreadPriority(Process.THREAD_PRIORITY_URGENT_AUDIO);
                            Log.d(LOG_TAG, "MyThread started");
                            for (int i = 0; i < Integer.MAX_VALUE; i++) {

                            }
                            Log.d(LOG_TAG, "MyThread ended");
                        }
                    }, "MyThread").start();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Process.setThreadPriority(Process.THREAD_PRIORITY_LOWEST);
                            Log.d(LOG_TAG, "MyLowPThread started");
                            for (int i = 0; i < Integer.MAX_VALUE; i++) {

                            }
                            Log.d(LOG_TAG, "MyLowPThread ended");
                        }
                    }, "MyLowPThread").start();
                    break;
            }
        }
    }

}