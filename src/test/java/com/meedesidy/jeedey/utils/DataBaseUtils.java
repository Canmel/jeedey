package com.meedesidy.jeedey.utils;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ibatis.common.jdbc.ScriptRunner;
import com.ibatis.common.resources.Resources;
import com.meedesidy.jeedey.entity.User;
import com.mysql.jdbc.Connection;

@Component
public class DataBaseUtils {

	@Value("${spring.datasource.url}")
	protected String url;

	@Value("${spring.datasource.username}")
	protected String username;

	@Value("${spring.datasource.password}")
	protected String password;

	@Value("${spring.datasource.driver-class-name}")
	protected String driver;

	public void runSchema() {
		try {
			Class.forName(driver).newInstance();
			Connection conn = (Connection) DriverManager.getConnection(url, username, password);
			ScriptRunner runner = new ScriptRunner(conn, false, false);
			runner.setErrorLogWriter(null);
			runner.setLogWriter(null);
			runner.runScript(Resources.getResourceAsReader("conf/schema.sql"));
			System.out.println("end...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void runInitScript() {
		try {
			Class.forName(driver).newInstance();
			Connection conn = (Connection) DriverManager.getConnection(url, username, password);
			ScriptRunner runner = new ScriptRunner(conn, false, false);
			runner.setErrorLogWriter(null);
			runner.setLogWriter(null);
			runner.runScript(Resources.getResourceAsReader("conf/init.sql"));
			System.out.println("end...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Integer countTable(String coutSql) {
		Integer rowCount = 0;
		try {
			Class.forName(driver).newInstance();
			Connection conn = (Connection) DriverManager.getConnection(url, username, password);
			PreparedStatement ps = conn.clientPrepareStatement(coutSql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				rowCount = rs.getInt("totalCount");
			}
			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	public String getLastStringColumValue(String lastSql, String columName) throws Exception {
		String result = "";
		try {
			Class.forName(driver).newInstance();
			Connection conn = (Connection) DriverManager.getConnection(url, username, password);
			PreparedStatement ps = conn.clientPrepareStatement(lastSql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getString(columName);
			}
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Integer getLastIntegerColumValue(String lastSql, String columName) throws Exception {
		Integer result = null;
		try {
			Class.forName(driver).newInstance();
			Connection conn = (Connection) DriverManager.getConnection(url, username, password);
			PreparedStatement ps = conn.clientPrepareStatement(lastSql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt(columName);
			}
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public User getFirstUser() {
		User user = null;
		try {
			Class.forName(driver).newInstance();
			Connection conn = (Connection) DriverManager.getConnection(url, username, password);
			PreparedStatement ps = conn
					.clientPrepareStatement("select name, email, phone from users where 1=1 order by id desc limit 1");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(rs.getString("name"), rs.getString("email"), rs.getString("phone"));
			}
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;

	}
}
