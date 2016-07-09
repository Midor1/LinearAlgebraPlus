package pw.midor.matrixcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondaryActivity extends Activity implements View.OnClickListener {
    double matrix1[][]=new double[4][4];


    TextView Matrix[][] = new TextView [4][4];
    Button plus,minus,multiply,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        setView();
    }

    protected void setView()
    {
        plus=(Button)findViewById(R.id.plus);
        minus=(Button)findViewById(R.id.minus);
        multiply=(Button)findViewById(R.id.multiply);
        back=(Button)findViewById(R.id.back);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        multiply.setOnClickListener(this);
        back.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        matrix1[0]=bundle.getDoubleArray("matrix10");
        matrix1[1]=bundle.getDoubleArray("matrix11");
        matrix1[2]=bundle.getDoubleArray("matrix12");
        matrix1[3]=bundle.getDoubleArray("matrix13");
        Matrix[0][0]=(TextView)findViewById(R.id.P11);
        Matrix[0][1]=(TextView)findViewById(R.id.P12);
        Matrix[0][2]=(TextView)findViewById(R.id.P13);
        Matrix[0][3]=(TextView)findViewById(R.id.P14);
        Matrix[1][0]=(TextView)findViewById(R.id.P21);
        Matrix[1][1]=(TextView)findViewById(R.id.P22);
        Matrix[1][2]=(TextView)findViewById(R.id.P23);
        Matrix[1][3]=(TextView)findViewById(R.id.P24);
        Matrix[2][0]=(TextView)findViewById(R.id.P31);
        Matrix[2][1]=(TextView)findViewById(R.id.P32);
        Matrix[2][2]=(TextView)findViewById(R.id.P33);
        Matrix[2][3]=(TextView)findViewById(R.id.P34);
        Matrix[3][0]=(TextView)findViewById(R.id.P41);
        Matrix[3][1]=(TextView)findViewById(R.id.P42);
        Matrix[3][2]=(TextView)findViewById(R.id.P43);
        Matrix[3][3]=(TextView)findViewById(R.id.P44);
    }

    @Override
    public void onClick(View v) {
        double matrix2[][] = new double[4][4];
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                matrix2[i][j]=Double.parseDouble(Matrix[i][j].getText().toString());
        if(v.getId()==R.id.back)
            finish();
        else if(v.getId()==R.id.plus) {
            double matrix[][] = new double[4][4];
            for(int i=0;i<4;i++)
                for(int j=0;j<4;j++) {
                    matrix[i][j] = matrix1[i][j] + matrix2[i][j];
                }
            Intent intent = new Intent(this,ResultActivity.class);
            Bundle bundle=new Bundle();
            bundle.putInt("type",1);
            bundle.putDoubleArray("matrix10",matrix[0]);
            bundle.putDoubleArray("matrix11",matrix[1]);
            bundle.putDoubleArray("matrix12",matrix[2]);
            bundle.putDoubleArray("matrix13",matrix[3]);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId()==R.id.multiply) {
            double matrix[][] = new double[4][4];
            for(int i=0;i<4;i++)
                for(int j=0;j<4;j++)
                    matrix[i][j]=0;
            for(int i=0;i<4;i++)
                for(int j=0;j<4;j++)
                    for(int k=0;k<4;k++)
                        matrix[i][j]+=matrix1[i][k]*matrix2[k][j];

            Intent intent = new Intent(this,ResultActivity.class);
            Bundle bundle=new Bundle();
            bundle.putInt("type",3);
            bundle.putDoubleArray("matrix10",matrix[0]);
            bundle.putDoubleArray("matrix11",matrix[1]);
            bundle.putDoubleArray("matrix12",matrix[2]);
            bundle.putDoubleArray("matrix13",matrix[3]);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId()==R.id.minus) {
            double matrix[][] = new double[4][4];
            for(int i=0;i<4;i++)
                for(int j=0;j<4;j++) {
                    matrix[i][j] = matrix1[i][j] - matrix2[i][j];
                }
            Intent intent = new Intent(this,ResultActivity.class);
            Bundle bundle=new Bundle();
            bundle.putInt("type",2);
            bundle.putDoubleArray("matrix10",matrix[0]);
            bundle.putDoubleArray("matrix11",matrix[1]);
            bundle.putDoubleArray("matrix12",matrix[2]);
            bundle.putDoubleArray("matrix13",matrix[3]);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
