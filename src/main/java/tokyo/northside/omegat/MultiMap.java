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

package tokyo.northside.omegat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A map that maps keys to sets of values. Does NOT allow null keys/values.
 *
 * @author Maxym Mykhalchuk
 */
public class MultiMap<K, V> {
    /** We're backed up by a HashMap<key, HashSet>. */
    Map<K, Set<V>> map;

    /** Creates an empty MultiMap. */
    public MultiMap() {
        map = new HashMap<K, Set<V>>();
    }

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the specified
     * key.
     */
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    /**
     * Returns <tt>true</tt> if this map maps the specified key to the specified
     * value.
     */
    public boolean containsPair(K key, V value) {
        if (containsKey(key)) {
            Set<V> values = map.get(key);
            return values.contains(value);
        } else {
            return false;
        }
    }

    /**
     * Return values for the specified key
     * @return Set of values.
     */
    public Set<V> getValues(K key) {
        if (containsKey(key)) {
            return map.get(key);
        } else {
            return new HashSet<V>();
        }
    }

    /**
     * Associates the specified value with the specified key in this multi-map.
     * Unlike normal Map, if the map previously contained a mapping for this
     * key, the new value is appended to the list of the values mapped from this
     * key.
     */
    public void put(K key, V value) {
        if (containsKey(key)) {
            Set<V> values = map.get(key);
            values.add(value);
        } else {
            Set<V> values = new HashSet<V>();
            values.add(value);
            map.put(key, values);
        }
    }

    /** Removes all the mappings for this key from this map if it is present. */
    public void remove(Object key) {
        map.remove(key);
    }
}
