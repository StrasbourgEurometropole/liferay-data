package eu.strasbourg.service.project.constants;

public enum InitiativeHelpTypes {
	
	TIME(1),
    MONEY(2),
    PLACE(3),
    EXPERTISE(4);
	
	private int helpTypeId;
	
	InitiativeHelpTypes(int helpTypeId) {
        this.helpTypeId = helpTypeId;
    }
	
    public int getId() {
        return helpTypeId;
    }
    
}
