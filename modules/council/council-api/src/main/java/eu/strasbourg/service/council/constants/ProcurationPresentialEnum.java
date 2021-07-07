package eu.strasbourg.service.council.constants;

import java.util.ArrayList;
import java.util.List;

public enum ProcurationPresentialEnum {
    OUI(0, "Oui"),
    NON(1, "Non"),
    VIDE(2, null);

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
        for (ProcurationPresentialEnum procurationPresential : values()) {
            procurationPresentials.add(procurationPresential);
        }
        return procurationPresentials;
    }

}
