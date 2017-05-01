package com.lcbo.model.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CART".
*/
public class CartDao extends AbstractDao<Cart, Long> {

    public static final String TABLENAME = "CART";

    /**
     * Properties of entity Cart.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ProductId = new Property(1, int.class, "productId", false, "PRODUCT_ID");
        public final static Property ProductName = new Property(2, String.class, "productName", false, "PRODUCT_NAME");
        public final static Property ProductMl = new Property(3, String.class, "productMl", false, "PRODUCT_ML");
        public final static Property PrimaryCategory = new Property(4, String.class, "primaryCategory", false, "PRIMARY_CATEGORY");
        public final static Property SecondaryCategory = new Property(5, String.class, "secondaryCategory", false, "SECONDARY_CATEGORY");
        public final static Property ProductImg = new Property(6, String.class, "productImg", false, "PRODUCT_IMG");
        public final static Property Count = new Property(7, int.class, "count", false, "COUNT");
        public final static Property Cost = new Property(8, int.class, "cost", false, "COST");
    }


    public CartDao(DaoConfig config) {
        super(config);
    }
    
    public CartDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CART\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"PRODUCT_ID\" INTEGER NOT NULL UNIQUE ," + // 1: productId
                "\"PRODUCT_NAME\" TEXT," + // 2: productName
                "\"PRODUCT_ML\" TEXT," + // 3: productMl
                "\"PRIMARY_CATEGORY\" TEXT," + // 4: primaryCategory
                "\"SECONDARY_CATEGORY\" TEXT," + // 5: secondaryCategory
                "\"PRODUCT_IMG\" TEXT," + // 6: productImg
                "\"COUNT\" INTEGER NOT NULL ," + // 7: count
                "\"COST\" INTEGER NOT NULL );"); // 8: cost
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CART\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Cart entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getProductId());
 
        String productName = entity.getProductName();
        if (productName != null) {
            stmt.bindString(3, productName);
        }
 
        String productMl = entity.getProductMl();
        if (productMl != null) {
            stmt.bindString(4, productMl);
        }
 
        String primaryCategory = entity.getPrimaryCategory();
        if (primaryCategory != null) {
            stmt.bindString(5, primaryCategory);
        }
 
        String secondaryCategory = entity.getSecondaryCategory();
        if (secondaryCategory != null) {
            stmt.bindString(6, secondaryCategory);
        }
 
        String productImg = entity.getProductImg();
        if (productImg != null) {
            stmt.bindString(7, productImg);
        }
        stmt.bindLong(8, entity.getCount());
        stmt.bindLong(9, entity.getCost());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Cart entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getProductId());
 
        String productName = entity.getProductName();
        if (productName != null) {
            stmt.bindString(3, productName);
        }
 
        String productMl = entity.getProductMl();
        if (productMl != null) {
            stmt.bindString(4, productMl);
        }
 
        String primaryCategory = entity.getPrimaryCategory();
        if (primaryCategory != null) {
            stmt.bindString(5, primaryCategory);
        }
 
        String secondaryCategory = entity.getSecondaryCategory();
        if (secondaryCategory != null) {
            stmt.bindString(6, secondaryCategory);
        }
 
        String productImg = entity.getProductImg();
        if (productImg != null) {
            stmt.bindString(7, productImg);
        }
        stmt.bindLong(8, entity.getCount());
        stmt.bindLong(9, entity.getCost());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Cart readEntity(Cursor cursor, int offset) {
        Cart entity = new Cart( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // productId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // productName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // productMl
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // primaryCategory
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // secondaryCategory
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // productImg
            cursor.getInt(offset + 7), // count
            cursor.getInt(offset + 8) // cost
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Cart entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setProductId(cursor.getInt(offset + 1));
        entity.setProductName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setProductMl(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPrimaryCategory(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSecondaryCategory(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setProductImg(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCount(cursor.getInt(offset + 7));
        entity.setCost(cursor.getInt(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Cart entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Cart entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Cart entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
