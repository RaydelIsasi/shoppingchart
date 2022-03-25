package raydel.isasi.shopping.pojo;


import javax.persistence.*;
import java.io.Serializable;

@Table(name = "Itinerary")
@Entity
public class Itinerary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "itinerary")
    private FlyingTicket flyingTicket;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FlyingTicket getFlyingTicket() {
        return flyingTicket;
    }

    public void setFlyingTicket(FlyingTicket flyingTicket) {
        this.flyingTicket = flyingTicket;
    }

    public String getFlyingNumber() {
        return flyingNumber;
    }

    public void setFlyingNumber(String flyingNumber) {
        this.flyingNumber = flyingNumber;
    }

    @Column(name = "origin", nullable = false)
    private String origin;
    @Column(name = "destiny", nullable = false)
    private String destiny;
    @Column(name = "flyingNumber", nullable = false)
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
