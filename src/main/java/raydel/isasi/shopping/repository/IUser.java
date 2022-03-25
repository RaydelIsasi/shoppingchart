package raydel.isasi.shopping.repository;


import raydel.isasi.shopping.pojo.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUser {

    User saveUser(User u) throws Exception;

    User updateUser(User u) throws Exception;

    String deleteUser(UUID idusuario);

    Optional<User> findUser(UUID idusuario);


    List<User> findAllUsers();

}
