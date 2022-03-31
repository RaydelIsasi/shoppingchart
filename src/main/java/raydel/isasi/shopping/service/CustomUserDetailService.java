package raydel.isasi.shopping.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import raydel.isasi.shopping.pojo.User;
import raydel.isasi.shopping.repository.UserRepository;

import java.util.ArrayList;

import static raydel.isasi.shopping.util.Constant.DB_RETRIEVING_MESSAGE;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailService.class);

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info(DB_RETRIEVING_MESSAGE);


        User dbuser = userRepository.findByName(username);
        if (dbuser != null && dbuser.getIsactive()) {
            org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(dbuser.getName(), dbuser.getPassword(), new ArrayList<>());

            return user;
        }
        return null;


    }

}
