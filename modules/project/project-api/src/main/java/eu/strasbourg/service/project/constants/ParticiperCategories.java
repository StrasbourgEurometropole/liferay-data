package eu.strasbourg.service.project.constants;

/**
 * @author alexandre.quere
 */
public enum ParticiperCategories {
	
    COMPLETED("completed"),
    FAILED("failed"),
    SOON_ARRIVED("soon_arrived"),
    NEW("new"),
    IN_PROGRESS("in_progress"),
    SOON_FINISHED("soon_finished"),
    FINISHED("finished"),
    BP_SUBMITTED("Depose"),
    BP_ACCEPTABLE("Recevable"),
    BP_NON_ACCEPTABLE("Non recevable"),
    BP_IN_FEASIBILITY_STUDIES("En cours d'étude de faisabilité"),
    BP_FEASIBLE("Faisable"),
    BP_NON_FEASIBLE("Non faisable"),
    BP_LAUREAT("Laureat"),
    BP_NON_SELECTED("Non retenu"),
    BP_IN_PROGRESS("En cours de realisation"),
    BP_REALIZED("Realise"),
    BP_SUSPENDED("Suspendu"),
    BP_CANCELLED("Annule");

    private String name;

    ParticiperCategories(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
