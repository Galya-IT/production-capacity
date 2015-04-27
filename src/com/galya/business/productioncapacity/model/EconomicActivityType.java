package com.galya.business.productioncapacity.model;

import static com.galya.business.productioncapacity.utils.CommonUtils.*;

public abstract class EconomicActivityType {

    private int databaseId;
    private String id;
    private String name;
    private String parentId;

    public EconomicActivityType(int databaseId, String id, String name) {
        this.databaseId = databaseId;
        this.id = id;
        this.name = name;
    }

    public EconomicActivityType(int databaseId, String id, String name, String parentId) {
        this(databaseId, id, name);
        this.parentId = parentId;
    }

    public int getDatabaseId() {
        return databaseId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setDatabaseId(int databaseId) {
        this.databaseId = databaseId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (obj instanceof EconomicActivityType) {
            EconomicActivityType economicActivityType = (EconomicActivityType) obj;

            String id = economicActivityType.getId();
            String parentId = economicActivityType.getParentId();
            String name = economicActivityType.getName();

            if (economicActivityType.getDatabaseId() == this.databaseId
                    && ((isEmpty(id) && isEmpty(this.id)) || (!isEmpty(id) && id.equals(this.id)))
                    && ((isEmpty(name) && isEmpty(this.name)) || (!isEmpty(name) && name.equals(this.name)))
                    && ((isEmpty(parentId) && isEmpty(this.parentId)) || (!isEmpty(parentId) && parentId
                            .equals(this.parentId)))) {

                isEqual = true;
            }
        }
        return isEqual;
    }

    @Override
    public String toString() {
        String objectToString = "";
        if (!isEmpty(this.id)) {
            objectToString += this.id + " - ";
        }
        objectToString += this.name;
        return objectToString;
    }
}
