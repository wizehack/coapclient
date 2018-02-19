package com.wizehack.test.coap;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

public class OcfClient {

	private URI uri = null; // URI parameter of the request
	private FileOutputStream out = null;
	private CoapClient client = null;

	public OcfClient(String coapUri) throws URISyntaxException {
		this.uri = new URI(coapUri);
		this.client = new CoapClient(this.uri);
	}
	
	public OcfClient(String coapUri, String out) throws URISyntaxException, FileNotFoundException {
		this.uri = new URI(coapUri);
		this.out = new FileOutputStream(out);
		this.client = new CoapClient(this.uri);
	}
	
	public CoapResponse get() {
		CoapResponse response = null;
		response = this.client.get();
		return response;
	}
	
	public CoapResponse put(String payload) {
		CoapResponse response = null;
		response = this.client.putIfNoneMatch(payload, MediaTypeRegistry.APPLICATION_JSON);
		return response;
	}
	
	public void displayResponse(CoapResponse response) {
		if (response!=null) {
			System.out.println(response.getCode());
			System.out.println(response.getOptions());
			
			if (out != null) {
				try {
					this.out.write(response.getPayload());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println(response.getResponseText());
				System.out.println(System.lineSeparator() + "ADVANCED" + System.lineSeparator());
				// access advanced API with access to more details through
				// .advanced()
				System.out.println(Utils.prettyPrint(response));
			}
		} else {
			System.out.println("No response received.");
		}
	}


}