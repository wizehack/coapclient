package com.wizehack.test.coap;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import org.eclipse.californium.core.CoapResponse;

public class Main {
	
	/*
	 * Application entry point.
	 * 
	 */	
	public static void main(String args[]) {
		
		OcfClient ocfClient = null;
		
		if (args.length > 0) {
			
			// input URI from command line arguments
			try {
				ocfClient = new OcfClient(args[0]);
			} catch (URISyntaxException e) {
				System.err.println("Invalid URI: " + e.getMessage());
				System.exit(-1);
			}
			
			if(ocfClient != null) {
				CoapResponse response = ocfClient.get();
				ocfClient.displayResponse(response);
			}			
			
		} else {
			// display help
			System.out.println("Usage : " + OcfClient.class.getSimpleName() + " URI [file]");
			System.out.println("  URI : The CoAP URI of the remote resource to GET");
			System.out.println("  file: optional filename to save the received payload");
		}
	}
}
