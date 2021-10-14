package com.dhcc.nursepro.workarea.rfid;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/**
 * 蓝牙工具
 * @author:gaoruishan
 * @date:202021/10/14/08:55
 * @email:grs0515@163.com
 */
public class BluetoothUtil {
    private static final boolean isOpen = true;

    private BlueToothReceiver mBlueReceive;
    private String deviceName;
    private String deviceAddress;
    private int maxRssi = -200;
    private boolean isDiscovery = false;
    private BluetoothAdapter mBluetoothAdapter;

    public class BlueToothReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent){
            String action = intent.getAction();
            // 发现设备
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //获得 BluetoothDevice
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                short rssi = intent.getExtras().getShort(BluetoothDevice.EXTRA_RSSI);
                Log.d("lhu",device.getName()+";address="+device.getAddress()+";state="+device.getBondState()+";rssi="+rssi);
                if(rssi > maxRssi) {
                    maxRssi = rssi;
                    deviceName = TextUtils.isEmpty(device.getName())?"未知设备":device.getName();
                    deviceAddress = device.getAddress();
                }
                if(isDiscovery && deviceName.startsWith("SRW")){
                    endDiscover();
                }
            }
        }
    }

    public void register(Activity context){
        if (!isOpen) {
            return;
        }
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(context,"当前设备不支持蓝牙！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(mBlueReceive == null) {
            IntentFilter mFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            mBlueReceive = new BlueToothReceiver();
            context.registerReceiver(mBlueReceive, mFilter);
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            context.startActivityForResult(enableBtIntent, 0x12);
        } else {
            startDiscovery();
        }
    }

    public void unRegister(Activity context) {
        if (!isOpen) {
            return;
        }
        if (mBlueReceive != null) {
            context.unregisterReceiver(mBlueReceive);
        }
    }

    private void startDiscovery(){
        isDiscovery = true;
//        showLoadingDialog();
        mBluetoothAdapter.startDiscovery();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if(isDiscovery) {
                    endDiscover();
                }
            }
        },10000);
    }

    private void endDiscover() {

    }
}
