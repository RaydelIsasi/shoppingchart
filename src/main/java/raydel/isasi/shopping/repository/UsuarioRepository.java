package raydel.isasi.shopping.repository;




import org.springframework.data.repository.CrudRepository;
import raydel.isasi.shopping.pojo.User;

import java.util.List;
import java.util.UUID;


public interface UsuarioRepository extends CrudRepository<User, UUID> {

    public List<User> findByEmail(String email);
    public User findByName(String name);

}
