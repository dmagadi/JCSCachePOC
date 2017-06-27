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
