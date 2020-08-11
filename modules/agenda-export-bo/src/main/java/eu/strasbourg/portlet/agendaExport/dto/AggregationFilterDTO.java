package eu.strasbourg.portlet.agendaExport.dto;

public class AggregationFilterDTO {

    private String type;

    private String value = "";

    private AggregationFilterDTO aggregationFilterDTO;

    public AggregationFilterDTO() {
    }

    public AggregationFilterDTO(String type, String value) {
        this.type = type;
        this.value = value != null ? value : "";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AggregationFilterDTO getAggregationFilterDTO() {
        return aggregationFilterDTO;
    }

    public void setAggregationFilterDTO(AggregationFilterDTO aggregationFilterDTO) {
        this.aggregationFilterDTO = aggregationFilterDTO;
    }
}
