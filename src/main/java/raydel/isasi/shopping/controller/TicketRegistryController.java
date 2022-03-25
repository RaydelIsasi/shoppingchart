package raydel.isasi.shopping.controller;


import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;
import raydel.isasi.shopping.pojo.FlyingTicket;
import raydel.isasi.shopping.pojo.Itinerary;
import raydel.isasi.shopping.pojo.Passenger;
import raydel.isasi.shopping.pojo.SuccessPersistenceResponse;
import raydel.isasi.shopping.service.FlyingTicketService;
import raydel.isasi.shopping.service.JWTService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("ticketRegistry")
public class TicketRegistryController {

    @Autowired
    JWTService jwtService;
    @Autowired
    FlyingTicketService flyingTicketService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketRegistryController.class);

    private static final String AUTHORIZATION = "Authorization";
    private static final String TOKEN_BEARER_PREFIX = "Bearer ";

    @RequestMapping(value = "/registerPassenger", method = RequestMethod.POST, produces = {
            "application/json"}, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> addPassenger(@RequestBody Passenger passenger, HttpServletRequest request) throws Exception {

        LOGGER.info("Initiating Passenger persistence");
        final String token = request.getHeader(AUTHORIZATION).substring(7);
        final String updated_token = flyingTicketService.savePassenger(passenger, token);
        final Map<String, Object> flyingTicket = jwtService.extractFlyingTicket(updated_token);
        SuccessPersistenceResponse successResponse = new SuccessPersistenceResponse(flyingTicket, updated_token);
        LOGGER.info("Finishing Passenger persistence");
        return new ResponseEntity<Object>(successResponse, HttpStatus.OK);

    }


    @RequestMapping(value = "/registerItinerary", method = RequestMethod.POST, produces = {
            "application/json"}, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> addItinerary(@RequestBody Itinerary itinerary, HttpServletRequest request) throws Exception {
        LOGGER.info("Initiating itinerary persistence");
        final String token = request.getHeader(AUTHORIZATION).substring(7);
        final String updated_token = flyingTicketService.saveItinerary(itinerary, token);
        final Map<String, Object> flyingTicket = jwtService.extractFlyingTicket(updated_token);
        SuccessPersistenceResponse successResponse = new SuccessPersistenceResponse(flyingTicket, updated_token);
        LOGGER.info("Finishing itinerary persistence");
        return new ResponseEntity<Object>(successResponse, HttpStatus.OK);

    }


}
