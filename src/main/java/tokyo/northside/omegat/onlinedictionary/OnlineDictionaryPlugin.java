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

package tokyo.northside.omegat.onlinedictionary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import org.omegat.core.Core;
import org.omegat.core.CoreEvents;
import org.omegat.core.dictionaries.DictionaryEntry;
import org.omegat.core.dictionaries.IDictionary;
import org.omegat.core.dictionaries.IDictionaryFactory;
import org.omegat.core.events.IApplicationEventListener;
import org.omegat.util.Language;

import tokyo.northside.omegat.onlinedictionary.drivers.IOnlineDictionaryDriver;
import tokyo.northside.omegat.onlinedictionary.drivers.OmegawikiDriver;
import tokyo.northside.omegat.onlinedictionary.drivers.OxfordDriver;
import tokyo.northside.omegat.onlinedictionary.dtd.OnlineDictionaryService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public final class OnlineDictionaryPlugin {

    private static OnlineDictionaryApplicationEventListener listener;

    private OnlineDictionaryPlugin() { }

    /**
     * load plugin.
     */
    public static void loadPlugins() {
        listener = new OnlineDictionaryApplicationEventListener();
        CoreEvents.registerApplicationEventListener(listener);
    }

    /**
     * unload plugin.
     */
    public static void unloadPlugins() {
        CoreEvents.unregisterApplicationEventListener(listener);
        listener = null;
    }

    static class OnlineDictionaryApplicationEventListener implements IApplicationEventListener {
        private static OnlineDictionaryMain main;

        @Override
        public void onApplicationStartup() {
            main = new OnlineDictionaryMain();
            Core.getDictionaries().addDictionaryFactory(main);
        }

        @Override
        public void onApplicationShutdown() {
            Core.getDictionaries().removeDictionaryFactory(main);
        }
    }

    /**
     * @author Hiroshi Miura
     */
    public static class OnlineDictionaryMain implements IDictionaryFactory {
        private Language source = null;
        private Language target = null;
        private static final List<String> SUPPORTED_DRIVERS = new ArrayList<>();
        static {
            SUPPORTED_DRIVERS.add("omegawiki");
            SUPPORTED_DRIVERS.add("oxfordapi");
            SUPPORTED_DRIVERS.add("cambridge");
        }

        public OnlineDictionaryMain() { }

        public OnlineDictionaryMain(final Language source, final Language target) {
            this.source = source;
            this.target = target;
        }

        /**
         * When file is YAML, return true.
         * @param file dictionary file.
         * @return true when supported, otherwise false.
         */
        @Override
        public boolean isSupportedFile(final File file) {
            if (file.isFile() && file.toPath().toString().endsWith("service.yml")) {
                OnlineDictionaryService service = getService(file);
                return service != null && SUPPORTED_DRIVERS.contains(service.getDriver());
            }
            return false;
        }

        @Override
        public IDictionary loadDict(final File file) {
            if (source == null) {
                source = Core.getProject().getProjectProperties().getSourceLanguage();
                target = Core.getProject().getProjectProperties().getTargetLanguage();
            }
            try {
                OnlineDictionaryService service = getService(file);
                return new OnlineDictionary(service, source, target);
            } catch (Exception exception) {
                exception.printStackTrace();
                return null;
            }
        }

        protected static OnlineDictionaryService getService(final File file) {
            OnlineDictionaryService service;
            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            try {
                service = om.readValue(file, OnlineDictionaryService.class);
            } catch (Exception exception) {
                exception.printStackTrace();
                return null;
            }
            return service;
        }
    }

    public static class OnlineDictionary implements IDictionary {
        private final List<IOnlineDictionaryDriver> drivers = new ArrayList<>();

        public OnlineDictionary(final OnlineDictionaryService service,
                                final Language source, final Language target) throws Exception {
            if ("omegawiki".equals(service.getDriver())) {
                drivers.add(new OmegawikiDriver(service, source, target));
            } else if ("oxfordapi".equals(service.getDriver())) {
                drivers.add(new OxfordDriver(service, source, target));
            } else {
                throw new Exception("Unknown driver");
            }
        }

        @Override
        public List<DictionaryEntry> readArticles(final String word) {
            List<DictionaryEntry> articles = new ArrayList<>();
            for (IOnlineDictionaryDriver driver : drivers) {
                StringBuilder sb = new StringBuilder();
                for (String entry : driver.readEntries(word)) {
                    articles.add(new DictionaryEntry(word, entry + "(" + driver.getName() + ")"));
                }
            }
            return articles;
        }
    }
}
