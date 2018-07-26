package eu.strasbourg.service.comment.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelListener;
import org.osgi.service.component.annotations.Component;

/**
 * @author alexandre.quere
 */
@Component(
        immediate = true,
        service = ModelListener.class
)
public class SignalementGroupModelListener extends BaseModelListener<Group> {
    @Override
    public void onAfterRemove(Group model) throws ModelListenerException {}
}
