package raydel.isasi.shopping.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import raydel.isasi.shopping.service.IJWTService;
import raydel.isasi.shopping.service.ITokenInfoService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    private static final String TOKEN_BEARER_PREFIX = "Bearer ";
    public static final String SECRET_KEY = "secret_key";
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTFilter.class);

    @Autowired
    ITokenInfoService tokenInfoService;

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private IJWTService jwtService;

    public JWTFilter() {

    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        LOGGER.info("Initiating JwtFilter for user authentication");
        final String authorizationHeader = req.getHeader(AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_BEARER_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LOGGER.info("Finishing JwtFilter for user authentication");
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION).substring(7);
        if (token != null) {


            String username = tokenInfoService.extractAllClaims(token).getSubject();
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);


                    if (userDetails != null && jwtService.validateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), new ArrayList<>());
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        return usernamePasswordAuthenticationToken;
                    }
                    return null;
                }

            }
            return null;
        }
        return null;
    }


}
