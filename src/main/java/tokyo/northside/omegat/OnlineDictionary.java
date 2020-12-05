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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import org.omegat.core.Core;
import org.omegat.core.dictionaries.IDictionary;
import org.omegat.core.dictionaries.IDictionaryFactory;
import org.omegat.util.Language;


/**
 * @author Hiroshi Miura
 */
public class OnlineDictionary implements IDictionaryFactory {

    private Language source = null;
    private Language target = null;

    public OnlineDictionary() {}

    public OnlineDictionary(final Language source, final Language target) {
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
        if (file.isFile()) {
            if (file.toPath().endsWith("service.yml")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public IDictionary loadDict(final File file) throws Exception {
        if (source == null) {
            source = Core.getProject().getProjectProperties().getSourceLanguage();
            target = Core.getProject().getProjectProperties().getTargetLanguage();
        }
        try {
            OnlineDictionaryService service = getService(file);
            return new OnlineDictionaryDictionary(service, source, target);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    protected OnlineDictionaryService getService(final File file) {
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
