package pro.acutus.keycloak.freemarkerplus;

import org.keycloak.Config;
import org.keycloak.email.freemarker.FreeMarkerEmailTemplateProvider;
import org.keycloak.email.freemarker.FreeMarkerEmailTemplateProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.theme.FreeMarkerUtil;

public class FreeMarkerPlusEmailTemplateProviderFactory extends FreeMarkerEmailTemplateProviderFactory {

    private FreeMarkerUtil freeMarker;

    @Override
    public FreeMarkerPlusEmailTemplateProvider create(KeycloakSession session) {
        return new FreeMarkerPlusEmailTemplateProvider(session, freeMarker);
    }

    @Override
    public void init(Config.Scope config) {
        freeMarker = new FreeMarkerUtil();
    }

    @Override
    public String getId() {
        return "freemarkerEmail+";
    }

}
