package com.lll.dialogfragmenttest;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,MyDialogFragment.DailogListener{

    private static final String TAG = "MainActivity";
    private Button btn_show;
    private Button btn_show2;
    private Button btn_show3;
    private Button btn_show4;
    private String items[] = new String[]{"计算机科学", "Oracke", "JAVA", "计算机网络"};
    private NoticeDialogFragment dialogFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialogFragment = new NoticeDialogFragment();
        btn_show = (Button) findViewById(R.id.btn_show);
        btn_show2 = (Button) findViewById(R.id.btn_show2);
        btn_show3 = (Button) findViewById(R.id.btn_show3);
        btn_show4 = (Button) findViewById(R.id.btn_show4);
        btn_show.setOnClickListener(this);
        btn_show2.setOnClickListener(this);
        btn_show3.setOnClickListener(this);
        btn_show4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:{//show一个对话框
                Log.e(TAG, "onClick: showFragmentDialog");
                dialogFragment.show(getSupportFragmentManager(),"fragmentID");
                break;
            }
            case R.id.btn_show2:{
                showMyDialog();
                break;
            }
            case R.id.btn_show3:{
                showTimePicker();
                break;
            }
            case R.id.btn_show4:{
                datePicker();
                break;
            }
        }
    }

    /**
     * 日期选择
     */
    private void datePicker() {
        DatePickerFragment pickerFragment = new DatePickerFragment();
        pickerFragment.show(getSupportFragmentManager(),"datePickerFragmentID");
    }

    /**
     * 时间选择器
     */
    private void showTimePicker() {
        TimePickerFragment pickerFragment = new TimePickerFragment();
        pickerFragment.show(getSupportFragmentManager(),"timePickerFragmentID");
    }

    /**
     * 创建对话框
     * 第二个参数 "myDialmogFragmentID" 是系统用于保存片段状态并在必要时进行恢复的唯一标记名称。
     * 该标记还允许您通过调用 findFragmentByTag() 获取片段的句柄
     */
    private void showMyDialog() {
        MyDialogFragment myDialogFragment = new MyDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("ITEMS",items);
        myDialogFragment.setArguments(bundle);
        myDialogFragment.show(getSupportFragmentManager(),"myDialmogFragmentID");
    }

    /**
     * 实现接口回调，进行交互
     * @param dialog
     */
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        MyDialogFragment myDialogFragment = (MyDialogFragment) dialog;
        Log.e(TAG, "onDialogPositiveClick: "+items[myDialogFragment.clickItem] );
        Toast.makeText(this, "点击了确定:"+myDialogFragment.clickItem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(this, "点击了取消", Toast.LENGTH_SHORT).show();
    }
}
