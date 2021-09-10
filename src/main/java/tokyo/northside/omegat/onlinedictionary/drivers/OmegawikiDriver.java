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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.hc.client5.http.ClientProtocolException;
import org.omegat.util.Language;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tokyo.northside.omegat.onlinedictionary.dtd.OnlineDictionaryService;
import tokyo.northside.omegat.utils.MultiMap;
import tokyo.northside.omegat.utils.QueryUtil;
import tokyo.northside.omegawiki.ExpressionParser;
import tokyo.northside.omegawiki.dtd.OmegawikiMeaning;
import tokyo.northside.omegawiki.dtd.OmegawikiDefinition;
import tokyo.northside.omegawiki.SyntransParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class OmegawikiDriver implements IOnlineDictionaryDriver {

    private Logger LOGGER = LoggerFactory.getLogger(OmegawikiDriver.class.getName());

    private final String endpointUrl;
    private final String name;
    private Language source;
    private Language target;
    private Map<String, String> languageMap;
    private final MultiMap cache = new MultiMap();
    private final MultiMap dmidMap = new MultiMap();

    public OmegawikiDriver(final OnlineDictionaryService service, final Language source, final Language target) throws IOException {
        endpointUrl = service.getEndpointUrl();
        name = service.getName();
        this.source = source;
        this.target = target;
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() { };
        try (InputStream is = OmegawikiDriver.class.getClassLoader().getResourceAsStream("languages.yml")) {
            assert is != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            languageMap = mapper.readValue(reader, typeRef);
        }
    }

    private boolean isSameLanguage(final String langId, final Language lang) {
        return languageMap.getOrDefault(langId, "en").equals(lang.getLanguageCode());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<String> readEntries(final String word) {
        if (!cache.containsKey(word)) {
            StringBuilder sb = new StringBuilder();
            List<OmegawikiDefinition> definitions = queryExpression(word);
            for (OmegawikiDefinition def : definitions) {
                if (isSameLanguage(def.getLangid(), source)) {
                    dmidMap.put(word, def.getDmid());
                    String text = def.getDefinition().getText();
                    sb.append(text);
                    sb.append("/");
                }
            }
            List<OmegawikiMeaning> meanings = querySyntrans(word);
            for (OmegawikiMeaning meaning : meanings) {
                sb.append("/");
                sb.append(meaning.getE());
            }
            cache.put(word, sb.toString());
        }
        return cache.getValues(word);
    }

    protected List<OmegawikiMeaning> querySyntrans(final String word) {
        List<OmegawikiMeaning> meanings = new ArrayList<>();
        String queryUrlBase = endpointUrl.concat("?action=ow_syntrans&format=json&dm=").concat(word);
        if (!dmidMap.containsKey(word)) {
            queryExpression(word);
        }
        if (!dmidMap.containsKey(word)) {
            for (String dmid : dmidMap.getValues(word)) {
                String queryUrl = queryUrlBase.concat(dmid);
                String resultJson = null;
                try {
                    resultJson = QueryUtil.query(queryUrl, new HashMap<>());
                } catch (ClientProtocolException cpe) {
                    LOGGER.info(cpe.getMessage());
                    return meanings;
                } catch (IOException e) {
                    e.printStackTrace();
                    return meanings;
                }
                SyntransParser parser = new SyntransParser();
                try {
                    parser.parse(resultJson);
                    List<OmegawikiMeaning> result = parser.getMeanings();
                    if (result.size() > 0) {
                        meanings.addAll(result);
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
        return meanings;
    }

    protected List<OmegawikiDefinition> queryExpression(final String word) {
        List<OmegawikiDefinition> definitions = new ArrayList<>();
        String resultJson;
        String queryUrl = endpointUrl.concat("?action=ow_express&format=json&search=").concat(word);
        try {
            resultJson = QueryUtil.query(queryUrl, new HashMap<>());
        } catch (ClientProtocolException cpe) {
            LOGGER.info(cpe.getMessage());
            return definitions;
        } catch (IOException e) {
            e.printStackTrace();
            return definitions;
        }
        ExpressionParser omegawikiParser = new ExpressionParser();
        try {
            omegawikiParser.parse(resultJson);
            definitions = omegawikiParser.getDefinitions();
            return definitions;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
