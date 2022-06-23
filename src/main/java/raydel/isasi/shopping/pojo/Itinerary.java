package raydel.isasi.shopping.pojo;


import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigInteger;

@Document
public class Itinerary implements Serializable {

    @Id
    private BigInteger  id;




    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger  id) {
        this.id = id;
    }



    public String getFlyingNumber() {
        return flyingNumber;
    }

    public void setFlyingNumber(String flyingNumber) {
        this.flyingNumber = flyingNumber;
    }


    private String origin;

    private String destiny;


    private String flyingNumber;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }
}
