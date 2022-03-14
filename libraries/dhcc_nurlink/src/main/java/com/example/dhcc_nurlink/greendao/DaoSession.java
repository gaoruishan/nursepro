package com.example.dhcc_nurlink.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.dhcc_nurlink.greendao.NativePhoneBean;

import com.example.dhcc_nurlink.greendao.NativePhoneBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig nativePhoneBeanDaoConfig;

    private final NativePhoneBeanDao nativePhoneBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        nativePhoneBeanDaoConfig = daoConfigMap.get(NativePhoneBeanDao.class).clone();
        nativePhoneBeanDaoConfig.initIdentityScope(type);

        nativePhoneBeanDao = new NativePhoneBeanDao(nativePhoneBeanDaoConfig, this);

        registerDao(NativePhoneBean.class, nativePhoneBeanDao);
    }
    
    public void clear() {
        nativePhoneBeanDaoConfig.clearIdentityScope();
    }

    public NativePhoneBeanDao getNativePhoneBeanDao() {
        return nativePhoneBeanDao;
    }

}