package com.puresoltechnologies.commons.osgi;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is an abstract Activator implementation which should be used by all
 * PureSol Technologies' OSGi bundles. This Activator logs the start and stop
 * and provides some simple functionality like {@link BundleContext} retrieval
 * and automated de-registration of registered services.
 * 
 * @author Rick-Rainer Ludwig
 */
public abstract class AbstractActivator implements BundleActivator {

    /**
     * This field keeps the SLF4J {@link Logger} to log messages.
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * This field holds the current bundle context. This field is static due to the
     * fact that the {@link BundleActivator} is treaded like a singleton and is only
     * called once per state.
     */
    private BundleContext context = null;

    /**
     * This field keeps the registered services. This field is needed for automated
     * de-registration on bundle stop.
     */
    private final List<ServiceRegistration<?>> serviceRegistrations = new ArrayList<ServiceRegistration<?>>();

    /**
     * This is the default constructor.
     */
    public AbstractActivator() {
	super();
	logger.debug("Bundle with base package " + getClass().getPackage().getName() + " was initialized.");
    }

    @Override
    public void start(BundleContext context) throws Exception {
	logger.info("Starting bundle '" + context.getBundle().getSymbolicName() + "'...");
	if (this.context != null) {
	    throw new RuntimeException("Bundle was already started.");

	}
	this.context = context;
    }

    @Override
    public void stop(BundleContext context) throws Exception {
	logger.info("Stopping bundle '" + context.getBundle().getSymbolicName() + "'...");
	if (this.context == null) {
	    throw new RuntimeException("Bundle was not started, yet.");
	}
	this.context = null;
	deregisterServices();
    }

    /**
     * This method de-registers all former registered services.
     */
    private void deregisterServices() {
	Iterator<ServiceRegistration<?>> serviceIterator = serviceRegistrations.iterator();
	while (serviceIterator.hasNext()) {
	    ServiceRegistration<?> serviceReference = serviceIterator.next();
	    serviceReference.unregister();
	    serviceIterator.remove();
	}
    }

    /**
     * This method returns the current bundle context.
     * 
     * @return A {@link BundleContext} is returned containing the current context.
     *         The return value is expected to be not null due to this class is
     *         called during bundle startup. If the bundle was not started, the
     *         return value is null.
     */
    public final BundleContext getBundleContext() {
	if (context == null) {
	    throw new RuntimeException("Bundle was not activated, yet.");
	}
	return context;
    }

    /**
     * This method should be used to register services. Services registered with
     * this method auto deregistered during bundle stop.
     * 
     * @param iface
     *            is the interface of the service to be used for later retrieval.
     * @param instance
     *            is an instance of the object to be registered as service for
     *            interface iface.
     * @param <T>
     *            is the service interface.
     * @return A {@link ServiceRegistration} is returned of the newly registered
     *         service.
     */
    public final <T> ServiceRegistration<?> registerService(Class<T> iface, T instance) {
	Hashtable<String, String> properties = new Hashtable<String, String>();
	return registerService(iface, instance, properties);
    }

    /**
     * This method should be used to register services. Services registered with
     * this method auto de-registered during bundle stop.
     * 
     * @param iface
     *            is the interface of the service to be used for later retrieval.
     * @param instance
     *            is an instance of the object to be registered as service for
     *            interface iface.
     * @param dictionary
     *            is a {@link Dictionary} of settings to be added to the service
     *            registration.
     * @param <T>
     *            is the service interface.
     * @return A {@link ServiceRegistration} is returned of the newly registered
     *         service.
     */
    public final <T> ServiceRegistration<?> registerService(Class<T> iface, T instance,
	    Dictionary<String, String> dictionary) {
	logger.info(
		"Register service '{}' for interface '{}' (context='" + context.getBundle().getSymbolicName() + "').",
		instance.getClass().getName(), iface.getName());
	ServiceRegistration<?> serviceRegistration = context.registerService(iface, instance, dictionary);
	serviceRegistrations.add(serviceRegistration);
	return serviceRegistration;
    }
}
