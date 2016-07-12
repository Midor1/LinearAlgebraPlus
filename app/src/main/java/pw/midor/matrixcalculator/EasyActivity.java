package pw.midor.matrixcalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class EasyActivity extends Activity implements View.OnClickListener{
    String qs[] = {"求此矩阵的秩","求此矩阵行列式的值","求此矩阵的伴随矩阵的元素之和","求此矩阵的伴随矩阵的秩","求此矩阵的迹"};
    TextView question,status;
    Button submit,easyBack;
    EditText t00,t01,t10,t11,answer;
    int qNum,times,score,ans;
    Random ra = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);
        setView();
        qNum = ra.nextInt(5);
        times = 1;
        score = 0;
        question.setText(qs[qNum]);
        t00.setText(Integer.toString(ra.nextInt(9)));
        t01.setText(Integer.toString(ra.nextInt(9)));
        t10.setText(Integer.toString(ra.nextInt(9)));
        t11.setText(Integer.toString(ra.nextInt(9)));
        status.setText("题目" +Integer.toString(times) +"/10" + "      " + "得分:" + Integer.toString(score));
    }

    final protected void setView() {
        question = (TextView)findViewById(R.id.question);
        status = (TextView)findViewById(R.id.status);
        submit = (Button)findViewById(R.id.submit);
        easyBack = (Button)findViewById(R.id.easyBack);
        t00 = (EditText)findViewById(R.id.t00);
        t01 = (EditText)findViewById(R.id.t01);
        t10 = (EditText)findViewById(R.id.t10);
        t11 = (EditText)findViewById(R.id.t11);
        t00.setKeyListener(null);
        t10.setKeyListener(null);
        t01.setKeyListener(null);
        t11.setKeyListener(null);
        answer = (EditText)findViewById(R.id.answer);
        submit.setOnClickListener(this);
        easyBack.setOnClickListener(this);
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

    final protected void newState() {
        times++;
        if(times>10) {
            status.setText("题目" + Integer.toString(--times) + "/10" + "       " + "得分:" + Integer.toString(score));
            new  AlertDialog.Builder(this)
                    .setTitle("练习结束")
                    .setMessage("你的得分为:"+Integer.toString(score)+"分!")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();
        }
        else {
            qNum = ra.nextInt(5);
            question.setText(qs[qNum]);
            t00.setText(Integer.toString(ra.nextInt(9)));
            t01.setText(Integer.toString(ra.nextInt(9)));
            t10.setText(Integer.toString(ra.nextInt(9)));
            t11.setText(Integer.toString(ra.nextInt(9)));
            answer.setText("");
            status.setText("题目" + Integer.toString(times) + "/10" + "       " + "得分:" + Integer.toString(score));
        }
    }

    @Override
    public void onClick(View v) {
        double sh[][] = new double[2][2];
        sh[0][0] = Integer.parseInt(t00.getText().toString());
        sh[0][1] = Integer.parseInt(t01.getText().toString());
        sh[1][0] = Integer.parseInt(t10.getText().toString());
        sh[1][1] = Integer.parseInt(t11.getText().toString());
        if(v.getId()==R.id.submit) {
            if(answer.getText().toString().equals("")) {
                Toast.makeText(this, "请输入答案!" , Toast.LENGTH_SHORT).show();
                return;
            }
            int correct=0;
            ans=Integer.parseInt(answer.getText().toString());
            if(qNum==0||qNum==3) {
                correct=Rank(sh,-1,2);
                if(correct==ans)
                    score+=10;
            }
            else if(qNum==1) {
                correct = (int)(sh[0][0]*sh[1][1]-sh[1][0]*sh[0][1]);
                if(correct==ans)
                    score+=10;
            }
            else if(qNum==2) {
                correct = (int)(sh[0][0]+sh[1][1]-sh[0][1]-sh[1][0]);
                if(correct==ans)
                    score+=10;
            }
            else if(qNum==4) {
                correct = (int)(sh[0][0]+sh[1][1]);
                if(correct==ans)
                    score+=10;
            }
            if(correct==ans)
                Toast.makeText(this, "答案正确" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "答案不正确,正确答案为" + Integer.toString(correct) + ".", Toast.LENGTH_LONG).show();
        }
        else if(v.getId()==R.id.easyBack) {
            finish();
        }
        newState();
    }
}
