package com.lcbo.model.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Product {

    @Id
    @Unique
    Long id;

    private String name;
    private int priceInCents;
    private String primaryCategory;
    private String secondaryCategory;
    private String _package;
    private String producerName;
    private String releasedOn;
    private boolean hasValueAddedPromotion;
    private boolean hasLimitedTimeOffer;
    private boolean hasBonusRewardMiles;
    private boolean isSeasonal;
    private boolean isVqa;
    private boolean isOcb;
    private boolean isKosher;
    private String valueAddedPromotionDescription;
    private String description;
    private String origin;
    private String servingSuggestion;
    private String tastingNote;
    private String updatedAt;
    private String imageThumbUrl;
    private String imageUrl;

    @Generated(hash = 1641748575)
    public Product(Long id, String name, int priceInCents, String primaryCategory,
            String secondaryCategory, String _package, String producerName,
            String releasedOn, boolean hasValueAddedPromotion,
            boolean hasLimitedTimeOffer, boolean hasBonusRewardMiles,
            boolean isSeasonal, boolean isVqa, boolean isOcb, boolean isKosher,
            String valueAddedPromotionDescription, String description,
            String origin, String servingSuggestion, String tastingNote,
            String updatedAt, String imageThumbUrl, String imageUrl) {
        this.id = id;
        this.name = name;
        this.priceInCents = priceInCents;
        this.primaryCategory = primaryCategory;
        this.secondaryCategory = secondaryCategory;
        this._package = _package;
        this.producerName = producerName;
        this.releasedOn = releasedOn;
        this.hasValueAddedPromotion = hasValueAddedPromotion;
        this.hasLimitedTimeOffer = hasLimitedTimeOffer;
        this.hasBonusRewardMiles = hasBonusRewardMiles;
        this.isSeasonal = isSeasonal;
        this.isVqa = isVqa;
        this.isOcb = isOcb;
        this.isKosher = isKosher;
        this.valueAddedPromotionDescription = valueAddedPromotionDescription;
        this.description = description;
        this.origin = origin;
        this.servingSuggestion = servingSuggestion;
        this.tastingNote = tastingNote;
        this.updatedAt = updatedAt;
        this.imageThumbUrl = imageThumbUrl;
        this.imageUrl = imageUrl;
    }
    @Generated(hash = 1890278724)
    public Product() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPriceInCents() {
        return this.priceInCents;
    }
    public void setPriceInCents(int priceInCents) {
        this.priceInCents = priceInCents;
    }
    public String getPrimaryCategory() {
        return this.primaryCategory;
    }
    public void setPrimaryCategory(String primaryCategory) {
        this.primaryCategory = primaryCategory;
    }
    public String getSecondaryCategory() {
        return this.secondaryCategory;
    }
    public void setSecondaryCategory(String secondaryCategory) {
        this.secondaryCategory = secondaryCategory;
    }
    public String get_package() {
        return this._package;
    }
    public void set_package(String _package) {
        this._package = _package;
    }
    public String getProducerName() {
        return this.producerName;
    }
    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }
    public String getReleasedOn() {
        return this.releasedOn;
    }
    public void setReleasedOn(String releasedOn) {
        this.releasedOn = releasedOn;
    }
    public boolean getHasValueAddedPromotion() {
        return this.hasValueAddedPromotion;
    }
    public void setHasValueAddedPromotion(boolean hasValueAddedPromotion) {
        this.hasValueAddedPromotion = hasValueAddedPromotion;
    }
    public boolean getHasLimitedTimeOffer() {
        return this.hasLimitedTimeOffer;
    }
    public void setHasLimitedTimeOffer(boolean hasLimitedTimeOffer) {
        this.hasLimitedTimeOffer = hasLimitedTimeOffer;
    }
    public boolean getHasBonusRewardMiles() {
        return this.hasBonusRewardMiles;
    }
    public void setHasBonusRewardMiles(boolean hasBonusRewardMiles) {
        this.hasBonusRewardMiles = hasBonusRewardMiles;
    }
    public boolean getIsSeasonal() {
        return this.isSeasonal;
    }
    public void setIsSeasonal(boolean isSeasonal) {
        this.isSeasonal = isSeasonal;
    }
    public boolean getIsVqa() {
        return this.isVqa;
    }
    public void setIsVqa(boolean isVqa) {
        this.isVqa = isVqa;
    }
    public boolean getIsOcb() {
        return this.isOcb;
    }
    public void setIsOcb(boolean isOcb) {
        this.isOcb = isOcb;
    }
    public boolean getIsKosher() {
        return this.isKosher;
    }
    public void setIsKosher(boolean isKosher) {
        this.isKosher = isKosher;
    }
    public String getValueAddedPromotionDescription() {
        return this.valueAddedPromotionDescription;
    }
    public void setValueAddedPromotionDescription(
            String valueAddedPromotionDescription) {
        this.valueAddedPromotionDescription = valueAddedPromotionDescription;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getServingSuggestion() {
        return this.servingSuggestion;
    }
    public void setServingSuggestion(String servingSuggestion) {
        this.servingSuggestion = servingSuggestion;
    }
    public String getTastingNote() {
        return this.tastingNote;
    }
    public void setTastingNote(String tastingNote) {
        this.tastingNote = tastingNote;
    }
    public String getUpdatedAt() {
        return this.updatedAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    public String getImageThumbUrl() {
        return this.imageThumbUrl;
    }
    public void setImageThumbUrl(String imageThumbUrl) {
        this.imageThumbUrl = imageThumbUrl;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getOrigin() {
        return this.origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
