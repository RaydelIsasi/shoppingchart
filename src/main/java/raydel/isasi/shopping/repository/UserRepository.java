package raydel.isasi.shopping.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import raydel.isasi.shopping.pojo.User;

import java.math.BigInteger;
import java.util.List;


public interface UserRepository extends MongoRepository<User, BigInteger> {

    public List<User> findByEmail(String email);
    public User findByName(String name);

}
