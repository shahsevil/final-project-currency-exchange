package app.dto;

import lombok.Data;

@Data
public class FormReg {
    private String full_name;
    private String email;
    private String password;
    private String confirmPassword;
    private String btnRegister;

}
