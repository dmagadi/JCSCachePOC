package poc.cache;

import java.util.UUID;
import java.util.logging.Logger;
import org.apache.commons.jcs.JCS;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import javax.ejb.Singleton;
import javax.ws.rs.QueryParam;
import org.apache.commons.jcs.access.CacheAccess;
import org.apache.commons.lang.StringUtils;

/**
 * Resource that handles one way asynchronous chatting based on consumes
 * produces mechanism.
 * <p/>
 * Message is sent (produces) using POST http method handled by
 * {@link #postMessage(javax.ws.rs.container.AsyncResponse, String)} and this
 * message is received (consumed) by GET http method handled by {@link #getMessage(javax.ws.rs.container.AsyncResponse,
 * String)}.
 * <p/>
 * This implementation of produces consumes mechanism does not keep a queue of
 * messages but a queue of responses waiting for messages. Message is always
 * delivered directly from thread handling posting of message to the thread
 * handling receiving of message. Request processing must be therefore
 * synchronized by blocking threads. In order to save server resources the
 * original threads are returned to the container.
 *
 * @author Miroslav Fuksa (miroslav.fuksa at oracle.com)
 *
 */
@Path("param")
@Singleton
public class ParamResource {

    private final CacheAccess<String, String> cache;

    public ParamResource() {
        this.cache = JCS.getInstance("default");
    }

    @GET
    public String getParams(final @QueryParam("param-name") String requestId) {
        
        long startMillis = System.currentTimeMillis();
        
        String value = cache.get(requestId);
        
        
        if (StringUtils.isEmpty(value)) {
            value = UUID.randomUUID().toString();
            cache.put(requestId, value);
        }
        
        Logger.getLogger("Perf").info("Time taken = " + (System.currentTimeMillis() - startMillis) +  " ms" );
        
        return value;
    }

}
