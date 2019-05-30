package com.dhcc.module.infusion.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.dhcc.module.infusion.db.InfusionInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "INFUSION_INFO".
*/
public class InfusionInfoDao extends AbstractDao<InfusionInfo, Long> {

    public static final String TABLENAME = "INFUSION_INFO";

    /**
     * Properties of entity InfusionInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property SchEnDateTime = new Property(1, String.class, "schEnDateTime", false, "SCH_EN_DATE_TIME");
        public final static Property SchStDateTime = new Property(2, String.class, "schStDateTime", false, "SCH_ST_DATE_TIME");
        public final static Property Status = new Property(3, String.class, "status", false, "STATUS");
        public final static Property UserId = new Property(4, String.class, "userId", false, "USER_ID");
        public final static Property UserCode = new Property(5, String.class, "userCode", false, "USER_CODE");
        public final static Property UserName = new Property(6, String.class, "userName", false, "USER_NAME");
        public final static Property GroupDesc = new Property(7, String.class, "groupDesc", false, "GROUP_DESC");
        public final static Property GroupId = new Property(8, String.class, "groupId", false, "GROUP_ID");
        public final static Property HospitalRowId = new Property(9, String.class, "hospitalRowId", false, "HOSPITAL_ROW_ID");
        public final static Property LinkLoc = new Property(10, String.class, "linkLoc", false, "LINK_LOC");
        public final static Property LocDesc = new Property(11, String.class, "locDesc", false, "LOC_DESC");
        public final static Property LocId = new Property(12, String.class, "locId", false, "LOC_ID");
        public final static Property WardId = new Property(13, String.class, "wardId", false, "WARD_ID");
    }


    public InfusionInfoDao(DaoConfig config) {
        super(config);
    }
    
    public InfusionInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"INFUSION_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"SCH_EN_DATE_TIME\" TEXT," + // 1: schEnDateTime
                "\"SCH_ST_DATE_TIME\" TEXT," + // 2: schStDateTime
                "\"STATUS\" TEXT," + // 3: status
                "\"USER_ID\" TEXT," + // 4: userId
                "\"USER_CODE\" TEXT," + // 5: userCode
                "\"USER_NAME\" TEXT," + // 6: userName
                "\"GROUP_DESC\" TEXT," + // 7: groupDesc
                "\"GROUP_ID\" TEXT," + // 8: groupId
                "\"HOSPITAL_ROW_ID\" TEXT," + // 9: hospitalRowId
                "\"LINK_LOC\" TEXT," + // 10: linkLoc
                "\"LOC_DESC\" TEXT," + // 11: locDesc
                "\"LOC_ID\" TEXT," + // 12: locId
                "\"WARD_ID\" TEXT);"); // 13: wardId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"INFUSION_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, InfusionInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String schEnDateTime = entity.getSchEnDateTime();
        if (schEnDateTime != null) {
            stmt.bindString(2, schEnDateTime);
        }
 
        String schStDateTime = entity.getSchStDateTime();
        if (schStDateTime != null) {
            stmt.bindString(3, schStDateTime);
        }
 
        String status = entity.getStatus();
        if (status != null) {
            stmt.bindString(4, status);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(5, userId);
        }
 
        String userCode = entity.getUserCode();
        if (userCode != null) {
            stmt.bindString(6, userCode);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(7, userName);
        }
 
        String groupDesc = entity.getGroupDesc();
        if (groupDesc != null) {
            stmt.bindString(8, groupDesc);
        }
 
        String groupId = entity.getGroupId();
        if (groupId != null) {
            stmt.bindString(9, groupId);
        }
 
        String hospitalRowId = entity.getHospitalRowId();
        if (hospitalRowId != null) {
            stmt.bindString(10, hospitalRowId);
        }
 
        String linkLoc = entity.getLinkLoc();
        if (linkLoc != null) {
            stmt.bindString(11, linkLoc);
        }
 
        String locDesc = entity.getLocDesc();
        if (locDesc != null) {
            stmt.bindString(12, locDesc);
        }
 
        String locId = entity.getLocId();
        if (locId != null) {
            stmt.bindString(13, locId);
        }
 
        String wardId = entity.getWardId();
        if (wardId != null) {
            stmt.bindString(14, wardId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, InfusionInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String schEnDateTime = entity.getSchEnDateTime();
        if (schEnDateTime != null) {
            stmt.bindString(2, schEnDateTime);
        }
 
        String schStDateTime = entity.getSchStDateTime();
        if (schStDateTime != null) {
            stmt.bindString(3, schStDateTime);
        }
 
        String status = entity.getStatus();
        if (status != null) {
            stmt.bindString(4, status);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(5, userId);
        }
 
        String userCode = entity.getUserCode();
        if (userCode != null) {
            stmt.bindString(6, userCode);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(7, userName);
        }
 
        String groupDesc = entity.getGroupDesc();
        if (groupDesc != null) {
            stmt.bindString(8, groupDesc);
        }
 
        String groupId = entity.getGroupId();
        if (groupId != null) {
            stmt.bindString(9, groupId);
        }
 
        String hospitalRowId = entity.getHospitalRowId();
        if (hospitalRowId != null) {
            stmt.bindString(10, hospitalRowId);
        }
 
        String linkLoc = entity.getLinkLoc();
        if (linkLoc != null) {
            stmt.bindString(11, linkLoc);
        }
 
        String locDesc = entity.getLocDesc();
        if (locDesc != null) {
            stmt.bindString(12, locDesc);
        }
 
        String locId = entity.getLocId();
        if (locId != null) {
            stmt.bindString(13, locId);
        }
 
        String wardId = entity.getWardId();
        if (wardId != null) {
            stmt.bindString(14, wardId);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public InfusionInfo readEntity(Cursor cursor, int offset) {
        InfusionInfo entity = new InfusionInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // schEnDateTime
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // schStDateTime
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // status
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // userId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // userCode
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // userName
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // groupDesc
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // groupId
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // hospitalRowId
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // linkLoc
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // locDesc
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // locId
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13) // wardId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, InfusionInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSchEnDateTime(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSchStDateTime(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setStatus(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setUserId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUserCode(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setUserName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setGroupDesc(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setGroupId(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setHospitalRowId(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setLinkLoc(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setLocDesc(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setLocId(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setWardId(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(InfusionInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(InfusionInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(InfusionInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
