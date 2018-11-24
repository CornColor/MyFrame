package com.example.myframe.utils.db;

import android.database.sqlite.SQLiteException;
import android.util.Log;



import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Collection;
import java.util.List;

/**
 * @Description: 数据库操作管理
 */
public abstract class DBManager<M, K> implements IDatabase<M, K> {
    public static final String TAG = "DBManager";
    @Override
    public boolean insert(@NotNull M m) {
        try {
            getAbstractDao().insert(m);
        } catch (SQLiteException e) {
            Log.e(TAG,"SQLiteException"+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean insertOrReplace(@NotNull M m) {
        try {
            getAbstractDao().insertOrReplace(m);
        } catch (SQLiteException e) {
            Log.e(TAG,"SQLiteException"+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean insertInTx(@NotNull List<M> list) {
        try {
            getAbstractDao().insertInTx(list);
        } catch (SQLiteException e) {
            Log.e(TAG,"SQLiteException"+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean insertOrReplaceInTx(@NotNull List<M> list) {
        try {
            getAbstractDao().insertOrReplaceInTx(list);
        } catch (SQLiteException e) {
            Log.e(TAG,"SQLiteException"+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(@NotNull M m) {
        try {
            getAbstractDao().delete(m);
        } catch (SQLiteException e) {
            Log.e(TAG,"SQLiteException"+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteByKey(@NotNull K key) {
        try {
            getAbstractDao().deleteByKey(key);
        } catch (SQLiteException e) {
            Log.e(TAG,"SQLiteException"+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteInTx(@NotNull List<M> list) {
        try {
            getAbstractDao().deleteInTx(list);
        } catch (SQLiteException e) {
            Log.e(TAG,"SQLiteException"+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteByKeyInTx(@NotNull K... key) {
        try {
            getAbstractDao().deleteByKeyInTx(key);
        } catch (SQLiteException e) {
            Log.e(TAG,"SQLiteException"+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAll() {
        try {
            getAbstractDao().deleteAll();
        } catch (SQLiteException e) {
            Log.e(TAG,"SQLiteException"+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(@NotNull M m) {
        try {
            getAbstractDao().update(m);
        } catch (SQLiteException e) {
            Log.e(TAG,"SQLiteException"+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateInTx(@NotNull M... m) {
        try {
            getAbstractDao().updateInTx(m);
        } catch (SQLiteException e) {
            Log.e(TAG,"SQLiteException"+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateInTx(@NotNull List<M> list) {
        try {
            getAbstractDao().updateInTx(list);
        } catch (SQLiteException e) {
            Log.e(TAG,"SQLiteException"+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public M load(@NotNull K key) {
        try {
            return getAbstractDao().load(key);
        } catch (SQLiteException e) {
            Log.e(TAG,"SQLiteException"+e.getMessage());
            return null;
        }
    }

    @Override
    public List<M> loadAll() {
        return getAbstractDao().loadAll();
    }

    @Override
    public boolean refresh(@NotNull M m) {
        try {
            getAbstractDao().refresh(m);
        } catch (SQLiteException e) {
            Log.e(TAG,"SQLiteException"+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void runInTx(@NotNull Runnable runnable) {
        try {
            getAbstractDao().getSession().runInTx(runnable);
        } catch (SQLiteException e) {
            Log.e(TAG,"SQLiteException"+e.getMessage());
        }
    }

    @Override
    public QueryBuilder<M> queryBuilder() {
        return getAbstractDao().queryBuilder();
    }

    @Override
    public List<M> queryRaw(@NotNull String where, @NotNull String... selectionArg) {
        return getAbstractDao().queryRaw(where, selectionArg);
    }

    @Override
    public Query<M> queryRawCreate(@NotNull String where, @NotNull Object... selectionArg) {
        return getAbstractDao().queryRawCreate(where, selectionArg);
    }

    @Override
    public Query<M> queryRawCreateListArgs(@NotNull String where, @NotNull Collection<Object> selectionArg) {
        return getAbstractDao().queryRawCreateListArgs(where, selectionArg);
    }

    @Override
    public abstract AbstractDao<M, K> getAbstractDao();

}
