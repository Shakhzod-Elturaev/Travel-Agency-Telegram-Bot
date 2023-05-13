package uz.travelAgency.country.entity;

import lombok.*;
import uz.travelAgency.user.entity.UserStep;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CountryEntity {
    private String name;
    private UserStep continent;
}
