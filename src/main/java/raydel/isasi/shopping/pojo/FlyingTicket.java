package raydel.isasi.shopping.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "FlyingTicket")
public class FlyingTicket implements Serializable {


    @Id
    private BigInteger id;


    private Itinerary itinerary;


    private List<Passenger> passengerList = new ArrayList<>();

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
