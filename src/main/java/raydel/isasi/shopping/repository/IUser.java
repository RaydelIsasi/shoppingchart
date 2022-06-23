package raydel.isasi.shopping.repository;


import raydel.isasi.shopping.pojo.User;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface IUser {

    User saveUser(User u) throws Exception;

    User updateUser(User u) throws Exception;

    String deleteUser(BigInteger idusuario);

    Optional<User> findUser(BigInteger  idusuario);


    List<User> findAllUsers();

}
