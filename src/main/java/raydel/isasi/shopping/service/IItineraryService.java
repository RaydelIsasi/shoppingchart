package raydel.isasi.shopping.service;

import raydel.isasi.shopping.pojo.Itinerary;

import javax.servlet.ServletException;
import java.io.IOException;

public interface IItineraryService {

    public String saveItineraryData(Itinerary itinerary, Object request) throws IOException, ServletException, Exception;
}
