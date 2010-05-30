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

import java.util.ArrayList;
import org.groupby.parser.JSON.JSON;

/**
 * <b>JSONArray</b> class
 *
 * <p>JSON Array class<br />
 * This class hold the JSON Array implementation.
 * 
 * </p>
 *
 * author : members@groupby.org
 *
 */
public class JSONArray extends JSON {

    // hold json array
    protected ArrayList<Object> items = new ArrayList();

    /**
     * JSONArray Constructor
     */
    public JSONArray() {
    }

    /**
     * Create JSONArray and start reading input string
     * @param b indicates whether start reading or not
     * @throws JSONParsingException
     */
    public JSONArray(boolean b) throws JSONParsingException {
        if (b)
            read();
    }

    /**
     * Read a chunk of the input string, matches JSON Array rules.
     * <p>
     * The input string, held by the String object "input" variable is shared
     * by all JSON subclasses (static). Note that the index of parsing is also
     * shared by all JSON child classes.
     * If the string is empty, the method returns null.<br />
     * </p>
     * @return a JSON Array representing a part of the input string.
     * @throws JSONParsingException if the string is not correctly wrote.
     * @see #parse(java.lang.String src)
     */    
    @Override
    protected JSONArray read() throws JSONParsingException {

        while (k < length) {

            c = input.charAt(k);

            // expected jsonobject
            if(c == OPEN_BRACE) {
                k++;
                items.add(new JSONObject().read());
            } else if(c == SEPARATOR_LIST || c == CLOSE_BRACKET || c == CLOSE_BRACE) {
                k++;
            } else if(c == OPEN_BRACKET) {
            // expected jsonarray
                k++;
                items.add(new JSONArray().read());
            } else if((int) c > 32) {
                throw new JSONParsingException("Invalid input JSON string. Expected character: " + DBLQUOTE + " found: " + c, k);
            } else k++;

        }

        return this;
    }

     /**
     * Put to the JSON Array one attribute name-value pair
     *
     * @param o The object to add
     */
    public final void put(Object o) {
        items.add(o);
    }

     /**
     * Get an item from JSON Object
     *
     * @return the Object held
     */
    public final Object get(int i) {
        return items.get(i);
    }

    @Override
    public void toBuffer(StringBuffer s) {
        s.append(OPEN_BRACKET);
        for (int i = 0;i < items.size(); i++) {
            if (i > 0) s.append(SEPARATOR_LIST);
            s.append(items.get(i).toString());
        }
        s.append(CLOSE_BRACKET);
    }

    /**
     * Print JSON Array.
     * <p>
     * This method calls all toString() method in a cascade manner.
     * </p>
     * @return the chunk string matched with this object.
     */
    @Override
    public final String toString() {
        StringBuffer s = new StringBuffer(items.size()*20);
        toBuffer(s);
        return s.toString();
    }
}