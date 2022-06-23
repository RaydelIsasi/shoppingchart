package raydel.isasi.shopping.pojo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Pattern;
import java.math.BigInteger;
import java.util.Date;

@Document(collection = "users")
public class User {

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger  id) {
        this.id = id;
    }

    @Id
    private BigInteger  id;

    private String name;

    private String email;


    @Pattern(regexp = "[A-Z][a-z]+[0-9]{2}", message = "Wrong  password")
    private String password;

    private Date created;


    private Date modified;


    private Date last_login;


    private Boolean isactive;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }


    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }


}


