package pw.midor.matrixcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseActivity extends Activity implements View.OnClickListener{
    Button two,three,four;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        two = (Button)findViewById(R.id.two);
        three = (Button)findViewById(R.id.three);
        four = (Button)findViewById(R.id.four);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.two) {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.three) {
            Intent intent = new Intent(this, Main3Activity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.four) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
