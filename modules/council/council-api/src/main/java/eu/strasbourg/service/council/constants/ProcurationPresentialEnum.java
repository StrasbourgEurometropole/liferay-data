package eu.strasbourg.service.council.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ProcurationPresentialEnum {
    VIDE(0, null),
    OUI(1, "Oui"),
    NON(2, "Non");

    private long id;
    private String name;

    ProcurationPresentialEnum(int id, String name) {
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


    public static ProcurationPresentialEnum get(long id) {
        for (ProcurationPresentialEnum procurationPresential : values()) {
            if (procurationPresential.getId() == id) {
                return procurationPresential;
            }
        }
        return null;
    }

    public static List<ProcurationPresentialEnum> getAll() {
        List<ProcurationPresentialEnum> procurationPresentials = new ArrayList<ProcurationPresentialEnum>();
        procurationPresentials.addAll(Arrays.asList(values()));
        return procurationPresentials;
    }

}
