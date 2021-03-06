package app.onepass.organizer.services;

import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.google.protobuf.BoolValue;
import com.google.protobuf.Empty;

import app.onepass.apis.AddQuestionGroupsRequest;
import app.onepass.apis.AddQuestionsRequest;
import app.onepass.apis.AnswerListResponse;
import app.onepass.apis.CheckInRequest;
import app.onepass.apis.CreateEventRequest;
import app.onepass.apis.CreateLocationRequest;
import app.onepass.apis.CreateOrganizationRequest;
import app.onepass.apis.CreateTagRequest;
import app.onepass.apis.Event;
import app.onepass.apis.EventDurationListResponse;
import app.onepass.apis.EventTagListResponse;
import app.onepass.apis.GenerateTicketRequest;
import app.onepass.apis.GetObjectByIdRequest;
import app.onepass.apis.GetObjectByNameRequest;
import app.onepass.apis.HasEventRequest;
import app.onepass.apis.Location;
import app.onepass.apis.LocationListResponse;
import app.onepass.apis.Organization;
import app.onepass.apis.OrganizationListResponse;
import app.onepass.apis.OrganizerServiceGrpc;
import app.onepass.apis.QuestionGroupListResponse;
import app.onepass.apis.QuestionListResponse;
import app.onepass.apis.RemoveEventRequest;
import app.onepass.apis.RemoveLocationRequest;
import app.onepass.apis.RemoveOrganizationRequest;
import app.onepass.apis.RemoveQuestionGroupsRequest;
import app.onepass.apis.RemoveQuestionsRequest;
import app.onepass.apis.Tag;
import app.onepass.apis.UpdateEventDurationsRequest;
import app.onepass.apis.UpdateEventRequest;
import app.onepass.apis.UpdateLocationRequest;
import app.onepass.apis.UpdateOrganizationRequest;
import app.onepass.apis.UpdateRegistrationRequestRequest;
import app.onepass.apis.UpdateTagRequest;
import app.onepass.apis.UpdateUsersInOrganizationRequest;
import app.onepass.apis.UserEvent;
import app.onepass.apis.UserListResponse;
import app.onepass.apis.UserOrganizationListResponse;
import app.onepass.organizer.utilities.ExceptionCatcher;
import io.grpc.stub.StreamObserver;

@GRpcService
public class BaseService extends OrganizerServiceGrpc.OrganizerServiceImplBase {

	@Autowired
	EventService eventService;

	@Autowired
	OrganizationService organizationService;

	@Autowired
	TagService tagService;

	@Autowired
	QuestionService questionService;

	@Autowired
	PingService pingService;

	@Autowired
	LocationService locationService;

	@Override
	@Transactional
	public void createOrganization(CreateOrganizationRequest request, StreamObserver<Organization> responseObserver) {
		ExceptionCatcher.catcher(organizationService::createOrganization, request, responseObserver);
	}

	@Override
	public void getOrganizations(Empty request, StreamObserver<OrganizationListResponse> responseObserver) {
		ExceptionCatcher.catcher(organizationService::getOrganizations, request, responseObserver);
	}

	@Override
	public void getOrganizationById(GetObjectByIdRequest request, StreamObserver<Organization> responseObserver) {
		ExceptionCatcher.catcher(organizationService::getOrganizationById, request, responseObserver);
	}

	@Override
	public void getUsersInOrganizationById(GetObjectByIdRequest request, StreamObserver<UserListResponse> responseObserver) {
		ExceptionCatcher.catcher(organizationService::getUsersInOrganizationById, request, responseObserver);
	}

	@Override
	@Transactional
	public void updateOrganization(UpdateOrganizationRequest request, StreamObserver<Organization> responseObserver) {
		ExceptionCatcher.catcher(organizationService::updateOrganization, request, responseObserver);
	}

	@Override
	@Transactional
	public void removeOrganization(RemoveOrganizationRequest request, StreamObserver<Organization> responseObserver) {
		ExceptionCatcher.catcher(organizationService::removeOrganization, request, responseObserver);
	}

	@Override
	@Transactional
	public void addUsersToOrganization(UpdateUsersInOrganizationRequest request, StreamObserver<UserOrganizationListResponse> responseObserver) {
		ExceptionCatcher.catcher(organizationService::addUsersToOrganization, request, responseObserver);
	}

	@Override
	@Transactional
	public void removeUsersFromOrganization(UpdateUsersInOrganizationRequest request, StreamObserver<UserOrganizationListResponse> responseObserver) {
		ExceptionCatcher.catcher(organizationService::removeUsersFromOrganization, request, responseObserver);
	}

	@Override
	@Transactional
	public void createEvent(CreateEventRequest request, StreamObserver<Event> responseObserver) {
		ExceptionCatcher.catcher(eventService::createEvent, request, responseObserver);
	}

	@Override
	@Transactional
	public void updateEvent(UpdateEventRequest request, StreamObserver<Event> responseObserver) {
		ExceptionCatcher.catcher(eventService::updateEvent, request, responseObserver);
	}

	@Override
	@Transactional
	public void updateEventDurations(
			UpdateEventDurationsRequest request, StreamObserver<EventDurationListResponse> responseObserver) {
		ExceptionCatcher.catcher(eventService::updateEventDurations, request, responseObserver);
	}

	@Override
	@Transactional
	public void removeEvent(RemoveEventRequest request, StreamObserver<Event> responseObserver) {
		ExceptionCatcher.catcher(eventService::removeEvent, request, responseObserver);
	}

	@Override
	public void updateRegistrationRequest(UpdateRegistrationRequestRequest request, StreamObserver<UserEvent> responseObserver) {
		ExceptionCatcher.catcher(eventService::updateRegistrationRequest, request, responseObserver);
	}

	@Override
	@Transactional
	public void createTag(CreateTagRequest request, StreamObserver<Tag> responseObserver) {
		ExceptionCatcher.catcher(tagService::createTag, request, responseObserver);
	}

	@Override
	public void addTags(UpdateTagRequest request, StreamObserver<EventTagListResponse> responseObserver) {
		ExceptionCatcher.catcher(tagService::addTags, request, responseObserver);
	}

	@Override
	@Transactional
	public void removeTags(UpdateTagRequest request, StreamObserver<EventTagListResponse> responseObserver) {
		ExceptionCatcher.catcher(tagService::removeTags, request, responseObserver);
	}

	@Override
	public void hasEvent(HasEventRequest request, StreamObserver<Event> responseObserver) {
		ExceptionCatcher.catcher(eventService::hasEvent, request, responseObserver);
	}

	@Override
	@Transactional
	public void addQuestionGroups(AddQuestionGroupsRequest request, StreamObserver<QuestionGroupListResponse> responseObserver) {
		ExceptionCatcher.catcher(questionService::addQuestionGroups, request, responseObserver);
	}

	@Override
	@Transactional
	public void removeQuestionGroups(RemoveQuestionGroupsRequest request, StreamObserver<QuestionGroupListResponse> responseObserver) {
		ExceptionCatcher.catcher(questionService::removeQuestionGroups, request, responseObserver);
	}

	@Override
	@Transactional
	public void addQuestions(AddQuestionsRequest request, StreamObserver<QuestionListResponse> responseObserver) {
		ExceptionCatcher.catcher(questionService::addQuestions, request, responseObserver);
	}

	@Override
	@Transactional
	public void removeQuestions(RemoveQuestionsRequest request, StreamObserver<QuestionListResponse> responseObserver) {
		ExceptionCatcher.catcher(questionService::removeQuestions, request, responseObserver);
	}

	@Override
	public void getAnswersByQuestionId(GetObjectByIdRequest request, StreamObserver<AnswerListResponse> responseObserver) {
		ExceptionCatcher.catcher(questionService::getAnswersByQuestionId, request, responseObserver);
	}

	@Override
	public void createLocation(CreateLocationRequest request, StreamObserver<Location> responseObserver) {
		ExceptionCatcher.catcher(locationService::createLocation, request, responseObserver);
	}

	@Override
	public void getLocations(Empty request, StreamObserver<LocationListResponse> responseObserver) {
		ExceptionCatcher.catcher(locationService::getLocations, request, responseObserver);
	}

	@Override
	public void getLocationById(GetObjectByIdRequest request, StreamObserver<Location> responseObserver) {
		ExceptionCatcher.catcher(locationService::getLocationById, request, responseObserver);
	}

	@Override
	public void searchLocationsByName(GetObjectByNameRequest request, StreamObserver<LocationListResponse> responseObserver) {
		ExceptionCatcher.catcher(locationService::searchLocationsByName, request, responseObserver);
	}

	@Override
	public void updateLocation(UpdateLocationRequest request, StreamObserver<Location> responseObserver) {
		ExceptionCatcher.catcher(locationService::updateLocation, request, responseObserver);
	}

	@Override
	public void removeLocation(RemoveLocationRequest request, StreamObserver<Location> responseObserver) {
		ExceptionCatcher.catcher(locationService::removeLocation, request, responseObserver);
	}

	@Override
	public void generateTicket(GenerateTicketRequest request, StreamObserver<UserEvent> responseObserver) {
		ExceptionCatcher.catcher(eventService::generateTicket, request, responseObserver);
	}

	@Override
	public void checkIn(CheckInRequest request, StreamObserver<UserEvent> responseObserver) {
		ExceptionCatcher.catcher(eventService::checkIn, request, responseObserver);
	}

	@Override
	public void ping(Empty request, StreamObserver<BoolValue> responseObserver) {
		pingService.ping(request, responseObserver);
	}
}

