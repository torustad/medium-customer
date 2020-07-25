package no.torustad.medium.customer.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccessLogSleuthConfiguration implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
    
    private SleuthValve sleuthValve;

    public AccessLogSleuthConfiguration(SleuthValve sleuthValve) {
        this.sleuthValve = sleuthValve;
        System.err.println("++++++++++++ AccessLogSleuthConfiguration()");
        System.err.println("++++++++++++ AccessLogSleuthConfiguration():::"+sleuthValve.toString()+".");
        
    }

    @Override
    public void customize(TomcatServletWebServerFactory tomcatServletWebServerFactory) {
        System.err.println("++++++++++++ AccessLogSleuthConfiguration::customize(); ENTER");
        tomcatServletWebServerFactory.addContextCustomizers(context -> {
            //context.getPipeline().addValve(sleuthValve);
            System.err.println("++++++++++++ AccessLogSleuthConfiguration:: i addContextCustomizers...");
            if (null != context) {
                System.err.println("++++++++++++ AccessLogSleuthConfiguration:: i addContextCustomizers... -- context != null");
                System.err.println("++++++++++++ AccessLogSleuthConfiguration:: i addContextCustomizers..."+context.getPipeline().toString()+".");
                System.err.println("++++++++++++ AccessLogSleuthConfiguration:: i addContextCustomizers..."+context.getPipeline().getValves().length+".");
                if (sleuthValve == null) {
                    System.err.println("++++++++++++ AccessLogSleuthConfiguration:: i addContextCustomizers...==null"+sleuthValve.toString()+".");
                } else {
                    System.err.println("++++++++++++ AccessLogSleuthConfiguration:: i addContextCustomizers...!=null - Domain="+sleuthValve.getDomain()+".");
                    System.err.println("++++++++++++ AccessLogSleuthConfiguration:: i addContextCustomizers...!=null - DomainInternal="+sleuthValve.getDomainInternal()+".");
                    //System.err.println("++++++++++++ AccessLogSleuthConfiguration:: i addContextCustomizers...!=null"+sleuthValve.getObjectNameKeyProperties()+".");
                    //System.err.println("++++++++++++ AccessLogSleuthConfiguration:: i addContextCustomizers...!=null"+sleuthValve.getStateName()+".");
                    System.err.println("++++++++++++ AccessLogSleuthConfiguration:: i addContextCustomizers...!=null - class="+sleuthValve.getClass()+".)");
                    System.err.println("++++++++++++ AccessLogSleuthConfiguration:: i addContextCustomizers...!=null - Container="+sleuthValve.getContainer()+".");
                }

            } else {
                System.err.println("++++++++++++ AccessLogSleuthConfiguration:: i addContextCustomizers... -- context==null");
            }
        });
        System.err.println("++++++++++++ AccessLogSleuthConfiguration::customize(): LEAVE");
    }
}