package com.infy.bpe.controllers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.ws.rs.core.Response;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infy.bpe.core.CodeAnalyser;
import com.infy.bpe.core.DataStore;
import com.infy.bpe.models.ConfigSettings;
import com.infy.bpe.models.ErrorMessage;

@RestController
public class AuthenticationController {

	private static ExecutorService executorService;

	private static synchronized ExecutorService getInstance() {
		if (executorService == null) {
			executorService = Executors.newFixedThreadPool(15);
		}
		return executorService;
	}

	@RequestMapping(value = { "/", "/**" }, method = RequestMethod.GET)
	public String getConfigDetails() {
		return "<h1>BPE application is Up and Running</h1>";
	}

	@RequestMapping(value = "/config", method = RequestMethod.POST)
	public Response setConfigDetails(@RequestBody ConfigSettings config) {
		Response response = null;
		try {
			DataStore.USERNAME = config.getSfdcUser();
			DataStore.PASSWORD = config.getSfdcPassword();
			DataStore.AUTHENDPOINT = config.getAuthUrl();

			DataStore.BESTPRACTICES = config.getBestPractices();
			DataStore.CODESTYLE = config.getCodeStyle();
			DataStore.ERRORPRONE = config.getErrorProne();
			DataStore.DESIGN = config.getDesign();
			DataStore.SECURITY = config.getSecurity();
			DataStore.PERFORMANCE = config.getPerformance();
			
			DataStore.METADATALIST=config.getMetadataList();

			response = validateData(config);
			System.out.println("***response code****  :: " + response.getStatus());
			if (response.getStatus() == 200) {

				asyncStartBPScan();
				System.out.println("async BPE scan started");

			}
		} catch (Exception e) {
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			e.printStackTrace();
		}

		return response;
	}

	@RequestMapping(value = "/config2", method = RequestMethod.POST)
	public Response setConfigDetails1(@RequestBody ConfigSettings config) {
		Response response = Response.status(Response.Status.OK).entity("test method").build();

		return response;
	}

	private void asyncStartBPScan() {
		ExecutorService executorService = AuthenticationController.getInstance();
		Future<?> future = executorService.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("!!!!!!!!!!!Asynchronous task started");
				try {

					System.out.println("$$$$$$ future$$$$$$$$$");
					CodeAnalyser.startScanSalesOrg();
				} catch (Exception e) {
					System.err.println("Asynchronous task failed due to " + e.getMessage());
					// implement insert scan failures method
					e.printStackTrace();
				}

			}
		});
		if (future.isDone()) {
			executorService.shutdown();
		}
	}

	private Response validateData(ConfigSettings config) {
		Response response = null;
		boolean isValid = true;
		String message = null;
		try {
			/*
			 * if (StringUtils.isEmpty(config.getSfdcUser())) { isValid = false; message =
			 * "SDFC user cannot be empty.Please provide the valid data"; } else if
			 * (StringUtils.isEmpty(config.getSfdcPassword())) { isValid = false; message =
			 * "SDFC password cannot be empty.Please provide the valid data"; } else if
			 * (StringUtils.isEmpty(config.getAuthUrl())) { isValid = false; message =
			 * "Auth URL cannot be empty.Please provide the valid URL"; }
			 * 
			 * 
			 * if (!isValid) { ErrorMessage errorMessage = new ErrorMessage(message, 400,
			 * "Field Data Invalid"); response =
			 * Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
			 * 
			 * }
			 */

			String loginstatus = CodeAnalyser.ValidateCredsViaLogin();

			if (loginstatus.equalsIgnoreCase("success")) {

				response = Response.status(Response.Status.OK).entity("Creds Validated and Scan has been initiated")
						.build();
				
				//Response.sts
				
				

			}

		} catch (Exception e) {

			ErrorMessage errorMessage = new ErrorMessage(message, 400, "Field Data Invalid");
			response=  Response.status(400, "BAD_REQUEST").build();
			//response = Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
			//response = Response.status(Response.Status.BAD_REQUEST).build();
			e.printStackTrace();
		}
		return response;
	}
}
