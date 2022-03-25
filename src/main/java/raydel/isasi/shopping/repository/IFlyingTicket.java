package raydel.isasi.shopping.repository;


import raydel.isasi.shopping.pojo.FlyingTicket;

import java.util.Map;

public interface IFlyingTicket {

    FlyingTicket saveFlyingTicket(Map<String, Object> flyingTicket) throws Exception;

    FlyingTicket updateFlyingTicket(Map<String, Object> flyingTicket) throws Exception;


}
