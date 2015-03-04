/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import json.JsonUtil;
import model.Expert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zsolti
 */
public class JsonUtilTest {
    
    public JsonUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testWriteObject() {
    Expert expert = new Expert();
    
    expert.setName("expert");
    expert.setStep(2.0);
    expert.setIsCloseMaTakeProfit(Boolean.TRUE);
    expert.setMaClosePeriod(Integer.MIN_VALUE);
    
        JsonUtil.writeObject(expert,expert.getName());
    }
    
    @Test
    public void testReadObject() {
       Expert expert = (Expert) JsonUtil.readObject("expert", Expert.class);
        System.out.println(expert.toString());
    }
    
    @Test
    public void testReadNonExistObject() {
       Expert expert = (Expert) JsonUtil.readObject("exp", Expert.class);
        System.out.println(expert);
    }
}
