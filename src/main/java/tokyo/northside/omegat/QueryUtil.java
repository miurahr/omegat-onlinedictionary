package tokyo.northside.omegat;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.util.Map;

final class QueryUtil {

    private QueryUtil() { }

    public static String query(final String queryUrl, final Map<String, Object> header) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(queryUrl);
            header.forEach(httpGet::addHeader);
            final HttpClientResponseHandler<String> responseHandler = response -> {
                final int status = response.getCode();
                if (status >= HttpStatus.SC_SUCCESS && status < HttpStatus.SC_REDIRECTION) {
                    try (HttpEntity entity = response.getEntity()) {
                        if (entity != null) {
                            return EntityUtils.toString(entity);
                        } else {
                            return null;
                        }
                    } catch (final ParseException ex) {
                        throw new ClientProtocolException(ex);
                    }
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            return httpclient.execute(httpGet, responseHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
