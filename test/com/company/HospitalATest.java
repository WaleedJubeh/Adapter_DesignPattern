package com.company;

import com.sun.source.tree.AssertTree;
import org.junit.Assert;
import  org.junit.Assert.*;
import  org.junit.Test;

public class HospitalATest {
    HospitalA tester=new HospitalA();
    @Test
    public void addPatient() {
        Patient p=new Patient("Waleed","Jubeh");
        tester.addPatient(p);
        Assert.assertEquals( tester.getPatients().get(0),p);
    }

    @Test
    public void exportXML() {
        tester.addPatient(new Patient("Waleed","Jubeh"));
        tester.addPatient(new Patient("Ahmad","Karaki"));
        tester.addPatient(new Patient("Tameem","Tamimi"));
        try{
            tester.ExportXML("testData");
            Assert.assertTrue(true);

        }catch (Exception e){
            Assert.assertTrue("Exception Error",false);

        }


    }

    @Test
    public void importXML_1() {
        tester.ImportXML("testData.xml");
    }

    @Test
    public void importXML_2() {
        tester.ImportXML("nothing.xml");
    }

    @Test
    public void getPatients() {
        tester.addPatient(new Patient("Waleed","Jubeh"));
        tester.addPatient(new Patient("Ahmad","Karaki"));
        tester.addPatient(new Patient("Tameem","Tamimi"));
        Assert.assertTrue(tester.getPatients().size()==3);
    }
}