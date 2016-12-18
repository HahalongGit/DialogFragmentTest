package com.lll.dialogfragmenttest;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/12/18.
 * 添加了宿主activity和对话框交互
 */

public class MyDialogFragment extends DialogFragment {

    private DailogListener dailogListener;
    private static final String TAG = "MyDialogFragment";

    public int clickItem;

    private String items[];

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Bundle arguments = getArguments();
        items = arguments.getStringArray("ITEMS");
        try {
            dailogListener = (DailogListener) activity;//宿主activity实现了接口,强制实现
        } catch (ClassCastException e) {
            new ClassCastException(activity.toString() + "must implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setSingleChoiceItems(items, 1,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e(TAG, "onClick: dialog-" + which);
                       // Toast.makeText(getActivity(), "你点击了：" + which, Toast.LENGTH_SHORT).show();
                        clickItem = which;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dailogListener.onDialogPositiveClick(MyDialogFragment.this);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dailogListener.onDialogNegativeClick(MyDialogFragment.this);
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);

        return alertDialog;
    }

    /**
     * 点击回调
     */
    public interface DailogListener {
        void onDialogPositiveClick(DialogFragment dialog);

        void onDialogNegativeClick(DialogFragment dialog);
    }

}
