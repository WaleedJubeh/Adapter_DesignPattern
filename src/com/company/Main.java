package com.company;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
	// write your code here
        HospitalA hoso=new HospitalA();
        Patient a =new Patient("Waleed","Jubeh");
        hoso.addPatient(a);
        Patient n =new Patient("Ahmad","Karaki");
        hoso.addPatient(n);

        HospitalB hospitalb=new HospitalB();
        hospitalb.addPatient(new Patient("Basil","Tamimi"));
        hospitalb.addPatient(new Patient("SOSO","Amar"));
        hoso.ExportXML("Adata");

        XML_JSON_Adapter adapeter=new XML_JSON_Adapter(hospitalb);
        adapeter.ExportXML("Bdata");
        adapeter.ImportXML("Adata.xml");
        hoso.ImportXML("Bdata.xml");

        System.out.println("Patients in Hospital A");
        for(int i=0;i<hoso.getPatients().size();i++)
            System.out.println(hoso.getPatients().get(i));
        System.out.println("Patients in Hospital B");
        for(int i=0;i<hospitalb.getPatients().size();i++)
            System.out.println(hospitalb.getPatients().get(i));


    }
}
