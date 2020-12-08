/**************************************************************************
Online dictionary access plugin for OmegaT CAT tool(http://www.omegat.org/)

 Copyright (C) 2020 Hiroshi Miura

 OmegaT is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 OmegaT is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **************************************************************************/

package tokyo.northside.omegat.onlinedictionary.drivers;

import org.omegat.util.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tokyo.northside.omegat.utils.QueryUtil;
import tokyo.northside.oxfordapi.dtd.*;
import tokyo.northside.oxfordapi.OxfordDictionaryParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OxfordDriver implements IOnlineDictionaryDriver {

    private final Logger LOGGER = LoggerFactory.getLogger(OxfordDriver.class.getName());
    private final String appId;
    private final String appKey;
    private final String endpointUrl;
    private final Language source;
    private final Language target;

    public OxfordDriver(final String endpointUrl, final String key, final String secret,
                        final Language source, final Language target) {
        this.endpointUrl = endpointUrl;
        this.appId = key;
        this.appKey = secret;
        this.source = source;
        this.target = target;
    }

    @Override
    public String getName() {
        return "Oxford Dictionaries(ONLINE)";
    }

    @Override
    public List<String> readDefinition(final String word) {
        String requestUrl = getEntriesRequestUrl(word, false);
        List<Result> results = query(requestUrl, word);
        List<String> definitions = new ArrayList<>();
        LOGGER.info("oxford : query for: " + word);
        for (Result result : results) {
            for (LexicalEntry lexicalEntry : result.getLexicalEntries()) {
                for (Entry entry: lexicalEntry.getEntries()) {
                    for (Sense sense: entry.getSenses()) {
                        if (sense.getDefinitions() != null) {
                            for (String text : sense.getDefinitions()) {
                                definitions.add(text);
                            }
                        }
                    }
                }
            }
        }
        LOGGER.info("oxford :Resulted # of definitions: " + definitions.size());
        return definitions;
    }

    @Override
    public List<String> readTranslation(final String word) {
        String requestUrl = getTranslationsRequestUrl(word, false);
        List<Result> results = query(requestUrl, word);
        List<String> translations = new ArrayList<>();
        for (Result result : results) {
            for (LexicalEntry lexicalEntry : result.getLexicalEntries()) {
                for (Entry entry: lexicalEntry.getEntries()) {
                    for (Sense sense: entry.getSenses()) {
                        for (Translation translation: sense.getTranslations()) {
                            translations.add(translation.getText());
                        }
                    }
                }
            }
        }
        LOGGER.info("oxford :Resulted # of translations: " + translations.size());
        return translations;
    }

    protected String getEntriesRequestUrl(final String word, final boolean strict) {
        final String strictMatch;
        if (strict) {
            strictMatch = "true";
        } else {
            strictMatch = "false";
        }
        final String wordId = word.toLowerCase();
        String language = source.getLanguageCode();
        return endpointUrl + "entries/" + language + "/" + wordId + "?" + "&strictMatch=" + strictMatch;
    }

    protected String getTranslationsRequestUrl(final String word, final boolean strictMatch) {
        final String wordId = word.toLowerCase();
        String sourceLang = source.getLanguageCode();
        String targetLang = target.getLanguageCode();
        return endpointUrl + "translations/" + sourceLang + "/" + targetLang + "/" + wordId + "?" + "&strictMatch=" + strictMatch;
    }

    private Map<String, Object> getHeaderEntries() {
        Map<String, Object> header = new HashMap<>();
        header.put("Accept", "application/json");
        header.put("app_id", appId);
        header.put("app_key", appKey);
        return header;
    }

    protected List<Result> query(final String requestUrl, final String word) {
        Map<String, Object> header = getHeaderEntries();
        String response = QueryUtil.query(requestUrl, header);
        if (response != null) {
            OxfordDictionaryParser parser = new OxfordDictionaryParser(word);
            try {
                parser.parse(response);
                List<Result> results = parser.getResults();
                LOGGER.info("Got query result from Oxford Dictionary API.");
                return results;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

}
