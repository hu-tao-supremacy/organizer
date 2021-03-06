package app.onepass.organizer.messages;

import app.onepass.apis.EventDuration;
import app.onepass.organizer.entities.EventDurationEntity;
import app.onepass.organizer.utilities.TypeUtil;
import lombok.Getter;

public class EventDurationMessage implements BaseMessage<EventDurationMessage, EventDurationEntity> {

	@Getter
	EventDuration eventDuration;

	public EventDurationMessage(EventDuration eventDuration) {
		this.eventDuration = eventDuration;
	}

	@Override
	public EventDurationEntity parseMessage() {

		java.sql.Timestamp startTime = TypeUtil.toSqlTimestamp(eventDuration.getStart());
		java.sql.Timestamp finishTime = TypeUtil.toSqlTimestamp(eventDuration.getFinish());

		return EventDurationEntity.builder()
				.id(eventDuration.getId())
				.eventId(eventDuration.getEventId())
				.start(eventDuration.hasStart() ? startTime : null)
				.finish(eventDuration.hasFinish() ? finishTime : null)
				.build();
	}
}