package raydel.isasi.shopping.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import raydel.isasi.shopping.exception.CustomException;
import raydel.isasi.shopping.pojo.User;
import raydel.isasi.shopping.repository.IUser;
import raydel.isasi.shopping.repository.UserRepository;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUser {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User u) throws Exception {

        LOGGER.info("Saving User into database");
        if (userRepository.findByEmail(u.getEmail()).isEmpty()) {
            try {
                return userRepository.save(u);


            } catch (ConstraintViolationException e) {
                throw new CustomException("Error al insertar el usuario");
            }

        } else {
            throw new CustomException("El correo ya registrado");
        }


    }

    @Override
    public User updateUser(User u) throws Exception {

        LOGGER.info("Updating  User");
        try {
            return userRepository.save(u);

        } catch (DataIntegrityViolationException e) {
            throw new Exception("User already registered");
        }


    }

    @Override
    public String deleteUser(UUID idusuario) {

        String r = "";

        try {
            userRepository.deleteById(idusuario);


        } catch (Exception e) {

            r = "There was an error trying to delete the user";
        }


        return r;

    }

    @Override
    public Optional<User> findUser(UUID idusuario) {

        Optional<User> u = userRepository.findById(idusuario);

        return u;
    }


    @Override
    public List<User> findAllUsers() {
        List<User> usuarios = (List<User>) userRepository.findAll();
        return usuarios;
    }

}
