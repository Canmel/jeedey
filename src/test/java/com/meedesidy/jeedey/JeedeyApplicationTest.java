package com.meedesidy.jeedey;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.meedesidy.jeedey.utils.DataBaseUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = JeedeyApplication.class)
public class JeedeyApplicationTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	public DataBaseUtils dataBaseUtils;
	
	@Autowired
    public WebApplicationContext context;
	
	@Before
	public void setUp() throws Exception {
		dataBaseUtils.runInitScript();
	}
	
	@Test
	public void test() {
		Integer te = 1;
		Assert.assertTrue("测试一个基类", te == 1);
	}
	
}
