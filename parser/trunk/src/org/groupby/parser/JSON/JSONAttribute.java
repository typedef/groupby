/*
 * Copyright (c) 2010, GroupBy.org foundation
 * All rights reserved.
 *
 * author : stephane@groupby.org
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
 * Neither the name of the GroupBy.org nor the names of its
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

import org.groupby.parser.JSON.JSON;

/**
 * <b>JSONAttribute</b> class
 * 
 * <p>
 * JSONAttribute class<br />
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
	public int k = 0;
	private JSONArray ja;
	private JSONObject jo;

	/**
	 * Read the input string
	 * 
	 * @param src
	 * @return a JSON Attribute representing a part of the input string.
	 * @throws JSONParsingException
	 * @see #read(java.lang.String src, int)
	 */
	@Override
	public JSON read(String src) throws JSONParsingException {
		return read(src, 0);
	}

	/**
	 * Read a chunk of the input string, matches JSON Attribute rules.
	 * <p>
	 * If the JS String is rounded with a valid pair of quotes, they are changed
	 * with DBLQUOTE. If the string is empty, the method returns null.<br />
	 * </p>
	 * 
	 * @param src
	 *            the input string to parse
	 * @param idx
	 *            the "cursor's" position, put 0 to begin a complete parsing
	 * @return a JSON Attribute representing a part of the input string.
	 * @throws JSONParsingException
	 *             if the string is not correctly wrote.
	 * @see #parse(java.lang.String src)
	 */
	@Override
	public JSONAttribute read(String src, int idx) throws JSONParsingException {
		int currentPos = idx;
		int ascii;
		char c;
		int length = src.length();

		try {
			// attribute name
			k = src.indexOf(DBLQUOTE, currentPos);
			name = src.substring(currentPos, k);
			// separator
			k++;
			k = src.indexOf(SEPARATOR_ATTRIBUTE, k);
			// attribute value
			k++;
			while (k < length) {
				c = src.charAt(k);
				ascii = (int) c;

				if (c == DBLQUOTE || c == QUOTE) {
					// string expected
					k++;
					currentPos = k;
					while (k < length) {
						c = src.charAt(k);
						// expected end string
						if (c == DBLQUOTE || c == QUOTE) {
							k++;
							break;
						} else if (c == ANTISLASH) {
							k++;
							c = src.charAt(idx);
							if (c == UNICODE)
								k += 3;
						}
						k++;
					}
					value = src.substring(currentPos, k - 1);
					break;

				} else if (c == OPEN_BRACE) {
					// jsonobject expected
					k++;
					jo = new JSONObject();
					value = jo.read(src, k);
					k = jo.k;
					break;

				} else if (c == OPEN_BRACKET) {
					// jsonarray expected
					k++;
					ja = new JSONArray();
					value = ja.read(src, k);
					k = ja.k;
					break;

				} else if (ascii > 32) {
					// number boolean expected
					currentPos = k;
					k++;
					while (k < length) {
						c = src.charAt(k);
						if (c == SEPARATOR_LIST || c == CLOSE_BRACE
								|| c == CLOSE_BRACKET) {
							tmp = src.substring(currentPos, k).toLowerCase();
							if (tmp.indexOf("true") > -1)
								value = true;
							else if (tmp.indexOf("false") > -1)
								value = false;
							else if (tmp.indexOf("null") > -1)
								value = null;
							else if (tmp.indexOf(EXPMIN) == -1
									&& tmp.indexOf(SEPARATOR_FLOATING) == -1)
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
			throw new JSONParsingException("Invalid input JSON string. "
					+ ex.getCause() + " " + ex.getMessage(), k);
		}

		return this;
	}

	@Override
	public void toBuffer(StringBuffer s) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public final String toString() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}