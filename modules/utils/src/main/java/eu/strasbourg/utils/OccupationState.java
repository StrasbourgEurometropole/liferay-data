package eu.strasbourg.utils;

public enum OccupationState {
	
    DISABLED("real-time-disabled", "white"),
    NOT_AVAILABLE("not-available", "white"), 
    GREEN("green-period", "green"), 
    ORANGE("orange-period", "orange"),
    RED("red-period", "red"),
    BLACK("black-period", "black"),
    FULL("full-period", "black"),
    CLOSED("closed-period", "grey"),
    OPEN("open-period", "green");
    
    String label;
    String cssClass;
    String occupation;
    String available;
    String capacity;
    
    OccupationState(String label, String cssClass) {
	this.label = label;
	this.cssClass = cssClass;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

}
