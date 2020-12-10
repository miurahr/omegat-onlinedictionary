/**************************************************************************
 OmegaT - Computer Assisted Translation (CAT) tool
          with fuzzy matching, translation memory, keyword search,
          glossaries, and translation leveraging into updated projects.

 Copyright (C) 2000-2006 Keith Godfrey and Maxym Mykhalchuk
               Home page: http://www.omegat.org/
               Support center: https://omegat.org/support

 This file is part of OmegaT.

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

package tokyo.northside.omegat.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A map that maps keys to sets of values. Does NOT allow null keys/values.
 *
 * @author Maxym Mykhalchuk
 * @author Hiroshi Miura
 */
public class MultiMap {
    /** We're backed up by a HashMap<key, HashSet>. */
    private Map<String, Set<String>> map;

    /** Creates an empty MultiMap. */
    public MultiMap() {
        map = new HashMap<String, Set<String>>();
    }

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the specified
     * key.
     * @param key to test whether contains or not.
     * @return true if map has key, otherwise false
     */
    public boolean containsKey(final String key) {
        return map.containsKey(key);
    }

    /**
     * Return values for the specified key.
     * @return Set of values.
     * @param key key of type K to get value
     */
    public Set<String> getValues(final String key) {
        if (containsKey(key)) {
            return map.get(key);
        } else {
            return new HashSet<String>();
        }
    }

    /**
     * Associates the specified value with the specified key in this multi-map.
     * Unlike normal Map, if the map previously contained a mapping for this
     * key, the new value is appended to the list of the values mapped from this
     * key.
     * @param key
     * @param value
     */
    public void put(final String key, final String value) {
        if (containsKey(key)) {
            Set<String> values = map.get(key);
            values.add(value);
        } else {
            Set<String> values = new HashSet<String>();
            values.add(value);
            map.put(key, values);
        }
    }

    /**
     *  Removes all the mappings for this key from this map if it is present.
     * @param key to remove
     */
    public void remove(final String key) {
        map.remove(key);
    }
}
