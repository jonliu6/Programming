package org.freecode.demo;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.freecode.demo.soapwsconsumer.wsdl.ConvertCelsiusToFahrenheit;
import org.freecode.demo.soapwsconsumer.wsdl.ConvertCelsiusToFahrenheitResponse;

public class WeatherForecastClient extends WebServiceGatewaySupport{
	
	public ConvertCelsiusToFahrenheitResponse getFahrenheitValue(int celsiusValue) {
		ConvertCelsiusToFahrenheit request = new ConvertCelsiusToFahrenheit();
		request.setCelsiusValue(celsiusValue);
		
		ConvertCelsiusToFahrenheitResponse response= (ConvertCelsiusToFahrenheitResponse) getWebServiceTemplate().marshalSendAndReceive(getDefaultUri(), request, new SoapActionCallback("http://tempuri.org/convertCelsiusToFahrenheit"));
		
		return response;
	}

}
