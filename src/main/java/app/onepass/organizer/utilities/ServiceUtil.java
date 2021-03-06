package app.onepass.organizer.utilities;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.protobuf.Empty;

import app.onepass.apis.HasPermissionRequest;
import app.onepass.apis.Permission;
import app.onepass.organizer.entities.BaseEntity;
import app.onepass.organizer.entities.EventEntity;
import app.onepass.organizer.messages.BaseMessage;
import app.onepass.organizer.repositories.EventRepository;
import app.onepass.organizer.services.AccountService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class ServiceUtil {

	@Deprecated
	public static <M extends BaseMessage<M, E>, E extends BaseEntity<M, E>> boolean saveEntity(BaseMessage<M, E> message,
			JpaRepository<E, Integer> repository) {

		E entity = message.parseMessage();

		repository.save(entity);

		return true;
	}

	@Deprecated
	public static <M extends BaseMessage<M, E>, E extends BaseEntity<M, E>> boolean deleteEntity(int id,
			JpaRepository<E, Integer> repository) {

		E entity;

		try {

			entity = repository.findById(id).orElseThrow(IllegalArgumentException::new);

		} catch (IllegalArgumentException illegalArgumentException) {

			return false;
		}

		repository.delete(entity);

		return true;
	}

	public static <T> void returnInvalidArgumentError(StreamObserver<T> responseObserver, String description) {

		responseObserver.onError(Status.INVALID_ARGUMENT.withDescription(description).asException());
	}

	public static <T> void returnPermissionDeniedError(StreamObserver<T> responseObserver) {

		responseObserver.onError(
				Status.PERMISSION_DENIED.withDescription("The user has no permission to execute the specified operation.")
						.asException());
	}

	public static <T> void returnObject(StreamObserver<T> responseObserver, T object) {

		responseObserver.onNext(object);

		responseObserver.onCompleted();
	}

	public static void returnEmpty(StreamObserver<Empty> responseObserver) {

		responseObserver.onNext(Empty.newBuilder().build());

		responseObserver.onCompleted();
	}

	public static HasPermissionRequest createHasPermissionRequest(int userId, int organizationId, Permission permission) {

		return HasPermissionRequest.newBuilder()
				.setUserId((int) userId)
				.setOrganizationId((int) organizationId)
				.setPermissionNameValue(permission.getNumber())
				.build();
	}

	public static <T> int getOrganizationIdFromEventId(EventRepository eventRepository, int eventId) {

		EventEntity eventEntity;

		eventEntity = eventRepository.findById(eventId).orElseThrow(IllegalArgumentException::new);

		return eventEntity.getOrganizationId();
	}

	public static <T> boolean hasValidParameters(AccountService accountService, EventRepository eventRepository,
			StreamObserver<T> responseObserver, int userId, int eventId, Permission permission) {

		int organizationId;

		try {

			organizationId = ServiceUtil.getOrganizationIdFromEventId(eventRepository, eventId);

		} catch (IllegalArgumentException exception) {

			ServiceUtil.returnInvalidArgumentError(responseObserver, "Cannot find event from given ID.");

			return false;
		}

		HasPermissionRequest hasPermissionRequest = ServiceUtil.createHasPermissionRequest(userId, organizationId, permission);

		if (!accountService.hasPermission(hasPermissionRequest).getValue()) {

			ServiceUtil.returnPermissionDeniedError(responseObserver);

			return false;
		}

		return true;
	}
}
