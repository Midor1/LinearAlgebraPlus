package pw.midor.matrixcalculator;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Result2Activity extends Activity implements View.OnClickListener{
    Button button;
    double res[][]=new double[2][2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);
        int typ = setView();
        TextView Result = (TextView)findViewById(R.id.result2Text);
        if(typ==1)
            Result.setText("和矩阵为↑");
        else if(typ==2)
            Result.setText("差矩阵为↑");
        else if(typ==3)
            Result.setText("积矩阵为↑");
        else if(typ==4)
            Result.setText("转置矩阵为↑");
        else if(typ==5)
            Result.setText("伴随矩阵为↑");
        else if(typ==6)
            Result.setText("逆矩阵为↑");
        else if(typ==7)
            Result.setText("化为阶梯形↑");
    }

    protected int setView() {
        button=(Button)findViewById(R.id.result2Back);
        button.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        res[0]=bundle.getDoubleArray("matrix10");
        res[1]=bundle.getDoubleArray("matrix11");
        int typ=bundle.getInt("type");
        EditText edittext[][] = new EditText[2][2];
        edittext[0][0] = (EditText)findViewById(R.id.result200);
        edittext[0][1] = (EditText)findViewById(R.id.result201);
        edittext[1][0] = (EditText)findViewById(R.id.result210);
        edittext[1][1] = (EditText)findViewById(R.id.result211);
        for(int i=0;i<2;i++)
            for(int j=0;j<2;j++)
                if(Math.abs(res[i][j])<=1E-8||Double.isNaN(res[i][j]))
                    res[i][j]=0.0;
        for(int i=0;i<2;i++)
            for(int j=0;j<2;j++)
                edittext[i][j].setText(Double.toString(res[i][j]));
        return typ;
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.result2Back)
            finish();
    }
}
