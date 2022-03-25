package raydel.isasi.shopping.pojo;

import java.util.Map;

public class SuccessPersistenceResponse {

    private Map<String, Object> flyingTicket;
    private String token;

    public SuccessPersistenceResponse(Map<String, Object> flyingTicket, String updated_token) {
        this.flyingTicket = flyingTicket;
        this.token = updated_token;
    }

    public Map<String, Object> getFlyingTicket() {
        return flyingTicket;
    }

    public void setFlyingTicket(Map<String, Object> flyingTicket) {
        this.flyingTicket = flyingTicket;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
