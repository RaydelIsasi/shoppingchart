package raydel.isasi.shopping.service.impl;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import raydel.isasi.shopping.pojo.FlyingTicket;
import raydel.isasi.shopping.service.IJWTService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import static raydel.isasi.shopping.util.Constant.ISSUER_INFO;
import static raydel.isasi.shopping.util.Constant.SECRET_KEY;

@Service
public class JWTServiceImpl implements IJWTService {


    private static final Logger LOGGER = LoggerFactory.getLogger(JWTServiceImpl.class);


    public static final long TOKEN_EXPIRATION_TIME = 864_000_000;

    public String generateToken(UserDetails userDetails, Map<String, Object> claims) throws IOException, ServletException {
        LOGGER.info("Initiating generation of Bearer Token");
        return createToken(claims, userDetails.getUsername());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsresolver) {


        final Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claimsresolver.apply(claims);
    }





    private String createToken(Map<String, Object> claims, String username) throws IOException, ServletException {


        if (claims.get("FLYING_TICKET") == null) {
            claims.put("FLYING_TICKET", new FlyingTicket());
        }

        String token = Jwts.builder().setIssuedAt(new Date()).setIssuer(ISSUER_INFO)
                .setClaims(claims).setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();

        LOGGER.info("Finishing token generation");
        return token;
    }

    private Boolean isTokenExpired(String token) {

        return extractClaim(token, Claims::getExpiration).before(new Date());

    }

    public boolean validateToken(String token, UserDetails userDetails) {
        LOGGER.info("Validating token");
        String username = extractClaim(token, Claims::getSubject);
        return username.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token);


    }
}
