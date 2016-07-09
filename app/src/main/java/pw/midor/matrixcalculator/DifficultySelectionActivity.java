package pw.midor.matrixcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DifficultySelectionActivity extends Activity implements View.OnClickListener{
    Button easy,regular,hard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_selection);
        easy = (Button)findViewById(R.id.easy);
        regular = (Button)findViewById(R.id.regular);
        hard = (Button)findViewById(R.id.hard);
        easy.setOnClickListener(this);
        regular.setOnClickListener(this);
        hard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.easy) {
            Intent intent = new Intent(this, EasyActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.regular) {
            Intent intent = new Intent(this, RegularActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.hard) {
            Intent intent = new Intent(this, HardActivity.class);
            startActivity(intent);
        }
    }
}
