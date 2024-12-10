package emp.EMSystem.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import emp.EMSystem.model.OurUsers;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRes {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private  String refreshToken;
    private String name;
    private String city;
    private  String email;
    private String  role;
    private String  password;
    private OurUsers ourUsers;
    private List<OurUsers> ourUsersList;

    public void setExpirationTime(String s) {

    }
}
