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

package tokyo.northside.omegat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
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

import tokyo.northside.omegawiki.ExpressionParser;
import tokyo.northside.omegawiki.OmegawikiMeaning;
import tokyo.northside.omegawiki.OmegawikiDefinition;
import tokyo.northside.omegawiki.SyntransParser;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class OmegawikiDriver implements IOnlineDictionaryDriver {

    private String endpointUrl;
    private Language source;
    private Language target;
    private Map<String, String> languageMap;
    private final MultiMap<String, OmegawikiEntry> cache = new MultiMap<>();

    public class OmegawikiEntry {
        private String dmid;

        public OmegawikiEntry(final String dmid) {
            this.dmid = dmid;
        }

        public String getDmid() {
            return dmid;
        }
    }

    public OmegawikiDriver(final String endpointUrl, final Language source, final Language target) throws IOException {
        this.endpointUrl = endpointUrl;
        this.source = source;
        this.target = target;
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference<HashMap<String, String>> typeRef = new TypeReference<>() { };
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
        for (OmegawikiDefinition def : queryExpression(word)) {
            if (isSameLanguage(def.getLangid(), source)) {
                String text = def.getDefinition().getText();
                list.add(text);
            }
        }
        return list;
    }

    @Override
    public List<String> readTranslation(final String word) {
        return querySyntrans(word).stream().map(OmegawikiMeaning::getE).collect(Collectors.toList());
    }

    protected List<OmegawikiMeaning> querySyntrans(final String word) {
        List<OmegawikiMeaning> meanings = new ArrayList<>();
        String queryUrlBase = endpointUrl.concat("?action=ow_syntrans&format=json&dm=").concat(word);
        if (!cache.containsKey(word)) {
            queryExpression(word);
        }
        if (!cache.containsKey(word)) {
            for (OmegawikiEntry entry : cache.getValues(word)) {
                String dmid = entry.getDmid();
                String queryUrl = queryUrlBase.concat(dmid);
                String resultJson = query(queryUrl);
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
        String queryUrl = endpointUrl.concat("?action=ow_express&format=json&search=").concat(word);
        String resultJson = query(queryUrl);
        ExpressionParser omegawikiParser = new ExpressionParser();
        try {
            omegawikiParser.parse(resultJson);
            List<OmegawikiDefinition> definitions = omegawikiParser.getDefinitions();
            if (definitions.size() > 0) {
                for (OmegawikiDefinition definition : definitions) {
                    cache.put(word, new OmegawikiEntry(definition.getDmid()));
                }
            }
            return definitions;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();

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
