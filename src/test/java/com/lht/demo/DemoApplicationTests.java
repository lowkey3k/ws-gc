package com.lht.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {




    @Test
	public void contextLoads() {
         System.out.println("nihao");
    }

    @Before
    public void Sout(){
        System.out.println("before");
    }
    @After
    public void out(){
        System.out.println("after");
    }
}
