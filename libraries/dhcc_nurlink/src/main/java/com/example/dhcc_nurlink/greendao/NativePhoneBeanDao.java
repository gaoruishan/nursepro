package com.example.dhcc_nurlink.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NATIVE_PHONE_BEAN".
*/
public class NativePhoneBeanDao extends AbstractDao<NativePhoneBean, Long> {

    public static final String TABLENAME = "NATIVE_PHONE_BEAN";

    /**
     * Properties of entity NativePhoneBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property CTLOCDesc = new Property(1, String.class, "CTLOCDesc", false, "CTLOCDESC");
        public final static Property DeviceId = new Property(2, String.class, "DeviceId", false, "DEVICE_ID");
        public final static Property FirstFlag = new Property(3, String.class, "FirstFlag", false, "FIRST_FLAG");
        public final static Property FirstPin = new Property(4, String.class, "FirstPin", false, "FIRST_PIN");
        public final static Property HCCSCLRowId = new Property(5, String.class, "HCCSCLRowId", false, "HCCSCLROW_ID");
        public final static Property NickName = new Property(6, String.class, "NickName", false, "NICK_NAME");
        public final static Property PinName = new Property(7, String.class, "PinName", false, "PIN_NAME");
        public final static Property UserCode = new Property(8, String.class, "UserCode", false, "USER_CODE");
        public final static Property UserId = new Property(9, String.class, "UserId", false, "USER_ID");
        public final static Property UserName = new Property(10, String.class, "UserName", false, "USER_NAME");
        public final static Property UserType = new Property(11, String.class, "UserType", false, "USER_TYPE");
        public final static Property VOIPId = new Property(12, String.class, "VOIPId", false, "VOIPID");
        public final static Property StrGroupList = new Property(13, String.class, "strGroupList", false, "STR_GROUP_LIST");
    }


    public NativePhoneBeanDao(DaoConfig config) {
        super(config);
    }
    
    public NativePhoneBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NATIVE_PHONE_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CTLOCDESC\" TEXT," + // 1: CTLOCDesc
                "\"DEVICE_ID\" TEXT," + // 2: DeviceId
                "\"FIRST_FLAG\" TEXT," + // 3: FirstFlag
                "\"FIRST_PIN\" TEXT," + // 4: FirstPin
                "\"HCCSCLROW_ID\" TEXT," + // 5: HCCSCLRowId
                "\"NICK_NAME\" TEXT," + // 6: NickName
                "\"PIN_NAME\" TEXT," + // 7: PinName
                "\"USER_CODE\" TEXT," + // 8: UserCode
                "\"USER_ID\" TEXT," + // 9: UserId
                "\"USER_NAME\" TEXT," + // 10: UserName
                "\"USER_TYPE\" TEXT," + // 11: UserType
                "\"VOIPID\" TEXT," + // 12: VOIPId
                "\"STR_GROUP_LIST\" TEXT);"); // 13: strGroupList
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NATIVE_PHONE_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, NativePhoneBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String CTLOCDesc = entity.getCTLOCDesc();
        if (CTLOCDesc != null) {
            stmt.bindString(2, CTLOCDesc);
        }
 
        String DeviceId = entity.getDeviceId();
        if (DeviceId != null) {
            stmt.bindString(3, DeviceId);
        }
 
        String FirstFlag = entity.getFirstFlag();
        if (FirstFlag != null) {
            stmt.bindString(4, FirstFlag);
        }
 
        String FirstPin = entity.getFirstPin();
        if (FirstPin != null) {
            stmt.bindString(5, FirstPin);
        }
 
        String HCCSCLRowId = entity.getHCCSCLRowId();
        if (HCCSCLRowId != null) {
            stmt.bindString(6, HCCSCLRowId);
        }
 
        String NickName = entity.getNickName();
        if (NickName != null) {
            stmt.bindString(7, NickName);
        }
 
        String PinName = entity.getPinName();
        if (PinName != null) {
            stmt.bindString(8, PinName);
        }
 
        String UserCode = entity.getUserCode();
        if (UserCode != null) {
            stmt.bindString(9, UserCode);
        }
 
        String UserId = entity.getUserId();
        if (UserId != null) {
            stmt.bindString(10, UserId);
        }
 
        String UserName = entity.getUserName();
        if (UserName != null) {
            stmt.bindString(11, UserName);
        }
 
        String UserType = entity.getUserType();
        if (UserType != null) {
            stmt.bindString(12, UserType);
        }
 
        String VOIPId = entity.getVOIPId();
        if (VOIPId != null) {
            stmt.bindString(13, VOIPId);
        }
 
        String strGroupList = entity.getStrGroupList();
        if (strGroupList != null) {
            stmt.bindString(14, strGroupList);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, NativePhoneBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String CTLOCDesc = entity.getCTLOCDesc();
        if (CTLOCDesc != null) {
            stmt.bindString(2, CTLOCDesc);
        }
 
        String DeviceId = entity.getDeviceId();
        if (DeviceId != null) {
            stmt.bindString(3, DeviceId);
        }
 
        String FirstFlag = entity.getFirstFlag();
        if (FirstFlag != null) {
            stmt.bindString(4, FirstFlag);
        }
 
        String FirstPin = entity.getFirstPin();
        if (FirstPin != null) {
            stmt.bindString(5, FirstPin);
        }
 
        String HCCSCLRowId = entity.getHCCSCLRowId();
        if (HCCSCLRowId != null) {
            stmt.bindString(6, HCCSCLRowId);
        }
 
        String NickName = entity.getNickName();
        if (NickName != null) {
            stmt.bindString(7, NickName);
        }
 
        String PinName = entity.getPinName();
        if (PinName != null) {
            stmt.bindString(8, PinName);
        }
 
        String UserCode = entity.getUserCode();
        if (UserCode != null) {
            stmt.bindString(9, UserCode);
        }
 
        String UserId = entity.getUserId();
        if (UserId != null) {
            stmt.bindString(10, UserId);
        }
 
        String UserName = entity.getUserName();
        if (UserName != null) {
            stmt.bindString(11, UserName);
        }
 
        String UserType = entity.getUserType();
        if (UserType != null) {
            stmt.bindString(12, UserType);
        }
 
        String VOIPId = entity.getVOIPId();
        if (VOIPId != null) {
            stmt.bindString(13, VOIPId);
        }
 
        String strGroupList = entity.getStrGroupList();
        if (strGroupList != null) {
            stmt.bindString(14, strGroupList);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public NativePhoneBean readEntity(Cursor cursor, int offset) {
        NativePhoneBean entity = new NativePhoneBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // CTLOCDesc
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // DeviceId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // FirstFlag
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // FirstPin
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // HCCSCLRowId
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // NickName
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // PinName
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // UserCode
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // UserId
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // UserName
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // UserType
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // VOIPId
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13) // strGroupList
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, NativePhoneBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCTLOCDesc(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDeviceId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFirstFlag(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setFirstPin(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setHCCSCLRowId(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setNickName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setPinName(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setUserCode(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setUserId(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setUserName(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setUserType(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setVOIPId(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setStrGroupList(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(NativePhoneBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(NativePhoneBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(NativePhoneBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
