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

package org.groupby.parser.JSON;

import org.groupby.parser.json.JSONArray;
import org.groupby.parser.json.JSONObject;
import org.groupby.parser.json.JSONParsingException;
import org.groupby.parser.json.JSONString;

/**
 * <b>JSON</b> class
 *
 * <p>JSON class <br />
 * This is our free implementation of JSON format.</p>
 * <p>Here below this is the BNF : <br />
 *
 * #SME add number -+ ^
 * 
 * jsonarray<br />
 *     = '[' jsonobject ',' jsonarray-list ']'<br />
 *     | '[' jsonarray-list ',' jsonobject ']'<br />
 *     | string<br />
 *     ;<br />
 *
 * jsonarray-list<br />
 *     = jsonarray ',' jsonarray-list<br />
 *     | jsonarray<br />
 *     ;<br />
 *
 * tag-name<br />
 *     = string<br />
 *     ;<br />
 *
 * jsonobject<br />
 *     = '{' jsonobject-list '}'<br />
 *     | '{' '}'<br />
 *     ;<br />
 *
 * jsonobject-list<br />
 *     = attribute ',' jsonobject-list<br />
 *     | attribute<br />
 *     ;<br />
 *
 * attribute<br />
 *    = attribute-name ':' attribute-value<br />
 *    ;<br />
 *
 * attribute-name<br />
 *    = string<br />
 *    ;<br />
 *
 * attribute-value<br />
 *    = string<br />
 *    | number<br />
 *    | 'true'<br />
 *    | 'false'<br />
 *    | 'null'<br />
 *    ;<br /><br />
 * 
 * author : members@groupby.org
 *
 */
public abstract class JSON {

    // useful constants for parsing
    protected final static char SPACE = ' ';
    protected final static char ANTISLASH = '\\';
    protected final static char UNICODE = 'u';
    
    protected final static char OPEN_BRACKET = '[';
    protected final static char OPEN_BRACE = '{';
    protected final static char DBLQUOTE = '"';
    protected final static char QUOTE = '\'';

    protected final static char CLOSE_BRACKET = ']';
    protected final static char CLOSE_BRACE = '}';

    protected final static char SEPARATOR_FLOATING = '.';
    protected final static char SEPARATOR_ATTRIBUTE = ':';
    protected final static char SEPARATOR_LIST = ',';
    protected final static String SEPARATOR_ATTRIBUTE_SPACE = ": ";
    protected final static String SEPARATOR_LIST_SPACE = ", ";

    protected final static char MINUS = '-';
    protected final static char EXPMIN = 'e';

    /**
     * Method for reading input string
     * @return the JSON object
     * @throws JSONParsingException
     */
    protected abstract JSON read() throws JSONParsingException;
    
    /**
     * Append string output to a stringBuffer.
     * @param s the stringBuffer.
     */
    public abstract void toBuffer(StringBuffer s);
    
    // parser's static variables
    public static String input = "";
    public static int length = 0;
    public static char c = SPACE;
    public static int k = 0;
    public static int ascii = 0;

    /**
     * Parse an input stream, and build a JSON object from an input string.
     * <p>
     * If the string is empty, the method returns null.<br />
     * To recursively output objects, use toString().
     * <ul>Input strings are :<br />
     *  <li>- JSONArray [...]</li>
     *  <li>- or JSONObject {...}</li>
     *  <li>- or a simple string "..."</li>
     * </ul>
     * </p>
     * @param src The input string top parse
     * @return a JSON object representing the entire string.
     * @throws JSONParsingException If the string is not correctly wrote.
     * @see #read()
     * @see #toString()
     */
    public final static JSON parse(String src) throws JSONParsingException  {
        clear();
        input = src;
        length = input.length();
        while (k < length) {
            c = input.charAt(k);
            // initial switch
            //  JSON object
            if(c == OPEN_BRACKET) {
                k++;
                return new JSONArray(true);
            // JSON array
            } else if(c == OPEN_BRACE) {
                k++;
                return new JSONObject(true);
            // simple JSON string
            } else if(c == DBLQUOTE) {
                k++;
                return new JSONString(true);
            // unexpected character found
            } else {
                if((int) c > 32) {
                    throw new JSONParsingException("Invalid input JSON string. Expected character: " + OPEN_BRACKET + OPEN_BRACE + " found: " + c, k);
                }
            }
            k++;
        }
        return null;

    }

    /**
     * Reset all vars.
     * <p>
     * This method is call by the parse method. This class is static, it is
     * necessary to clear those variables before each parsing.
     * </p>
     * @see #parse(java.lang.String src)
     */
    public static void clear() {
        input = "";
        length = 0;
        c = SPACE;
        k = 0;
        ascii = 0;
    }

}
