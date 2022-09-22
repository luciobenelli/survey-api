package surveyservice.survey.model;

import lombok.*;
import surveyservice.question.model.Question;
import surveyservice.response.model.Response;
import surveyservice.utils.StatusEnum;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Entity
@Table(name = "SURVEY")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "survey_sequence")
    @SequenceGenerator(name = "survey_sequence", sequenceName = "SEQ_SURVEY_ID", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<Question> questionList;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<Response> responseList;

    public List<Question> getQuestionList() {
        return this.questionList == null ? List.of() : this.questionList;
    }

    public List<Response> getResponseList() {
        return this.responseList == null ? List.of() : this.responseList;
    }

    public void setQuestionList(List<Question> questionList) {
        questionList.forEach(question -> question.setSurvey(this));
        this.questionList = questionList;
    }

    public void setResponseList(List<Response> responseList) {
        responseList.forEach(response -> response.setSurvey(this));
        this.responseList = responseList;
    }

    public void addQuestion(Question question) {
        question.setSurvey(this);
        this.questionList.add(question);
    }
}
