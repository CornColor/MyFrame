package com.example.myframe.utils.db;

import android.content.Context;

import com.example.myframe.db.DaoMaster;
import com.example.myframe.db.DaoSession;
import com.example.myframe.db.User;

import org.greenrobot.greendao.AbstractDao;


public class DbHelper {
    private static final String DB_NAME = "woosport.db";//数据库名称
    private static DbHelper instance;

    private static DBManager<User,Long> baseConfigLongDBManager;
    private DaoMaster.DevOpenHelper mHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private DbHelper() {

    }

    public static DbHelper getInstance() {
        if (instance == null) {
            synchronized (DbHelper.class) {
                if (instance == null) {
                    instance = new DbHelper();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        mHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public void init(Context context, String dbName) {
        mHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }


    public DBManager<User, Long> userConfigLongDBManager() {
        if (baseConfigLongDBManager == null) {
            baseConfigLongDBManager = new DBManager<User, Long>(){
                @Override
                public AbstractDao<User, Long> getAbstractDao() {
                    return mDaoSession.getUserDao();
                }
            };
        }
        return baseConfigLongDBManager;
    }



    public DaoMaster getDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public void clear() {
        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }
    }

    public void close() {
        clear();
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
    }
}
