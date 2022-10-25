package com.astontech.bo;

import java.util.Date;

public class ReviewData extends BaseBO{
    private int ReviewDataId;
    private int ReviewId;
    private int EntityTypeId;
    private String ReviewDataValue;
    private Date   CreateDate;

    //region CONSTRUCTOR
        public ReviewData(){}

        public ReviewData(int reviewDataId, String reviewDataValue){
            this.setReviewDataId(reviewDataId);
            this.setReviewDataValue(reviewDataValue);
        }
    //endregion

    //region GETTERS/SETTERS
    public int getReviewDataId() {
        return ReviewDataId;
    }

    public void setReviewDataId(int reviewDataId) {
        ReviewDataId = reviewDataId;
    }

    public int getReviewId() {
        return ReviewId;
    }

    public void setReviewId(int reviewId) {
        ReviewId = reviewId;
    }

    public int getEntityTypeId() {
        return EntityTypeId;
    }

    public void setEntityTypeId(int entityTypeId) {
        EntityTypeId = entityTypeId;
    }

    public String getReviewDataValue() {
        return ReviewDataValue;
    }

    public void setReviewDataValue(String reviewDataValue) {
        ReviewDataValue = reviewDataValue;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }
    //endregion
}
