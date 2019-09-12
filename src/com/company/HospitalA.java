package com.company;

import java.io.*;
import java.util.ArrayList;


public class HospitalA  implements  Import_Export{
    ArrayList<Patient> patients=new ArrayList<>();

    public void addPatient(Patient p)
    {
        patients.add(p);
    }

    @Override
    public void Export(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
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
    public void Import(String path) throws FileNotFoundException, UnsupportedEncodingException {

    }

}

