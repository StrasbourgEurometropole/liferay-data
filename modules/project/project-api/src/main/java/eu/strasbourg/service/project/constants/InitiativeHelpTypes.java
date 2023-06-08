package eu.strasbourg.service.project.constants;

public enum InitiativeHelpTypes {
	
	TIME(1, "du temps"),
    MONEY(2, "de l'argent"),
    PLACE(3, "un lieu"),
    EXPERTISE(4, "une expetise");
	
	private int helpTypeId;
	private String helpTypeLabel;
	
	InitiativeHelpTypes(int helpTypeId, String helpTypeLabel) {
        this.helpTypeId = helpTypeId;
        this.helpTypeLabel = helpTypeLabel;
    }
	
    public int getId() {
        return helpTypeId;
    }
    
    public String getLabel() {
        return helpTypeLabel;
    }
    
    public String toString(){
    	return Integer.toString(this.helpTypeId);
	}
    
}
