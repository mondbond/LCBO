package com.lcbo.model.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class StoreProductRelation {

    @Id(autoincrement = true)
    Long id;

    Long storeId;

    Long productId;

    @Unique
    String relationId;

    @Generated(hash = 1971086518)
    public StoreProductRelation(Long id, Long storeId, Long productId,
            String relationId) {
        this.id = id;
        this.storeId = storeId;
        this.productId = productId;
        this.relationId = relationId;
    }

    @Generated(hash = 385554585)
    public StoreProductRelation() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return this.storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getRelationId() {
        return this.relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }
}
