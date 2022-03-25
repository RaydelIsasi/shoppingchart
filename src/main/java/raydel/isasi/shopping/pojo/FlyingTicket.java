package raydel.isasi.shopping.pojo;

import java.io.Serializable;
import java.util.List;

public class FlyingTicket implements Serializable {
    private Itinerary itinerary;
    private List<Passenger> passengerList;

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }
}
