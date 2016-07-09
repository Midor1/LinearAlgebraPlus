package pw.midor.matrixcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

public class OpenActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        final OpenActivity This = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(This,InitActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}
