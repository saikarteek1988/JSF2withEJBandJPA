/**
 * 
 */
package com.jsf.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author MounikaAmrutha
 *
 */
public class EmployeeValidator implements Validator {


	private static final String REGEX_PATTERN = "^[a-zA-Z0-9]*$";

	private Pattern pattern;
	private Matcher matcher;

	public EmployeeValidator(){
		  pattern = Pattern.compile(REGEX_PATTERN);
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {

		matcher = pattern.matcher(value.toString());
		if(!matcher.matches()){

			FacesMessage msg =
				new FacesMessage("E-mail validation failed.",
						"Invalid E-mail format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		
	}
		

}
