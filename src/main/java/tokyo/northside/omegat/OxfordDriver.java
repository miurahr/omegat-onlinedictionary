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

import org.omegat.util.Language;
import tokyo.northside.oxfordapi.Entry;
import tokyo.northside.oxfordapi.EntryParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OxfordDriver implements IOnlineDictionaryDriver {
    private final String app_id = "<my_app_id>";
    private final String app_key = "<my_app_key>";
    private final String endpointUrl;
    private final Language source;
    private final Language target;

    public OxfordDriver(final String endpointUrl, final Language source, final Language target) {
        this.endpointUrl = endpointUrl;
        this.source = source;
        this.target = target;
    }

    @Override
    public String getName() {
        return "Oxford Dictionaries(ONLINE)";
    }

    @Override
    public List<String> readDefinition(final String word) {
        return null;
    }

    @Override
    public List<String> readTranslation(final String word) {
        return null;
    }

    private String getRequestUrl(final String word, final boolean strict) {
        final String strictMatch = strict ? "true" : "false";
        final String word_id = word.toLowerCase();
        String language = source.getLanguageCode();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id
                + "?" + "&strictMatch=" + strictMatch;
    }

    private Map<String, Object> getHeaderEntries() {
        Map<String, Object> header = new HashMap<>();
        header.put("Accept", "application/json");
        header.put("app_id", app_id);
        header.put("app_key", app_key);
        return header;
    }

    private List<String> queryDefinition(final String word) {
        List<String> definitions = new ArrayList<>();
        String requestUrl = getRequestUrl(word, false);
        Map<String, Object> header = getHeaderEntries();
        String response = QueryUtil.query(requestUrl, header);
        EntryParser parser = new EntryParser(word);
        try {
            parser.parse(response);
            List<Entry> result = parser.getResults();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return definitions;
    }
}
