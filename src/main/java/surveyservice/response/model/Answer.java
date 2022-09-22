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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CHOICE_ID")
    private Choice choice;

    @ManyToOne
    @JoinColumn(name = "RESPONSE_ID")
    private Response response;


}
