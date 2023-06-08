package eu.strasbourg.utils.api;

import aQute.bnd.annotation.ProviderType;

import java.net.URI;
import java.net.URISyntaxException;

@ProviderType
public interface UriHelperService {
    URI appendUri(String uri, String appendQuery) throws URISyntaxException;

    URI appendUriImagePreview(String uri) throws URISyntaxException;

    String normalizeToFriendlyUrl(String s);
}
