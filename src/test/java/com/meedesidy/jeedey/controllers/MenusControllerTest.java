package com.meedesidy.jeedey.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.meedesidy.jeedey.entity.Menu;
import com.meedesidy.jeedey.mapper.MenuMapper;
import com.meedesidy.jeedey.service.MenuService;
import com.meedesidy.jeedey.utils.ErrorStrings;

public class MenusControllerTest extends JeedeyApplicationTest {

	@Autowired
	private MenusController menusController;

	@Autowired
	private MenuMapper menuMapper;

	@Autowired
	private MenuService menuService;

	private MockMvc mockMvc;

	private int initMenuNum = 2;

	private final String contentPath = "menus";

	private final String resultUrlIndex = "menus/index";
	
	private final String resultUrlNew = "menus/new";

	@Before
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	/**
	 * 初始化首页
	 * 
	 * @throws Exception
	 */
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void indexSuccess() throws Exception {
		// 执行查询方法（地址） 获取返回值
		MvcResult result = mockMvc.perform(get("/menus")).andExpect(status().is3xxRedirection()).andReturn();
		// 判断查询条数
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE,
				((PageInfo<Menu>) (result.getModelAndView().getModel().get("pageInfo"))).getList()
						.size() == this.initMenuNum);
		// 断言返回页面
		Assert.assertTrue(ErrorStrings.RESPONSE_VIEW_NAME,
				result.getModelAndView().getViewName().equals(resultUrlIndex));
		result.getModelAndView().getModelMap().containsKey("menu");
	}

	/**
	 * 測試内容路径
	 * 
	 */
	@Test
	public void testGetContentPath() {
		Assert.assertTrue(ErrorStrings.CONTENT_PATH, contentPath.equals(menusController.getContentPath()));
	}

	/**
	 * 测试编辑
	 */
	@Test
	public void testEdit() throws Exception {
		// 定义参数
		MultiValueMap<String, String> map = new HttpHeaders();
		map.add("id", "7");
		map.add("name", "杭州");
		map.add("level", "1");
		map.add("description", "杭州西湖");
		// 执行插入方法（地址）（参数）获取 返回值
		MvcResult result = mockMvc.perform(get("/menus").params(map)).andExpect(status().is3xxRedirection())
				.andReturn();
		Assert.assertTrue(ErrorStrings.RESPONSE_VIEW_NAME,
				result.getModelAndView().getViewName().equals("menus/index"));

	}

	/**
	 * 测试删除
	 * 
	 * @throws Exception
	 */

	@Test
	@Transactional
	@SuppressWarnings("unused")
	public void testDelete() throws Exception {
		// 定义参数
		MultiValueMap<String, String> map = new HttpHeaders();
		map.add("id", "2");
		// 删除之前断言的数量
		Integer count = dataBaseUtils.countTable("select count(*) totalCount from menus where status = 1");
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE, count == initMenuNum);
		MvcResult result = mockMvc.perform(get("/menus/" + map.getFirst("id") + "?_pjax=%5Bdata-pjax-container%5D"))
				.andExpect(status().is3xxRedirection()).andReturn();
		// 删除之后断言数量
		count = dataBaseUtils.countTable("select count(*) totalCount from menus where status = 1");
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE, count == initMenuNum - 1);
	}

	/**
	 * 测试菜单的插入
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIndexModelMenu() throws Exception {
		// 定义参数
		MultiValueMap<String, String> map = new HttpHeaders();
		map.add("id", "6");
		map.add("name", "杭州");
		map.add("level", "1");
		map.add("status", "0");
		// 获取执行插入方法前的数据数量
		Integer count = dataBaseUtils.countTable("select count(*) totalCount from menus where status = 1");
		// 判断插入前数量
	    Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE, count == initMenuNum);
	 // 执行插入方法（地址）（参数）获取返回值
	 	MvcResult result = mockMvc.perform(post("/menus").params(map)).andExpect(status().is3xxRedirection())
	 				.andReturn();	
		// 查询获取插入方法后的数据数量
		count = dataBaseUtils.countTable("select count(*) totalCount from menus where status = 1");
		// 判断插入后数量
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE, count == initMenuNum);
	   // 断言返回页面
	   Assert.assertTrue(ErrorStrings.RESPONSE_VIEW_NAME, result.getModelAndView().getViewName().equals(resultUrlNew));
	}

	/**
	 * 测试创建菜单
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateModelMenu() throws Exception {
		MultiValueMap<String, String> map = new HttpHeaders();
		map.add("name", "顺丰快递");
		map.add("level", "1");
		map.add("id", "6");
		MvcResult result = mockMvc.perform(get("/menus").params(map)).andExpect(status().is3xxRedirection())
				.andReturn();
		result.getModelAndView().getModelMap().containsKey("menu");
	}

	@Test
	@SuppressWarnings("unused")
	public void testUpdateModelMenuHttpServletResponse() throws Exception {
		// 定义参数
		MultiValueMap<String, String> map = new HttpHeaders();
		map.add("id", "1");
		map.add("name", "菜单");
		MvcResult result = mockMvc.perform(post("/menus/" + map.getFirst("id")).params(map))
				.andExpect(status().is3xxRedirection()).andReturn();
		
	}

	@Test
	public void testGetService() {
		Assert.assertTrue(ErrorStrings.RESPONSE_CONTENT_SERVICE, menuMapper == menuService.getMapper());
	}

}
