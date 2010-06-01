/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.groupby.parser.json;

import org.groupby.parser.JSON.JSON;

/**
 *
 * @author stephane
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String sx ;



// simple JSONobject
                /* sx =
                "{" +
                "   \"unicode\": \"x\u0029x\", " +
                "   \"haha\": '\"test', " +
                "   \"f\": \"test\", " +
                "   \"\\tTOTAL\": \"I am \\\"a \\/test\", " +
                "   \"demo\": 55.23" +
                "}";*/
        //sx = "{\"A\": 545}";

//  JSONArray with simple JSONObject
        //sx =
        //       "[{\"record\": 987, \"name\": \"Community\"}]";
        //       01234567890123456789012345678901234567890123   44 bytes before escape (index from 0 to 43)
        //                                                      38 bytes after


//  JSONArray with simple JSONObject 3 items
//        sx =
//                "[ {\"record\": 1    , \"name\": \"test\"},    {\"record\": 987, \"name\": \"Community\"}, {\"record\": 999, \"name\": \"Group\"}]";

//  JSONArray with simple 3 JSONObject imbricated
//        sx =
//                "[{\"record\": 1, \"name\": {\"row\": {\"col\": \"colNAME\"}}}]";


//  JSONArray with JSONObject with a JSONArray imbricated (contains 2 JSONObject)
        //sx =
         //       "[{\"record\": 1, \"name\": [{\"row\": \"r1\"}, {\"row\": \"r2\"}] }]";

//  JSONArray 3
        //sx =
          //      "[[{\"row\": \"r2\"}, [{\"x\": 9898}]]]";

        sx = "{\"title\":\"some title\",\"sling:resourceType\":\"foo/bar\",\"jcr:primaryType\":\"nt:unstructured\"}";
       try {
            JSON o = null;
            Object j = null ;
            
            for (int i=0; i < 1; i++) {
                j = (JSONObject) JSON.parse(sx);
                System.out.println(" origin : " + i + " = " + sx);
                System.out.println(" result : " + i + " = " + j.toString());
                
            }
        } catch (JSONParsingException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        /*long xl = 589754565465L;
        int xi = 65465;
        double xd = 65455465.56588D;
        Object xn = null;

        for (int i=0; i < 3000; i++) {
            long nxl = ((Number) xl).longValue();
            System.out.println( "XL = " +  nxl);

            int nxi = ((Number) xi).intValue();
            System.out.println( "Xi = " +  nxi);

            double nxd = ((Number) xd).doubleValue();
            System.out.println( "Xd = " +  nxd);

            Object nxn = ((Number) xn);
            System.out.println( "Xnull = " +  nxn);
        }*/

        /*Long xl = 589754565465L;
        Integer xi = 65465;
        Double xd = 65455465.56588D;
        Object xn = null;

        for (int i=0; i < 1; i++) {
            Long nxl = (xl == null ? 0 : xl);
            System.out.println( "XL = " +  nxl);

            Integer nxi = (xi == null ? 0 : xi);
            System.out.println( "Xi = " +  nxi);

            Double nxd = (xd == null ? 0 : xd);
            System.out.println( "Xd = " +  nxd);

            Object nxn = (xn == null ? null : xn);
            System.out.println( "Xnull = " +  nxn);
        }*/

    }

}
