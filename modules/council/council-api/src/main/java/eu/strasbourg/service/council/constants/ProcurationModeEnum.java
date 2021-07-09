package eu.strasbourg.service.council.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ProcurationModeEnum {
    MAIL(0, "Mail"),
    PAPIER(1, "Papier"),
    TCHAT(2, "Tchat"),
    AUTRE(3, "Autre");

    private long id;
    private String name;

    ProcurationModeEnum(int id, String name) {
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


    public static ProcurationModeEnum get(long id) {
        for (ProcurationModeEnum procurationPresential : values()) {
            if (procurationPresential.getId() == id) {
                return procurationPresential;
            }
        }
        return null;
    }

    public static List<ProcurationModeEnum> getAll() {
        List<ProcurationModeEnum> procurationPresentials = new ArrayList<ProcurationModeEnum>();
        procurationPresentials.addAll(Arrays.asList(values()));
        return procurationPresentials;
    }

}
