package com.company;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class HospitalBTest {
    HospitalB tester=new HospitalB();

    @Test
    public void addPatient() {
        Patient p=new Patient("Waleed","Jubeh");
        tester.addPatient(p);
        Assert.assertEquals( tester.getPatients().get(0),p);

    }

    @Test
    public void getPatients() {
        tester.addPatient(new Patient("Waleed","Jubeh"));
        tester.addPatient(new Patient("Ahmad","Karaki"));
        tester.addPatient(new Patient("Tameem","Tamimi"));
        Assert.assertTrue(tester.getPatients().size()==3);

    }

    @Test
    public void importJSON_test1() {
        tester.importJSON("testData.json");

    }
    @Test
    public void importJSON_test2() {
        tester.importJSON("nothing.json");

    }
    @Test
    public void exportJSON_test1() {
        tester.addPatient(new Patient("Waleed","Jubeh"));
        tester.addPatient(new Patient("Ahmad","Karaki"));
        tester.addPatient(new Patient("Tameem","Tamimi"));
        try{
            tester.exportJSON("testData");
            Assert.assertTrue(true);

        }catch (Exception e){
            Assert.assertTrue("Exception Error",false);

        }


    }
    @Test
    public void exportJSON_test2() {
        tester.addPatient(new Patient("Waleed","Jubeh"));
        tester.addPatient(new Patient("Ahmad","Karaki"));
        tester.addPatient(new Patient("Tameem","Tamimi"));
        try{
            tester.exportJSON(null);
            Assert.assertTrue(true);

        }catch (Exception e){
            Assert.assertTrue("Exception Error",false);

        }



    }
}