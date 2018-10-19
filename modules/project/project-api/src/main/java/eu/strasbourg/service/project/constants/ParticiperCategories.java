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
    BP_SUBMITTED("submitted"),
    BP_ACCEPTABLE("acceptable"),
    BP_NON_ACCEPTABLE("non_acceptabled"),
    BP_FEASIBLE("feasible"),
    BP_NON_FEASIBLE("non_feasible"),
    BP_LAUREAT("laureat"),
    BP_NON_SELECTED("selected"),
    BP_IN_PROGRESS("bp_in_progress"),
    BP_REALIZED("realized"),
    BP_SUSPENDED("suspended"),
    BP_CANCELLED("cancelled");

    private String name;

    ParticiperCategories(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
