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
import com.meedesidy.jeedey.entity.Role;
import com.meedesidy.jeedey.utils.ErrorStrings;

public class RolesControllerTest extends JeedeyApplicationTest {

	private MockMvc mockMvc;
	@Autowired
	private RolesController rolesController;

	private int initUserNum = 2;
	//private int initInvalidUserNum = 1;

	private final String contentPath = "roles";
	private final String resultUrlIndex = "roles/index";
	private final String resultUrlEdit = "roles/edit";
	private final String resultUrlNew = "roles/new";

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
		MvcResult result = mockMvc.perform(get("/roles")).andExpect(status().is3xxRedirection()).andReturn();
		// 判断查询条数
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE,
				((PageInfo<Role>) (result.getModelAndView().getModel().get("pageInfo"))).getList()
						.size() == this.initUserNum);
		// 断言返回页面
		Assert.assertTrue(ErrorStrings.RESPONSE_VIEW_NAME,
				result.getModelAndView().getViewName().equals(resultUrlIndex));
		result.getModelAndView().getModelMap().containsKey("role");
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
		map.add("name", "admin");
		map.add("desc", "dm");
		map.add("status.id", "1");
		// 执行插入方法（地址）（参数）获取返回值
		MvcResult result = mockMvc.perform(get("/roles").params(map)).andExpect(status().is3xxRedirection())
				.andReturn();
		// 判断查询项
		Role role = (Role) result.getModelAndView().getModel().get("searchEntity");
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("desc").equals(role.getDesc()));
		// 判断查询条数
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE,
				((PageInfo<Role>) (result.getModelAndView().getModel().get("pageInfo"))).getList().size() == 1);
		// 判断返回数据
		role = ((PageInfo<Role>) (result.getModelAndView().getModel().get("pageInfo"))).getList().get(0);
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("name").equals(role.getName()));
		// 断言返回页面
		Assert.assertTrue(ErrorStrings.RESPONSE_VIEW_NAME,
				result.getModelAndView().getViewName().equals(resultUrlIndex));
		result.getModelAndView().getModelMap().containsKey("role");
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
		MvcResult result = mockMvc.perform(get("/roles/new")).andExpect(status().is3xxRedirection()).andReturn();
		// 判断查询结果是否正确
		Role role = (Role) result.getModelAndView().getModel().get("role");
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, role.getId() == null);
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
		MvcResult result = mockMvc.perform(get("/roles/" + map.getFirst("id") + "/edit").params(map))
				.andExpect(status().is3xxRedirection()).andReturn();
		// 判断查询结果是否正确
		Role role = (Role) result.getModelAndView().getModel().get("role");
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("id").equals(role.getId().toString()));
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
		map.add("name", "user");
		map.add("desc", "user");
		map.add("status.id", "1");
		// 获取执行插入方法前的数据数量
		Integer count = dataBaseUtils.countTable("select count(*) totalCount from roles where status = 1");
		// 判断插入前数量
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE, count == initUserNum);
		// 执行插入方法（地址）（参数）获取 返回值
		MvcResult result = mockMvc.perform(post("/roles").params(map)).andExpect(status().is3xxRedirection())
				.andReturn();
		// 查询获取插入方法后的数据数量
		count = dataBaseUtils.countTable("select count(*) totalCount from roles where status = 1");
		// 判断插入后数量
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE, count == initUserNum + 1);
		String lastValue = dataBaseUtils.getLastStringColumValue("select * from roles order by id desc limit 1",
				"description");
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_TYPE, lastValue.equals(map.get("desc").get(0)));
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
		map.add("name", "");
		map.add("desc", "user");
		map.add("status.id", "1");
		// 获取执行插入方法前的数据数量
		Integer count = dataBaseUtils.countTable("select count(*) totalCount from roles where status = 1");
		// 判断插入前数量
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE, count == initUserNum);
		// 执行插入方法（地址）（参数）获取返回值
		MvcResult result = mockMvc.perform(post("/roles").params(map)).andExpect(status().is3xxRedirection())
				.andReturn();
		// 查询获取插入方法后的数据数量
		count = dataBaseUtils.countTable("select count(*) totalCount from roles where status = 1");
		// 判断插入后数量
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE, count == initUserNum);
		// 判断查询项
		Role role = (Role) result.getModelAndView().getModel().get("role");
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("desc").equals(role.getDesc()));
		// 判断错误提示是否正确
		List<BindingResult> errorList = (List<BindingResult>) result.getModelAndView().getModel().get("errors");
		String nameError = ((DefaultMessageSourceResolvable) errorList.get(0)).getDefaultMessage();
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, "名称不能为空".equals(nameError));
		// 断言返回页面
		Assert.assertTrue(ErrorStrings.RESPONSE_VIEW_NAME, result.getModelAndView().getViewName().equals(resultUrlNew));
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
		map.add("id", "3");
		map.add("name", "general");
		map.add("desc", "general");
		map.add("status.id", "1");
		// 执行插入方法（地址）（参数）获取返回值
		MvcResult result = mockMvc.perform(post("/roles/" + map.getFirst("id")).params(map))
				.andExpect(status().is3xxRedirection()).andReturn();
		// 判断修改参数是否正确
		Role role = (Role) result.getModelAndView().getModel().get("role");
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("name").equals(role.getName()));
		// 判断修改后的数据是否正确
		List<Role> userList = ((PageInfo<Role>) result.getModelAndView().getModel().get("pageInfo")).getList();
		Integer id = userList.get(0).getId();
		String resultName = userList.get(0).getName();
		String resultDesc = userList.get(0).getDesc();
		Integer resultStatus = userList.get(0).getStatus().getId();
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("id").equals(id.toString()));
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("name").equals(resultName));
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("desc").equals(resultDesc));
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("status.id").equals(resultStatus.toString()));
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
		map.add("id", "3");
		map.add("name", "");
		map.add("desc", "general");
		map.add("status.id", "1");
		// 执行插入方法（地址）（参数）获取返回值
		MvcResult result = mockMvc.perform(post("/roles/" + map.getFirst("id")).params(map))
				.andExpect(status().is3xxRedirection()).andReturn();
		// 判断修改参数是否正确
		Role role = (Role) result.getModelAndView().getModel().get("role");
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("desc").equals(role.getDesc()));
		// 判断错误提示是否正确
		List<BindingResult> errorList = (List<BindingResult>) result.getModelAndView().getModel().get("errors");
		String nameError = ((DefaultMessageSourceResolvable) errorList.get(0)).getDefaultMessage();
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, "名称不能为空".equals(nameError));
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
		Integer count = dataBaseUtils.countTable("select count(*) totalCount from roles where status = 1");
		// 判断删除前数量
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE, count == initUserNum);
		// 执行插入方法（地址）（参数）获取返回值
		MvcResult result = mockMvc.perform(get("/roles/" + map.getFirst("id")).params(map))
				.andExpect(status().is3xxRedirection()).andReturn();
		// 查询获取删除方法后的数据数量
		count = dataBaseUtils.countTable("select count(*) totalCount from roles where status = 1");
		// 判断删除后数量
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_SIZE, count == initUserNum - 1);
		// 判断修改参数是否正确
		Role role = (Role) result.getModelAndView().getModel().get("role");
		Assert.assertTrue(ErrorStrings.RESPONSE_MODEL_FEILD, map.getFirst("id").equals(role.getId().toString()));
		// 断言返回页面
		Assert.assertTrue(ErrorStrings.RESPONSE_VIEW_NAME,
				result.getModelAndView().getViewName().equals(resultUrlIndex));
	}

	@Test
	public void getContentPathSuccess() {
		Assert.assertTrue(ErrorStrings.CONTENT_PATH, contentPath.equals(rolesController.getContentPath()));
	}

}
