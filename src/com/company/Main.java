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
        hoso.ExportXML("data");
        HospitalB hospitalb=new HospitalB();
        XML_JSON_Adapter adapeter=new XML_JSON_Adapter(hospitalb);
        adapeter.ImportXML("data.xml");
    }
}
