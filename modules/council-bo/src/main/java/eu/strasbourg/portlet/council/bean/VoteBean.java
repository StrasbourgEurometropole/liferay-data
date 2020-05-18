package eu.strasbourg.portlet.council.bean;

public class VoteBean {

    private String voter;
    private String result;
    private String procurationVoter;
    private String cssClass;

    public VoteBean() {
    }

    public String getVoter() {
        return voter;
    }

    public void setVoter(String voter) {
        this.voter = voter;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getProcurationVoter() {
        return procurationVoter;
    }

    public void setProcurationVoter(String procurationVoter) {
        this.procurationVoter = procurationVoter;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }
}
