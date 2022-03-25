package raydel.isasi.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raydel.isasi.shopping.pojo.Itinerary;
import raydel.isasi.shopping.pojo.Passenger;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UtilStorageService {

    @Autowired
    JWTService jwtService;

    @Autowired
    CustomUserDetailService customUserDetailService;

    /* this method saves the passenger data into the JWT */
    public String savePassengerData(Passenger passenger, String token) throws IOException, ServletException {

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

    /* this method saves the itinerary data into the JWT */
    public String saveItineraryData(Itinerary itinerary, String token) throws IOException, ServletException {

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
