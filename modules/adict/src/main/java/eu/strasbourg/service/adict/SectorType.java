package eu.strasbourg.service.adict;

/**
 * Type de secteur Adict
 *
 */
public class SectorType {
	private String id;
	private String name;

	public SectorType(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
