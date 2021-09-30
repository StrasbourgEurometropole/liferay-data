package eu.strasbourg.service.notif.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum BroadcastTypeEnum {
    VIDE(0, null),
    DEFAULT(1, "Default"),
    DISTRICT(2, "District"),
    ALL(3, "All");

    private long id;
    private String name;

    BroadcastTypeEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static BroadcastTypeEnum get(long id) {
        for (BroadcastTypeEnum broadcastType : values()) {
            if (broadcastType.getId() == id) {
                return broadcastType;
            }
        }
        return null;
    }

    public static List<BroadcastTypeEnum> getAll() {
        List<BroadcastTypeEnum> broadcastTypes = new ArrayList<BroadcastTypeEnum>();
        broadcastTypes.addAll(Arrays.asList(values()));
        return broadcastTypes;
    }

}
