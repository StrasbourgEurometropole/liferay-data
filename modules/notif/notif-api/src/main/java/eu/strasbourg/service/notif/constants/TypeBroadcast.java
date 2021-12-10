package eu.strasbourg.service.notif.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TypeBroadcast {
    ALL(1, "Tout le monde"),
    DEFAULT(2, "Abonn\u00E9s"),
    DISTRICT(3, "Quartier");

    private long id;
    private String label;

    TypeBroadcast(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public static TypeBroadcast get(long id) {
        for (TypeBroadcast broadcastType : values()) {
            if (broadcastType.getId() == id) {
                return broadcastType;
            }
        }
        return null;
    }

    public static List<TypeBroadcast> getAll() {
        List<TypeBroadcast> broadcastTypes = new ArrayList<TypeBroadcast>();
        broadcastTypes.addAll(Arrays.asList(values()));
        return broadcastTypes;
    }

}
