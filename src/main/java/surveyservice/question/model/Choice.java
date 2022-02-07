package surveyservice.question.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "CHOICE")
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "choice_sequence")
    @SequenceGenerator(name = "choice_sequence", sequenceName = "SEQ_CHOICE_ID", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "ID")
    private Question question;

}
