package com.company;
import java.io.*;
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
            PrintWriter writer =new PrintWriter("data.json","UTF-8");
            //Found the data
            String openAttribute[]={"<FirstName>","<LastName>"};
            String closeAttribute[]={"</FirstName>","</LastName>"};
            writer.println("{");

            for (int i=0;i<patientBlock.length;i++)
            {
                if(patientBlock[i].trim().length()==0)
                     continue;
                writer.println("\"Patients\": [");
                String patientsData[]=patientBlock[i].split("<Patient>");
                for (int patient =0 ;patient<patientsData.length;patient++)
                {
                    if(patientsData[patient].trim().length()==0)
                        continue;
                    writer.println("{");
                        for(int attrib =0;attrib<openAttribute.length;attrib++)
                        {
                            try {
                                String s = patientsData[patient].substring(patientsData[patient].indexOf(openAttribute[attrib]) + openAttribute[attrib].length());
                                s = s.substring(0, s.indexOf(closeAttribute[attrib]));
                                System.out.println(s);
                                writer.println("\""+ openAttribute[attrib].substring(1,openAttribute[attrib].length()-1)+"\":"+"\""+s+"\",");

                            }catch (StringIndexOutOfBoundsException e){};
                        }
                    writer.println("},");
                }
                writer.println("]");

            }
            writer.println("}");
            writer.close();
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
