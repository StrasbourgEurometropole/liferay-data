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
    FINISHED("finished");

    private String name;

    ParticiperCategories(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
