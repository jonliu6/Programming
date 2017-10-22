package org.freecode.demo.view;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@FacesValidator("recordIdValidator")
public class RecordIdValidator implements Validator{
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// validate file size 
		// String maxFileSizeAttr = (String) component.getAttributes().get("maxFileSize");
//		String maxFileSizeAttr = ResourceBundle.getBundle("messages").getString(FILE_UPLOAD_MAX_SIZE);
//		long maxFileSize = 0;
//		if (maxFileSizeAttr != null) {
//			maxFileSize = Long.valueOf(maxFileSizeAttr);
//		}
//
//		Part filePart = (Part) value;
		if (value == null || value.toString().trim().length() < 1) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Null value", "Please enter a record ID."));
		}
//		else {
//			if (filePart.getSize() > maxFileSize) {
//				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "File too big", "File size cannot exceed " + maxFileSize + " bytes."));
//			}
//		}
	}
	
}
