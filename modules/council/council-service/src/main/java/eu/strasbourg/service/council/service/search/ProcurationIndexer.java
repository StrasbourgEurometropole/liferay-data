package eu.strasbourg.service.council.service.search;

import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Indexer;
import eu.strasbourg.service.council.model.Procuration;
import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        service = Indexer.class
)
public class ProcurationIndexer extends BaseIndexer<Procuration> {
}
