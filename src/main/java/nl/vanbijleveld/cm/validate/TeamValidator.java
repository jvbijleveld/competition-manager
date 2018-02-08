package nl.vanbijleveld.cm.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import nl.vanbijleveld.cm.team.Team;

public class TeamValidator implements Validator{

	@Override
	public boolean supports(Class<?> aClass) {
		return Team.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "notEmpty");
	}

}
