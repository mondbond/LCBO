
package com.lcbo.model.pojo.Products;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreProducts implements Parcelable
{

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("pager")
    @Expose
    private Pager pager;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;
    @SerializedName("suggestion")
    @Expose
    private Object suggestion;
    public final static Creator<StoreProducts> CREATOR = new Creator<StoreProducts>() {


        @SuppressWarnings({
            "unchecked"
        })
        public StoreProducts createFromParcel(Parcel in) {
            StoreProducts instance = new StoreProducts();
            instance.status = ((int) in.readValue((int.class.getClassLoader())));
            instance.message = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.pager = ((Pager) in.readValue((Pager.class.getClassLoader())));
            in.readList(instance.result, (Result.class.getClassLoader()));
            instance.suggestion = ((Object) in.readValue((Object.class.getClassLoader())));
            return instance;
        }

        public StoreProducts[] newArray(int size) {
            return (new StoreProducts[size]);
        }

    }
    ;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public Object getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(Object suggestion) {
        this.suggestion = suggestion;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeValue(pager);
        dest.writeList(result);
        dest.writeValue(suggestion);
    }

    public int describeContents() {
        return  0;
    }

}
