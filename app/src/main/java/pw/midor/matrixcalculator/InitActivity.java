package pw.midor.matrixcalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class InitActivity extends Activity{
    private static final String TAG = "Init";
    private static boolean isExit = false;
    final InitActivity This = this;
    ListView lv;
    ArrayList<String> list = new ArrayList<>();
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        initCloudChannel(this);
        lv = (ListView)findViewById(R.id.listView);
        list.add("矩阵计算器");
        list.add("矩阵运算练习");
        list.add("关于");
        list.add("退出");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(list.get(position).equals("矩阵计算器")) {
                    Intent intent = new Intent(This,ChooseActivity.class);
                    startActivity(intent);
                }
                if(list.get(position).equals("矩阵运算练习")) {
                    Intent intent = new Intent(This,DifficultySelectionActivity.class);
                    startActivity(intent);
                }
                if(list.get(position).equals("关于")) {
                    Intent intent = new Intent(This,AboutActivity.class);
                    startActivity(intent);
                }
                if(list.get(position).equals("退出")) {
                    finish();
                }
            }
        });
    }

    private void initCloudChannel(Context applicationContext) {
        PushServiceFactory.init(applicationContext);
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d(TAG, "init cloudchannel success");
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.d(TAG, "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });
    }

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



