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

import java.util.ArrayList;
import org.groupby.parser.JSON.JSON;

/**
 * <b>JSONArray</b> class
 * 
 * <p>
 * JSON Array class<br />
 * This class hold the JSON Array implementation.
 * 
 * </p>
 * 
 * author : members@groupby.org
 * 
 */
public class JSONArray extends JSON {

	// hold json array
	protected ArrayList<Object> items = new ArrayList<Object>();

	public int k = 0;
	private JSONArray ja;
	private JSONObject jo;

	/**
	 * JSONArray Constructor
	 */
	public JSONArray() {
	}

	/**
	 * Read the input string
	 * 
	 * @param src
	 * @return a JSON Array representing a part of the input string.
	 * @throws JSONParsingException
	 * @see #read(java.lang.String src, int)
	 */
	@Override
	public JSON read(String src) throws JSONParsingException {
		return read(src, 0);
	}

	/**
	 * Read a chunk of the input string, match JSON Array rules.
	 * <p>
	 * If the string is empty, the method returns null.<br />
	 * </p>
	 * 
	 * @param src
	 *            the input string to parse
	 * @param idx
	 *            the "cursor's" position, put 0 to begin a complete parsing
	 * @return a JSON Array representing a part of the input string.
	 * @throws JSONParsingException
	 *             if the string is not correctly wrote.
	 * @see #parse(java.lang.String src)
	 */
	@Override
	public JSONArray read(String src, int idx) throws JSONParsingException {
		boolean start = true;
		char c;
		int length = src.length();
		k = idx;
		while (k < length) {
			c = src.charAt(k);
			// expected jsonobject
			if (c == OPEN_BRACE) {
				k++;
				jo = new JSONObject();
				items.add(jo.read(src, k));
				k = jo.k;
			} else if (c == SEPARATOR_LIST || c == CLOSE_BRACKET
					|| c == CLOSE_BRACE) {
				k++;
			} else if (c == OPEN_BRACKET) {
				k++;
				if (start) {
					start = false;
				} else {
					// expected jsonarray
					ja = new JSONArray();
					items.add(ja.read(src, k));
					k = ja.k;
				}
			} else if ((int) c > 32) {
				throw new JSONParsingException(
						"Invalid input JSON string. Expected character: "
								+ DBLQUOTE + " found: " + c, k);
			} else
				k++;

		}

		return this;
	}

	/**
	 * Put to the JSON Array one attribute name-value pair
	 * 
	 * @param o
	 *            The object to add
	 */
	public final void put(Object o) {
		items.add(o);
	}

	/**
	 * Get an Object with the index
	 * 
	 * @param i
	 *            The index
	 * @return the Object linked to the key name. Null value possibly returned.
	 */
	public final Object get(int i) {
		return items.get(i);
	}

	/**
	 * Get an JSONObject with the index
	 * 
	 * @param i
	 *            The index
	 * @return the Object linked to the key name. Null value possibly returned.
	 */
	public final JSONObject getJSONObject(int i) {
		return (JSONObject) items.get(i);
	}

	/**
	 * Get an JSONObject with the index
	 * 
	 * @param i
	 *            The index
	 * @return the Object linked to the key name. Null value possibly returned.
	 */
	public final JSONArray getJSONArray(int i) {
		return (JSONArray) items.get(i);
	}

	/**
	 * Get a boolean with the index
	 * 
	 * @param i
	 *            The index
	 * @return the Object linked to the key name. Never returns null.
	 */
	public final boolean getBoolean(int i) {
		Boolean b = (Boolean) items.get(i);
		return (b == null ? false : b);
	}

	/**
	 * Get a int with the index
	 * 
	 * @param i
	 *            The index
	 * @return the Object linked to the key name. Never returns null.
	 */
	public final int getInt(int i) {
		Number o = (Number) items.get(i);
		return (o == null ? 0 : o.intValue());
	}

	/**
	 * Get a double with the index
	 * 
	 * @param i
	 *            The index
	 * @return the Object linked to the key name. Never returns null.
	 */
	public final double getDouble(int i) {
		Number o = (Number) items.get(i);
		return (o == null ? 0 : o.doubleValue());
	}

	/**
	 * Get a long with the index
	 * 
	 * @param i
	 *            The index
	 * @return the Object linked to the key name. Never returns null.
	 */
	public final Long getLong(int i) {
		Number o = (Number) items.get(i);
		return (o == null ? 0 : o.longValue());
	}

	/**
	 * Get a String with the index
	 * 
	 * @param i
	 *            The index
	 * @return the Object linked to the key name. Never returns null.
	 */
	public final String getString(int i) {
		Object o = items.get(i);
		return (o == null ? "" : (String) o);
	}

	@Override
	public void toBuffer(StringBuffer s) {
		s.append(OPEN_BRACKET);
		for (int i = 0; i < items.size(); i++) {
			if (i > 0) {
				s.append(SEPARATOR_LIST);
				s.append(SPACE);
			}
			s.append(items.get(i).toString());
		}
		s.append(CLOSE_BRACKET);
	}

	/**
	 * Print JSON Array.
	 * <p>
	 * This method calls all toString() method in a cascade manner.
	 * </p>
	 * 
	 * @return the chunk string matched with this object.
	 */
	@Override
	public final String toString() {
		StringBuffer s = new StringBuffer(items.size() * 20);
		toBuffer(s);
		return s.toString();
	}

}