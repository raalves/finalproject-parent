package pt.uc.dei.aor.pf.rafaelaricardo;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.GuideEntity;

//@FacesConverter(forClass = GuideEntity.class)
@FacesConverter("guideConverter")
public class GuideConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && !value.isEmpty()) {
			return (GuideEntity) uic.getAttributes().get(value);
		}
		return null;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((GuideEntity) object).getId());
		} else {
			return null;
		}
	}
}
