package org.freecode.demo.faces;

import java.io.IOException;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

@FacesComponent(value="org.freecode.demo.faces.HelloWorldComponent")
public class HelloWorldComponent extends UIComponentBase {
    // Mapping helloworld attribute to a bean property
    public ValueExpression helloworld = null;
    
    public String getFamily() {
        return "HELLO_WORLD_FAMILY";
    }
    
    public void encodeBegin(FacesContext ctx) throws IOException {
        ResponseWriter responseWriter = ctx.getResponseWriter();
        String helloworld = (String) getAttributes().get("helloworld");
        responseWriter.startElement("b", this);
        if (helloworld != null) {
        responseWriter.writeText(helloworld, "helloworld");
        } else {
        responseWriter.writeText("This is a simple Hello World JSF custom component!", null);
        }
        responseWriter.endElement("b");
        
    }
    
}
