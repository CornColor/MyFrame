package com.example.myframe.ui.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myframe.R;
import com.example.myframe.utils.util.SizeUtils;

/**
 * created by dan
 */
public class BaseToolBarActivity extends BaseActivity {
    //左侧
    public static final int TAG_LEFT = 0;
    public static final int TAG_RIGHT = 1;
    //左侧图标
    private LinearLayout rlLeft;
    private ImageView btnLeft;
    private TextView tvLeft;
    //标题
    private TextView tvTitle;
    //右侧图标
    private LinearLayout rlRight;
    private ImageView btnRight;
    private TextView tvRight;
    //根布局
    private ViewGroup mRootView;
    //内容布局
    private ViewGroup mContentView;
    //ToolBar
    private ViewGroup mToolBarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        try {
            mRootView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.common_toolbar_activity,null);
            rlLeft = mRootView.findViewById(R.id.rl_left);
            btnLeft = mRootView.findViewById(R.id.btn_left);
            tvLeft = mRootView.findViewById(R.id.tv_left);

            rlRight = mRootView.findViewById(R.id.rl_right);
            btnRight = mRootView.findViewById(R.id.btn_right);
            tvRight  = mRootView.findViewById(R.id.rl_right);

            tvTitle = mRootView.findViewById(R.id.tv_title);
            mToolBarView = mRootView.findViewById(R.id.rl_toolbar);
            mContentView = mRootView.findViewById(R.id.fl_content);
        }catch (Exception e){
        }
        if(mRootView!=null){
            try {
                View mChildContentView = LayoutInflater.from(this).inflate(layoutResID,null);
                if(mChildContentView!=null){
                    mContentView.addView(mChildContentView);
                }
            }catch (Exception e){
            }
            super.setContentView(mRootView);
        }else {
            super.setContentView(layoutResID);
        }
    }

    /**
     * 设置按钮
     * @param tag        用于标记是哪一个按钮
     * @param resourceId 按钮的图片资源
     * @param text       按钮的文本
     * @param size       文本大小
     * @param mColor     文本颜色
     */
    public void setBtn(int tag,int resourceId,String text,int size,String mColor){
        switch (tag){
            case TAG_LEFT:
                btnLeft.setImageResource(resourceId);
                tvLeft.setText(text);
                tvLeft.setTextColor(Color.parseColor(mColor));
                tvLeft.setTextSize(SizeUtils.sp2px(size));
                break;
            case TAG_RIGHT:
                btnRight.setImageResource(resourceId);
                tvRight.setText(text);
                tvRight.setTextColor(Color.parseColor(mColor));
                tvRight.setTextSize(SizeUtils.sp2px(size));
                break;
        }
    }

    public void setToolBarBackgroundColor(String mColor){
        mToolBarView.setBackgroundColor(Color.parseColor(mColor));
    }
    public void setToolBarBackgroundColor(int mColor){
        mToolBarView.setBackgroundColor(mColor);
    }
    public void setToolBarBackground(int resource){
        mToolBarView.setBackgroundResource(resource);
    }
    /**
     * 隐藏头部
     * @param isHide
     */
    public void hideToolBar(boolean isHide){
        if(isHide){
            mToolBarView.setVisibility(View.GONE);
        }else {
            mToolBarView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置左侧按钮点击事件
     * @param leftClickListener
     */
    public void setBtnLeftClickListener(View.OnClickListener leftClickListener){
        if(rlLeft!= null){
            rlLeft.setOnClickListener(leftClickListener);
        }
    }

    /**
     * 设置右侧按钮点击事件
     * @param rightClickListener
     */
    public void setBtnRightClickListener(View.OnClickListener rightClickListener){
        if(rlRight!= null){
            rlRight.setOnClickListener(rightClickListener);
        }
    }

    /**
     * 设置标题
     * @param title
     */
    public void setTextTitle(String title){
        if(tvTitle!= null){
            tvTitle.setText(title);
        }
    }


    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {

    }
}
