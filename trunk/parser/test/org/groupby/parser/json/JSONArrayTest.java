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
public class JSONArrayTest {

    public JSONArrayTest() {
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
     * Test of read method, of class JSONArray.
     */
    @Test
    public void testRead_String() throws Exception {
        System.out.println("read");
        String src = "";
        JSONArray instance = new JSONArray();
        JSON expResult = null;
        JSON result = instance.read(src);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class JSONArray.
     */
    @Test
    public void testRead_String_int() throws Exception {
        System.out.println("read");
        String src = "";
        int idx = 0;
        JSONArray instance = new JSONArray();
        JSONArray expResult = null;
        JSONArray result = instance.read(src, idx);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of put method, of class JSONArray.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        Object o = null;
        JSONArray instance = new JSONArray();
        instance.put(o);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class JSONArray.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int i = 0;
        JSONArray instance = new JSONArray();
        Object expResult = null;
        Object result = instance.get(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJSONObject method, of class JSONArray.
     */
    @Test
    public void testGetJSONObject() {
        System.out.println("getJSONObject");
        int i = 0;
        JSONArray instance = new JSONArray();
        JSONObject expResult = null;
        JSONObject result = instance.getJSONObject(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJSONArray method, of class JSONArray.
     */
    @Test
    public void testGetJSONArray() {
        System.out.println("getJSONArray");
        int i = 0;
        JSONArray instance = new JSONArray();
        JSONArray expResult = null;
        JSONArray result = instance.getJSONArray(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBoolean method, of class JSONArray.
     */
    @Test
    public void testGetBoolean() {
        System.out.println("getBoolean");
        int i = 0;
        JSONArray instance = new JSONArray();
        boolean expResult = false;
        boolean result = instance.getBoolean(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInt method, of class JSONArray.
     */
    @Test
    public void testGetInt() {
        System.out.println("getInt");
        int i = 0;
        JSONArray instance = new JSONArray();
        int expResult = 0;
        int result = instance.getInt(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDouble method, of class JSONArray.
     */
    @Test
    public void testGetDouble() {
        System.out.println("getDouble");
        int i = 0;
        JSONArray instance = new JSONArray();
        double expResult = 0.0;
        double result = instance.getDouble(i);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLong method, of class JSONArray.
     */
    @Test
    public void testGetLong() {
        System.out.println("getLong");
        int i = 0;
        JSONArray instance = new JSONArray();
        Long expResult = null;
        Long result = instance.getLong(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getString method, of class JSONArray.
     */
    @Test
    public void testGetString() {
        System.out.println("getString");
        int i = 0;
        JSONArray instance = new JSONArray();
        String expResult = "";
        String result = instance.getString(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}