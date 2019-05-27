package com.openfaas.function;

import com.openfaas.model.IHandler;
import com.openfaas.model.IResponse;
import com.openfaas.model.IRequest;
import com.openfaas.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Handler implements com.openfaas.model.IHandler {
	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
    public IResponse Handle(IRequest req) {
		String jsonBody = req.getBody();
		log.info("Request Body: {}", jsonBody);
        Response res = new Response();
	    res.setBody(jsonBody);
	    return res;
    }
}
