package tokyo.northside.omegat;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.omegat.util.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tokyo.northside.omegawiki.Parser;
import tokyo.northside.omegawiki.OmegawikiDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OmegawikiDriver implements IOnlineDictionaryDriver {
    private static final Logger LOGGER = LoggerFactory.getLogger(OmegawikiDriver.class);

    private String endpointUrl;
    private Language source;
    private Language target;
    private final Map<String, OmegawikiEntry> cache = new HashMap<>();

    public class OmegawikiEntry {
        private String word;
        private Integer dmid;
        private List<String> definitions;

        public OmegawikiEntry(final String word, final Integer dmid) {
            this.word = word;
            this.dmid = dmid;
        }

        public Integer getDmid() {
            return dmid;
        }

        public List<String> getDefinitions() {
            return definitions;
        }

        public void addDefinition(final String definition) {
            this.definitions.add(definition);
        }

    }

    public OmegawikiDriver(final String endpointUrl, final Language source, final Language target) {
        this.endpointUrl = endpointUrl;
        this.source = source;
        this.target = target;
    }

    private boolean isSameLanguage(final String deflang, final Language lang) {
        return deflang.toLowerCase().equals(lang.getLocale().getDisplayLanguage().toLowerCase());
    }

    @Override
    public String getName() {
        return "OmegaWiki";
    }

    @Override
    public List<String> readDefinition(final String word) {
        List<String> result = new ArrayList<>();
        List<OmegawikiDefinition> definitions = queryExpression(word);
        for (OmegawikiDefinition def : definitions) {
            //if (isSameLanguage(def.getLang(), target)) {
            //    if (isSameLanguage(def.getDefinition().getLang(), target) ||
            //            isSameLanguage(def.getDefinition().getLang(), source)) {
                    result.add(def.getDefinition().getText());
            //    }
            //}
        }
        return result;
    }


    protected List<OmegawikiDefinition> queryExpression(final String word) {
        String queryUrl = endpointUrl.concat("?action=ow_express&format=json&search=").concat(word);
        String resultJson = query(queryUrl);
        Parser omegawikiParser = new Parser();
        try {
            omegawikiParser.parse(resultJson);
            List<OmegawikiDefinition> definitions = omegawikiParser.getDefinitions();
            if (definitions.size() > 0) {
                return definitions;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;

    }

    protected String query(final String queryUrl) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(queryUrl);
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
