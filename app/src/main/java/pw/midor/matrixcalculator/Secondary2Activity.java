package pw.midor.matrixcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Secondary2Activity extends Activity implements View.OnClickListener {
    double matrix1[][]=new double[2][2];


    TextView Matrix[][] = new TextView [2][2];
    Button plus,minus,multiply,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary2);
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
        Matrix[0][0]=(TextView)findViewById(R.id.secondary200);
        Matrix[0][1]=(TextView)findViewById(R.id.secondary201);
        Matrix[1][0]=(TextView)findViewById(R.id.secondary210);
        Matrix[1][1]=(TextView)findViewById(R.id.secondary211);
    }

    @Override
    public void onClick(View v) {
        double matrix2[][] = new double[2][2];
        for(int i=0;i<2;i++)
            for(int j=0;j<2;j++)
                matrix2[i][j]=Double.parseDouble(Matrix[i][j].getText().toString());
        if(v.getId()==R.id.back)
            finish();
        else if(v.getId()==R.id.plus) {
            double matrix[][] = new double[2][2];
            for(int i=0;i<2;i++)
                for(int j=0;j<2;j++) {
                    matrix[i][j] = matrix1[i][j] + matrix2[i][j];
                }
            Intent intent = new Intent(this,Result2Activity.class);
            Bundle bundle=new Bundle();
            bundle.putInt("type",1);
            bundle.putDoubleArray("matrix10",matrix[0]);
            bundle.putDoubleArray("matrix11",matrix[1]);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId()==R.id.multiply) {
            double matrix[][] = new double[2][2];
            for(int i=0;i<2;i++)
                for(int j=0;j<2;j++)
                    matrix[i][j]=0;
            for(int i=0;i<2;i++)
                for(int j=0;j<2;j++)
                    for(int k=0;k<2;k++)
                        matrix[i][j]+=matrix1[i][k]*matrix2[k][j];

            Intent intent = new Intent(this,Result2Activity.class);
            Bundle bundle=new Bundle();
            bundle.putInt("type",3);
            bundle.putDoubleArray("matrix10",matrix[0]);
            bundle.putDoubleArray("matrix11",matrix[1]);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId()==R.id.minus) {
            double matrix[][] = new double[2][2];
            for(int i=0;i<2;i++)
                for(int j=0;j<2;j++) {
                    matrix[i][j] = matrix1[i][j] - matrix2[i][j];
                }
            Intent intent = new Intent(this,Result2Activity.class);
            Bundle bundle=new Bundle();
            bundle.putInt("type",2);
            bundle.putDoubleArray("matrix10",matrix[0]);
            bundle.putDoubleArray("matrix11",matrix[1]);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
