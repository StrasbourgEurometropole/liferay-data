import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = "Strasbourg",
        scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
        id = "eu.strasbourg.portlet.council.configuration.CommentConfiguration",
        localization = "content/Language",
        name = "portlet.map.configuration.name"
)
public interface CouncilConfiguration {

    @Meta.AD(name="orderBy", deflt = "", required = false)
    boolean orderBy();

}
