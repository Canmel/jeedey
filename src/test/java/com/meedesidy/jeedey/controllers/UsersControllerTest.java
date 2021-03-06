package com.meedesidy.jeedey.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;

import com.github.pagehelper.PageInfo;
import com.meedesidy.jeedey.JeedeyApplicationTest;
import com.meedesidy.jeedey.entity.User;
import com.meedesidy.jeedey.utils.ErrorStrings;

public class UsersControllerTest extends JeedeyApplicationTest {

	private MockMvc mockMvc;
	@Autowired
	private UsersController usersController;

	private int initUserNum = 4;
	// private int initInvalidUserNum = 3;

	private final String contentPath = "users";
	private final String resultUrlIndex = "users/index";
	private final String resultUrlEdit = "users/edit";
	private final String resultUrlNew = "users/new";

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
		MvcResult result = mockMvc.perform(get("/users")).andExpect(status().is3xxRedirection()).andReturn();
		// 判断查询条数
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE,
				((PageInfo<User>) (result.getModelAndView().getModel().get("pageInfo"))).getList()
						.size() == this.initUserNum);
		// 断言返回页面
		Assert.assertTrue(ErrorStrings.RESPONSE_VIEW_NAME,
				result.getModelAndView().getViewName().equals(resultUrlIndex));
		result.getModelAndView().getModelMap().containsKey("user");
	}

	/**
	 * 初始化首页，带参（条件查询）
	 * 
	 * @throws Exception
	 */
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void indexWithParams() throws Exception {
		// 定义参数
		MultiValueMap<String, String> map = new HttpHeaders();
		map.add("name", "Jack");
		map.add("email", "ck@test.c");
		map.add("phone", "8789");
		// 执行插入方法（地址）（参数）获取返回值
		MvcResult result = mockMvc.perform(get("/users").params(map)).andExpect(status().is3xxRedirection())
				.andReturn();
		// 判断查询项
		User user = (User) result.getModelAndView().getModel().get("searchEntity");
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("phone").equals(user.getPhone()));
		// 判断查询条数
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE,
				((PageInfo<User>) (result.getModelAndView().getModel().get("pageInfo"))).getList().size() == 1);
		// 判断返回数据
		user = ((PageInfo<User>) (result.getModelAndView().getModel().get("pageInfo"))).getList().get(0);
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("name").equals(user.getName()));
		// 断言返回页面
		Assert.assertTrue(ErrorStrings.RESPONSE_VIEW_NAME,
				result.getModelAndView().getViewName().equals(resultUrlIndex));
		result.getModelAndView().getModelMap().containsKey("user");
	}

	/**
	 * 进入新建页面
	 * 
	 * @throws Exception
	 */
	@Test
	@Transactional
	public void createSuccess() throws Exception {
		// 根据ID查询数据
		MvcResult result = mockMvc.perform(get("/users/new")).andExpect(status().is3xxRedirection()).andReturn();
		// 判断查询结果是否正确
		User user = (User) result.getModelAndView().getModel().get("user");
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, user.getId() == null);
		// 断言返回页面
		Assert.assertTrue(ErrorStrings.RESPONSE_VIEW_NAME, result.getModelAndView().getViewName().equals(resultUrlNew));
	}

	/**
	 * 进入编辑页面
	 * 
	 * @throws Exception
	 */
	@Test
	@Transactional
	public void editSuccess() throws Exception {
		// 定义参数
		MultiValueMap<String, String> map = new HttpHeaders();
		map.add("id", "1");
		// 根据ID查询数据
		MvcResult result = mockMvc.perform(get("/users/" + map.getFirst("id") + "/edit").params(map))
				.andExpect(status().is3xxRedirection()).andReturn();
		// 判断查询结果是否正确
		User user = (User) result.getModelAndView().getModel().get("user");
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("id").equals(user.getId().toString()));
		// 断言返回页面
		Assert.assertTrue(ErrorStrings.RESPONSE_VIEW_NAME,
				result.getModelAndView().getViewName().equals(resultUrlEdit));
	}

	/**
	 * 保存成功
	 * 
	 * @throws Exception
	 */
	@Test
	@Transactional
	public void saveSuccess() throws Exception {
		// 定义参数
		MultiValueMap<String, String> map = new HttpHeaders();
		map.add("name", "indexTest");
		map.add("email", "test@tt.com");
		map.add("phone", "13909090909");
		// 获取执行插入方法前的数据数量
		Integer count = dataBaseUtils.countTable("select count(*) totalCount from users where status = 1");
		// 判断插入前数量
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE, count == initUserNum);
		// 执行插入方法（地址）（参数）获取 返回值
		MvcResult result = mockMvc.perform(post("/users").params(map)).andExpect(status().is3xxRedirection())
				.andReturn();
		// 查询获取插入方法后的数据数量
		count = dataBaseUtils.countTable("select count(*) totalCount from users where status = 1");
		// 判断插入后数量
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE, count == initUserNum + 1);
		String lastValue = dataBaseUtils.getLastStringColumValue("select * from users order by id desc limit 1",
				"email");
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_TYPE, lastValue.equals(map.get("email").get(0)));
		// 断言返回页面
		Assert.assertTrue(ErrorStrings.RESPONSE_VIEW_NAME,
				result.getModelAndView().getViewName().equals(resultUrlIndex));
	}

	/**
	 * 保存失败
	 * 
	 * @throws Exception
	 */
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void saveValidFaild() throws Exception {
		// 定义参数
		MultiValueMap<String, String> map = new HttpHeaders();
		map.add("name", "张珊");
		map.add("email", "shanshan@ss.com");
		map.add("phone", "123456");
		// 获取执行插入方法前的数据数量
		Integer count = dataBaseUtils.countTable("select count(*) totalCount from users where status = 1");
		// 判断插入前数量
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE, count == initUserNum);
		// 执行插入方法（地址）（参数）获取返回值
		MvcResult result = mockMvc.perform(post("/users").params(map)).andExpect(status().is3xxRedirection())
				.andReturn();
		// 查询获取插入方法后的数据数量
		count = dataBaseUtils.countTable("select count(*) totalCount from users where status = 1");
		// 判断插入后数量
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE, count == initUserNum);
		// 判断查询项
		User user = (User) result.getModelAndView().getModel().get("user");
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("phone").equals(user.getPhone()));
		// 判断错误提示是否正确
		List<BindingResult> errorList = (List<BindingResult>) result.getModelAndView().getModel().get("errors");
		String phoneError = ((DefaultMessageSourceResolvable) errorList.get(0)).getDefaultMessage();
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, "请输入正确的手机号码".equals(phoneError));
		// 断言返回页面
		Assert.assertTrue(ErrorStrings.RESPONSE_VIEW_NAME, result.getModelAndView().getViewName().equals(resultUrlNew));
	}

	/**
	 * 保存相同的邮箱
	 * 
	 * @throws Exception
	 */
	@Test
	@Transactional
	public void saveValidWithSameEmail() throws Exception {

	}

	/**
	 * 修改成功
	 * 
	 * @throws Exception
	 */
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void updateSuccess() throws Exception {
		// 定义参数
		MultiValueMap<String, String> map = new HttpHeaders();
		map.add("id", "1");
		map.add("name", "indexTest");
		map.add("email", "test@tt.com");
		map.add("phone", "13909090909");
		// 执行插入方法（地址）（参数）获取返回值
		MvcResult result = mockMvc.perform(post("/users/" + map.getFirst("id")).params(map))
				.andExpect(status().is3xxRedirection()).andReturn();
		// 判断修改参数是否正确
		User user = (User) result.getModelAndView().getModel().get("user");
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("name").equals(user.getName()));
		// 判断修改后的数据是否正确
		List<User> userList = ((PageInfo<User>) result.getModelAndView().getModel().get("pageInfo")).getList();
		Integer id = userList.get(userList.size() - 1).getId();
		String resultName = userList.get(userList.size() - 1).getName();
		String resultEmail = userList.get(userList.size() - 1).getEmail();
		String resultPhone = userList.get(userList.size() - 1).getPhone();
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("id").equals(id.toString()));
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("name").equals(resultName));
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("email").equals(resultEmail));
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("phone").equals(resultPhone));
		// 断言返回页面
		Assert.assertTrue(ErrorStrings.RESPONSE_VIEW_NAME,
				result.getModelAndView().getViewName().equals(resultUrlIndex));
	}

	/**
	 * 修改失败
	 * 
	 * @throws Exception
	 */
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void updateValidFaild() throws Exception {
		// 定义参数
		MultiValueMap<String, String> map = new HttpHeaders();
		map.add("id", "1");
		map.add("name", "indexTest");
		map.add("email", "test@");
		map.add("phone", "13909090909");
		// 执行插入方法（地址）（参数）获取返回值
		MvcResult result = mockMvc.perform(post("/users/" + map.getFirst("id")).params(map))
				.andExpect(status().is3xxRedirection()).andReturn();
		// 判断修改参数是否正确
		User user = (User) result.getModelAndView().getModel().get("user");
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("email").equals(user.getEmail()));
		// 判断错误提示是否正确
		List<BindingResult> errorList = (List<BindingResult>) result.getModelAndView().getModel().get("errors");
		String emailError = ((DefaultMessageSourceResolvable) errorList.get(0)).getDefaultMessage();
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, "not a well-formed email address".equals(emailError));
		// 断言返回页面
		Assert.assertTrue(ErrorStrings.RESPONSE_VIEW_NAME,
				result.getModelAndView().getViewName().equals(resultUrlEdit));
	}

	@Test
	public void updateWithSameEmail() {

	}

	/**
	 * 删除
	 * 
	 * @throws Exception
	 */
	@Test
	@Transactional
	public void deleteSuccess() throws Exception {
		// 定义参数
		MultiValueMap<String, String> map = new HttpHeaders();
		map.add("id", "1");
		// 获取执行删除方法前的数据数量
		Integer count = dataBaseUtils.countTable("select count(*) totalCount from users where status = 1");
		// 判断删除前数量
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE, count == initUserNum);
		// 执行插入方法（地址）（参数）获取返回值
		MvcResult result = mockMvc
				.perform(get("/users/" + map.getFirst("id") + "?_pjax=%5Bdata-pjax-container%5D").params(map))
				.andExpect(status().is3xxRedirection()).andReturn();
		// 查询获取删除方法后的数据数量
		count = dataBaseUtils.countTable("select count(*) totalCount from users where status = 1");
		// 判断删除后数量
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE, count == initUserNum - 1);
		// 判断修改参数是否正确
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD,
				map.getFirst("id").equals(result.getRequest().getParameter("id").toString()));
	}

	@Test
	public void getContentPathSuccess() {
		Assert.assertTrue(ErrorStrings.CONTENT_PATH, contentPath.equals(usersController.getContentPath()));
	}

}
