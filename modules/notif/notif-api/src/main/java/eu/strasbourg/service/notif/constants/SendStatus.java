package eu.strasbourg.service.notif.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum SendStatus {
    NOT_CONCERNED(1, "Non concern\u00E9"),
    COMING(2, "A venir"),
    SENDING(3, "En cours d’envoi"),
    SEND(4, "Envoy\u00E9"),
    ERROR(5, "Erreur");

    private long id;
    private String label;

    SendStatus(int id, String label) {
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


    public static SendStatus get(long id) {
        for (SendStatus statusCsmap : values()) {
            if (statusCsmap.getId() == id) {
                return statusCsmap;
            }
        }
        return null;
    }

    public static List<SendStatus> getAll() {
        List<SendStatus> statutesCsmap = new ArrayList<SendStatus>();
        statutesCsmap.addAll(Arrays.asList(values()));
        return statutesCsmap;
    }

}
