package com.sanss.testweb.bundle;

import com.sanss.testweb.rest.BundleRestService;
import org.osgi.framework.*;

import java.util.HashMap;
import java.util.Map;

public class MyActivator implements BundleActivator, BundleListener, ServiceListener {
    private static Map<String, Integer> map = new HashMap<String, Integer>();

    static void init() {
        map.put("cn.com.sanss.petstore.model", 1);
        map.put("cn.com.sanss.petstore.server", 2);
        map.put("cn.com.sanss.petstore.test", 3);
        map.put("cn.com.sanss.test.user-model", 4);
        map.put("cn.com.sanss.test.user-server", 5);

    }

    public static Map<Integer, Integer> bundletype = new HashMap<Integer, Integer>();

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("bundle start");
        init();
        Bundle[] bundles = context.getBundles();
        for (Bundle b : bundles) {

            String bundlename = b.getSymbolicName();


            Integer tab = map.get(bundlename);
//            System.out.print(tab);
            if (null != tab) {
                ServiceReference[] refs = b.getRegisteredServices();

                System.out.println("---------service1-------------------");
                if (null != refs) {
                    for (ServiceReference ref : refs) {
                        for (String key : ref.getPropertyKeys()) {
                            System.out.println(key + "---" + ref.getProperty(key));
                        }
                    }
                }
                System.out.println("---------service2-------------------");


                bundletype.put(tab, b.getState());
            }
            System.out.println("bundletype:" + bundletype);
           /* System.out.println("bundleId:" + b.getBundleId());
            System.out.println("bundleType:" + b.getState());
            System.out.println("bundleName:" + b.getSymbolicName());*/
        }

        System.out.println(bundletype);
        context.addBundleListener(this);
        context.addServiceListener(this);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }

    @Override
    public void bundleChanged(BundleEvent event) {
        String name = event.getBundle().getSymbolicName();
        //Integer key = map.get(name);
//        bundletype.put(key, event.getType());

        System.out.println("bundleId:" + event.getBundle().getBundleId());
        System.out.println("bundleType:" + event.getType());
        System.out.println("bundleName:" + event.getBundle().getSymbolicName());

    }

    @Override
    public void serviceChanged(ServiceEvent event) {
        System.out.println("=======================");
        System.out.println(event.getType());
        System.out.println(event.getServiceReference().getBundle().getSymbolicName());
    }
}
