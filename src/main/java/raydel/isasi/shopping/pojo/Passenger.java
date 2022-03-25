package raydel.isasi.shopping.pojo;

import java.io.Serializable;

public class Passenger implements Serializable {

    private String ID;


    private String name;

    private String lastName;

    private String passportNumber;

    private String seatNumber;

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