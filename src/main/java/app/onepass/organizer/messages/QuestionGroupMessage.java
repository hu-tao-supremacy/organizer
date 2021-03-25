package app.onepass.organizer.messages;

import app.onepass.apis.Question;
import app.onepass.apis.QuestionGroup;
import app.onepass.organizer.entities.QuestionEntity;
import app.onepass.organizer.entities.QuestionGroupEntity;
import lombok.Getter;

public class QuestionGroupMessage implements BaseMessage<QuestionGroupMessage, QuestionGroupEntity> {

	public QuestionGroupMessage(QuestionGroup questionGroup) { this.questionGroup = questionGroup; }

	@Getter
	QuestionGroup questionGroup;

	@Override
	public QuestionGroupEntity parseMessage() {

		return QuestionGroupEntity.builder()
				.id(questionGroup.getId())
				.eventId(questionGroup.getEventId())
				.type(questionGroup.getType().toString())
				.order(questionGroup.getOrder())
				.title(questionGroup.getTitle())
				.build();
	}
}