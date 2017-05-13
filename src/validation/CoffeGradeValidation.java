package validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by Valeria on 04.05.2017.
 */
public class CoffeGradeValidation implements Validator {
    public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        //if (value == null)
        String console = (String) value;
    }
}
