/*
* Copyright (c) 2010, GroupBy foundation
* All rights reserved.
*
* author : members@groupby.org
*
* - Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*
* - Redistributions of source code must retain the above copyright notice, this
* list of conditions and the following disclaimer.
*
* - Redistributions in binary form must reproduce the above copyright notice,
* this list of conditions and the following disclaimer in the documentation
* and/or other materials provided with the distribution.
* Neither the name of the Swiss information Group nor the names of its
* contributors may be used to endorse or promote products derived from this
* software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
* AND ANY EXPRESS OR IMPLIED WARRANTIES, * INCLUDING, BUT NOT LIMITED TO, THE
* IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
* FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
* DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
* SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
* CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
* OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
* OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
*/

package org.groupby.parser.json;

import java.util.Iterator;
import java.util.LinkedHashMap;
import org.groupby.parser.JSON.JSON;

/**
 * <b>JSONObject</b> class
 *
 * <p>JSONObject class<br />
 * This class hold the JSON Array implementation.
 *
 * </p>
 *
 * author : members@groupby.org
 *
 */
public class JSONObject extends JSON {

    // hold json object
    protected LinkedHashMap<String, Object> items = new LinkedHashMap<String, Object>();

    /**
     * JSONObject Constructor
     */
    public JSONObject() {
    }

    /**
     * Create JSONObject and start reading input string
     * @param b indicates whether start reading or not
     * @throws JSONParsingException
     */
    public JSONObject(boolean b) throws JSONParsingException {
        if (b)
            read();
    }

    /**
     * Create JSONObject and put one name-value pair
     *
     * @param name The name string
     * @param value The value linked with the key. Null accepted.
     */
    public JSONObject(String name, Object value) {
        this();
        put(name, value);
    }

    /**
     * Read a chunk of the input string, matches JSON Object rules.
     * <p>
     * The input string, held by the String object "input" variable is shared
     * by all JSON subclasses (static). Note that the index of parsing is also
     * shared by all JSON child classes.
     * If the string is empty, the method returns null.<br />
     * </p>
     * @return a JSON Object representing a part of the input string.
     * @throws JSONParsingException if the string is not correctly wrote.
     * @see #parse(java.lang.String src)
     */ 
    @Override
    protected JSONObject read() throws JSONParsingException {
        while (k < length) {
            c = input.charAt(k);
            // expected string only
            if(c == DBLQUOTE || c == QUOTE) {
                k++;
                put(new JSONAttribute().read());
            } else if(c == CLOSE_BRACE) {
                k++;
                break;
            } else if(c == SEPARATOR_LIST) {
                k++;
            } else if((int) c > 32) {
                throw new JSONParsingException("Invalid input JSON string. Expected character: " + DBLQUOTE + CLOSE_BRACE + SEPARATOR_LIST + " found: " + c, k);
            } else k++;
        }
        return this;
    }

     /**
     * Put to the JSON Object one name-value pair
     *
     * @param attr The JSON Attribute containing name-value pair
     */
    public final void put(JSONAttribute attr) {
        items.put(attr.name, attr.value);
    }

     /**
     * Put to the JSON Object one name-value pair
     *
     * @param name The key string
     * @param value The value linked with the key. Null accepted.
     */
    public final void put(String name, Object value) {
        items.put(name, value);
    }

     /**
     * Get an Object with the key name
     *
     * @param name The key string
     * @return the Object linked to the key name. Null value possibly returned.
     */
    public final Object get(String name) {
        return items.get(name);
    }

     /**
     * Get a Boolean with the key name
     *
     * @param name The key string
     * @return the Object linked to the key name. Never returns null.
     */
    public final boolean getBoolean(String name) {
        return (items.get(name) == null ? false : Boolean.parseBoolean((String) items.get(name)));
    }

     /**
     * Get an Integer with the key name
     *
     * @param name The key string
     * @return the Object linked to the key name. Never returns null.
     */
    public final Integer getInt(String name) {
        if (items.get(name) == null)
            return 0;
        else 
            return Integer.valueOf(items.get(name).toString());
    }

      /**
     * Get a Double with the key name
     *
     * @param name The key string
     * @return the Object linked to the key name. Never returns null.
     */
    public final Double getDouble(String name) {
        if (items.get(name) == null)
            return 0D;
        else
            return Double.valueOf(items.get(name).toString());
    }

     /**
     * Get an Integer with the key name
     *
     * @param name The key string
     * @return the Object linked to the key name. Never returns null.
     */
    public final Long getLong(String name) {
        if (items.get(name) == null)
            return 0L;
        else
            return Long.valueOf(items.get(name).toString());
    }

     /**
     * Get a Boolean with the key name
     *
     * @param name The key string
     * @return the Object linked to the key name. Never returns null.
     */
    public final String getString(String name) {
        if (items.get(name) == null)
            return "";
        else
            return items.get(name).toString();
    }

    @Override
    public void toBuffer(StringBuffer s) {
        s.append(OPEN_BRACE);
        boolean start = true;
        for (Iterator<String> iter = items.keySet().iterator(); iter.hasNext(); ) {
            if (!start) {
                s.append(SEPARATOR_LIST_SPACE);
            } else start = false;
            // append attribute-name
            String si = iter.next();
            s.append(DBLQUOTE + si + DBLQUOTE);
            // separator
            s.append(SEPARATOR_ATTRIBUTE_SPACE);
            // append attribute-value
            // could be null, string, number, bool, JSON
            Object o = items.get(si);
            if (o instanceof String) {
                s.append(DBLQUOTE + o.toString() + DBLQUOTE);
            } else if (o == null) {
                s.append("null");
            } else {
                s.append(o.toString());
            }
        }
        s.append(CLOSE_BRACE);
    }

    @Override
    public final String toString() {
        StringBuffer s = new StringBuffer(items.size()*20);
        toBuffer(s);
        return s.toString();
    }
}