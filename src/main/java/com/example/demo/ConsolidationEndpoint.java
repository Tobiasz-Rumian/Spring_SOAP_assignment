package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ConsolidationEndpoint {
    private static final String NAMESPACE_URI = "demo.example.com";
    private final ConsolidationService consolidationService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserLoansRequest")
    @ResponsePayload
    public GetUserLoansResponse getUserLoans(@RequestPayload GetUserLoansRequest request) {
        var response = new GetUserLoansResponse();
        response.getLoans().addAll(consolidationService.getUserLoans(request.getUserId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "calculateOfferRequest")
    @ResponsePayload
    public CalculateOfferResponse calculateOffer(@RequestPayload CalculateOfferRequest request) {
        var response = new CalculateOfferResponse();
        response.setOffer(consolidationService.calculateOffer(request.selectedLoans));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "acceptOfferRequest")
    @ResponsePayload
    public AcceptOfferResponse acceptOffer(@RequestPayload AcceptOfferRequest request) {
        var response = new AcceptOfferResponse();
        response.setAccepted(consolidationService.acceptOffer(request.getOffer()));
        return response;
    }

}
