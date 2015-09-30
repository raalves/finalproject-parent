package pt.uc.dei.aor.pf.rafaelaricardo;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;

//@FacesConverter(forClass = UserEntity.class)
@FacesConverter(value = "userConverter")
public class UserConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && !value.isEmpty()) {
			return (UserEntity) uic.getAttributes().get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object value) {
		if (value instanceof UserEntity) {
			UserEntity entity = (UserEntity) value;
			if (entity != null && entity instanceof UserEntity
					&& entity.getId() != null) {
				uic.getAttributes().put(entity.getId().toString(), entity);
				return entity.getId().toString();
			}
		}
		return "";
	}
}
