package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;


public class XML_JSON_Adapter implements Import_Export {
    HospitalB hospitalB = null;

    public XML_JSON_Adapter(HospitalB hospitalB) {
        this.hospitalB = hospitalB;
    }

    @Override
    public void ExportXML(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        hospitalB.exportJSON(fileName);

        try {
            PrintWriter writer = new PrintWriter(fileName+".xml");
            writer.println("<Patients>");
            String file = readFile(fileName + ".json");
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
                        writer.print("<Patient>");
                        writer.print("<FirstName>");
                        writer.print(temp_data.get(0));
                        writer.println("</FirstName>");
                        writer.print("<LastName>");
                        writer.print(temp_data.get(1));
                        writer.println("</LastName>");
                        writer.print("</Patient>");

                    }
                }
            }
            writer.println("</Patients>");

            writer.close();
        } catch (IOException e) {
            System.out.println("File not Found");
            return;
        }


    }

    @Override
    public void ImportXML(String path) {
        try {
            String file = readFile(path);
            String[] patientBlock = file.split("<Patients>");
            PrintWriter writer = new PrintWriter("temp_data.json", StandardCharsets.UTF_8);
            //Found the data
            String[] openAttribute = {"<FirstName>", "<LastName>"};
            String[] closeAttribute = {"</FirstName>", "</LastName>"};
            writer.println("{");

            for (int i = 0; i < patientBlock.length; i++) {
                if (patientBlock[i].trim().length() == 0)
                    continue;
                writer.println("\"Patients\": [");
                String[] patientsData = patientBlock[i].split("<Patient>");
                for (int patient = 0; patient < patientsData.length; patient++) {
                    if (patientsData[patient].trim().length() == 0)
                        continue;
                    writer.println("{");
                    for (int attrib = 0; attrib < openAttribute.length; attrib++) {
                        try {
                            String s = patientsData[patient].substring(patientsData[patient].indexOf(openAttribute[attrib]) + openAttribute[attrib].length());
                            s = s.substring(0, s.indexOf(closeAttribute[attrib]));
                            writer.println("\"" + openAttribute[attrib].substring(1, openAttribute[attrib].length() - 1) + "\":" + "\"" + s + "\",");

                        } catch (StringIndexOutOfBoundsException e) {
                        }
                    }
                    writer.println("},");
                }
                writer.println("]");

            }
            writer.println("}");
            writer.close();
        } catch (IOException e) {
            System.out.println("File not found");
            System.exit(0);
        }
        hospitalB.importJSON("temp_data.json");

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
