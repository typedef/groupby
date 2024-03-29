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

/**
 * 
 * @author stephane
 */
public class JSONParsingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int pos;

	/**
	 * Constructs a new JSON parsing exception
	 * 
	 * @param message
	 *            a human readable error message.
	 * @param pos
	 *            the position where the exception occured
	 */
	public JSONParsingException(String message, int pos) {
		super(message);
		this.pos = pos;
	}

	@Override
	public String getMessage() {
		String s = super.getMessage();
		if (pos > -1)
			return s + " near position: " + pos;
		return s;
	}

	@Override
	public String getLocalizedMessage() {
		return super.getLocalizedMessage();
	}
}
