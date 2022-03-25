package raydel.isasi.shopping.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raydel.isasi.shopping.pojo.FlyingTicket;
import raydel.isasi.shopping.pojo.Itinerary;
import raydel.isasi.shopping.pojo.Passenger;
import raydel.isasi.shopping.repository.FlyingTicketRepository;
import raydel.isasi.shopping.repository.IFlyingTicket;

import java.util.List;
import java.util.Map;

@Service
public class FlyingServiceImpl implements IFlyingTicket {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlyingServiceImpl.class);
    @Autowired
    private FlyingTicketRepository flyingTicketRepository;


    @Override
    public FlyingTicket saveFlyingTicket(Map<String, Object> flyingTicket) throws Exception {
        FlyingTicket ticket = new FlyingTicket();
        if (flyingTicket != null) {

            if (flyingTicket.get("passengerList") != null) {
                List<Passenger> passengerList = (List<Passenger>) flyingTicket.get("passengerList");

            /*    List<Passenger> updatedPassengerList = passengerList.stream().map(p -> {

                    p.setFlyingTicket(ticket);
                    return p;
                }).collect(Collectors.toList());*/


                ticket.setPassengerList( passengerList);
            }

            if (flyingTicket.get("itinerary") != null) {

                Map<String, Object> itineraryMap = (Map<String, Object>) flyingTicket.get("itinerary");
                Itinerary itinerary = new Itinerary();
                itinerary.setDestiny(String.valueOf(itineraryMap.get("destiny")));
          //      itinerary.setFlyingTicket(ticket);
                itinerary.setOrigin(String.valueOf(itineraryMap.get("origin")));
                itinerary.setFlyingNumber(String.valueOf(itineraryMap.get("flyingNumber")));


                ticket.setItinerary(itinerary);
            }

        }

        return flyingTicketRepository.save(ticket);
    }

    @Override
    public FlyingTicket updateFlyingTicket(Map<String, Object> flyingTicket) throws Exception {
        FlyingTicket ticket = new FlyingTicket();
        if (flyingTicket != null) {

            if (flyingTicket.get("passengerList") != null) {
                List<Passenger> passengerList = (List<Passenger>) flyingTicket.get("passengerList");
                ticket.setPassengerList(passengerList);
            }

            if (flyingTicket.get("itinerary") != null) {
                Itinerary itinerary = (Itinerary) flyingTicket.get("itinerary");
                ticket.setItinerary(itinerary);
            }

        }

        return flyingTicketRepository.save(ticket);
    }
}
