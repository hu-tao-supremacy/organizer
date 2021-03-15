package app.onepass.organizer.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.google.protobuf.Timestamp;

import app.onepass.apis.EventDuration;
import app.onepass.organizer.messages.EventDurationMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "event_duration")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDurationEntity implements BaseEntity<EventDurationMessage, EventDurationEntity> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private long eventId;
	@NotNull
	private Timestamp start;
	@NotNull
	private Timestamp finish;

	@Override
	public EventDurationMessage parseEntity() {

		EventDuration eventDuration = EventDuration.newBuilder()
				.setId(id)
				.setEventId(eventId)
				.setStart(start)
				.setFinish(finish)
				.build();

		return new EventDurationMessage(eventDuration);
	}
}