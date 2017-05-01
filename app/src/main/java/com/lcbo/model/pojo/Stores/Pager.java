
package com.lcbo.model.pojo.Stores;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pager implements Parcelable
{

    @SerializedName("records_per_page")
    @Expose
    private int recordsPerPage;
    @SerializedName("total_record_count")
    @Expose
    private int totalRecordCount;
    @SerializedName("current_page_record_count")
    @Expose
    private int currentPageRecordCount;
    @SerializedName("is_first_page")
    @Expose
    private boolean isFirstPage;
    @SerializedName("is_final_page")
    @Expose
    private boolean isFinalPage;
    @SerializedName("current_page")
    @Expose
    private int currentPage;
    @SerializedName("current_page_path")
    @Expose
    private String currentPagePath;
    @SerializedName("next_page")
    @Expose
    private int nextPage;
    @SerializedName("next_page_path")
    @Expose
    private String nextPagePath;
    @SerializedName("previous_page")
    @Expose
    private Object previousPage;
    @SerializedName("previous_page_path")
    @Expose
    private Object previousPagePath;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("total_pages_path")
    @Expose
    private String totalPagesPath;
    public final static Creator<Pager> CREATOR = new Creator<Pager>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Pager createFromParcel(Parcel in) {
            Pager instance = new Pager();
            instance.recordsPerPage = ((int) in.readValue((int.class.getClassLoader())));
            instance.totalRecordCount = ((int) in.readValue((int.class.getClassLoader())));
            instance.currentPageRecordCount = ((int) in.readValue((int.class.getClassLoader())));
            instance.isFirstPage = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.isFinalPage = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.currentPage = ((int) in.readValue((int.class.getClassLoader())));
            instance.currentPagePath = ((String) in.readValue((String.class.getClassLoader())));
            instance.nextPage = ((int) in.readValue((int.class.getClassLoader())));
            instance.nextPagePath = ((String) in.readValue((String.class.getClassLoader())));
            instance.previousPage = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.previousPagePath = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.totalPages = ((int) in.readValue((int.class.getClassLoader())));
            instance.totalPagesPath = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Pager[] newArray(int size) {
            return (new Pager[size]);
        }

    }
    ;

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public int getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public int getCurrentPageRecordCount() {
        return currentPageRecordCount;
    }

    public void setCurrentPageRecordCount(int currentPageRecordCount) {
        this.currentPageRecordCount = currentPageRecordCount;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsFinalPage() {
        return isFinalPage;
    }

    public void setIsFinalPage(boolean isFinalPage) {
        this.isFinalPage = isFinalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getCurrentPagePath() {
        return currentPagePath;
    }

    public void setCurrentPagePath(String currentPagePath) {
        this.currentPagePath = currentPagePath;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public String getNextPagePath() {
        return nextPagePath;
    }

    public void setNextPagePath(String nextPagePath) {
        this.nextPagePath = nextPagePath;
    }

    public Object getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(Object previousPage) {
        this.previousPage = previousPage;
    }

    public Object getPreviousPagePath() {
        return previousPagePath;
    }

    public void setPreviousPagePath(Object previousPagePath) {
        this.previousPagePath = previousPagePath;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getTotalPagesPath() {
        return totalPagesPath;
    }

    public void setTotalPagesPath(String totalPagesPath) {
        this.totalPagesPath = totalPagesPath;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(recordsPerPage);
        dest.writeValue(totalRecordCount);
        dest.writeValue(currentPageRecordCount);
        dest.writeValue(isFirstPage);
        dest.writeValue(isFinalPage);
        dest.writeValue(currentPage);
        dest.writeValue(currentPagePath);
        dest.writeValue(nextPage);
        dest.writeValue(nextPagePath);
        dest.writeValue(previousPage);
        dest.writeValue(previousPagePath);
        dest.writeValue(totalPages);
        dest.writeValue(totalPagesPath);
    }

    public int describeContents() {
        return  0;
    }

}
