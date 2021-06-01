package eu.strasbourg.utils;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import eu.strasbourg.utils.exception.NoUserFormException;
import eu.strasbourg.utils.models.Procedure;

import java.util.ArrayList;
import java.util.List;

public class ProcedureHelper {

    public static List<Procedure> getProcedures(String idUser) throws NoUserFormException {

        // récupération des démarches
        List<Procedure> procedures = new ArrayList<Procedure>();
        JSONObject userForms = PublikApiClient.getUserForms(idUser, true);
        if (userForms.toString().equals("{}")) {
            throw new NoUserFormException( "No user form found" );
        } else {
            JSONArray forms = userForms.getJSONArray("data");
            if(forms != null){
                for (int  i=0; i<forms.length(); i++) {
                    JSONObject form = forms.getJSONObject(i);
                    if(form.getString("form_status_is_endpoint").equals("false")){
                        Procedure procedure = Procedure.fromValues(form.getString("form_name"), form.getString("form_status"), form.getString("url"));
                        procedures.add(procedure);
                    }
                }
            }
        }
        return procedures;
    }
}
