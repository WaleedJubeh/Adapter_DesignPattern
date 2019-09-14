package com.company;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class XML_JSON_Adapter implements  Import_Export{
    HospitalB hospitalB=null;
    public XML_JSON_Adapter(HospitalB hospitalB){
        this.hospitalB=hospitalB;
    }
    @Override
    public void ExportXML(String fileName) throws FileNotFoundException, UnsupportedEncodingException {

    }

    @Override
    public void ImportXML(String path)  {
        try{
            String file=readFile(path);
            String patientBlock[]=file.split("<Patients>");

            //Found the data
            String openAttribute[]={"<FirstName>","<LastName>"};
            String closeAttribute[]={"</FirstName>","</LastName>"};
            for (int i=0;i<patientBlock.length;i++)
            {
                String patientsData[]=patientBlock[i].split("<Patient>");
                for (int patient =0 ;patient<patientsData.length;patient++)
                {
                        for(int attrib =0;attrib<openAttribute.length;attrib++)
                        {
                            try {
                                String s = patientsData[patient].substring(patientsData[patient].indexOf(openAttribute[attrib]) + openAttribute[attrib].length());
                                s = s.substring(0, s.indexOf(closeAttribute[attrib]));
                                System.out.println(s);
                            }catch (StringIndexOutOfBoundsException e){};
                        }
                }
            }
            System.out.println("I'm ready to read the file");
        }catch (IOException e) {
            System.out.println("File not found");
            System.exit(0);
        }


    }

    private String readFile(String path) throws IOException {
        Stream<String> x= Files.lines(Paths.get(path), StandardCharsets.UTF_8);
        Object[] lines= x.toArray();
        String result="";
        for(int i=0; i<lines.length;i++)
        {
            result+=lines[i];
        }
        return result;
    }
}
