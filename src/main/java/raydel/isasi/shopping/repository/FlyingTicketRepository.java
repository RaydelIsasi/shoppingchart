package raydel.isasi.shopping.repository;




import org.springframework.data.repository.CrudRepository;
import raydel.isasi.shopping.pojo.FlyingTicket;

import java.util.UUID;


public interface FlyingTicketRepository extends CrudRepository<FlyingTicket, UUID> {



}
