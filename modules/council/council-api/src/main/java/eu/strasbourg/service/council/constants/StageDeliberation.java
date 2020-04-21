package eu.strasbourg.service.council.constants;

import java.util.ArrayList;
import java.util.List;

public enum StageDeliberation {
    CREE(1, "Crée"),
    A_VENIR(2, "A venir"),
    AFFICHAGE_EN_COURS(3, "Affichage en cours"),
    VOTE_OUVERT(4, "Vote ouvert"),
    ADOPTE(5, "Adopté"),
    REJETE(6, "Rejeté"),
    COMMUNIQUE(7, "Communiqué"),
    RETIRE(8, "Retiré");

    private long id;
    private String name;

    StageDeliberation(int id, String name) {
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


    public static StageDeliberation get(long id) {
        for (StageDeliberation stageDelib : values()) {
            if (stageDelib.getId() == id) {
                return stageDelib;
            }
        }
        return null;
    }

    public static List<StageDeliberation> getAll() {
        List<StageDeliberation> stageDelibs = new ArrayList<StageDeliberation>();
        for (StageDeliberation stageDelib : values()) {
            stageDelibs.add(stageDelib);
        }
        return stageDelibs;
    }

}
