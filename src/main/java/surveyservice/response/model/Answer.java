package surveyservice.response.model;

import lombok.*;
import surveyservice.question.model.Choice;
import surveyservice.question.model.Question;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "ANSWER")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_sequence")
    @SequenceGenerator(name = "answer_sequence", sequenceName = "SEQ_ANSWER_ID", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "ID")
    private Question question;

    @OneToOne
    @JoinColumn(name = "CHOICE_ID", referencedColumnName = "ID")
    private Choice choice;

    @ManyToOne
    @JoinColumn(name = "RESPONSE_ID", referencedColumnName = "ID")
    private Response response;
}
