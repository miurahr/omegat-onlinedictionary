package tokyo.northside.omegat.utils;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.util.Map;

public final class QueryUtil {

    private QueryUtil() { }

    public static String queryPost(final String queryUrl, final Map<String, Object> header, final String json)
            throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(queryUrl);
            header.forEach(httpPost::addHeader);
            StringEntity stringEntity = new StringEntity(json);
            httpPost.setEntity(stringEntity);
            return httpclient.execute(httpPost, responseHandler);
        }
    }

    public static String query(final String queryUrl, final Map<String, Object> header) throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(queryUrl);
            header.forEach(httpGet::addHeader);
            return httpclient.execute(httpGet, responseHandler);
        }
    }

    private static final HttpClientResponseHandler<String> responseHandler = response -> {
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

}
