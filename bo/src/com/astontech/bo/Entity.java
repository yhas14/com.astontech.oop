package com.astontech.bo;

public class Entity extends BaseBO{
    private int EntityId;
    private String EntityName;

    //region CONSTRUCTOR
        public Entity(){}
        public Entity(int entityId, String entityName){
            this.setEntityId(entityId);
            this.setEntityName(entityName);
        }
    //endregion

    //region GETTERS/SETTERS
    public int getEntityId() {
        return EntityId;
    }

    public void setEntityId(int entityId) {
        EntityId = entityId;
    }

    public String getEntityName() {
        return EntityName;
    }

    public void setEntityName(String entityName) {
        EntityName = entityName;
    }
    //endregion

    //region CUSTOM METHOD
        public int entityId(){
            return this.EntityId;
        }
    //endregion
}
