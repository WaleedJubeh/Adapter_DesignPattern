package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;


public class HospitalA  implements  Import_Export{
    ArrayList<Patient> patients=new ArrayList<>();

    public void addPatient(Patient p)
    {
        patients.add(p);
    }

    @Override
    public void ExportXML(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(fileName+".xml", "UTF-8");
        writer.println("<Patients>");
        for (int i=0;i<patients.size();i++)
        {
            writer.println("\t<Patient>");
            writer.print("\t\t<FirstName>");
            writer.print(patients.get(i).getFirstName());
            writer.println("</FirstName>");
            writer.print("\t\t<LastName>");
            writer.print(patients.get(i).getLastName());
            writer.println("</LastName>");
            writer.println("\t</Patient>");

        }
        writer.println("</Patients>");

        writer.close();

    }

    @Override
    public void ImportXML(String path)  {


        String file=readFile(path);
        String Patients[]=file.split("<Patients>");
        String[] openAttribute = {"<FirstName>", "<LastName>"};
        String[] closeAttribute = {"</FirstName>", "</LastName>"};

        for (int i=0;i<Patients.length;i++)
        {
            if(Patients[i].trim().length()==0)
                continue;
            String []patientBlocks=Patients[i].split("<Patient>");
            for (int j=0;j<patientBlocks.length;j++) {
                ArrayList<String> temp=new ArrayList<>();
                for (int attrib = 0; attrib < openAttribute.length; attrib++) {
                    try {
                        String s = patientBlocks[j].substring(patientBlocks[j].indexOf(openAttribute[attrib]) + openAttribute[attrib].length());
                        s = s.substring(0, s.indexOf(closeAttribute[attrib]));
                        temp.add(s);
                    }catch (StringIndexOutOfBoundsException e)
                    {

                    }
                }
                if(temp.size()==2)
                {
                    addPatient(new Patient(temp.get(0),temp.get(1)));
                }
            }

        }
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }
    private String readFile(String path)  {
        String result = "";

        try {
            Stream<String> x = Files.lines(Paths.get(path), StandardCharsets.UTF_8);
            Object[] lines = x.toArray();
            for (int i = 0; i < lines.length; i++) {
                result += lines[i];
            }
        }catch (IOException e)
        {
            System.out.println("File not found");
        }
        return result;
    }
}

