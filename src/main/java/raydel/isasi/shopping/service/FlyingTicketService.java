package raydel.isasi.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raydel.isasi.shopping.pojo.FlyingTicket;
import raydel.isasi.shopping.pojo.Itinerary;
import raydel.isasi.shopping.pojo.Passenger;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.*;

@Service
public class FlyingTicketService {

    @Autowired
    JWTService jwtService;

    @Autowired
    CustomUserDetailService customUserDetailService;

    public String savePassenger(Passenger passenger, String token) throws IOException, ServletException {

        Map<String, Object> flyingTicket = jwtService.extractFlyingTicket(token);
        Map<String, Object> claims = (jwtService.extractAllClaims(token));


        if (flyingTicket == null) {
            flyingTicket = new HashMap<>();
            List<Passenger> passengerList = new ArrayList<>();
            passengerList.add(passenger);


            flyingTicket.put("passengerList", passengerList);


            claims.put("FLYING_TICKET", flyingTicket);


        } else {
            List<Passenger> passengerList = (List<Passenger>) flyingTicket.get("passengerList");

            if (passengerList == null) {
                passengerList = new ArrayList<>();
            }

            passengerList.add(passenger);

            flyingTicket.put("passengerList", passengerList);


            claims.put("FLYING_TICKET", flyingTicket);


        }

        final String updated_token = jwtService.generateToken(customUserDetailService.loadUserByUsername(jwtService.extractUser(token)), claims);

        return updated_token;
    }

    public String saveItinerary(Itinerary itinerary, String token) throws IOException, ServletException {

        Map<String, Object> flyingTicket = jwtService.extractFlyingTicket(token);
        Map<String, Object> claims = (jwtService.extractAllClaims(token));


        if (flyingTicket == null) {
            flyingTicket = new HashMap<>();


        }


        flyingTicket.put("itinerary", itinerary);


        claims.put("FLYING_TICKET", flyingTicket);


        final String updated_token = jwtService.generateToken(customUserDetailService.loadUserByUsername(jwtService.extractUser(token)), claims);
        return updated_token;
    }


}
