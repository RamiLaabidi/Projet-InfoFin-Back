package tn.esprit.tradingback.DTO;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String phone;
    private String location;
    private String description;
    private String age;
    private String profession;
}
