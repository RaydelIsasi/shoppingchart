package raydel.isasi.shopping.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface IJWTService {

    public String generateToken(UserDetails userDetails, Map<String, Object> claims) throws Exception;

    public boolean validateToken(String token, UserDetails userDetails);


}
