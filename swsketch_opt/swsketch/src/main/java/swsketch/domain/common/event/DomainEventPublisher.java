package swsketch.domain.common.event;

public interface DomainEventPublisher {
	void publish(DomainEvent event);
}
