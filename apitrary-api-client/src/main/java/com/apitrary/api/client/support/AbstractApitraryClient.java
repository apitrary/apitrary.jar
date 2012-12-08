package com.apitrary.api.client.support;

import java.io.InputStream;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import com.apitrary.api.client.common.HttpStatus;
import com.apitrary.api.client.common.Timer;
import com.apitrary.api.client.exception.CommunicationErrorException;
import com.apitrary.api.client.serialization.ResultSerializer;
import com.apitrary.api.client.util.HttpMethodUtil;
import com.apitrary.api.client.util.PathUtil;
import com.apitrary.api.client.util.RequestUtil;
import com.apitrary.api.common.HttpMethod;
import com.apitrary.api.request.Request;
import com.apitrary.api.response.Response;

public abstract class AbstractApitraryClient {
	protected final Logger log = Logger.getLogger(this.getClass());

	protected static final String apitraryUrl = "api.apitrary.com";
	protected static final String protocol = "http://";
	protected static final String apiAuthHeaderKey = "X-Api-Key";
	
	protected ResultSerializer resultSerializer = new ResultSerializer();
	
	protected <T> Response<T> dispatchByMethod(Request<T> request) {
		HttpMethod method = HttpMethodUtil.retrieveMethod(request);

		WebClient webClient = instantiateWebClient();

		switch (method) {
			case GET:
				return doGet(webClient, request);
			case POST:
				return doPost(webClient, request);
			case PUT:
				return doPut(webClient, request);
			case DELETE:
				return doDelete(webClient, request);
			default:
				throw new CommunicationErrorException(HttpStatus.Not_Implemented);
		}
	}
	
	protected <T> Response<T> doGet(WebClient webClient, Request<T> request) {
		webClient = webClient.path(inquirePath(request));
		webClient = RequestUtil.resolveAndSetQueryPart(request, webClient);

		Timer timer = Timer.tic();
		javax.ws.rs.core.Response cxfResponse = webClient.get();
		timer.toc();

		log.debug(cxfResponse.getStatus() + " " + webClient.getCurrentURI() + " took " + timer.getDifference() + "ms");

		Response<T> response = toResponse(timer, cxfResponse, request);

		log.debug(response);

		return response;
	}

	protected <T> Response<T> doPost(WebClient webClient, Request<T> request) {
		webClient = webClient.path(inquirePath(request));
		webClient = RequestUtil.resolveAndSetQueryPart(request, webClient);

		Timer timer = Timer.tic();
		javax.ws.rs.core.Response cxfResponse = webClient.post(RequestUtil.getRequestPayload(request));
		timer.toc();

		log.debug(cxfResponse.getStatus() + " " + webClient.getCurrentURI() + " took " + timer.getDifference() + "ms");

		Response<T> response = toResponse(timer, cxfResponse, request);

		log.debug(response);

		return response;
	}

	protected <T> Response<T> doPut(WebClient webClient, Request<T> request) {
		webClient = webClient.path(inquirePath(request));
		webClient = RequestUtil.resolveAndSetQueryPart(request, webClient);

		Timer timer = Timer.tic();
		javax.ws.rs.core.Response cxfResponse = webClient.put(RequestUtil.getRequestPayload(request));
		timer.toc();

		log.debug(cxfResponse.getStatus() + " " + webClient.getCurrentURI() + " took " + timer.getDifference() + "ms");

		Response<T> response = toResponse(timer, cxfResponse, request);

		log.debug(response);

		return response;
	}

	protected <T> Response<T> doDelete(WebClient webClient, Request<T> request) {
		webClient = webClient.path(inquirePath(request));
		webClient = RequestUtil.resolveAndSetQueryPart(request, webClient);

		Timer timer = Timer.tic();
		javax.ws.rs.core.Response cxfResponse = webClient.delete();
		timer.toc();

		log.debug(cxfResponse.getStatus() + " " + webClient.getCurrentURI() + " took " + timer.getDifference() + "ms");

		Response<T> response = toResponse(timer, cxfResponse, request);

		log.debug(response);

		return response;
	}

	private <T> Response<T> toResponse(Timer timer, javax.ws.rs.core.Response cxfResponse, Request<T> request) {
		InputStream inputStream = (InputStream) cxfResponse.getEntity();
		Response<T> response = null;

		HttpStatus status = HttpStatus.getStatus(cxfResponse.getStatus());

		response = deserialize(inputStream, request);

		response.setStatusCode(status.getCode());
		response.setResponseTime(timer.getDifference());

		return response;
	}

	protected <T> String inquirePath(Request<T> request) {
		return PathUtil.resolveResourcePath(request);
	}
	
	protected abstract <T> String inquireVHost();

	protected abstract <T> Response<T> deserialize(String response, Request<T> request);

	protected abstract <T> Response<T> deserialize(InputStream inputStream, Request<T> request);

	protected abstract WebClient instantiateWebClient();
}
