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

import tokyo.northside.omegat.utils.OnlineDictionaryEntry;
import tokyo.northside.omegat.utils.OnlineDictionaryMultiMap;
import tokyo.northside.omegat.utils.QueryUtil;
import tokyo.northside.omegawiki.ExpressionParser;
import tokyo.northside.omegawiki.dtd.OmegawikiMeaning;
import tokyo.northside.omegawiki.dtd.OmegawikiDefinition;
import tokyo.northside.omegawiki.SyntransParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OmegawikiDriver implements IOnlineDictionaryDriver {

    private Logger LOGGER = LoggerFactory.getLogger(OmegawikiDriver.class.getName());

    private String endpointUrl;
    private Language source;
    private Language target;
    private Map<String, String> languageMap;
    private final OnlineDictionaryMultiMap cache = new OnlineDictionaryMultiMap();

    public OmegawikiDriver(final String endpointUrl, final Language source, final Language target) throws IOException {
        this.endpointUrl = endpointUrl;
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
        return "OmegaWiki";
    }

    @Override
    public List<String> readDefinition(final String word) {
        List<String> list = new ArrayList<>();
        List<OmegawikiDefinition> definitions = queryExpression(word);
        List<OmegawikiMeaning> meanings = querySyntrans(word);
        for (OmegawikiDefinition def : definitions) {
            if (isSameLanguage(def.getLangid(), source)) {
                String dmid = def.getDmid();
                OnlineDictionaryEntry entry = new OnlineDictionaryEntry(dmid, def.getDefinition().getText());
                for (OmegawikiMeaning meaning : meanings) {
                    if (dmid.equals(meaning.getIm())) {
                        entry.addTranslation(meaning.getE());
                    }
                }
                cache.put(word, entry);
                StringBuilder sb = new StringBuilder();
                for (String definition : entry.getDefinitions()) {
                    sb.append(definition);
                    sb.append("/");
                }
                sb.append(" - ");
                for (String translation : entry.getTranslations()) {
                    sb.append(translation);
                    sb.append("/");
                }
                list.add(sb.toString());
            }
        }
        return list;
   }

    @Override
    public List<String> readTranslation(String word) {
        return new ArrayList<>();
    }

    protected List<OmegawikiMeaning> querySyntrans(final String word) {
        List<OmegawikiMeaning> meanings = new ArrayList<>();
        String queryUrlBase = endpointUrl.concat("?action=ow_syntrans&format=json&dm=").concat(word);
        if (!cache.containsKey(word)) {
            queryExpression(word);
        }
        if (!cache.containsKey(word)) {
            for (OnlineDictionaryEntry entry : cache.getValues(word)) {
                String dmid = entry.getSenseId();
                String queryUrl = queryUrlBase.concat(dmid);
                String resultJson = null;
                try {
                    resultJson = QueryUtil.query(queryUrl, new HashMap<String, Object>());
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
            resultJson = QueryUtil.query(queryUrl, new HashMap<String, Object>());
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
