package com.company;

import java.util.ArrayList;

public class HospitalA  implements  JSON_XML_HospitalA_Adapter{
    ArrayList<Patient> patients=new ArrayList<>();

    public void addPatient(Patient p)
    {
        patients.add(p);
    }

    @Override
    public void Export(String fileName) {

    }

    @Override
    public void Import(String path)
    {

    }
}
