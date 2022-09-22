package surveyservice.response.model;

import lombok.*;
import surveyservice.survey.model.Survey;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "RESPONSE")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "response_sequence")
    @SequenceGenerator(name = "response_sequence", sequenceName = "SEQ_RESPONSE_ID", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RESPONDENT_ID")
    private Respondent respondent;

    @OneToMany(mappedBy = "response", cascade = CascadeType.ALL)
    private List<Answer> answerList;

    @ManyToOne
    @JoinColumn(name = "SURVEY_ID")
    private Survey survey;

    public List<Answer> getAnswerList() {
        return this.answerList == null ? List.of() : this.answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        answerList.forEach(answer -> answer.setResponse(this));
        this.answerList = answerList;
    }
}
