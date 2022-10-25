package com.astontech.bo;

public class EntityType extends BaseBO{

        //region PROPERTIES
            private int EntityTypeId;
            private String EntityTypeName;

    //endregion

        //region CONSTRUCTOR
        public EntityType(){}

        public EntityType(String entityTypeName){
            this.EntityTypeName = entityTypeName;
        }

        //endregion


       //region GETTERS/SETTERS
            public int getEntityTypeId() {
                   return EntityTypeId;
               }

            public void setEntityTypeId(int entityTypeId) {
                EntityTypeId = entityTypeId;
            }

            public String getEntityTypeName() {
                return EntityTypeName;
            }

            public void setEntityTypeName(String entityTypeName) {
                EntityTypeName = entityTypeName;
            }
      //endregion

        public String test_method(){
        return "sub Method";
    }
}
