/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.groupby.parser.json;

import org.groupby.parser.JSON.JSON;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author stephane
 */
public class JSONTest {

    public JSONTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of parse method, of class JSON.
     */
    @Test
    public void testParse() throws Exception {
        System.out.println("parse");
        String sx =        "{\"title\":\"some title\",\"sling:resourceType\":\"foo/bar\",\"jcr:primaryType\":\"nt:unstructured\"}";
        String expResult = "{\"title\": \"some title\", \"sling:resourceType\": \"foo/bar\", \"jcr:primaryType\": \"nt:unstructured\"}";
        String result = JSON.parse(sx).toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    public class JSONImpl extends JSON {

        public JSON read() throws JSONParsingException {
            return null;
        }

        public void toBuffer(StringBuffer s) {
        }

        @Override
        public JSON read(String src, int idx) throws JSONParsingException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public JSON read(String src) throws JSONParsingException {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

}