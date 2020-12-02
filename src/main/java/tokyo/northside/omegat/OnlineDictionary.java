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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import org.omegat.core.Core;
import org.omegat.core.CoreEvents;
import org.omegat.core.dictionaries.DictionaryEntry;
import org.omegat.core.dictionaries.IDictionary;
import org.omegat.core.dictionaries.IDictionaryFactory;
import org.omegat.core.events.IApplicationEventListener;
import org.omegat.util.Language;


/**
 * @author Hiroshi Miura
 */
public class OnlineDictionary implements IDictionaryFactory {

    /**
     * load plugin.
     */
    public static void loadPlugins() {
        CoreEvents.registerApplicationEventListener(new OnlineDictionaryApplicationEventListener());
    }

    /**
     * unload plugin.
     */
    public static void unloadPlugins() {
    }

    /**
     * When file is YAML, return true.
     * @param file dictionary file.
     * @return true when supported, otherwise false.
     */
    @Override
    public boolean isSupportedFile(File file) {
        if (file.toPath().endsWith("service.yml")) {
            return true;
        }
        return false;
    }

    @Override
    public IDictionary loadDict(File file) throws Exception {
        Language source = Core.getProject().getProjectProperties().getSourceLanguage();
        Language target = Core.getProject().getProjectProperties().getTargetLanguage();
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        try {
            OnlineDictionaryService[] services = om.readValue(file, OnlineDictionaryService[].class);
            return new OnlineDictionaryDictionary(services, source, target);
        } catch (Exception ignored) {
            return null;
        }
    }

    public class OnlineDictionaryService {

        public OnlineDictionaryService (final String name, final String endpointUrl, final String driver) {
            this.name = name;
            this.endpointUrl = endpointUrl;
            this.driver = driver;
        }

        public OnlineDictionaryService() {}

        private String name;
        private String endpointUrl;
        private String driver;
    }

    public class OnlineDictionaryDictionary implements IDictionary {
        private Language source;
        private Language target;
        private List<IOnlineDictionaryDriver> drivers = new ArrayList<>();

        public OnlineDictionaryDictionary(final OnlineDictionaryService[] services,
                                          final Language source, final Language target) {
            this.source = source;
            this.target = target;

            for (OnlineDictionaryService srv: services) {

            }
        }

        @Override
        public List<DictionaryEntry> readArticles(String word) throws Exception {
            return null;
        }

        @Override
        public List<DictionaryEntry> readArticlesPredictive(String word) throws Exception {
            return null;
        }
    }

    static class OnlineDictionaryApplicationEventListener implements IApplicationEventListener {
        @Override
        public void onApplicationStartup() {
            Core.getDictionaries().addDictionaryFactory(new OnlineDictionary());
        }

        @Override
        public void onApplicationShutdown() {
        }
    }

}
