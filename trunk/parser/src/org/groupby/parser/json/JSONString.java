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

import org.groupby.parser.JSON.JSON;

/**
 * <b>JSONString</b> class
 *
 * <p>JSONString class<br />
 * This class is a String. Method Heritated from JSON class, are used to parse
 * the string to return
 *
 * </p>
 *
 * author : members@groupby.org
 *
 */
public class JSONString extends JSON {

    // hold json string object
    protected String value = new String();
    
    /**
     * JSONObject Constructor
     */
    public JSONString() {
    }

    /**
     * Create JSONString and start reading input string
     * @param b indicates whether start reading or not
     * @throws JSONParsingException
     */
    public JSONString(boolean b) throws JSONParsingException {
        if (b)
            read();
    }

    /**
     * Read a chunk of the input string, matches JSON String rules.
     * <p>
     * The input string, held by the String object "input" variable is shared
     * by all JSON subclasses (static). Note that the index of parsing is also
     * shared by all JSON child classes.
     * If the string is empty, the method returns null.<br />
     * </p>
     * @return a JSON Attribute representing a part of the input string.
     * @throws JSONParsingException if the string is not correctly wrote.
     * @see #parse(java.lang.String src)
     */
    @Override
    protected JSONString read() throws JSONParsingException {
        value = input.substring(k, length);
        return this;
    }

    /**
     * Print JSON String.
     * <p>
     * This method is a simple substring extracted
     * from the global input String held by static JSON object.
     * </p>
     * @return the chunk string matched with this object.
     */
    @Override
    public String toString() {
        return DBLQUOTE + value + DBLQUOTE;
    }

    @Override
    public void toBuffer(StringBuffer s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}