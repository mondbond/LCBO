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
 * DAO for table "STORE".
*/
public class StoreDao extends AbstractDao<Store, Long> {

    public static final String TABLENAME = "STORE";

    /**
     * Properties of entity Store.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property IsDead = new Property(1, boolean.class, "isDead", false, "IS_DEAD");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Tags = new Property(3, String.class, "tags", false, "TAGS");
        public final static Property AddressLine1 = new Property(4, String.class, "addressLine1", false, "ADDRESS_LINE1");
        public final static Property AddressLine2 = new Property(5, String.class, "addressLine2", false, "ADDRESS_LINE2");
        public final static Property City = new Property(6, String.class, "city", false, "CITY");
        public final static Property PostalCode = new Property(7, String.class, "postalCode", false, "POSTAL_CODE");
        public final static Property Telephone = new Property(8, String.class, "telephone", false, "TELEPHONE");
        public final static Property Latitude = new Property(9, double.class, "latitude", false, "LATITUDE");
        public final static Property Longitude = new Property(10, double.class, "longitude", false, "LONGITUDE");
        public final static Property ProductsCount = new Property(11, int.class, "productsCount", false, "PRODUCTS_COUNT");
        public final static Property InventoryCount = new Property(12, int.class, "inventoryCount", false, "INVENTORY_COUNT");
        public final static Property InventoryPriceInCents = new Property(13, int.class, "inventoryPriceInCents", false, "INVENTORY_PRICE_IN_CENTS");
        public final static Property InventoryVolumeInMilliliters = new Property(14, int.class, "inventoryVolumeInMilliliters", false, "INVENTORY_VOLUME_IN_MILLILITERS");
        public final static Property HasWheelchairAccessability = new Property(15, boolean.class, "hasWheelchairAccessability", false, "HAS_WHEELCHAIR_ACCESSABILITY");
        public final static Property HasBilingualServices = new Property(16, boolean.class, "hasBilingualServices", false, "HAS_BILINGUAL_SERVICES");
        public final static Property HasProductConsultant = new Property(17, boolean.class, "hasProductConsultant", false, "HAS_PRODUCT_CONSULTANT");
        public final static Property HasTastingBar = new Property(18, boolean.class, "hasTastingBar", false, "HAS_TASTING_BAR");
        public final static Property HasBeerColdRoom = new Property(19, boolean.class, "hasBeerColdRoom", false, "HAS_BEER_COLD_ROOM");
        public final static Property HasSpecialOccasionPermits = new Property(20, boolean.class, "hasSpecialOccasionPermits", false, "HAS_SPECIAL_OCCASION_PERMITS");
        public final static Property HasVintagesCorner = new Property(21, boolean.class, "hasVintagesCorner", false, "HAS_VINTAGES_CORNER");
        public final static Property HasParking = new Property(22, boolean.class, "hasParking", false, "HAS_PARKING");
        public final static Property HasTransitAccess = new Property(23, boolean.class, "hasTransitAccess", false, "HAS_TRANSIT_ACCESS");
        public final static Property UpdatedAt = new Property(24, String.class, "updatedAt", false, "UPDATED_AT");
    }

    private DaoSession daoSession;


    public StoreDao(DaoConfig config) {
        super(config);
    }
    
    public StoreDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"STORE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE ," + // 0: id
                "\"IS_DEAD\" INTEGER NOT NULL ," + // 1: isDead
                "\"NAME\" TEXT," + // 2: name
                "\"TAGS\" TEXT," + // 3: tags
                "\"ADDRESS_LINE1\" TEXT," + // 4: addressLine1
                "\"ADDRESS_LINE2\" TEXT," + // 5: addressLine2
                "\"CITY\" TEXT," + // 6: city
                "\"POSTAL_CODE\" TEXT," + // 7: postalCode
                "\"TELEPHONE\" TEXT," + // 8: telephone
                "\"LATITUDE\" REAL NOT NULL ," + // 9: latitude
                "\"LONGITUDE\" REAL NOT NULL ," + // 10: longitude
                "\"PRODUCTS_COUNT\" INTEGER NOT NULL ," + // 11: productsCount
                "\"INVENTORY_COUNT\" INTEGER NOT NULL ," + // 12: inventoryCount
                "\"INVENTORY_PRICE_IN_CENTS\" INTEGER NOT NULL ," + // 13: inventoryPriceInCents
                "\"INVENTORY_VOLUME_IN_MILLILITERS\" INTEGER NOT NULL ," + // 14: inventoryVolumeInMilliliters
                "\"HAS_WHEELCHAIR_ACCESSABILITY\" INTEGER NOT NULL ," + // 15: hasWheelchairAccessability
                "\"HAS_BILINGUAL_SERVICES\" INTEGER NOT NULL ," + // 16: hasBilingualServices
                "\"HAS_PRODUCT_CONSULTANT\" INTEGER NOT NULL ," + // 17: hasProductConsultant
                "\"HAS_TASTING_BAR\" INTEGER NOT NULL ," + // 18: hasTastingBar
                "\"HAS_BEER_COLD_ROOM\" INTEGER NOT NULL ," + // 19: hasBeerColdRoom
                "\"HAS_SPECIAL_OCCASION_PERMITS\" INTEGER NOT NULL ," + // 20: hasSpecialOccasionPermits
                "\"HAS_VINTAGES_CORNER\" INTEGER NOT NULL ," + // 21: hasVintagesCorner
                "\"HAS_PARKING\" INTEGER NOT NULL ," + // 22: hasParking
                "\"HAS_TRANSIT_ACCESS\" INTEGER NOT NULL ," + // 23: hasTransitAccess
                "\"UPDATED_AT\" TEXT);"); // 24: updatedAt
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"STORE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Store entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getIsDead() ? 1L: 0L);
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String tags = entity.getTags();
        if (tags != null) {
            stmt.bindString(4, tags);
        }
 
        String addressLine1 = entity.getAddressLine1();
        if (addressLine1 != null) {
            stmt.bindString(5, addressLine1);
        }
 
        String addressLine2 = entity.getAddressLine2();
        if (addressLine2 != null) {
            stmt.bindString(6, addressLine2);
        }
 
        String city = entity.getCity();
        if (city != null) {
            stmt.bindString(7, city);
        }
 
        String postalCode = entity.getPostalCode();
        if (postalCode != null) {
            stmt.bindString(8, postalCode);
        }
 
        String telephone = entity.getTelephone();
        if (telephone != null) {
            stmt.bindString(9, telephone);
        }
        stmt.bindDouble(10, entity.getLatitude());
        stmt.bindDouble(11, entity.getLongitude());
        stmt.bindLong(12, entity.getProductsCount());
        stmt.bindLong(13, entity.getInventoryCount());
        stmt.bindLong(14, entity.getInventoryPriceInCents());
        stmt.bindLong(15, entity.getInventoryVolumeInMilliliters());
        stmt.bindLong(16, entity.getHasWheelchairAccessability() ? 1L: 0L);
        stmt.bindLong(17, entity.getHasBilingualServices() ? 1L: 0L);
        stmt.bindLong(18, entity.getHasProductConsultant() ? 1L: 0L);
        stmt.bindLong(19, entity.getHasTastingBar() ? 1L: 0L);
        stmt.bindLong(20, entity.getHasBeerColdRoom() ? 1L: 0L);
        stmt.bindLong(21, entity.getHasSpecialOccasionPermits() ? 1L: 0L);
        stmt.bindLong(22, entity.getHasVintagesCorner() ? 1L: 0L);
        stmt.bindLong(23, entity.getHasParking() ? 1L: 0L);
        stmt.bindLong(24, entity.getHasTransitAccess() ? 1L: 0L);
 
        String updatedAt = entity.getUpdatedAt();
        if (updatedAt != null) {
            stmt.bindString(25, updatedAt);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Store entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getIsDead() ? 1L: 0L);
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String tags = entity.getTags();
        if (tags != null) {
            stmt.bindString(4, tags);
        }
 
        String addressLine1 = entity.getAddressLine1();
        if (addressLine1 != null) {
            stmt.bindString(5, addressLine1);
        }
 
        String addressLine2 = entity.getAddressLine2();
        if (addressLine2 != null) {
            stmt.bindString(6, addressLine2);
        }
 
        String city = entity.getCity();
        if (city != null) {
            stmt.bindString(7, city);
        }
 
        String postalCode = entity.getPostalCode();
        if (postalCode != null) {
            stmt.bindString(8, postalCode);
        }
 
        String telephone = entity.getTelephone();
        if (telephone != null) {
            stmt.bindString(9, telephone);
        }
        stmt.bindDouble(10, entity.getLatitude());
        stmt.bindDouble(11, entity.getLongitude());
        stmt.bindLong(12, entity.getProductsCount());
        stmt.bindLong(13, entity.getInventoryCount());
        stmt.bindLong(14, entity.getInventoryPriceInCents());
        stmt.bindLong(15, entity.getInventoryVolumeInMilliliters());
        stmt.bindLong(16, entity.getHasWheelchairAccessability() ? 1L: 0L);
        stmt.bindLong(17, entity.getHasBilingualServices() ? 1L: 0L);
        stmt.bindLong(18, entity.getHasProductConsultant() ? 1L: 0L);
        stmt.bindLong(19, entity.getHasTastingBar() ? 1L: 0L);
        stmt.bindLong(20, entity.getHasBeerColdRoom() ? 1L: 0L);
        stmt.bindLong(21, entity.getHasSpecialOccasionPermits() ? 1L: 0L);
        stmt.bindLong(22, entity.getHasVintagesCorner() ? 1L: 0L);
        stmt.bindLong(23, entity.getHasParking() ? 1L: 0L);
        stmt.bindLong(24, entity.getHasTransitAccess() ? 1L: 0L);
 
        String updatedAt = entity.getUpdatedAt();
        if (updatedAt != null) {
            stmt.bindString(25, updatedAt);
        }
    }

    @Override
    protected final void attachEntity(Store entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Store readEntity(Cursor cursor, int offset) {
        Store entity = new Store( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getShort(offset + 1) != 0, // isDead
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // tags
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // addressLine1
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // addressLine2
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // city
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // postalCode
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // telephone
            cursor.getDouble(offset + 9), // latitude
            cursor.getDouble(offset + 10), // longitude
            cursor.getInt(offset + 11), // productsCount
            cursor.getInt(offset + 12), // inventoryCount
            cursor.getInt(offset + 13), // inventoryPriceInCents
            cursor.getInt(offset + 14), // inventoryVolumeInMilliliters
            cursor.getShort(offset + 15) != 0, // hasWheelchairAccessability
            cursor.getShort(offset + 16) != 0, // hasBilingualServices
            cursor.getShort(offset + 17) != 0, // hasProductConsultant
            cursor.getShort(offset + 18) != 0, // hasTastingBar
            cursor.getShort(offset + 19) != 0, // hasBeerColdRoom
            cursor.getShort(offset + 20) != 0, // hasSpecialOccasionPermits
            cursor.getShort(offset + 21) != 0, // hasVintagesCorner
            cursor.getShort(offset + 22) != 0, // hasParking
            cursor.getShort(offset + 23) != 0, // hasTransitAccess
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24) // updatedAt
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Store entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setIsDead(cursor.getShort(offset + 1) != 0);
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTags(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAddressLine1(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setAddressLine2(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCity(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setPostalCode(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setTelephone(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setLatitude(cursor.getDouble(offset + 9));
        entity.setLongitude(cursor.getDouble(offset + 10));
        entity.setProductsCount(cursor.getInt(offset + 11));
        entity.setInventoryCount(cursor.getInt(offset + 12));
        entity.setInventoryPriceInCents(cursor.getInt(offset + 13));
        entity.setInventoryVolumeInMilliliters(cursor.getInt(offset + 14));
        entity.setHasWheelchairAccessability(cursor.getShort(offset + 15) != 0);
        entity.setHasBilingualServices(cursor.getShort(offset + 16) != 0);
        entity.setHasProductConsultant(cursor.getShort(offset + 17) != 0);
        entity.setHasTastingBar(cursor.getShort(offset + 18) != 0);
        entity.setHasBeerColdRoom(cursor.getShort(offset + 19) != 0);
        entity.setHasSpecialOccasionPermits(cursor.getShort(offset + 20) != 0);
        entity.setHasVintagesCorner(cursor.getShort(offset + 21) != 0);
        entity.setHasParking(cursor.getShort(offset + 22) != 0);
        entity.setHasTransitAccess(cursor.getShort(offset + 23) != 0);
        entity.setUpdatedAt(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Store entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Store entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Store entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
