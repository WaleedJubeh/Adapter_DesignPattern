package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class XML_JSON_AdapterTest {
    HospitalB hospital=new HospitalB();
    XML_JSON_Adapter adapter =new XML_JSON_Adapter(hospital);
    @Test
    public void exportXML_test1() {
        hospital.addPatient(new Patient("Waleed","Jubeh"));
        hospital.addPatient(new Patient("Ahmad","Karaki"));
        hospital.addPatient(new Patient("Tameem","Tamimi"));
        try {
            adapter.ExportXML("TestAdapter");
            assertTrue(true);

        }catch (Exception e)
        {
            System.out.printf("Error");
            assertTrue(false);

        }

    }
    @Test
    public void exportXML_test2() {
        hospital.addPatient(new Patient("Waleed","Jubeh"));
        hospital.addPatient(new Patient("Ahmad","Karaki"));
        hospital.addPatient(new Patient("Tameem","Tamimi"));
        try {
            adapter.ExportXML(null);
            assertTrue(true);

        }catch (Exception e)
        {
            System.out.printf("Error");
            assertTrue(false);

        }

    }
    @Test
    public void importXML_test1() {
        adapter.ImportXML("TestAdapter.xml");
        assertTrue(true);

    }
    @Test
    public void importXML_test2() {
        adapter.ImportXML(null);
        assertTrue(true);
    }
}