package Garuda.com.DNC.api.models;

import jakarta.validation.constraints.*;

public class Registereduser {





    @NotBlank
    @Size(min=5)
    private String password;

    @NotBlank
    @Size(min=5)
    @Email
    private String email;

    @NotBlank
    @Size(min=5)
    private String user;

    public String getMobile() {
        return mobile;
    }



    private String mobile;


    public String getUser() {
        return user;
    }








    public String getPassword() {
        return password;
    }



    public String getEmail() {
        return email;
    }


}
