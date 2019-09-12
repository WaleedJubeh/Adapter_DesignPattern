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
        hoso.Export("data");

    }
}
