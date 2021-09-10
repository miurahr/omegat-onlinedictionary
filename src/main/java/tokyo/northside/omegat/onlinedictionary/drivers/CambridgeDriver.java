package tokyo.northside.omegat.onlinedictionary.drivers;

import org.apache.hc.client5.http.ClientProtocolException;
import org.omegat.util.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tokyo.northside.omegat.onlinedictionary.dtd.OnlineDictionaryService;
import tokyo.northside.omegat.utils.MultiMap;
import tokyo.northside.omegat.utils.QueryUtil;
import tokyo.northside.oxfordapi.OxfordDictionaryParser;
import tokyo.northside.oxfordapi.dtd.Result;

import java.io.IOException;
import java.util.*;

/**
 *
 */
public class CambridgeDriver implements IOnlineDictionaryDriver {

    private final Logger LOGGER = LoggerFactory.getLogger(CambridgeDriver.class.getName());

    private final String name;
    private final String endpointUrl;
    private final String accessKey;
    private final String dictCode;

    private final MultiMap cache = new MultiMap();

    public CambridgeDriver(final OnlineDictionaryService service, final Language source, final Language target) {
        endpointUrl = service.getEndpointUrl();
        name = service.getName();
        accessKey = service.getSecret();
        dictCode = getDictCode(source, target);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<String> readEntries(final String word) {
        if (dictCode == null) {
            return null;
        }
        if (cache.containsKey(word)) {
            return cache.getValues(word);
        }
        return null;
    }

    private Map<String, Object> getHeaderEntries() {
        Map<String, Object> header = new HashMap<>();
        header.put("Accept", "application/json");
        header.put("accessKey", accessKey);
        return header;
    }

    protected List<Result> query(final String requestUrl, final String word) {

        // GET https://dictionary.cambridge.org/api/v1/dictionaries/{dictCode}/entries/{entryId}
        Map<String, Object> header = getHeaderEntries();
        String response = null;
        String json = "";

        try {
            response = QueryUtil.queryPost(requestUrl, header, json);
        } catch (ClientProtocolException cpe) {
            LOGGER.info(cpe.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response != null) {
            OxfordDictionaryParser parser = new OxfordDictionaryParser(word);
            try {
                parser.parse(response);
                return parser.getResults();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    protected String getDictCode(final Language source, final Language target) {
        if (!source.isSameLanguage(new Language("en"))) {
            return null;
        }
        if (target.isSameCountryLanguage(new Language("zh_CN"))) {
            return "english-chinese-simplified";
        } else if (target.isSameCountryLanguage(new Language("zh_TW"))) {
            return "english-chinese-traditional";
        } else if (target.isSameLanguage(new Language("ja"))) {
            return "english-japanese";
        } else {
            return null;
        }
    }

}
