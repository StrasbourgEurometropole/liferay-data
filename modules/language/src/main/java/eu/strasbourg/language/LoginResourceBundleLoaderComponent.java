package eu.strasbourg.language;

import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.util.AggregateResourceBundleLoader;
import com.liferay.portal.kernel.util.CacheResourceBundleLoader;
import com.liferay.portal.kernel.util.ClassResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoader;

@Component(
    immediate = true,
    property = {
        "bundle.symbolic.name=com.liferay.login.web",
        "resource.bundle.base.name=login.Language",
        "servlet.context.name=login-web"
    }
)
public class LoginResourceBundleLoaderComponent
	implements ResourceBundleLoader {

	  @Override
      public ResourceBundle loadResourceBundle(String languageId) {
          return _resourceBundleLoader.loadResourceBundle(languageId);
      }
	  
	  @Reference(target = "(bundle.symbolic.name=com.liferay.login.web)")
      public void setResourceBundleLoader(
          ResourceBundleLoader resourceBundleLoader) {

          _resourceBundleLoader = new AggregateResourceBundleLoader(
              new CacheResourceBundleLoader(
                  new ClassResourceBundleLoader(
                      "login.Language",
                      LoginResourceBundleLoaderComponent.class.getClassLoader())),
              resourceBundleLoader);
      }

      private AggregateResourceBundleLoader _resourceBundleLoader;
}
