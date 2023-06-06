package eu.strasbourg.service.csmap.upgrade;

import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        service = {UpgradeRegistrator.class, UpgradeStepRegistrator.class}
)
public class UpgradeRegistrator implements UpgradeStepRegistrator {

    @Override
    public void register(Registry registry) {

        registry.register( "1.1.0", "1.2.0",
                new eu.strasbourg.service.csmap.upgrade.v1_2_0.UpgradeSchema());
    }
}