package raydel.isasi.shopping.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raydel.isasi.shopping.pojo.Itinerary;
import raydel.isasi.shopping.pojo.Passenger;
import raydel.isasi.shopping.service.FlyingServiceImpl;
import raydel.isasi.shopping.service.JWTService;
import raydel.isasi.shopping.service.UtilStorageService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
@RequestMapping("ticketRegistry")
public class TicketRegistryController {

    @Autowired
    JWTService jwtService;
    @Autowired
    UtilStorageService utilStorageService;

    @Autowired
    FlyingServiceImpl flyingService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketRegistryController.class);



    @RequestMapping(value = "/registerPassenger", method = RequestMethod.POST, produces = {
            "application/json"}, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> addPassenger(@RequestBody Passenger passenger, HttpServletRequest request) throws Exception {

        LOGGER.info("Initiating Passenger persistence");

        final String updated_token = utilStorageService.savePassengerData(passenger, request);

        List<String> values = new ArrayList<>();
        values.add(updated_token);
        HttpHeaders headers = new HttpHeaders();
        headers.put("token", values);

        LOGGER.info("Finishing Passenger persistence");
        return new ResponseEntity<Object>(jwtService.extractFlyingTicket(updated_token), headers, HttpStatus.OK);

    }


    @RequestMapping(value = "/registerItinerary", method = RequestMethod.POST, produces = {
            "application/json"}, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> addItinerary(@RequestBody Itinerary itinerary, HttpServletRequest request) throws Exception {
        LOGGER.info("Initiating itinerary persistence");
        final String updated_token = utilStorageService.saveItineraryData(itinerary, request);
        List<String> values = new ArrayList<>();
        values.add(updated_token);
        HttpHeaders headers = new HttpHeaders();
        headers.put("token", values);
        LOGGER.info("Finishing itinerary persistence");
        return new ResponseEntity<Object>(jwtService.extractFlyingTicket(updated_token), headers, HttpStatus.OK);

    }


    @RequestMapping(value = "/confirmTicket", method = RequestMethod.POST, produces = {
            "application/json"}, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> confirmTicket(HttpServletRequest request) throws Exception {
        LOGGER.info("Initiating ticket confirmation persistence");
        final String token = request.getHeader(AUTHORIZATION).substring(7);
        final Map<String, Object> flyingTicket = jwtService.extractFlyingTicket(token);

        LOGGER.info("Finishing ticket confirmation persistence");
        return new ResponseEntity<Object>(flyingService.saveFlyingTicket(flyingTicket), HttpStatus.OK);

    }


}
