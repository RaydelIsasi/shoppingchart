package raydel.isasi.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raydel.isasi.shopping.pojo.Passenger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static raydel.isasi.shopping.util.Constant.PASSENGER;
import static raydel.isasi.shopping.util.Constant.TICKET;

@Service
public class PassengerServiceImpl implements IPassengerService {

    @Autowired
    JWTService jwtService;

    @Autowired
    CustomUserDetailService customUserDetailService;


    @Override
    public String savePassengerData(Passenger passenger, Object request) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String token = httpReq.getHeader(AUTHORIZATION).substring(7);
        Map<String, Object> flyingTicket = jwtService.extractFlyingTicket(token);
        Map<String, Object> claims = (jwtService.extractAllClaims(token));


        if (flyingTicket == null) {
            flyingTicket = new HashMap<>();
            List<Passenger> passengerList = new ArrayList<>();
            passengerList.add(passenger);


            flyingTicket.put(PASSENGER, passengerList);


            claims.put(TICKET, flyingTicket);


        } else {
            List<Passenger> passengerList = (List<Passenger>) flyingTicket.get(PASSENGER);

            if (passengerList == null) {
                passengerList = new ArrayList<>();
            }

            passengerList.add(passenger);

            flyingTicket.put(PASSENGER, passengerList);


            claims.put(TICKET, flyingTicket);


        }
        final String updated_token = jwtService.generateToken(customUserDetailService.loadUserByUsername(jwtService.extractUser(token)), claims);

        return updated_token;
    }
}