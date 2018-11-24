package com.example.myframe.ui.base;

import android.annotation.SuppressLint;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.myframe.R;
import com.example.myframe.eventbus.MessageEvent;
import com.example.myframe.other.AppManager;
import com.example.myframe.permission.EasyPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class BaseActivity extends SupportActivity implements BaseView,EasyPermissions.PermissionWithDialogCallbacks {
    //注解框架绑定信息
    private Unbinder unbinder;
    //当被观察者被订阅会出现Disposable，用于存储Disposable当页面销毁的时候取消订阅
    private CompositeDisposable mCompositeDisposable;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity管理
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            AppManager.getInstance().addActivity(this);
        }
        //添加注解框架
        unbinder = ButterKnife.bind(this);
        //注册事件接收
        EventBus.getDefault().register(this);
        mCompositeDisposable = new CompositeDisposable();
        initView();
        initData();
        initPresenter();
    }

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化view
     */
    public abstract void initView();

    /**
     * 初始化Presenter
     */
    public abstract void initPresenter();


    /**
     * 添加订阅
     */
    public void addDisposable(Disposable mDisposable) {
        if (mCompositeDisposable != null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(mDisposable);
    }

    /**
     * 取消所有订阅
     */
    public void clearDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }


    @Override
    public void showToast(String msg) {
        if(!isDeath()){
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showProgressDialog(String msg) {
       if(isDeath()){
           return;
       }
        if (msg != null || !"".equals(msg)) {
            ProgressDialog dialog;
            dialog = ProgressDialog.show(this,"",msg);
            dialog.show();
        }
    }

    /**
     * 判断页面是否死亡
     * @return
     */
    public boolean isDeath(){
        if (this == null && this.isFinishing()) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (this.isDestroyed()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void showDialog(String ...msg) {
        if(isDeath()){
            return;
        }
        String[]data = msg;
        int lenght = data.length;
        if(data!= null){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            switch (lenght){
                case 1: {
                    String message = data[0];
                    builder.setMessage(message);
                }
                    break;
                case 2: {
                    String title = data[0];
                    String message = data[1];
                    builder.setTitle(title);
                    builder.setMessage(message);
                }
                    break;
            }
             builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            builder.create().show();
        }

    }

    @Subscribe
    public void onBusEvent(MessageEvent messageEvent) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            AppManager.getInstance().finishActivity(this);
        }
        //解除绑定
        unbinder.unbind();
        //取消所有订阅
        clearDisposable();
        /**
         * 解除EventBus绑定
         */
        EventBus.getDefault().unregister(this);
    }
    /**
     * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     * Android6.0权限控制
     * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     */
    private Map<Integer, PermissionCallback> mPermissonCallbacks = null;
    private Map<Integer, String[]> mPermissions = null;

    public interface PermissionCallback {
        /**
         * has all permission
         *
         * @param allPerms all permissions
         */
        void hasPermission(List<String> allPerms);

        /**
         * denied some permission
         *
         * @param deniedPerms          denied permission
         * @param grantedPerms         granted permission
         * @param hasPermanentlyDenied has permission denied permanently
         */
        void noPermission(List<String> deniedPerms, List<String> grantedPerms, Boolean hasPermanentlyDenied);

        /**
         * @param dialogType dialogType
         * @param callback   callback from easypermissions
         */
        void showDialog(int dialogType, EasyPermissions.DialogCallback callback);
    }

    /**
     * request permission
     *
     * @param dialogType  dialogType
     * @param requestCode requestCode
     * @param perms       permissions
     * @param callback    callback
     */
    public void performCodeWithPermission(int dialogType,
                                          final int requestCode, @NonNull String[] perms, @NonNull PermissionCallback callback) {
        if (EasyPermissions.hasPermissions(this, perms)) {
            callback.hasPermission(Arrays.asList(perms));
        } else {
            if (mPermissonCallbacks == null) {
                mPermissonCallbacks = new HashMap<>();
            }
            mPermissonCallbacks.put(requestCode, callback);

            if (mPermissions == null) {
                mPermissions = new HashMap<>();
            }
            mPermissions.put(requestCode, perms);

            EasyPermissions.requestPermissions(this, dialogType, requestCode, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (mPermissonCallbacks == null || !mPermissonCallbacks.containsKey(requestCode)) {
            return;
        }
        if (mPermissions == null || !mPermissions.containsKey(requestCode)) {
            return;
        }

        // 100% granted permissions
        if (mPermissions.get(requestCode).length == perms.size()) {
            mPermissonCallbacks.get(requestCode).hasPermission(Arrays.asList(mPermissions.get(requestCode)));
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (mPermissonCallbacks == null || !mPermissonCallbacks.containsKey(requestCode)) {
            return;
        }
        if (mPermissions == null || !mPermissions.containsKey(requestCode)) {
            return;
        }

        //granted permission
        List<String> grantedPerms = new ArrayList<>();
        for (String perm : mPermissions.get(requestCode)) {
            if (!perms.contains(perm)) {
                grantedPerms.add(perm);
            }
        }

        //check has permission denied permanently
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            mPermissonCallbacks.get(requestCode).noPermission(perms, grantedPerms, true);
        } else {
            mPermissonCallbacks.get(requestCode).noPermission(perms, grantedPerms, false);
        }
    }

    @Override
    public void onDialog(int requestCode, int dialogType, EasyPermissions.DialogCallback callback) {
        if (mPermissonCallbacks == null || !mPermissonCallbacks.containsKey(requestCode)) {
            return;
        }
        mPermissonCallbacks.get(requestCode).showDialog(dialogType, callback);
    }
}
