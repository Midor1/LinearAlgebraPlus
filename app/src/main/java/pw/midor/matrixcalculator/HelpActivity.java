package pw.midor.matrixcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpActivity extends Activity implements View.OnClickListener {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        btn = (Button)findViewById(R.id.aboutBack);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
