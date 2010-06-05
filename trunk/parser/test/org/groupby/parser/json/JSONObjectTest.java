/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.groupby.parser.json;

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
public class JSONObjectTest {

    private static String inputstring = "{\"string\": \"this is a String\", \"long\": 98948949999999999, \"double\": 1.2345678955E8, \"int\": 56789, \"bool\": false, \"title\": \"some title\", \"sling:resourceType\": \"foo/bar\", \"jcr:primaryType\": \"nt:unstructured\", \"jsonobjecthere\": {\"row\": 1}}";
    private static String inputstring2 = "{\"title\": [{\"row\": 1}, {\"row\": 2}]}";

    public JSONObjectTest() {
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
     * Test of read method, of class JSONObject.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        JSONObject instance = new JSONObject();
        String expResult = inputstring;
        String result = instance.read(inputstring).toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class JSONObject.
     */
    @Test
    public void testGet() throws Exception {
        System.out.println("get");
        JSONObject instance = new JSONObject();
        instance.read(inputstring);
        String expResult = "some title";
        String result = (String) instance.get("title");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getJSONObject method, of class JSONObject.
     */
    @Test
    public void testGetJSONObject() throws Exception {
        System.out.println("getJSONObject");
        JSONObject instance = (JSONObject) new JSONObject().read(inputstring);
        JSONObject expResult = (JSONObject) new JSONObject();
        expResult.put("row",  1);

        JSONObject result = instance.getJSONObject("jsonobjecthere");
        assertEquals(expResult.toString(), result.toString());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getJSONArray method, of class JSONObject.
     */
    @Test
    public void testGetJSONArray() throws JSONParsingException {
        System.out.println("getJSONArray");
        JSONObject instance = (JSONObject) new JSONObject().read(inputstring2);
        
        JSONArray expResult = new JSONArray();
        JSONObject jo1 = new JSONObject();
        jo1.put("row", 1);
        JSONObject jo2 = new JSONObject();
        jo2.put("row", 2);
        expResult.put(jo1);
        expResult.put(jo2);

        JSONArray result = instance.getJSONArray("title");
        assertEquals(expResult.toString(), result.toString());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getBoolean method, of class JSONObject.
     */
    @Test
    public void testGetBoolean() throws JSONParsingException {
        
        System.out.println("getBoolean");
        JSONObject instance = new JSONObject();
        instance.read(inputstring);
        Boolean expResult = false;
        Boolean result = instance.getBoolean("bool");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");        
    }

    /**
     * Test of getInt method, of class JSONObject.
     */
    @Test
    public void testGetInt() throws JSONParsingException {
        System.out.println("getInt");
        JSONObject instance = new JSONObject();
        instance.read(inputstring);
        Integer expResult = 56789;
        Integer result = instance.getInt("int");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDouble method, of class JSONObject.
     */
    @Test
    public void testGetDouble() throws JSONParsingException {
        System.out.println("getDouble");
        JSONObject instance = new JSONObject();
        instance.read(inputstring);
        Double expResult = 1.2345678955E8;
        Double result = instance.getDouble("double");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getLong method, of class JSONObject.
     */
    @Test
    public void testGetLong() throws JSONParsingException {
        System.out.println("getLong");
        JSONObject instance = new JSONObject();
        instance.read(inputstring);
        Long expResult = 98948949999999999L;
        Long result = instance.getLong("long");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getString method, of class JSONObject.
     */
    @Test
    public void testGetString() throws JSONParsingException {
        System.out.println("getString");
        JSONObject instance = new JSONObject();
        instance.read(inputstring);
        String expResult = "this is a String";
        String result = instance.getString("string");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}