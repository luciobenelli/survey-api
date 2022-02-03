package survey.model;

import lombok.*;
import survey.utils.StatusEnum;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
public class Survey {

    @Id
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

}
