package pw.midor.matrixcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DefiniteActivity extends Activity implements View.OnClickListener{
    EditText q1,q2,q3,q12,q23,q13;
    TextView det,std;
    Button solve;

    public static int Hessenberg(double[][] Matrix,int n,double[][]ret)
    {

        int i;
        int j;
        int k;
        double temp;
        int MaxNu;
        n-=1;
        for(k=1;k<=n-1;k++)
        {
            i=k-1;
            MaxNu=k;
            temp=Math.abs(Matrix[k][i]);
            for(j=k+1;j<=n;j++)
            {
                if(Math.abs(Matrix[j][i])>temp)
                {
                    MaxNu=j;
                }
            }
            ret[0][0]=Matrix[MaxNu][i];
            i=MaxNu;
            if(ret[0][0]!=0)
            {
                if(i!=k)
                {
                    for(j=k-1;j<=n;j++)
                    {
                        temp=Matrix[i][j];
                        Matrix[i][j]=Matrix[k][j];
                        Matrix[k][j]=temp;
                    }
                    for(j=0;j<=n;j++)
                    {
                        temp=Matrix[j][i];
                        Matrix[j][i]=Matrix[j][k];
                        Matrix[j][k]=temp;
                    }
                }
                for(i=k+1;i<=n;i++)
                {
                    temp=Matrix[i][k-1]/ret[0][0];
                    Matrix[i][k-1]=0;
                    for(j=k;j<=n;j++)
                    {
                        Matrix[i][j]-=temp*Matrix[k][j];
                    }
                    for(j=0;j<=n;j++)
                    {
                        Matrix[j][k]+=temp*Matrix[j][i];
                    }
                }
            }
        }
        for(i=0;i<=n;i++)
        {
            for(j=0;j<=n;j++)
            {
                ret[i][j]=Matrix[i][j];
            }
        }
        return n+1;
    }

    ////////////
    public static void EigenValue(double[][]Matrix,int n,int LoopNu,int Erro,double[][]Ret)
    {
        int i=Matrix.length;
        int j;
        int k;
        int t;
        int m;
        double[][]A=new double[n][n];
        double erro=Math.pow(0.1, Erro);
        double b;
        double c;
        double d;
        double g;
        double xy;
        double p;
        double q;
        double r;
        double x;
        double s;
        double e;
        double f;
        double z;
        double y;
        int loop1=LoopNu;
        Hessenberg(Matrix,n,A);//将方阵K1转化成上Hessenberg矩阵A
        m=n;
        while(m!=0)
        {
            t=m-1;
            while(t>0)
            {
                if(Math.abs(A[t][t-1])>erro*(Math.abs(A[t-1][t-1])+Math.abs(A[t][t])))
                {
                    t-=1;
                }
                else
                {
                    break;
                }
            }
            if(t==m-1)
            {
                Ret[m-1][0]=A[m-1][m-1];
                Ret[m-1][1]=0;
                m-=1;
                loop1=LoopNu;

            }
            else if(t==m-2)
            {
                b=-(A[m-1][m-1]+A[m-2][m-2]);
                c=A[m-1][m-1]*A[m-2][m-2]-A[m-1][m-2]*A[m-2][m-1];
                d=b*b-4*c;
                y=Math.pow(Math.abs(d), 0.5);
                if(d>0)
                {
                    xy=1;
                    if(b<0)
                    {
                        xy=-1;
                    }
                    Ret[m-1][0]=-(b+xy*y)/2;
                    Ret[m-1][1]=0;
                    Ret[m-2][0]=c/Ret[m-1][0];
                    Ret[m-2][1]=0;
                }
                else
                {
                    Ret[m-1][0]=-b/2;
                    Ret[m-2][0]=Ret[m-1][0];
                    Ret[m-1][1]=y/2;
                    Ret[m-2][1]=-Ret[m-1][1];
                }
                m-=2;
                loop1=LoopNu;
            }
            else
            {
                if(loop1<1)
                {
                    return;
                }
                loop1-=1;
                j=t+2;
                while(j<m)
                {
                    A[j][j-2]=0;
                    j+=1;
                }
                j=t+3;
                while(j<m)
                {
                    A[j][j-3]=0;
                    j+=1;
                }
                k=t;
                while(k<m-1)
                {
                    if(k!=t)
                    {
                        p=A[k][k-1];
                        q=A[k+1][k-1];
                        if(k!=m-2)
                        {
                            r=A[k+2][k-1];
                        }
                        else
                        {
                            r=0;
                        }
                    }
                    else
                    {
                        b=A[m-1][m-1];
                        c=A[m-2][m-2];
                        x=b+c;
                        y=c*b-A[m-2][m-1]*A[m-1][m-2];
                        p=A[t][t]*(A[t][t]-x)+A[t][t+1]*A[t+1][t]+y;
                        q=A[t+1][t]*(A[t][t]+A[t+1][t+1]-x);
                        r=A[t+1][t]*A[t+2][t+1];
                    }
                    if(p!=0||q!=0||r!=0)
                    {
                        if(p<0)
                        {
                            xy=-1;
                        }
                        else
                        {
                            xy=1;
                        }
                        s=xy*Math.pow(p*p+q*q+r*r, 0.5);
                        if(k!=t)
                        {
                            A[k][k-1]=-s;
                        }
                        e=-q/s;
                        f=-r/s;
                        x=-p/s;
                        y=-x-f*r/(p+s);
                        g=e*r/(p+s);
                        z=-x-e*q/(p+s);
                        for(j=k;j<=m-1;j++)
                        {
                            b=A[k][j];
                            c=A[k+1][j];
                            p=x*b+e*c;
                            q=e*b+y*c;
                            r=f*b+g*c;
                            if(k!=m-2)
                            {
                                b=A[k+2][j];
                                p+=f*b;
                                q+=g*b;
                                r+=z*b;
                                A[k+2][j]=r;
                            }
                            A[k+1][j]=q;
                            A[k][j]=p;

                        }
                        j=k+3;
                        if(j>=m-1)
                        {
                            j=m-1;
                        }
                        for(i=t;i<=j;i++)
                        {
                            b=A[i][k];
                            c=A[i][k+1];
                            p=x*b+e*c;
                            q=e*b+y*c;
                            r=f*b+g*c;
                            if(k!=m-2)
                            {
                                b=A[i][k+2];
                                p+=f*b;
                                q+=g*b;
                                r+=z*b;
                                A[i][k+2]=r;
                            }
                            A[i][k+1]=q;
                            A[i][k]=p;
                        }
                    }
                    k+=1;
                }
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definite);
        setView();
    }

    final protected void setView() {
        q1 = (EditText)findViewById(R.id.q1);
        q2 = (EditText)findViewById(R.id.q2);
        q3 = (EditText)findViewById(R.id.q3);
        q12 = (EditText)findViewById(R.id.q12);
        q13 = (EditText)findViewById(R.id.q13);
        q23 = (EditText)findViewById(R.id.q23);
        det = (TextView)findViewById(R.id.det);
        std = (TextView)findViewById(R.id.std);
        solve = (Button)findViewById(R.id.solve);
        solve.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        double matrix[][] = new double[3][3];
        matrix[0][0]=Double.parseDouble(q1.getText().toString().equals("")?"0":q1.getText().toString());
        matrix[1][1]=Double.parseDouble(q2.getText().toString().equals("")?"0":q2.getText().toString());
        matrix[2][2]=Double.parseDouble(q3.getText().toString().equals("")?"0":q3.getText().toString());
        matrix[0][1]=matrix[1][0]=Double.parseDouble(q12.getText().toString().equals("")?"0":q12.getText().toString());
        matrix[1][2]=matrix[2][1]=Double.parseDouble(q23.getText().toString().equals("")?"0":q23.getText().toString());
        matrix[0][2]=matrix[2][0]=Double.parseDouble(q13.getText().toString().equals("")?"0":q13.getText().toString());
        double resu[][] = new double[3][3];
        EigenValue(matrix,3,800,12,resu);
        int anstyp=0;
        if(resu[0][0]>0.0&&resu[1][0]>0.0&&resu[2][0]>0.0)
            anstyp=2;
        else if(resu[0][0]<0.0&&resu[1][0]<0.0&&resu[2][0]<0.0)
            anstyp=-2;
        else if(resu[0][0]<=0.0&&resu[1][0]<=0.0&&resu[2][0]<=0.0)
            anstyp=-1;
        else if(resu[0][0]>=0.0&&resu[1][0]>=0.0&&resu[2][0]>=0.0)
            anstyp=1;
        if(resu[0][0]==resu[1][1]&&resu[1][1]==resu[2][2]&&resu[2][2]==0.0)
            anstyp=6;
        switch(anstyp){
            case 0:
                det.setText("不定二次型");
                break;
            case 1:
                det.setText("半正定二次型");
                break;
            case 2:
                det.setText("正定二次型");
                break;
            case -1:
                det.setText("半负定二次型");
                break;
            case -2:
                det.setText("负定二次型");
                break;
            default:
                det.setText("半正定二次型,半负定二次型");
        }
        java.text.DecimalFormat Format =new java.text.DecimalFormat("#.000");
        std.setText("标准型为:" +Format.format(resu[0][0])+ "y1+" + Format.format(resu[1][0])+ "y2+" +Format.format(resu[2][0]) +"y3");
    }
}
