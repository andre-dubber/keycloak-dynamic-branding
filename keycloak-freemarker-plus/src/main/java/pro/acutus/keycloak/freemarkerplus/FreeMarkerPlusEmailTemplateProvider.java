package pro.acutus.keycloak.freemarkerplus;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.UriBuilder;
import org.jboss.resteasy.spi.ResteasyUriInfo;
import org.keycloak.email.freemarker.FreeMarkerEmailTemplateProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.email.EmailException;
import org.keycloak.theme.FreeMarkerUtil;
import org.keycloak.theme.beans.LinkExpirationFormatterMethod;
import org.keycloak.theme.Theme;

public class FreeMarkerPlusEmailTemplateProvider extends FreeMarkerEmailTemplateProvider {

    private static final Logger LOG = Logger.getLogger(FreeMarkerPlusEmailTemplateProvider.class.getName());

    public FreeMarkerPlusEmailTemplateProvider(KeycloakSession session, FreeMarkerUtil freeMarker) {
        super(session, freeMarker);
    }


    /**
     * Add link info into template attributes.
     * 
     * @param link to add
     * @param expirationInMinutes to add
     * @param attributes to add link info into
     */
    @Override
    protected void addLinkInfoIntoAttributes(String link, long expirationInMinutes, Map<String, Object> attributes) throws EmailException {
        attributes.put("link", link);
        attributes.put("linkExpiration", expirationInMinutes);
        try {
            Locale locale = session.getContext().resolveLocale(user);
            attributes.put("linkExpirationFormatter", new LinkExpirationFormatterMethod(getTheme().getMessages(locale), locale));
            attributes.put("theme", link);
        } catch (IOException e) {
            throw new EmailException("Failed to template email", e);
        }
    }
}
