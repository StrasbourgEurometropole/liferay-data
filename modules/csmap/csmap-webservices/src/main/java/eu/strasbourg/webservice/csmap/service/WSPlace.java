package eu.strasbourg.webservice.csmap.service;

import org.osgi.service.component.annotations.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service s'occuppant de verifier si les places de l'utilisateur sont à jour.
 */
@Component(
        immediate = true,
        service = WSPlace.class
)
public class WSPlace {

    // Function to validate hexadecimal color code .
    public static boolean isValidHexaCode(String str)
    {
        // Regex to check valid hexadecimal color code.
        String regex = "([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the string is empty
        // return false
        if (str == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given string
        // and regular expression.
        Matcher m = p.matcher(str);

        // Return if the string
        // matched the ReGex
        return m.matches();
    }
}
