package raydel.isasi.shopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raydel.isasi.shopping.pojo.Itinerary;
import raydel.isasi.shopping.service.IItineraryService;
import raydel.isasi.shopping.service.IJWTService;
import raydel.isasi.shopping.service.ITokenInfoService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static raydel.isasi.shopping.util.Constant.ITINERARY;
import static raydel.isasi.shopping.util.Constant.TICKET;

@Service
public class ItineraryServiceImpl implements IItineraryService {

    @Autowired
    IJWTService jwtService;

    @Autowired
    ITokenInfoService tokenInfoService;


    @Autowired
    CustomUserDetailService customUserDetailService;

    /* this method saves the itinerary data into the JWT */
    @Override
    public String saveItineraryData(Itinerary itinerary, Object request) throws Exception {
        HttpServletRequest httpReq = (HttpServletRequest) request;

        String token = httpReq.getHeader(AUTHORIZATION).substring(7);
        Map<String, Object> flyingTicket = tokenInfoService.extractFlyingTicketInfo(token);
        Map<String, Object> claims = (tokenInfoService.extractAllClaims(token));


        if (flyingTicket == null) {
            flyingTicket = new HashMap<>();


        }


        flyingTicket.put(ITINERARY, itinerary);


        claims.put(TICKET, flyingTicket);


        final String updated_token = jwtService.generateToken(customUserDetailService.loadUserByUsername(tokenInfoService.extractAllClaims(token).getSubject()), claims);
        return updated_token;
    }
}

