package raydel.isasi.shopping.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigInteger;

@Document
public class Passenger implements Serializable {

    @Id
     private BigInteger id;


    private String name;


    private String lastName;


    private String passportNumber;


    private String seatNumber;




    public BigInteger  getID() {
        return id;
    }

    public void setID(BigInteger  ID) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    private boolean hasLuggagge;


}