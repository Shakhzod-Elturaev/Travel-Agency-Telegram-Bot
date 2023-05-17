package uz.travelAgency.user.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserEntity {

    private String phoneNumber;
    private Long id;
    private UserStep step;
}
