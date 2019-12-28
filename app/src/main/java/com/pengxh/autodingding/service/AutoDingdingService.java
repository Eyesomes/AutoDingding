package com.pengxh.autodingding.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import com.pengxh.app.multilib.utils.BroadcastManager;
import com.pengxh.autodingding.utils.BroadcastAction;
import com.pengxh.autodingding.utils.Utils;

/**
 * @description: TODO
 * @author: Pengxh
 * @email: 290677893@qq.com
 * @date: 2019/12/25 23:17
 */
public class AutoDingdingService extends Service {

    private static final String TAG = "AutoDingdingService";
    private BroadcastManager broadcastManager;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: 自动打卡服务已启动");
        broadcastManager = BroadcastManager.getInstance(this);
        broadcastManager.addAction(BroadcastAction.ACTIONS, new BroadcastReceiver() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onReceive(final Context context, Intent intent) {
                //更新UI
                String action = intent.getAction();
                if (action != null) {
                    if (action.equals(BroadcastAction.ACTIONS[0])) {
                        String data = intent.getStringExtra("data");
                        long deltaTime = Long.parseLong(data) * 1000;
                        new CountDownTimer(deltaTime, 1000) {
                            @Override
                            public void onTick(long l) {
                                int tickTime = (int) (l / 1000);
                                //更新UI
                                broadcastManager.sendBroadcast(BroadcastAction.ACTIONS[2], String.valueOf(tickTime));
                            }

                            @Override
                            public void onFinish() {
                                Utils.openDingding(context, BroadcastAction.DINGDING);
                            }
                        }.start();
                    } else if (action.equals(BroadcastAction.ACTIONS[1])) {
                        String data = intent.getStringExtra("data");
                        long deltaTime = Long.parseLong(data) * 1000;
                        new CountDownTimer(deltaTime, 1000) {
                            @Override
                            public void onTick(long l) {
                                int tickTime = (int) (l / 1000);
                                //更新UI
                                broadcastManager.sendBroadcast(BroadcastAction.ACTIONS[3], String.valueOf(tickTime));
                            }

                            @Override
                            public void onFinish() {
                                Utils.openDingding(context, BroadcastAction.DINGDING);
                            }
                        }.start();
                    }
                } else {
                    Log.e(TAG, "onReceive: ", new Throwable());
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        broadcastManager.destroy(BroadcastAction.ACTIONS);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
