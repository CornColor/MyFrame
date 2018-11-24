package com.example.myframe.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Switch;

import com.example.myframe.R;

/**
 * created by dan
 */
public class CoustomDialog extends Dialog implements View.OnClickListener{
    OnMultiClickListener listener;
    public static CoustomDialog create(Context context,int mLayoutId,int mGravity,OnMultiClickListener listener){
        CoustomDialog dialog = new CoustomDialog(context);
        dialog.init(context,mLayoutId,mGravity,listener);
        return dialog;
    }

    public CoustomDialog(@NonNull Context context) {
        this(context,R.style.CoustomDialog);
    }

    public CoustomDialog(@NonNull Context context, int themeResId) {
        super(context,R.style.CoustomDialog);
    }

    protected CoustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context,R.style.CoustomDialog);
    }

    private void init (Context context,int mLayoutId,int mGravity,OnMultiClickListener listener){
        this.listener = listener;
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.gravity = mGravity;
        getWindow().setAttributes(lp);
        ViewGroup view = (ViewGroup) View.inflate(context,mLayoutId,null);
        int size = view.getChildCount();
        for (int i = 0; i < size;i++){
            View childView = view.getChildAt(i);
            if(childView.getId() == R.id.btn_confirm){
                childView.setOnClickListener(this);
            }else if(childView.getId() == R.id.btn_cancel){
                childView.setOnClickListener(this);
            }
        }
        setContentView(view);
    }

    @Override
    public void onClick(View v) {
        if(listener == null){
            return;
        }
        switch (v.getId()){
            //确定按钮
            case R.id.btn_confirm:
                listener.onConfirm(this,v);
                break;
                //取消
            case R.id.btn_cancel:
                listener.onCancel(this,v);
                break;
        }

    }

    /**
     * 多的点击事件监听
     */
    public interface OnMultiClickListener {
        void  onConfirm(Dialog dialog,View  view);
        void onCancel(Dialog dialog,View view);
    }

}
