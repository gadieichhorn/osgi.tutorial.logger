package osgi.tutorial.logger.foo.provider;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(name = "osgi.tutorial.logger.bar", immediate=true)
public class BarImpl {

	private static final Logger logger = LoggerFactory.getLogger(BarImpl.class);

	public BarImpl() {
		logger.error("Error");
		logger.warn("Warn");
		logger.info("Info");
		logger.debug("Debug");
		logger.trace("Trace");	}
}
