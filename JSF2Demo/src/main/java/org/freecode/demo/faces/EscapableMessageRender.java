package org.freecode.demo.faces;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.context.ResponseWriterWrapper;

import com.sun.faces.renderkit.html_basic.MessageRenderer;

/**
 * The class overrides the default render of h:message. For h:messages, place anywhere (component family, renderer type and class names) "Message" with "Messages"
 * Solution provided by BalusC, from https://stackoverflow.com/questions/3356546/embedding-a-link-or-other-html-in-a-jsf-message
 * Somehow, not working for JSF2 + Richfaces 
 */
// @FacesRenderer(componentFamily="javax.faces.Message", rendererType="javax.faces.Message")
public class EscapableMessageRender extends MessageRenderer {

	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		final ResponseWriter originalResponseWriter = context.getResponseWriter();
		// String escape = (String) component.getAttributes().get("escape");
		// System.out.println("escape=" + escape);
		try {
			context.setResponseWriter(new ResponseWriterWrapper() {
				
				@Override
				public ResponseWriter getWrapped() {
					return originalResponseWriter;
				}
				
				@Override
			    public void writeText(Object text, UIComponent component, String property) throws IOException {
					String string = String.valueOf(text);
					String escape = (String) component.getAttributes().get("escape");
					if (escape != null && !Boolean.valueOf(escape)) {
						super.write(string);
					}
					else {
						super.writeText(string,  component, property);
					}
				}
			});
			
			super.encodeEnd(context, component); // Now, render it!
		}
		finally {
			context.setResponseWriter(originalResponseWriter);
		}
	}
	
}
