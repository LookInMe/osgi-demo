package sanss.bmt.test.rest.listen;

import org.osgi.framework.*;

import java.util.HashMap;
import java.util.Map;

public class MyActivator implements BundleActivator, BundleListener, ServiceListener {
    private static Map<String, String> map = new HashMap<String, String>();

    static void init() {
        map.put("sanss.bmt.test.rest-xj", "xj");
        map.put("sanss.bmt.test.rest-flow", "flow");

    }

    public static Map<String, Integer> bundletype = new HashMap<String, Integer>();

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("bundle start");
        init();
        Bundle[] bundles = context.getBundles();
        for (Bundle b : bundles) {

            String bundlename = b.getSymbolicName();


            String tab = map.get(bundlename);
            if (null != tab) {
                ServiceReference[] refs = b.getRegisteredServices();
                boolean serviceready = false;
                System.out.println("---------service1-------------------");
                if (null != refs) {
                    /*for (ServiceReference ref : refs) {
                        for (String key : ref.getPropertyKeys()) {
                            System.out.println(key + "---" + ref.getProperty(key));
                        }
                    }*/
                    serviceready = true;

                }
                System.out.println("---------service2-------------------");
                if (b.getState() == 32 && serviceready) {
                    bundletype.put(tab, 1);
                } else {
                    bundletype.put(tab, 2);
                }

            }
        }

        System.out.println("bundletype:" + bundletype);
        context.addBundleListener(this);
        context.addServiceListener(this);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }

    @Override
    public void bundleChanged(BundleEvent event) {
        String name = event.getBundle().getSymbolicName();
        String key = map.get(name);
        if (null == key)
            return;
        boolean serviceready = false;
        ServiceReference[] refs = event.getBundle().getRegisteredServices();
        if (null != refs)
            serviceready = true;

        if (event.getType() == 2 && serviceready)
            bundletype.put(key, 1);
        else
            bundletype.put(key, 2);

        System.out.println("bundleId:" + event.getBundle().getBundleId());
        System.out.println("bundleType:" + event.getType());
        System.out.println("bundleName:" + event.getBundle().getSymbolicName());

    }

    @Override
    public void serviceChanged(ServiceEvent event) {
        System.out.println("=======================");
        System.out.println(event.getType());
        System.out.println(event.getServiceReference().getBundle().getSymbolicName());

        String name = event.getServiceReference().getBundle().getSymbolicName();
        String key = map.get(name);
        if (null == key)
            return;
        if (event.getType() == 1)
            bundletype.put(key, 1);
        else
            bundletype.put(key, 2);

    }
}
