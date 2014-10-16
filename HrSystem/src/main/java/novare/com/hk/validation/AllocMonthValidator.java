/* Spring form validation for allocation
 * For testing purposes, can either be continued or deleted for other models.
 * */
package novare.com.hk.validation;

import novare.com.hk.model.Project;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AllocMonthValidator implements Validator {
	
	public boolean supports(Class<?> paramClass){
		return Project.class.equals(paramClass);
	}
	
	public void validate(Object obj, Errors errors){
		ValidationUtils.rejectIfEmpty(errors, "start_date", "field.start_date", "Field must not be empty!");
		//ValidationUtils.rejectIfEmpty(errors, "end_date", "field.end_date", "Field must not be empty!");
	}

}
