package eu.strasbourg.utils;

import eu.strasbourg.utils.api.UriHelperService;
import org.osgi.service.component.annotations.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component(
        immediate = true,
        property = {},
        service = UriHelperService.class)
public class UriHelperImpl implements UriHelperService {
    @Override
    public URI appendUri(String uri, String appendQuery) throws URISyntaxException {
        return UriHelper.appendUri(uri,appendQuery);
    }

    @Override
    public URI appendUriImagePreview(String uri) throws URISyntaxException {
        return UriHelper.appendUriImagePreview(uri);
    }

    @Override
    public String normalizeToFriendlyUrl(String s) {
        return UriHelper.normalizeToFriendlyUrl(s);
    }
}
