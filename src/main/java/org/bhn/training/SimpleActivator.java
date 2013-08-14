package org.bhn.training;

import org.bhn.training.commands.GreeterCommands;
import org.bhn.training.impl.SimpleStringGreeterImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.util.Hashtable;

public class SimpleActivator implements BundleActivator
{
    ServiceRegistration registration;
    ServiceRegistration command;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
      Hashtable props = new Hashtable();
      props.put("osgi.command.scope","tutorial");
      props.put("osgi.command.function",new String[] { "greet" });

      Hashtable noprops = new Hashtable();

        registration = bundleContext
              .registerService(org.bhn.training.api.Greeter.class.getName(),
              new SimpleStringGreeterImpl(),noprops);
        command = bundleContext.registerService(org.bhn.training.commands.GreeterCommands.class.getName(),
                new GreeterCommands(),props);

    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
      registration.unregister();
    }
}
