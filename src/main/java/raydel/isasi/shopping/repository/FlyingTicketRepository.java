package raydel.isasi.shopping.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import raydel.isasi.shopping.pojo.FlyingTicket;

import java.math.BigInteger;


public interface FlyingTicketRepository extends MongoRepository<FlyingTicket,
        BigInteger> {



}
