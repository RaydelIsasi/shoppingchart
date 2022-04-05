package raydel.isasi.shopping.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Map;

import static raydel.isasi.shopping.util.Constant.SECRET_KEY;

public interface ITokenInfoService {


    public default Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }


    public default Map<String, Object> extractFlyingTicketInfo(String token) {


        return (Map<String, Object>) extractAllClaims(token).get("FLYING_TICKET");


    }


}
