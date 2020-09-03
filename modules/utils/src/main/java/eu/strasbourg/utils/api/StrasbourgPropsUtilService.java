package eu.strasbourg.utils.api;

import aQute.bnd.annotation.ProviderType;

/**
 * Classe Helper pour tout ce qui concerne les vairable portal-ext.propertie
 */
@ProviderType
public interface StrasbourgPropsUtilService {
    public String getPublikApiBase() ;

    public String getEJobURLOfferApply() ;

    public String getEJobURLApply() ;
}
