
package com.lcbo.model.pojo.Products;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("is_dead")
    @Expose
    private boolean isDead;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("is_discontinued")
    @Expose
    private boolean isDiscontinued;
    @SerializedName("price_in_cents")
    @Expose
    private int priceInCents;
    @SerializedName("regular_price_in_cents")
    @Expose
    private int regularPriceInCents;
    @SerializedName("limited_time_offer_savings_in_cents")
    @Expose
    private int limitedTimeOfferSavingsInCents;
    @SerializedName("limited_time_offer_ends_on")
    @Expose
    private Object limitedTimeOfferEndsOn;
    @SerializedName("bonus_reward_miles")
    @Expose
    private int bonusRewardMiles;
    @SerializedName("bonus_reward_miles_ends_on")
    @Expose
    private Object bonusRewardMilesEndsOn;
    @SerializedName("stock_type")
    @Expose
    private String stockType;
    @SerializedName("primary_category")
    @Expose
    private String primaryCategory;
    @SerializedName("secondary_category")
    @Expose
    private String secondaryCategory;
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("package")
    @Expose
    private String _package;
    @SerializedName("package_unit_type")
    @Expose
    private String packageUnitType;
    @SerializedName("package_unit_volume_in_milliliters")
    @Expose
    private int packageUnitVolumeInMilliliters;
    @SerializedName("total_package_units")
    @Expose
    private int totalPackageUnits;
    @SerializedName("volume_in_milliliters")
    @Expose
    private int volumeInMilliliters;
    @SerializedName("alcohol_content")
    @Expose
    private int alcoholContent;
    @SerializedName("price_per_liter_of_alcohol_in_cents")
    @Expose
    private int pricePerLiterOfAlcoholInCents;
    @SerializedName("price_per_liter_in_cents")
    @Expose
    private int pricePerLiterInCents;
    @SerializedName("inventory_count")
    @Expose
    private int inventoryCount;
    @SerializedName("inventory_volume_in_milliliters")
    @Expose
    private int inventoryVolumeInMilliliters;
    @SerializedName("inventory_price_in_cents")
    @Expose
    private int inventoryPriceInCents;
    @SerializedName("sugar_content")
    @Expose
    private Object sugarContent;
    @SerializedName("producer_name")
    @Expose
    private String producerName;
    @SerializedName("released_on")
    @Expose
    private Object releasedOn;
    @SerializedName("has_value_added_promotion")
    @Expose
    private boolean hasValueAddedPromotion;
    @SerializedName("has_limited_time_offer")
    @Expose
    private boolean hasLimitedTimeOffer;
    @SerializedName("has_bonus_reward_miles")
    @Expose
    private boolean hasBonusRewardMiles;
    @SerializedName("is_seasonal")
    @Expose
    private boolean isSeasonal;
    @SerializedName("is_vqa")
    @Expose
    private boolean isVqa;
    @SerializedName("is_ocb")
    @Expose
    private boolean isOcb;
    @SerializedName("is_kosher")
    @Expose
    private boolean isKosher;
    @SerializedName("value_added_promotion_description")
    @Expose
    private Object valueAddedPromotionDescription;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("serving_suggestion")
    @Expose
    private Object servingSuggestion;
    @SerializedName("tasting_note")
    @Expose
    private Object tastingNote;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("image_thumb_url")
    @Expose
    private String imageThumbUrl;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("varietal")
    @Expose
    private String varietal;
    @SerializedName("style")
    @Expose
    private String style;
    @SerializedName("tertiary_category")
    @Expose
    private Object tertiaryCategory;
    @SerializedName("sugar_in_grams_per_liter")
    @Expose
    private Object sugarInGramsPerLiter;
    @SerializedName("clearance_sale_savings_in_cents")
    @Expose
    private int clearanceSaleSavingsInCents;
    @SerializedName("has_clearance_sale")
    @Expose
    private boolean hasClearanceSale;
    @SerializedName("product_no")
    @Expose
    private int productNo;
    public final static Creator<Result> CREATOR = new Creator<Result>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Result createFromParcel(Parcel in) {
            Result instance = new Result();
            instance.id = ((int) in.readValue((int.class.getClassLoader())));
            instance.isDead = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.tags = ((String) in.readValue((String.class.getClassLoader())));
            instance.isDiscontinued = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.priceInCents = ((int) in.readValue((int.class.getClassLoader())));
            instance.regularPriceInCents = ((int) in.readValue((int.class.getClassLoader())));
            instance.limitedTimeOfferSavingsInCents = ((int) in.readValue((int.class.getClassLoader())));
            instance.limitedTimeOfferEndsOn = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.bonusRewardMiles = ((int) in.readValue((int.class.getClassLoader())));
            instance.bonusRewardMilesEndsOn = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.stockType = ((String) in.readValue((String.class.getClassLoader())));
            instance.primaryCategory = ((String) in.readValue((String.class.getClassLoader())));
            instance.secondaryCategory = ((String) in.readValue((String.class.getClassLoader())));
            instance.origin = ((String) in.readValue((String.class.getClassLoader())));
            instance._package = ((String) in.readValue((String.class.getClassLoader())));
            instance.packageUnitType = ((String) in.readValue((String.class.getClassLoader())));
            instance.packageUnitVolumeInMilliliters = ((int) in.readValue((int.class.getClassLoader())));
            instance.totalPackageUnits = ((int) in.readValue((int.class.getClassLoader())));
            instance.volumeInMilliliters = ((int) in.readValue((int.class.getClassLoader())));
            instance.alcoholContent = ((int) in.readValue((int.class.getClassLoader())));
            instance.pricePerLiterOfAlcoholInCents = ((int) in.readValue((int.class.getClassLoader())));
            instance.pricePerLiterInCents = ((int) in.readValue((int.class.getClassLoader())));
            instance.inventoryCount = ((int) in.readValue((int.class.getClassLoader())));
            instance.inventoryVolumeInMilliliters = ((int) in.readValue((int.class.getClassLoader())));
            instance.inventoryPriceInCents = ((int) in.readValue((int.class.getClassLoader())));
            instance.sugarContent = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.producerName = ((String) in.readValue((String.class.getClassLoader())));
            instance.releasedOn = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.hasValueAddedPromotion = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.hasLimitedTimeOffer = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.hasBonusRewardMiles = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.isSeasonal = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.isVqa = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.isOcb = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.isKosher = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.valueAddedPromotionDescription = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.description = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.servingSuggestion = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.tastingNote = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
            instance.imageThumbUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.imageUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.varietal = ((String) in.readValue((String.class.getClassLoader())));
            instance.style = ((String) in.readValue((String.class.getClassLoader())));
            instance.tertiaryCategory = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.sugarInGramsPerLiter = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.clearanceSaleSavingsInCents = ((int) in.readValue((int.class.getClassLoader())));
            instance.hasClearanceSale = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.productNo = ((int) in.readValue((int.class.getClassLoader())));
            return instance;
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    }
    ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsDead() {
        return isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public boolean isIsDiscontinued() {
        return isDiscontinued;
    }

    public void setIsDiscontinued(boolean isDiscontinued) {
        this.isDiscontinued = isDiscontinued;
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(int priceInCents) {
        this.priceInCents = priceInCents;
    }

    public int getRegularPriceInCents() {
        return regularPriceInCents;
    }

    public void setRegularPriceInCents(int regularPriceInCents) {
        this.regularPriceInCents = regularPriceInCents;
    }

    public int getLimitedTimeOfferSavingsInCents() {
        return limitedTimeOfferSavingsInCents;
    }

    public void setLimitedTimeOfferSavingsInCents(int limitedTimeOfferSavingsInCents) {
        this.limitedTimeOfferSavingsInCents = limitedTimeOfferSavingsInCents;
    }

    public Object getLimitedTimeOfferEndsOn() {
        return limitedTimeOfferEndsOn;
    }

    public void setLimitedTimeOfferEndsOn(Object limitedTimeOfferEndsOn) {
        this.limitedTimeOfferEndsOn = limitedTimeOfferEndsOn;
    }

    public int getBonusRewardMiles() {
        return bonusRewardMiles;
    }

    public void setBonusRewardMiles(int bonusRewardMiles) {
        this.bonusRewardMiles = bonusRewardMiles;
    }

    public Object getBonusRewardMilesEndsOn() {
        return bonusRewardMilesEndsOn;
    }

    public void setBonusRewardMilesEndsOn(Object bonusRewardMilesEndsOn) {
        this.bonusRewardMilesEndsOn = bonusRewardMilesEndsOn;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getPrimaryCategory() {
        return primaryCategory;
    }

    public void setPrimaryCategory(String primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    public String getSecondaryCategory() {
        return secondaryCategory;
    }

    public void setSecondaryCategory(String secondaryCategory) {
        this.secondaryCategory = secondaryCategory;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPackage() {
        return _package;
    }

    public void setPackage(String _package) {
        this._package = _package;
    }

    public String getPackageUnitType() {
        return packageUnitType;
    }

    public void setPackageUnitType(String packageUnitType) {
        this.packageUnitType = packageUnitType;
    }

    public int getPackageUnitVolumeInMilliliters() {
        return packageUnitVolumeInMilliliters;
    }

    public void setPackageUnitVolumeInMilliliters(int packageUnitVolumeInMilliliters) {
        this.packageUnitVolumeInMilliliters = packageUnitVolumeInMilliliters;
    }

    public int getTotalPackageUnits() {
        return totalPackageUnits;
    }

    public void setTotalPackageUnits(int totalPackageUnits) {
        this.totalPackageUnits = totalPackageUnits;
    }

    public int getVolumeInMilliliters() {
        return volumeInMilliliters;
    }

    public void setVolumeInMilliliters(int volumeInMilliliters) {
        this.volumeInMilliliters = volumeInMilliliters;
    }

    public int getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(int alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public int getPricePerLiterOfAlcoholInCents() {
        return pricePerLiterOfAlcoholInCents;
    }

    public void setPricePerLiterOfAlcoholInCents(int pricePerLiterOfAlcoholInCents) {
        this.pricePerLiterOfAlcoholInCents = pricePerLiterOfAlcoholInCents;
    }

    public int getPricePerLiterInCents() {
        return pricePerLiterInCents;
    }

    public void setPricePerLiterInCents(int pricePerLiterInCents) {
        this.pricePerLiterInCents = pricePerLiterInCents;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public int getInventoryVolumeInMilliliters() {
        return inventoryVolumeInMilliliters;
    }

    public void setInventoryVolumeInMilliliters(int inventoryVolumeInMilliliters) {
        this.inventoryVolumeInMilliliters = inventoryVolumeInMilliliters;
    }

    public int getInventoryPriceInCents() {
        return inventoryPriceInCents;
    }

    public void setInventoryPriceInCents(int inventoryPriceInCents) {
        this.inventoryPriceInCents = inventoryPriceInCents;
    }

    public Object getSugarContent() {
        return sugarContent;
    }

    public void setSugarContent(Object sugarContent) {
        this.sugarContent = sugarContent;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public Object getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(Object releasedOn) {
        this.releasedOn = releasedOn;
    }

    public boolean isHasValueAddedPromotion() {
        return hasValueAddedPromotion;
    }

    public void setHasValueAddedPromotion(boolean hasValueAddedPromotion) {
        this.hasValueAddedPromotion = hasValueAddedPromotion;
    }

    public boolean isHasLimitedTimeOffer() {
        return hasLimitedTimeOffer;
    }

    public void setHasLimitedTimeOffer(boolean hasLimitedTimeOffer) {
        this.hasLimitedTimeOffer = hasLimitedTimeOffer;
    }

    public boolean isHasBonusRewardMiles() {
        return hasBonusRewardMiles;
    }

    public void setHasBonusRewardMiles(boolean hasBonusRewardMiles) {
        this.hasBonusRewardMiles = hasBonusRewardMiles;
    }

    public boolean isIsSeasonal() {
        return isSeasonal;
    }

    public void setIsSeasonal(boolean isSeasonal) {
        this.isSeasonal = isSeasonal;
    }

    public boolean isIsVqa() {
        return isVqa;
    }

    public void setIsVqa(boolean isVqa) {
        this.isVqa = isVqa;
    }

    public boolean isIsOcb() {
        return isOcb;
    }

    public void setIsOcb(boolean isOcb) {
        this.isOcb = isOcb;
    }

    public boolean isIsKosher() {
        return isKosher;
    }

    public void setIsKosher(boolean isKosher) {
        this.isKosher = isKosher;
    }

    public Object getValueAddedPromotionDescription() {
        return valueAddedPromotionDescription;
    }

    public void setValueAddedPromotionDescription(Object valueAddedPromotionDescription) {
        this.valueAddedPromotionDescription = valueAddedPromotionDescription;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getServingSuggestion() {
        return servingSuggestion;
    }

    public void setServingSuggestion(Object servingSuggestion) {
        this.servingSuggestion = servingSuggestion;
    }

    public Object getTastingNote() {
        return tastingNote;
    }

    public void setTastingNote(Object tastingNote) {
        this.tastingNote = tastingNote;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImageThumbUrl() {
        return imageThumbUrl;
    }

    public void setImageThumbUrl(String imageThumbUrl) {
        this.imageThumbUrl = imageThumbUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVarietal() {
        return varietal;
    }

    public void setVarietal(String varietal) {
        this.varietal = varietal;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Object getTertiaryCategory() {
        return tertiaryCategory;
    }

    public void setTertiaryCategory(Object tertiaryCategory) {
        this.tertiaryCategory = tertiaryCategory;
    }

    public Object getSugarInGramsPerLiter() {
        return sugarInGramsPerLiter;
    }

    public void setSugarInGramsPerLiter(Object sugarInGramsPerLiter) {
        this.sugarInGramsPerLiter = sugarInGramsPerLiter;
    }

    public int getClearanceSaleSavingsInCents() {
        return clearanceSaleSavingsInCents;
    }

    public void setClearanceSaleSavingsInCents(int clearanceSaleSavingsInCents) {
        this.clearanceSaleSavingsInCents = clearanceSaleSavingsInCents;
    }

    public boolean isHasClearanceSale() {
        return hasClearanceSale;
    }

    public void setHasClearanceSale(boolean hasClearanceSale) {
        this.hasClearanceSale = hasClearanceSale;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(isDead);
        dest.writeValue(name);
        dest.writeValue(tags);
        dest.writeValue(isDiscontinued);
        dest.writeValue(priceInCents);
        dest.writeValue(regularPriceInCents);
        dest.writeValue(limitedTimeOfferSavingsInCents);
        dest.writeValue(limitedTimeOfferEndsOn);
        dest.writeValue(bonusRewardMiles);
        dest.writeValue(bonusRewardMilesEndsOn);
        dest.writeValue(stockType);
        dest.writeValue(primaryCategory);
        dest.writeValue(secondaryCategory);
        dest.writeValue(origin);
        dest.writeValue(_package);
        dest.writeValue(packageUnitType);
        dest.writeValue(packageUnitVolumeInMilliliters);
        dest.writeValue(totalPackageUnits);
        dest.writeValue(volumeInMilliliters);
        dest.writeValue(alcoholContent);
        dest.writeValue(pricePerLiterOfAlcoholInCents);
        dest.writeValue(pricePerLiterInCents);
        dest.writeValue(inventoryCount);
        dest.writeValue(inventoryVolumeInMilliliters);
        dest.writeValue(inventoryPriceInCents);
        dest.writeValue(sugarContent);
        dest.writeValue(producerName);
        dest.writeValue(releasedOn);
        dest.writeValue(hasValueAddedPromotion);
        dest.writeValue(hasLimitedTimeOffer);
        dest.writeValue(hasBonusRewardMiles);
        dest.writeValue(isSeasonal);
        dest.writeValue(isVqa);
        dest.writeValue(isOcb);
        dest.writeValue(isKosher);
        dest.writeValue(valueAddedPromotionDescription);
        dest.writeValue(description);
        dest.writeValue(servingSuggestion);
        dest.writeValue(tastingNote);
        dest.writeValue(updatedAt);
        dest.writeValue(imageThumbUrl);
        dest.writeValue(imageUrl);
        dest.writeValue(varietal);
        dest.writeValue(style);
        dest.writeValue(tertiaryCategory);
        dest.writeValue(sugarInGramsPerLiter);
        dest.writeValue(clearanceSaleSavingsInCents);
        dest.writeValue(hasClearanceSale);
        dest.writeValue(productNo);
    }

    public int describeContents() {
        return  0;
    }

}
