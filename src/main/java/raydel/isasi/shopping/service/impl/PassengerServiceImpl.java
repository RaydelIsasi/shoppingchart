package raydel.isasi.shopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raydel.isasi.shopping.pojo.Passenger;
import raydel.isasi.shopping.service.IJWTService;
import raydel.isasi.shopping.service.IPassengerService;
import raydel.isasi.shopping.service.ITokenInfoService;

import javax.servlet.http.HttpServletRequest;
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
    IJWTService jwtService;

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    ITokenInfoService tokenInfoService;



    @Override
    public String savePassengerData(Passenger passenger, Object request) throws Exception {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String token = httpReq.getHeader(AUTHORIZATION).substring(7);
        Map<String, Object> flyingTicket = tokenInfoService.extractFlyingTicketInfo(token);
        Map<String, Object> claims = (tokenInfoService.extractAllClaims(token));


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
        final String updated_token = jwtService.generateToken(customUserDetailService.loadUserByUsername(tokenInfoService.extractAllClaims(token).getSubject()), claims);

        return updated_token;
    }
}