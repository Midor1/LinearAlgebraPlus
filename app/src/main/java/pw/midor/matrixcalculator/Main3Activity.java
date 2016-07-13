package pw.midor.matrixcalculator;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends Activity implements View.OnClickListener{
    TextView Matrix[][] = new TextView [3][3];
    Button next,more;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setView();
        next=(Button)findViewById(R.id.next);
        more=(Button)findViewById(R.id.more);
        next.setOnClickListener(this);
        more.setOnClickListener(this);
    }

    protected void setView()
    {
        Matrix[0][0]=(TextView)findViewById(R.id.p11);
        Matrix[0][1]=(TextView)findViewById(R.id.p12);
        Matrix[0][2]=(TextView)findViewById(R.id.p13);
        Matrix[1][0]=(TextView)findViewById(R.id.p21);
        Matrix[1][1]=(TextView)findViewById(R.id.p22);
        Matrix[1][2]=(TextView)findViewById(R.id.p23);
        Matrix[2][0]=(TextView)findViewById(R.id.p31);
        Matrix[2][1]=(TextView)findViewById(R.id.p32);
        Matrix[2][2]=(TextView)findViewById(R.id.p33);
    }

    final protected double SubMatrix(int x, int y, double[][] m) {
        double d[][]=new double[2][2];
        int xx=0,yy=0;
        for(int i=0;i<3;i++) {
            if(i==x)
                continue;
            for(int j=0;j<3;j++) {
                if(j==y)
                    continue;
                d[xx][yy]=m[i][j];
                yy++;
            }
            xx++;
            yy=0;
        }
        return d[0][0]*d[1][1]-d[0][1]*d[1][0];
    }

    final protected int Determination(int times) {
        int ans=1;
        for(int i=1;i<=times;i++)
            ans=-ans;
        return ans;
    }

    final public int Rank(double[][] Matrix,int error_,int List)
    {
        int n=List;
        int m=Matrix.length ;
        int i=0;
        int i1;
        int j=0;
        int j1;
        double temp1;
        if(m>n)
        {
            i=m;
            m=n;
            n=i;
            i=1;
        }
        m-=1;
        n-=1;
        double[][]temp=new double[m+1][n+1];
        if(i==0)
        {
            for(i=0;i<=m;i++)
            {
                for(j=0;j<=n;j++)
                {
                    temp[i][j]=Matrix[i][j];
                }

            }
        }
        else
        {
            for(i=0;i<=m;i++)
            {
                for(j=0;j<=n;j++)
                {
                    temp[i][j]=Matrix[j][i];
                }
            }
        }
        if(m==0)
        {
            i=0;
            while(i<=n)
            {
                if(Matrix[0][i]!=0)
                {
                    return 1;
                }
                i+=1;
            }
            return 0;
        }
        double error0;
        if(error_==-1)
        {
            error0=Math.pow(0.1, 10);
        }
        else
        {
            error0=Math.pow(0.1, error_);
        }
        i=0;
        while(i<=m)
        {
            j=0;
            while(j<=n)
            {
                if(temp[i][j]!=0)
                {
                    error0*=temp[i][j];
                    i=m;
                    break;
                }
                j+=1;
            }
            i+=1;
        }
        double error1;
        for(i=0;i<=m;i++)
        {
            j=0;
            while(j<=n)
            {
                if(temp[i][j]!=0)
                {
                    break;
                }
                j+=1;
            }
            if(j<=n)
            {
                i1=0;
                while(i1<=m)
                {
                    if(temp[i1][j]!=0&&i1!=i)
                    {
                        temp1=temp[i][j]/temp[i1][j];
                        error1=Math.abs((temp[i][j]-temp[i1][j]*temp1))*100;
                        error1+=error0;
                        for(j1=0;j1<=n;j1++)
                        {
                            temp[i1][j1]=temp[i][j1]-temp[i1][j1]*temp1;
                            if(Math.abs(temp[i1][j1])<error1)
                            {
                                temp[i1][j1]=0;
                            }
                        }

                    }
                    i1+=1;
                }
            }
        }

        i1=0;
        for(i=0;i<=m;i++)
        {
            for(j=0;j<=n;j++)
            {
                if(temp[i][j]!=0)
                {
                    i1+=1;
                    break;
                }
            }
        }
        return i1;
    }

    final public void UpTri(double[][]Matrix,int n){
        int Count=1;
        while(Count<n)
        {
            for(int N=n-1;N>=Count;N--)
            {
                double z;
                if(Matrix[Count-1][Count-1]!=0){
                    z=Matrix[N][Count-1]/Matrix[Count-1][Count-1];
                }else{
                    for(int i=0;i<n;i++)
                    {
                        Matrix[Count-1][i]+=Matrix[N][i];
                    }
                    z=Matrix[N][Count-1]/Matrix[Count-1][Count-1];
                }
                for(int i=0;i<n;i++)
                {
                    Matrix[N][i]=Matrix[N][i]-Matrix[Count-1][i]*z;
                }
            }
            Count++;
        }

    }

    @Override
    public void onClick(View v) {

        final double matrix[][] = new double[3][3];
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                matrix[i][j]=Double.parseDouble(Matrix[i][j].getText().toString().equals("")?"0":Matrix[i][j].getText().toString());
        if(v.getId()==R.id.next) {
            Intent intent = new Intent(this,Secondary3Activity.class);
            Bundle bundle=new Bundle();
            bundle.putDoubleArray("matrix10",matrix[0]);
            bundle.putDoubleArray("matrix11",matrix[1]);
            bundle.putDoubleArray("matrix12",matrix[2]);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        else if(v.getId()==R.id.more) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Main3Activity.this);
            builder.setTitle("选择一个功能");
            final String[] options = {"求矩阵的行列式的值", "求转置矩阵", "求伴随矩阵", "求逆矩阵", "将矩阵化为阶梯形", "求矩阵的秩", "求矩阵的特征值"};
            final Main3Activity This = this;
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (options[which]) {
                        case "求矩阵的行列式的值":
                            //double ans = matrix[0][0] * (matrix[1][1] * matrix[2][2] * matrix[3][3] + matrix[2][1] * matrix[3][2] * matrix[1][3] + matrix[1][2] * matrix[2][3] * matrix[3][1] - matrix[1][3] * matrix[2][2] * matrix[3][1] - matrix[1][1] * matrix[2][3] * matrix[3][2] - matrix[2][1] * matrix[1][2] * matrix[3][3]) - matrix[0][1] * (matrix[1][0] * matrix[2][2] * matrix[3][3] + matrix[1][2] * matrix[2][3] * matrix[3][0] + matrix[2][0] * matrix[3][2] * matrix[1][3] - matrix[3][0] * matrix[2][2] * matrix[1][3] - matrix[1][0] * matrix[3][2] * matrix[2][3] - matrix[2][0] * matrix[1][2] * matrix[3][3]) + matrix[0][2] * (matrix[1][0] * matrix[2][1] * matrix[3][3] + matrix[2][0] * matrix[3][1] * matrix[1][3] + matrix[1][1] * matrix[2][3] * matrix[3][0] - matrix[3][0] * matrix[2][1] * matrix[1][3] - matrix[2][0] * matrix[1][1] * matrix[3][3] - matrix[1][0] * matrix[3][1] * matrix[2][3]) - matrix[0][3] * (matrix[1][0] * matrix[2][1] * matrix[3][2] + matrix[2][0] * matrix[3][1] * matrix[1][2] + matrix[1][1] * matrix[2][2] * matrix[3][0] - matrix[3][0] * matrix[2][1] * matrix[1][2] - matrix[2][0] * matrix[1][1] * matrix[3][2] - matrix[1][0] * matrix[3][1] * matrix[2][2]);
                            double ans = matrix[0][0]* SubMatrix(0,0,matrix)-matrix[0][1]* SubMatrix(0,1,matrix)+matrix[0][2]* SubMatrix(0,2,matrix);
                            Toast.makeText(Main3Activity.this, "矩阵行列式的值为" + Double.toString(ans), Toast.LENGTH_LONG).show();
                            break;
                        case "求转置矩阵":
                            double b[][] = new double[3][3];
                            for(int i=0;i<3;i++)
                                for(int j=0;j<3;j++)
                                    b[i][j]=matrix[j][i];
                            Intent intent = new Intent(This,Result3Activity.class);
                            Bundle bundle=new Bundle();
                            bundle.putInt("type",4);
                            bundle.putDoubleArray("matrix10",b[0]);
                            bundle.putDoubleArray("matrix11",b[1]);
                            bundle.putDoubleArray("matrix12",b[2]);
                            intent.putExtras(bundle);
                            This.startActivity(intent);
                            break;
                        case "求伴随矩阵":
                            double an[][] = new double[3][3];
                            for(int i=0;i<3;i++)
                                for(int j=0;j<3;j++)
                                    an[i][j] = Determination(i+j) * SubMatrix(i,j,matrix);
                            double a[][] = new double[3][3];
                            for(int i=0;i<3;i++)
                                for(int j=0;j<3;j++)
                                    a[i][j]=an[j][i];
                            Intent intenT = new Intent(This,Result3Activity.class);
                            Bundle bundlE=new Bundle();
                            bundlE.putInt("type",5);
                            bundlE.putDoubleArray("matrix10",a[0]);
                            bundlE.putDoubleArray("matrix11",a[1]);
                            bundlE.putDoubleArray("matrix12",a[2]);
                            intenT.putExtras(bundlE);
                            This.startActivity(intenT);
                            break;
                        case "求逆矩阵":
                            double anss = matrix[0][0]* SubMatrix(0,0,matrix)-matrix[0][1]* SubMatrix(0,1,matrix)+matrix[0][2]* SubMatrix(0,2,matrix);
                            if(anss==0.0) {
                                Toast.makeText(Main3Activity.this, "矩阵不可逆" , Toast.LENGTH_SHORT).show();
                                break;
                            }
                            double ann[][] = new double[3][3];
                            for(int i=0;i<3;i++)
                                for(int j=0;j<3;j++)
                                    ann[i][j] = Determination(i+j) * SubMatrix(i,j,matrix);
                            double aa[][] = new double[3][3];
                            for(int i=0;i<3;i++)
                                for(int j=0;j<3;j++)
                                    aa[i][j]=ann[j][i]/anss;

                            Intent IntenT = new Intent(This,Result3Activity.class);
                            Bundle BundlE=new Bundle();
                            BundlE.putInt("type",6);
                            BundlE.putDoubleArray("matrix10",aa[0]);
                            BundlE.putDoubleArray("matrix11",aa[1]);
                            BundlE.putDoubleArray("matrix12",aa[2]);
                            IntenT.putExtras(BundlE);
                            This.startActivity(IntenT);
                            break;
                        case "将矩阵化为阶梯形":
                            UpTri(matrix,3);
                            Intent INtenT = new Intent(This,Result3Activity.class);
                            Bundle BUndlE=new Bundle();
                            BUndlE.putInt("type",7);
                            BUndlE.putDoubleArray("matrix10",matrix[0]);
                            BUndlE.putDoubleArray("matrix11",matrix[1]);
                            BUndlE.putDoubleArray("matrix12",matrix[2]);
                            INtenT.putExtras(BUndlE);
                            This.startActivity(INtenT);

                            break;
                        case "求矩阵的秩":
                            Toast.makeText(Main3Activity.this, "矩阵的秩为" + Integer.toString(Rank(matrix,-1,3)) , Toast.LENGTH_SHORT).show();
                            break;
                        case "求矩阵的特征值":
                            double resu[][] = new double[4][4];
                            EigenValue(matrix,3,800,12,resu);
                            Toast.makeText(Main3Activity.this, "矩阵的特征值分别为" + "\n" + Double.toString(resu[0][0]) + "\n" + Double.toString(resu[1][0]) + "\n" + Double.toString(resu[2][0]) , Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
            builder.show();
        }
    }
}

