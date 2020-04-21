package eu.strasbourg.service.council.constants;

import java.util.ArrayList;
import java.util.List;

public enum StageDeliberation {
    CREE(1, "Cr\u00E9\u00E9"),
    A_VENIR(2, "A venir"),
    AFFICHAGE_EN_COURS(3, "Affichage en cours"),
    VOTE_OUVERT(4, "Vote ouvert"),
    ADOPTE(5, "Adopt\u00E9"),
    REJETE(6, "Rejet\u00E9"),
    COMMUNIQUE(7, "Communiqu\u00E9"),
    RETIRE(8, "Retir\u00E9");

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
