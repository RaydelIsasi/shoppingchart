package raydel.isasi.shopping.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "Passenger ")
@Entity
public class Passenger implements Serializable {

    @Id
    @Column(name = "ID")
    private String ID;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "passportNumber")
    private String passportNumber;

    @Column(name = "seatNumber")
    private String seatNumber;

    @OneToOne(mappedBy = "itinerary")


    @ManyToOne
    @JoinColumn(name = "flyingticket_id", nullable = false)
    private FlyingTicket flyingTicket;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FlyingTicket getFlyingTicket() {
        return flyingTicket;
    }

    public void setFlyingTicket(FlyingTicket flyingTicket) {
        this.flyingTicket = flyingTicket;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isHasLuggagge() {
        return hasLuggagge;
    }

    public void setHasLuggagge(boolean hasLuggagge) {
        this.hasLuggagge = hasLuggagge;
    }

    @Column(name = "hasLuggagge")
    private boolean hasLuggagge;


}