package raydel.isasi.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raydel.isasi.shopping.pojo.Itinerary;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static raydel.isasi.shopping.util.Constant.ITINERARY;
import static raydel.isasi.shopping.util.Constant.TICKET;

@Service
public class ItineraryServiceImpl implements IItineraryService {

    @Autowired
    JWTService jwtService;

    @Autowired
    CustomUserDetailService customUserDetailService;

    /* this method saves the itinerary data into the JWT */
    @Override
    public String saveItineraryData(Itinerary itinerary, Object request) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;

        String token = httpReq.getHeader(AUTHORIZATION).substring(7);
        Map<String, Object> flyingTicket = jwtService.extractFlyingTicket(token);
        Map<String, Object> claims = (jwtService.extractAllClaims(token));


        if (flyingTicket == null) {
            flyingTicket = new HashMap<>();


        }


        flyingTicket.put(ITINERARY, itinerary);


        claims.put(TICKET, flyingTicket);


        final String updated_token = jwtService.generateToken(customUserDetailService.loadUserByUsername(jwtService.extractUser(token)), claims);
        return updated_token;
    }
}

