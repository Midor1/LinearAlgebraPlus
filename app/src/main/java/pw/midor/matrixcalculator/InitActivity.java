package pw.midor.matrixcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InitActivity extends Activity implements View.OnClickListener {
    Button cal,play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        cal = (Button)findViewById(R.id.cal);
        cal.setOnClickListener(this);
        play = (Button)findViewById(R.id.play);
        play.setOnClickListener(this);
    }

    public void onClick(View view) {
        if(view.getId()==R.id.cal) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if(view.getId()==R.id.play) {
            Intent intent = new Intent(this, DifficultySelectionActivity.class);
            startActivity(intent);
        }
    }

    private static boolean isExit = false;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出线代+",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}



