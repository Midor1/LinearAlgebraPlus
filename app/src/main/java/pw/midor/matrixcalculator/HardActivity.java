package pw.midor.matrixcalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class HardActivity extends Activity implements View.OnClickListener{
    String qs[] = {"求此矩阵的秩","求此矩阵行列式的值","求此矩阵的伴随矩阵的秩","求此矩阵的迹"};
    TextView question,status;
    Button submit,Back;
    EditText m[][] = new EditText[4][4],answer;
    int qNum,times,score,ans;
    Random ra = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard);
        setView();
        qNum = ra.nextInt(qs.length);
        times = 1;
        score = 0;
        question.setText(qs[qNum]);
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                m[i][j].setText(Integer.toString(ra.nextInt(9)));
        status.setText("题目" +Integer.toString(times) +"/10" + "      " + "得分:" + Integer.toString(score));
    }

    final protected void setView() {
        question = (TextView)findViewById(R.id.questionHard);
        status = (TextView)findViewById(R.id.statusHard);
        submit = (Button)findViewById(R.id.submitHard);
        Back = (Button)findViewById(R.id.backHard);
        m[0][0] = (EditText)findViewById(R.id.hard00);
        m[0][1] = (EditText)findViewById(R.id.hard01);
        m[0][2] = (EditText)findViewById(R.id.hard02);
        m[0][3] = (EditText)findViewById(R.id.hard03) ;
        m[1][0] = (EditText)findViewById(R.id.hard10);
        m[1][1] = (EditText)findViewById(R.id.hard11);
        m[1][2] = (EditText)findViewById(R.id.hard12);
        m[1][3] = (EditText)findViewById(R.id.hard13);
        m[2][0] = (EditText)findViewById(R.id.hard20);
        m[2][1] = (EditText)findViewById(R.id.hard21);
        m[2][2] = (EditText)findViewById(R.id.hard22);
        m[2][3] = (EditText)findViewById(R.id.hard23);
        m[3][0] = (EditText)findViewById(R.id.hard30);
        m[3][1] = (EditText)findViewById(R.id.hard31);
        m[3][2] = (EditText)findViewById(R.id.hard32);
        m[3][3] = (EditText)findViewById(R.id.hard33);
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                m[i][j].setKeyListener(null);
        answer = (EditText)findViewById(R.id.answerHard);
        submit.setOnClickListener(this);
        Back.setOnClickListener(this);
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
                    .setNegativeButton("分享", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_TEXT," 我在线代+的矩阵运算练习困难模式下获得了" +Integer.toString(score) + "分!\n线代+，你专属的线代助手！http://apkl.oss-cn-qingdao.aliyuncs.com/pw.linearalgebraplus_v1.0beta.apk");
                            intent.setType("text/plain");
                            startActivity(Intent.createChooser(intent,"分享你的成绩"));
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();
        }
        else {
            qNum = ra.nextInt(qs.length);
            question.setText(qs[qNum]);
            for(int i=0;i<4;i++)
                for(int j=0;j<4;j++)
                    m[i][j].setText(Integer.toString(ra.nextInt(7)));
            answer.setText("");
            status.setText("题目" + Integer.toString(times) + "/10" + "       " + "得分:" + Integer.toString(score));
        }
    }

    final protected double SubMatrix(int x, int y, double[][] m) {
        double d[][]=new double[3][3];
        int xx=0,yy=0;
        for(int i=0;i<4;i++) {
            if(i==x)
                continue;
            for(int j=0;j<4;j++) {
                if(j==y)
                    continue;
                d[xx][yy]=m[i][j];
                yy++;
            }
            xx++;
            yy=0;
        }
        return d[0][0]*d[1][1]*d[2][2]+d[0][1]*d[1][2]*d[2][0]+d[1][0]*d[2][1]*d[0][2]-d[0][2]*d[1][1]*d[2][0]-d[0][0]*d[1][2]*d[2][1]-d[0][1]*d[1][0]*d[2][2];
    }

    @Override
    public void onClick(View v) {
        double sh[][] = new double[4][4];
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                sh[i][j] = Integer.parseInt(m[i][j].getText().toString());
        if(v.getId()==R.id.submitHard) {
            if(answer.getText().toString().equals("")) {
                Toast.makeText(this, "请输入答案!" , Toast.LENGTH_SHORT).show();
                return;
            }
            int correct=0;
            ans=Integer.parseInt(answer.getText().toString());
            if(qNum==0) {
                correct=Rank(sh,-1,4);
                if(correct==ans)
                    score+=10;
            }
            else if(qNum==1) {
                correct = (int)(sh[0][0]* SubMatrix(0,0,sh)-sh[0][1]* SubMatrix(0,1,sh)+sh[0][2]* SubMatrix(0,2,sh)-sh[0][3]* SubMatrix(0,3,sh));
                if(correct==ans)
                    score+=10;
            }
            else if(qNum==2) {
                correct=Rank(sh,-1,4);
                if(correct==3) {
                    correct=1;
                }
                else if(correct==1||correct==2) {
                    correct=0;
                }
                if(correct==ans)
                    score+=10;
            }
            else if(qNum==3) {
                correct = (int)(sh[0][0]+sh[1][1]+sh[2][2]+sh[3][3]);
                if(correct==ans)
                    score+=10;
            }
            if(correct==ans)
                Toast.makeText(this, "答案正确" , Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "答案不正确,正确答案为" + Integer.toString(correct) + ".", Toast.LENGTH_LONG).show();
        }
        else if(v.getId()==R.id.backHard) {
            finish();
        }
        newState();
    }
}
