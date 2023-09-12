package nl.strohalm.cyclos.utils.hibernate;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PostCollectionRecreateEventListener;
import org.hibernate.event.spi.PostCollectionRemoveEventListener;
import org.hibernate.event.spi.PostCollectionUpdateEventListener;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateEventWiring {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private WriteDetectEventListener writeDetectEventListener;

	@PostConstruct
	public void registerListeners() {
		final EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry()
				.getService(EventListenerRegistry.class);
		registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener((PostUpdateEventListener) writeDetectEventListener);
		registry.getEventListenerGroup(EventType.POST_INSERT).appendListener((PostInsertEventListener) writeDetectEventListener);
		registry.getEventListenerGroup(EventType.POST_DELETE).appendListener((PostDeleteEventListener) writeDetectEventListener);
		registry.getEventListenerGroup(EventType.POST_COLLECTION_RECREATE).appendListener((PostCollectionRecreateEventListener) writeDetectEventListener);
		registry.getEventListenerGroup(EventType.POST_COLLECTION_REMOVE).appendListener((PostCollectionRemoveEventListener) writeDetectEventListener);
		registry.getEventListenerGroup(EventType.POST_COLLECTION_UPDATE).appendListener((PostCollectionUpdateEventListener) writeDetectEventListener);
	}
}
