package org.freecode.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.transport.http.HttpsUrlConnectionMessageSender;

@Configuration
public class WeatherForecastConfiguration {
	
	@Value("${weather.forecast.keystore}")
	private String keyStore;

	@Value("${weather.forecast.keystore.password}")
	private String keyStorePass;

	@Bean
	public Jaxb2Marshaller marshaller() {
	    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
	    // this package must match the package in the <generatePackage> specified in
	    // pom.xml
	    marshaller.setContextPath("org.freecode.demo.soapwsconsumer.wsdl");
	    return marshaller;
	}
	
	@Bean
	public WeatherForecastClient weatherForecastClient(Jaxb2Marshaller marshaller) throws FileNotFoundException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException {
		WeatherForecastClient client = new WeatherForecastClient();
		//client.setDefaultUri("http://localhost:5246/WeatherForecastService.asmx");
		client.setDefaultUri("https://localhost:7137/WeatherForecastService.asmx");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		
		FileInputStream fis = new FileInputStream(keyStore);
	    KeyStore ks = KeyStore.getInstance("JKS");
	    ks.load(fis, keyStorePass.toCharArray());

		try 
		{
		    fis.close();
		} 
		catch (IOException e) 
		{
		}
		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		keyManagerFactory.init(ks, keyStorePass.toCharArray());

		FileInputStream fisTS = new FileInputStream(keyStore);
		KeyStore ts = KeyStore.getInstance("JKS");
		ts.load(fisTS, keyStorePass.toCharArray());

		try 
		{
			fisTS.close();
		} 
		catch(IOException e) 
		{
		}
		//TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		
		HttpsUrlConnectionMessageSender messageSender = new HttpsUrlConnectionMessageSender();
		messageSender.setKeyManagers(keyManagerFactory.getKeyManagers());
		//messageSender.setTrustManagers(trustManagerFactory.getTrustManagers());

		HostnameVerifier hv = new HostnameVerifier(){
			@Override
			public boolean verify( String hostname, SSLSession session)
			{
				return true;
			}
		};
		messageSender.setHostnameVerifier(hv);
		client.setMessageSender(messageSender);
		
		return client;
	}
}
