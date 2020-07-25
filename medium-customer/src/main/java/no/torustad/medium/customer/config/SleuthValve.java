package no.torustad.medium.customer.config;

import java.io.IOException;
import javax.servlet.ServletException;

import org.apache.catalina.Valve;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;
import org.apache.tomcat.util.buf.MessageBytes;
import org.apache.tomcat.util.http.MimeHeaders;
import org.springframework.stereotype.Component;

import brave.Span;
import brave.Tracer;

@Component
class SleuthValve extends ValveBase {
    
    private final Tracer tracer;

    public SleuthValve(Tracer tracer) {
        this.tracer = tracer;
        System.err.println("++++++++++++ SleuthValve()");
        System.err.println("++++++++++++ SleuthValve():"+tracer.toString()+"."+tracer.hashCode()+".");
    }

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        System.err.println("++++++++++++ SleuthValve():invoke():"+tracer.toString()+"."+tracer.hashCode()+".");
        enrichWithSleuthHeaderWhenMissing(tracer, request);
        Valve next = getNext();
        if (null==next) {
            return;
        }
        next.invoke(request, response);
    }

    private static void enrichWithSleuthHeaderWhenMissing(final Tracer tracer, final Request request) {
        String header = request.getHeader("X-B3-TraceId");
        System.err.println("++++++++++++ SleuthValve():enrichWithSleuthHeaderWhenMissing():");
        if (null == header) {
            System.err.println("++++++++++++ SleuthValve():enrichWithSleuthHeaderWhenMissing(): null==header: ");
            org.apache.coyote.Request coyoteRequest = request.getCoyoteRequest();
            // MimeHeaders mimeHeaders = coyoteRequest.getMimeHeaders();
            // Span span = tracer.newTrace();
            // addHeader(mimeHeaders, "X-B3-TraceId", span.context().traceIdString());
            // addHeader(mimeHeaders, "X-B3-SpanId", span.context().traceIdString());
        } else {
            System.err.println("++++++++++++ SleuthValve():enrichWithSleuthHeaderWhenMissing(): null!=heade...");
        }
    }

    private static void addHeader(MimeHeaders mimeHeaders, String traceIdName, String traceValue) {
        System.err.println("++++++++++++ SleuthValve():addHeader");;
        MessageBytes messageBytes = mimeHeaders.addValue(traceIdName);
        messageBytes.setString(traceValue);
    }
}