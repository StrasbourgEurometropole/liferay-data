package eu.strasbourg.service.notif.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum StatusCsmapEnum {
    TO_SEND(1, "A envoyer"),
    SENT(2, "envoyé"),
    KO(3, "echec");

    private long id;
    private String name;

    StatusCsmapEnum(int id, String name) {
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


    public static StatusCsmapEnum get(long id) {
        for (StatusCsmapEnum statusCsmap : values()) {
            if (statusCsmap.getId() == id) {
                return statusCsmap;
            }
        }
        return null;
    }

    public static List<StatusCsmapEnum> getAll() {
        List<StatusCsmapEnum> statutesCsmap = new ArrayList<StatusCsmapEnum>();
        statutesCsmap.addAll(Arrays.asList(values()));
        return statutesCsmap;
    }

}
