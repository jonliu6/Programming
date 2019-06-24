package org.freecode.demo.view;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@FacesValidator("fileExtensionValidator")
public class FileExtensionValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Part filePart = (Part) value;
		if (filePart == null) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Null value", "Please choose a file."));
		}
		else {
			if (filePart.getSubmittedFileName() != null && (filePart.getSubmittedFileName().indexOf(".xls") >= 0)) {
//				if (filePart.getSubmittedFileName().indexOf(".xlsx") >= 0) {
//					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Not support new MS Excel format", "The file extension has to be *.xls - MS Excel 2003 and older version."));
//				}
			}
			else {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not a valid MS Excel File", "The file extension has to be *.xls - MS Excel 2003 and older version."));
			}
		}
	}
}
