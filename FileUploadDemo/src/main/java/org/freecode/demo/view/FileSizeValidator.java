package org.freecode.demo.view;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@FacesValidator("fileSizeValidator")
public class FileSizeValidator implements Validator{
	public static final String FILE_UPLOAD_MAX_SIZE = "file.upload.size.max";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// validate file size 
		// String maxFileSizeAttr = (String) component.getAttributes().get("maxFileSize");
		String maxFileSizeAttr = ResourceBundle.getBundle("messages").getString(FILE_UPLOAD_MAX_SIZE);
		long maxFileSize = 0;
		if (maxFileSizeAttr != null) {
			maxFileSize = Long.valueOf(maxFileSizeAttr);
		}

		Part filePart = (Part) value;
		if (filePart == null) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Null value", "Please choose a file."));
		}
		else {
			if (filePart.getSize() > maxFileSize) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "File too big", "File size cannot exceed " + maxFileSize + " bytes."));
			}
		}
	}
	
}
