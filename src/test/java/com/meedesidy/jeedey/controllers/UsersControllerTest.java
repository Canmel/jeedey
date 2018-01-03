package com.meedesidy.jeedey.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import com.github.pagehelper.PageInfo;
import com.meedesidy.jeedey.JeedeyApplicationTest;
import com.meedesidy.jeedey.entity.User;
import com.meedesidy.jeedey.utils.ErrorStrings;

public class UsersControllerTest extends JeedeyApplicationTest {

	private MockMvc mockMvc;
	
	private int initUserNum = 4;
	
	@Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void indexSuccess() throws Exception {
		MvcResult result = mockMvc.perform(get("/users")).andExpect(status().is3xxRedirection()).andReturn();
		Assert.assertTrue(ErrorStrings.RESPONSE_VIEW_NAME, result.getModelAndView().getViewName().equals("users/index"));
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE, ((PageInfo<User>)(result.getModelAndView().getModel().get("pageInfo"))).getList().size() == this.initUserNum);
		result.getModelAndView().getModelMap().containsKey("user");
	}
	
	@Test
	public void indexWithParams() {
		
	}
	
	@Test
	@Transactional
	public void saveSuccess() throws Exception {
		MultiValueMap<String, String> map = new HttpHeaders();
		map.add("name", "indexTest");
		map.add("email", "test@tt.com");
		map.add("phone", "13909090909");
//		插入之前断言数量
		Integer count= dataBaseUtils.countTable("select count(*) totalCount from users where status = 1");
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE ,count == initUserNum);
		MvcResult result = mockMvc.perform(post("/users").params(map)).andExpect(status().is3xxRedirection()).andReturn();
//		断言返回页面
		Assert.assertTrue(ErrorStrings.RESPONSE_VIEW_NAME, result.getModelAndView().getViewName().equals("users/index"));
//		插入之后断言数量
		count= dataBaseUtils.countTable("select count(*) totalCount from users where status = 1");
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE, count == initUserNum + 1);
		String  lastValue = dataBaseUtils.getLastStringColumValue("select * from users order by id desc limit 1", "email");
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_TYPE, lastValue.equals(map.get("email").get(0)));;
	}
	
	@Test
	@Transactional
	public void saveValidFaild() throws Exception {
		
	}
	
	@Test
	@Transactional
	public void saveValidWithSameEmail() throws Exception {
		
	}
	
	@Test
	public void editSuccess() {
		
	}
	
	@Test
	public void editWithWrongArgs() {
		
	}
	
	@Test
	public void createSuccess() {
		
	}
	
	@Test
	public void updateSuccess() {
		
	}
	
	@Test
	public void updateValidFaild() {
		
	}
	
	@Test 
	public void updateWithSameEmail() {
		
	}
	
	@Test
	public void deleteSuccess() {
		
	}
	
	@Test
	public void getContentPathSuccess() {
		
	}

}
