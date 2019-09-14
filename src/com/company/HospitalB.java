package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class HospitalB {

    ArrayList<Patient> patients=new ArrayList<>();

    public void addPatient(Patient p)
    {
        patients.add(p);
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void importJSON(String path) {
        try {
            String file = readFile(path);
            String[] patientBlock = file.split("\"Patients\":");

            for (int i = 0; i < patientBlock.length; i++) {
                if (patientBlock[i].trim().length() == 0)
                    continue;
                //Get Patient data
                String[] patients = patientBlock[i].split("},");
                for (int patient = 0; patient < patients.length; patient++) {
                    String[] attributes = patients[patient].split(",");

                    //Now store the data temproraliy
                    ArrayList<String> temp_data = new ArrayList<String>();
                    for (int attrib = 0; attrib < attributes.length; attrib++) {
                        try {
                            int Beginindex = attributes[attrib].indexOf(":") + 2;
                            int Endindex = attributes[attrib].lastIndexOf("\"");
                            String data = attributes[attrib].substring(Beginindex, Endindex);
                            temp_data.add(data);
                        } catch (StringIndexOutOfBoundsException e) {

                        }
                    }
                    if (temp_data.size() == 2) {
                        Patient p = new Patient(temp_data.get(0), temp_data.get(1));
                        addPatient(p);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File not Found");
            return;
        }

    }

    public void exportJSON(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName + ".json", StandardCharsets.UTF_8);
            writer.println("{");
            writer.println("\"Patients\": [ ");
            for (int i = 0; i < patients.size(); i++) {
                writer.println("{");
                writer.println("\"FirstName\":" + "\"" + patients.get(i).getFirstName() + "\",");
                writer.println("\"LastName\":" + "\"" + patients.get(i).getLastName() + "\",");
                writer.println("},");
            }
            writer.println("]\t}");
            writer.close();
        } catch (IOException e) {

        }

    }


    private String readFile(String path) throws IOException {
        Stream<String> x = Files.lines(Paths.get(path), StandardCharsets.UTF_8);
        Object[] lines = x.toArray();
        String result = "";
        for (int i = 0; i < lines.length; i++) {
            result += lines[i];
        }
        return result;
    }
}
