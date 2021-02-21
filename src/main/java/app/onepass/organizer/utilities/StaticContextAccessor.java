package app.onepass.organizer.utilities;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

// DEBUG
@Component
public class StaticContextAccessor {

	private static StaticContextAccessor instance;

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void registerInstance() {
		instance = this;
	}

	public static <T> T getBean(Class<T> clazz) {
		return instance.applicationContext.getBean(clazz);
	}

}