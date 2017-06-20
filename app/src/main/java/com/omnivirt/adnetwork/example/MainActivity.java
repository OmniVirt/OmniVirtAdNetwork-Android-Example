package com.omnivirt.adnetwork.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.omnivirt.vrkit.AdState;
import com.omnivirt.vrkit.OnVRAdInteractionListener;
import com.omnivirt.vrkit.VRAd;

public class MainActivity extends AppCompatActivity {

    private Button btnLoadAd = null;
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
        btnLoadAd = (Button) findViewById(R.id.start_ad_button);
        btnLoadAd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                btnLoadAd.setEnabled(false);
                vrAd.load(MainActivity.this);
            }
        });

        tvLogger = (TextView) findViewById(R.id.tvLogger);
    }

    @Override
    public void onBackPressed() {
        // This method will close VR Ad when user hit back button.
        //
        if (vrAd.getState() == AdState.Showing) {
            vrAd.unload();
            return;
        }

        super.onBackPressed();
    }

    ////////////////////////
    // Listener for VRAd
    ////////////////////////

    private OnVRAdInteractionListener vrAdInteractionListener = new OnVRAdInteractionListener() {
        @Override
        public void onAdStatusChanged(VRAd instance, AdState status) {
            switch (status) {
                case None:
                    break;
                case Loading:
                    MainActivity.this.log("Ad state is loading");
                    break;
                case Ready:
                    MainActivity.this.log("Ad state is ready");
                    vrAd.show();
                    break;
                case Showing:
                    MainActivity.this.log("Ad state is showing");
                    break;
                case Completed:
                    MainActivity.this.log("Ad state is completed");
                    btnLoadAd.setEnabled(true);
                    break;
                case Failed:
                    MainActivity.this.log("Ad state is failed");
                    btnLoadAd.setEnabled(true);
                    break;
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
