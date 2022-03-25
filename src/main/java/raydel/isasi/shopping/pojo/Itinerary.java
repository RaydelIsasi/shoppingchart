package raydel.isasi.shopping.pojo;


import java.io.Serializable;

public class Itinerary implements Serializable {
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
