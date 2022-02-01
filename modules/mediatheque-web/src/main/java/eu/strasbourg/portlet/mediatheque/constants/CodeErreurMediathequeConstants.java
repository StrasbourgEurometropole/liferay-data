package eu.strasbourg.portlet.mediatheque.constants;

import java.util.Arrays;
import java.util.List;

public class CodeErreurMediathequeConstants {

   public static final String AUCUNE_ASSOCIATION = "AUCUNE_ASSOCIATION";
   public static final String DELAI_DEPASSE = "DELAI_DEPASSE";
   public static final String ASSOCIATION_A_VALIDER = "ASSOCIATION_A_VALIDER";
   public static final String AUCUN_EMAIL = "AUCUN_EMAIL";
   public static final String AUCUNE_CARTE = "AUCUNE_CARTE";
   public static final String CARTE_DEJA_ASSOCIEE = "CARTE_DEJA_ASSOCIEE";
   public static final String ASSOCIATION_SUPPRIMEE = "ASSOCIATION_SUPPRIMEE";

   public static List<String> allCodesErreurList = Arrays.asList(AUCUNE_ASSOCIATION, DELAI_DEPASSE, ASSOCIATION_A_VALIDER, AUCUN_EMAIL, AUCUNE_CARTE, ASSOCIATION_SUPPRIMEE);
}
