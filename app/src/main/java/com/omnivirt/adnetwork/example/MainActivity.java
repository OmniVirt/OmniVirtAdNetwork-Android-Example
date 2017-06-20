package com.omnivirt.adnetwork.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.omnivirt.vrkit.AdState;
import com.omnivirt.vrkit.OnVRAdInteractionListener;
import com.omnivirt.vrkit.VRAd;

public class MainActivity extends AppCompatActivity {

    private Button btnShowAd = null;
    private TextView tvLogger = null;

    private VRAd vrAd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creating VR Ad instance
        //
        vrAd = new VRAd(1, vrAdInteractionListener);

        // Loading and starting VR Ad using a button
        //
        btnShowAd = (Button) findViewById(R.id.start_ad_button);
        btnShowAd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                btnShowAd.setEnabled(false);
                vrAd.show();
            }
        });

        tvLogger = (TextView) findViewById(R.id.tvLogger);

        // Load ad in background
        vrAd.loadAd(MainActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vrAd.unloadAd();
    }

    @Override
    public void onBackPressed() {
        // This method will close VR Ad when user hit back button.
        //
        if (vrAd.isShowing()) {
            vrAd.unloadAd();
            return;
        }

        super.onBackPressed();
    }

    ////////////////////////
    // Listener for VRAd
    ////////////////////////

    private OnVRAdInteractionListener vrAdInteractionListener = new OnVRAdInteractionListener() {
        @Override
        public void onAdStatusChanged(VRAd vrAd, AdState status) {
            switch (status) {
                case None:
                    break;
                case Loading:
                    MainActivity.this.log("Ad state is loading");
                    btnShowAd.setText("Loading Ad");
                    btnShowAd.setEnabled(false);
                    break;
                case Ready:
                    MainActivity.this.log("Ad state is ready");
                    btnShowAd.setText("Show Ad");
                    btnShowAd.setEnabled(true);
                    break;
                case Showing:
                    MainActivity.this.log("Ad state is showing");
                    btnShowAd.setText("Ad is showing");
                    btnShowAd.setEnabled(false);
                    break;
                case Completed:
                    MainActivity.this.log("Ad state is completed");
                    btnShowAd.setText("Ad completed");
                    btnShowAd.setEnabled(false);
                    break;
                case Failed:
                    MainActivity.this.log("Ad state is failed");
                    btnShowAd.setText("Failed to load ad");
                    btnShowAd.setEnabled(false);
                    break;
            }
            if (vrAd.isCompleted()) {
                // Reload an ad in background
                vrAd.loadAd(MainActivity.this);
            }
        }
    };

    private void log(final String text) {
        tvLogger.post(new Runnable() {
            @Override
            public void run() {
                tvLogger.setText(tvLogger.getText() + text + "\n");
                tvLogger.invalidate();
            }
        });
    }

}
