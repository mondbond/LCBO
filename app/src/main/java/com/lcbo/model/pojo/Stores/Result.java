
package com.lcbo.model.pojo.Stores;

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
    @SerializedName("address_line_1")
    @Expose
    private String addressLine1;
    @SerializedName("address_line_2")
    @Expose
    private Object addressLine2;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("postal_code")
    @Expose
    private String postalCode;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("latitude")
    @Expose
    private double latitude;
    @SerializedName("longitude")
    @Expose
    private double longitude;
    @SerializedName("products_count")
    @Expose
    private int productsCount;
    @SerializedName("inventory_count")
    @Expose
    private int inventoryCount;
    @SerializedName("inventory_price_in_cents")
    @Expose
    private int inventoryPriceInCents;
    @SerializedName("inventory_volume_in_milliliters")
    @Expose
    private int inventoryVolumeInMilliliters;
    @SerializedName("has_wheelchair_accessability")
    @Expose
    private boolean hasWheelchairAccessability;
    @SerializedName("has_bilingual_services")
    @Expose
    private boolean hasBilingualServices;
    @SerializedName("has_product_consultant")
    @Expose
    private boolean hasProductConsultant;
    @SerializedName("has_tasting_bar")
    @Expose
    private boolean hasTastingBar;
    @SerializedName("has_beer_cold_room")
    @Expose
    private boolean hasBeerColdRoom;
    @SerializedName("has_special_occasion_permits")
    @Expose
    private boolean hasSpecialOccasionPermits;
    @SerializedName("has_vintages_corner")
    @Expose
    private boolean hasVintagesCorner;
    @SerializedName("has_parking")
    @Expose
    private boolean hasParking;
    @SerializedName("has_transit_access")
    @Expose
    private boolean hasTransitAccess;
    @SerializedName("sunday_open")
    @Expose
    private int sundayOpen;
    @SerializedName("sunday_close")
    @Expose
    private int sundayClose;
    @SerializedName("monday_open")
    @Expose
    private int mondayOpen;
    @SerializedName("monday_close")
    @Expose
    private int mondayClose;
    @SerializedName("tuesday_open")
    @Expose
    private int tuesdayOpen;
    @SerializedName("tuesday_close")
    @Expose
    private int tuesdayClose;
    @SerializedName("wednesday_open")
    @Expose
    private int wednesdayOpen;
    @SerializedName("wednesday_close")
    @Expose
    private int wednesdayClose;
    @SerializedName("thursday_open")
    @Expose
    private int thursdayOpen;
    @SerializedName("thursday_close")
    @Expose
    private int thursdayClose;
    @SerializedName("friday_open")
    @Expose
    private int fridayOpen;
    @SerializedName("friday_close")
    @Expose
    private int fridayClose;
    @SerializedName("saturday_open")
    @Expose
    private int saturdayOpen;
    @SerializedName("saturday_close")
    @Expose
    private int saturdayClose;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("store_no")
    @Expose
    private int storeNo;
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
            instance.addressLine1 = ((String) in.readValue((String.class.getClassLoader())));
            instance.addressLine2 = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.city = ((String) in.readValue((String.class.getClassLoader())));
            instance.postalCode = ((String) in.readValue((String.class.getClassLoader())));
            instance.telephone = ((String) in.readValue((String.class.getClassLoader())));
            instance.fax = ((String) in.readValue((String.class.getClassLoader())));
            instance.latitude = ((double) in.readValue((double.class.getClassLoader())));
            instance.longitude = ((double) in.readValue((double.class.getClassLoader())));
            instance.productsCount = ((int) in.readValue((int.class.getClassLoader())));
            instance.inventoryCount = ((int) in.readValue((int.class.getClassLoader())));
            instance.inventoryPriceInCents = ((int) in.readValue((int.class.getClassLoader())));
            instance.inventoryVolumeInMilliliters = ((int) in.readValue((int.class.getClassLoader())));
            instance.hasWheelchairAccessability = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.hasBilingualServices = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.hasProductConsultant = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.hasTastingBar = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.hasBeerColdRoom = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.hasSpecialOccasionPermits = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.hasVintagesCorner = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.hasParking = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.hasTransitAccess = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.sundayOpen = ((int) in.readValue((int.class.getClassLoader())));
            instance.sundayClose = ((int) in.readValue((int.class.getClassLoader())));
            instance.mondayOpen = ((int) in.readValue((int.class.getClassLoader())));
            instance.mondayClose = ((int) in.readValue((int.class.getClassLoader())));
            instance.tuesdayOpen = ((int) in.readValue((int.class.getClassLoader())));
            instance.tuesdayClose = ((int) in.readValue((int.class.getClassLoader())));
            instance.wednesdayOpen = ((int) in.readValue((int.class.getClassLoader())));
            instance.wednesdayClose = ((int) in.readValue((int.class.getClassLoader())));
            instance.thursdayOpen = ((int) in.readValue((int.class.getClassLoader())));
            instance.thursdayClose = ((int) in.readValue((int.class.getClassLoader())));
            instance.fridayOpen = ((int) in.readValue((int.class.getClassLoader())));
            instance.fridayClose = ((int) in.readValue((int.class.getClassLoader())));
            instance.saturdayOpen = ((int) in.readValue((int.class.getClassLoader())));
            instance.saturdayClose = ((int) in.readValue((int.class.getClassLoader())));
            instance.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
            instance.storeNo = ((int) in.readValue((int.class.getClassLoader())));
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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public Object getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(Object addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public int getInventoryPriceInCents() {
        return inventoryPriceInCents;
    }

    public void setInventoryPriceInCents(int inventoryPriceInCents) {
        this.inventoryPriceInCents = inventoryPriceInCents;
    }

    public int getInventoryVolumeInMilliliters() {
        return inventoryVolumeInMilliliters;
    }

    public void setInventoryVolumeInMilliliters(int inventoryVolumeInMilliliters) {
        this.inventoryVolumeInMilliliters = inventoryVolumeInMilliliters;
    }

    public boolean isHasWheelchairAccessability() {
        return hasWheelchairAccessability;
    }

    public void setHasWheelchairAccessability(boolean hasWheelchairAccessability) {
        this.hasWheelchairAccessability = hasWheelchairAccessability;
    }

    public boolean isHasBilingualServices() {
        return hasBilingualServices;
    }

    public void setHasBilingualServices(boolean hasBilingualServices) {
        this.hasBilingualServices = hasBilingualServices;
    }

    public boolean isHasProductConsultant() {
        return hasProductConsultant;
    }

    public void setHasProductConsultant(boolean hasProductConsultant) {
        this.hasProductConsultant = hasProductConsultant;
    }

    public boolean isHasTastingBar() {
        return hasTastingBar;
    }

    public void setHasTastingBar(boolean hasTastingBar) {
        this.hasTastingBar = hasTastingBar;
    }

    public boolean isHasBeerColdRoom() {
        return hasBeerColdRoom;
    }

    public void setHasBeerColdRoom(boolean hasBeerColdRoom) {
        this.hasBeerColdRoom = hasBeerColdRoom;
    }

    public boolean isHasSpecialOccasionPermits() {
        return hasSpecialOccasionPermits;
    }

    public void setHasSpecialOccasionPermits(boolean hasSpecialOccasionPermits) {
        this.hasSpecialOccasionPermits = hasSpecialOccasionPermits;
    }

    public boolean isHasVintagesCorner() {
        return hasVintagesCorner;
    }

    public void setHasVintagesCorner(boolean hasVintagesCorner) {
        this.hasVintagesCorner = hasVintagesCorner;
    }

    public boolean isHasParking() {
        return hasParking;
    }

    public void setHasParking(boolean hasParking) {
        this.hasParking = hasParking;
    }

    public boolean isHasTransitAccess() {
        return hasTransitAccess;
    }

    public void setHasTransitAccess(boolean hasTransitAccess) {
        this.hasTransitAccess = hasTransitAccess;
    }

    public int getSundayOpen() {
        return sundayOpen;
    }

    public void setSundayOpen(int sundayOpen) {
        this.sundayOpen = sundayOpen;
    }

    public int getSundayClose() {
        return sundayClose;
    }

    public void setSundayClose(int sundayClose) {
        this.sundayClose = sundayClose;
    }

    public int getMondayOpen() {
        return mondayOpen;
    }

    public void setMondayOpen(int mondayOpen) {
        this.mondayOpen = mondayOpen;
    }

    public int getMondayClose() {
        return mondayClose;
    }

    public void setMondayClose(int mondayClose) {
        this.mondayClose = mondayClose;
    }

    public int getTuesdayOpen() {
        return tuesdayOpen;
    }

    public void setTuesdayOpen(int tuesdayOpen) {
        this.tuesdayOpen = tuesdayOpen;
    }

    public int getTuesdayClose() {
        return tuesdayClose;
    }

    public void setTuesdayClose(int tuesdayClose) {
        this.tuesdayClose = tuesdayClose;
    }

    public int getWednesdayOpen() {
        return wednesdayOpen;
    }

    public void setWednesdayOpen(int wednesdayOpen) {
        this.wednesdayOpen = wednesdayOpen;
    }

    public int getWednesdayClose() {
        return wednesdayClose;
    }

    public void setWednesdayClose(int wednesdayClose) {
        this.wednesdayClose = wednesdayClose;
    }

    public int getThursdayOpen() {
        return thursdayOpen;
    }

    public void setThursdayOpen(int thursdayOpen) {
        this.thursdayOpen = thursdayOpen;
    }

    public int getThursdayClose() {
        return thursdayClose;
    }

    public void setThursdayClose(int thursdayClose) {
        this.thursdayClose = thursdayClose;
    }

    public int getFridayOpen() {
        return fridayOpen;
    }

    public void setFridayOpen(int fridayOpen) {
        this.fridayOpen = fridayOpen;
    }

    public int getFridayClose() {
        return fridayClose;
    }

    public void setFridayClose(int fridayClose) {
        this.fridayClose = fridayClose;
    }

    public int getSaturdayOpen() {
        return saturdayOpen;
    }

    public void setSaturdayOpen(int saturdayOpen) {
        this.saturdayOpen = saturdayOpen;
    }

    public int getSaturdayClose() {
        return saturdayClose;
    }

    public void setSaturdayClose(int saturdayClose) {
        this.saturdayClose = saturdayClose;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(int storeNo) {
        this.storeNo = storeNo;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(isDead);
        dest.writeValue(name);
        dest.writeValue(tags);
        dest.writeValue(addressLine1);
        dest.writeValue(addressLine2);
        dest.writeValue(city);
        dest.writeValue(postalCode);
        dest.writeValue(telephone);
        dest.writeValue(fax);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(productsCount);
        dest.writeValue(inventoryCount);
        dest.writeValue(inventoryPriceInCents);
        dest.writeValue(inventoryVolumeInMilliliters);
        dest.writeValue(hasWheelchairAccessability);
        dest.writeValue(hasBilingualServices);
        dest.writeValue(hasProductConsultant);
        dest.writeValue(hasTastingBar);
        dest.writeValue(hasBeerColdRoom);
        dest.writeValue(hasSpecialOccasionPermits);
        dest.writeValue(hasVintagesCorner);
        dest.writeValue(hasParking);
        dest.writeValue(hasTransitAccess);
        dest.writeValue(sundayOpen);
        dest.writeValue(sundayClose);
        dest.writeValue(mondayOpen);
        dest.writeValue(mondayClose);
        dest.writeValue(tuesdayOpen);
        dest.writeValue(tuesdayClose);
        dest.writeValue(wednesdayOpen);
        dest.writeValue(wednesdayClose);
        dest.writeValue(thursdayOpen);
        dest.writeValue(thursdayClose);
        dest.writeValue(fridayOpen);
        dest.writeValue(fridayClose);
        dest.writeValue(saturdayOpen);
        dest.writeValue(saturdayClose);
        dest.writeValue(updatedAt);
        dest.writeValue(storeNo);
    }

    public int describeContents() {
        return  0;
    }

}
