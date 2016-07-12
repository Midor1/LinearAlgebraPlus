package pw.midor.matrixcalculator;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends Activity implements View.OnClickListener{
    Button button;
    double res[][]=new double[4][4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        int typ = setView();
        TextView Result = (TextView)findViewById(R.id.resultText);
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
        button=(Button)findViewById(R.id.resultBack);
        button.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        res[0]=bundle.getDoubleArray("matrix10");
        res[1]=bundle.getDoubleArray("matrix11");
        res[2]=bundle.getDoubleArray("matrix12");
        res[3]=bundle.getDoubleArray("matrix13");
        int typ=bundle.getInt("type");
        EditText edittext[][] = new EditText[4][4];
        edittext[0][0] = (EditText)findViewById(R.id.r11);
        edittext[0][1] = (EditText)findViewById(R.id.r12);
        edittext[0][2] = (EditText)findViewById(R.id.r13);
        edittext[0][3] = (EditText)findViewById(R.id.r14);
        edittext[1][0] = (EditText)findViewById(R.id.r21);
        edittext[1][1] = (EditText)findViewById(R.id.r22);
        edittext[1][2] = (EditText)findViewById(R.id.r23);
        edittext[1][3] = (EditText)findViewById(R.id.r24);
        edittext[2][0] = (EditText)findViewById(R.id.r31);
        edittext[2][1] = (EditText)findViewById(R.id.r32);
        edittext[2][2] = (EditText)findViewById(R.id.r33);
        edittext[2][3] = (EditText)findViewById(R.id.r34);
        edittext[3][0] = (EditText)findViewById(R.id.r41);
        edittext[3][1] = (EditText)findViewById(R.id.r42);
        edittext[3][2] = (EditText)findViewById(R.id.r43);
        edittext[3][3] = (EditText)findViewById(R.id.r44);
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                if(Math.abs(res[i][j])<=1E-8)
                    res[i][j]=0.0;
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                edittext[i][j].setText(Double.toString(res[i][j]));
        return typ;
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.resultBack)
        finish();
    }
}
