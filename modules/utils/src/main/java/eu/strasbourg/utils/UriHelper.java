package eu.strasbourg.utils;

import java.net.URI;
import java.net.URISyntaxException;

public class UriHelper {

    public static URI appendUri(String uri, String appendQuery) throws URISyntaxException {
        URI oldUri = new URI(uri);

        String newQuery = oldUri.getQuery();
        if (newQuery == null) {
            newQuery = appendQuery;
        } else if (!uri.contains(appendQuery)){
            newQuery += "&" + appendQuery;
        }

        return new URI(oldUri.getScheme(), oldUri.getAuthority(),
                oldUri.getPath(), newQuery, oldUri.getFragment());
    }

    public static URI appendUriImagePreview(String uri) throws URISyntaxException {
        return appendUri(uri, "imagePreview=1");
    }
}
