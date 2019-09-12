package com.company;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class XML_JSON_Adapter implements  Import_Export{
    HospitalB hospitalB=new HospitalB();
    private JSONParser parser=new JSONParser();

    String readFile(String path) throws IOException {
        Stream<String> x= Files.lines(Paths.get(path), StandardCharsets.UTF_8);
        Object[] lines= x.toArray();
        String result="";
        for(int i=0; i<lines.length;i++)
        {
            result+=lines[i];
        }
        return result;
    }

    @Override
    public void Export(String fileName) {

    }

    private String parseJSONUnit(Object  innerString ) throws  ParseException {
        String result = "";
        if (innerString instanceof JSONArray)
        {
            Iterator it =((Collection) innerString).iterator();
            while(it.hasNext())
                result+=parseJSONUnit((Object) it.next());
            // if ts an Array do a new round (Recursive) until find An object
        }
        else {
            Iterator it = ((JSONObject)(innerString)).entrySet().iterator();
            while (it.hasNext()) {
                Entry q = (Entry) it.next();

                result += (q.getKey() + ": " + q.getValue() + "\n");
            }
            result += "\n";


        }
        return result;
    }


    @Override
    public void Import(String path) {
        String json_String= null;
        try {
            json_String = readFile(path);
        } catch (IOException e) {
            System.out.println("File not found");
            return;
        }
        Object obj = null;
        try
        {
            obj = parser.parse(json_String);
        } catch (ParseException e) {
            System.out.println("Cant parse this file , Its not in JSON format");
            return ;
        }
        JSONObject array = (JSONObject)obj;
        int numberOfObject=array.size();
        System.out.println();
        Iterator keys= array.keySet().iterator();
        Collection data = array.values();
        Iterator innerIterator=data.iterator();
        try {
            FileWriter output = new FileWriter(result);
            for(int i=0;i<numberOfObject;i++)
            {
                String type= ((String) keys.next());
                //Capitilize the first letter of the type
                type=type.substring(0,1).toUpperCase() + type.substring(1).toLowerCase();
                //print all data of this type
                output.write("Type: " + type + "\n");
                output.write("------------------\n");
                output.write(parseJSONUnit(innerIterator.next()));
            }
            System.out.println("Parse Done check file "+result);
            output.close();
        } catch (IOException | ParseException d) {
            System.out.println("Cant parse this file , Its not in JSON format or its a Complex one");
            return ;
        }




    }
}
