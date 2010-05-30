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
 * <b>JSONAttribute</b> class
 *
 * <p>JSONAttribute class<br />
 * This class allows to manage JSON attribute-name and attribute-value rules.
 *
 * </p>
 *
 * author : members@groupby.org
 *
 */
public class JSONAttribute extends JSON {

    protected String name = null;
    protected Object value = null;
    private String tmp = "";

    /**
     * Read a chunk of the input string, matches JSON Attribute rules.
     * <p>
     * The input string, held by the String object "input" variable is shared
     * by all JSON subclasses (static). Note that the index of parsing is also
     * shared by all JSON child classes. If the JS String is round with a
     * valid pair of quotes, they are changed with DBLQUOTE.
     * If the string is empty, the method returns null.<br />
     * </p>
     * @return a JSON Attribute representing a part of the input string.
     * @throws JSONParsingException if the string is not correctly wrote.
     * @see #parse(java.lang.String src)
     */
    @Override
    protected JSONAttribute read() throws JSONParsingException {
        int currentPos = k;
        try {
            // attribute name
            k = input.indexOf(DBLQUOTE, currentPos);
            name = input.substring(currentPos, k);
            // separator
            k++;
            k = input.indexOf(SEPARATOR_ATTRIBUTE, k);
            // attribute value
            k++;
            while(k < length) {
                c = input.charAt(k);
                ascii = (int) c;

                if (c == DBLQUOTE || c == QUOTE) {
                // string expected
                    k++;
                    currentPos = k;
                    while (k < length) {
                        c = input.charAt(k);
                        // expected end string
                        if (c == DBLQUOTE || c == QUOTE) {
                            k++;
                            break;
                        } else if (c == ANTISLASH) {
                            k++;
                            c = input.charAt(k);
                            if (c == UNICODE)
                                k += 3;
                        }
                        k++;
                    }
                    value = input.substring(currentPos, k-1);
                    break;

                } else if (c == OPEN_BRACE) {
                // jsonobject expected
                    k++;
                    value = new JSONObject().read();
                    break;

                } else if (c == OPEN_BRACKET) {
                // jsonarray expected
                    k++;
                    value = new JSONArray().read();
                    break;

                } else if (ascii > 32) {
                // number boolean expected
                    currentPos = k;
                    k++;
                    while (k < length) {
                        c = input.charAt(k);
                        if (c == SEPARATOR_LIST || c == CLOSE_BRACE || c == CLOSE_BRACKET) {
                            tmp = input.substring(currentPos, k).toLowerCase();
                            if (tmp.indexOf("true") > -1)
                                value = true;
                            else if (tmp.indexOf("false") > -1)
                                value = false;                            
                            else if (tmp.indexOf("null") > -1)
                                value = null;
                            else if (tmp.indexOf(EXPMIN) == -1 && tmp.indexOf(SEPARATOR_FLOATING) == -1)
                                value = Long.parseLong(tmp);
                            else
                                value = Double.parseDouble(tmp);
                            break;
                        }
                        k++;
                    }
                    break;

                }
                k++;
            }
        } catch (Exception ex) {
            throw new JSONParsingException("Invalid input JSON string. " + ex.getCause() + " " + ex.getMessage() , k);
        }
        
        return this;
    }

    @Override
    public void toBuffer(StringBuffer s) {
        throw new UnsupportedOperationException("Not supported yet.");
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

}