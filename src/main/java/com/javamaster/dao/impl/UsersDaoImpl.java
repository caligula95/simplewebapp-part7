package com.javamaster.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javamaster.dao.UserDao;
import com.javamaster.entity.Role;
import com.javamaster.entity.Users;

public class UsersDaoImpl implements UserDao {
	
	private QueryExecutor executor = QueryExecutor.getInstance();
	
	/**
	 * SQL queries
	 */

	private static final String FIND_BY_LOGIN_PASSWORD = "SELECT users.id, users.login, users.password,"
			+ "users.email,  users.role_id, role.name FROM users JOIN role ON users.role_id = role.id WHERE users.login = ? AND users.password = ?";
	private static final String CREATE_USER = "INSERT INTO users (login, password, email, role_id) VALUES(?, ?, ?, ?)";
	private static final String UPDATE_USER = "UPDATE users SET password = ?, email = ? WHERE id = ?";


	public int createUser(Users entity) {
		Object[] args = { entity.getLogin(), entity.getPassword(), entity.getEmail(), 
				 entity.getRoleId().getId() };
		return executor.executeStatement(CREATE_USER, args);
	}

	public int editUser(Users entity) {
		Object[] args = { entity.getPassword(), entity.getEmail(), entity.getId() };
		return executor.executeStatement(UPDATE_USER, args);
	}

	public Users getUserByPasswordAndLogin(String password, String login) {
		Users user = null;
		if (login != null && password != null) {
			try {
				ResultSet rs = executor.getResultSet(FIND_BY_LOGIN_PASSWORD, login, password);
				if (rs.next()) {
					user = createEntity(rs);
				}
			} catch (SQLException e) {
				//LOGGER.error("SQL exception " + e.getMessage());
				e.printStackTrace();
			}
		}
		return user;
	}
	
	/**
	 * Creates entity from result set
	 * 
	 * @param rs
	 * @return entity
	 */
	private Users createEntity(ResultSet rs) {
		Users user = new Users();
		try {
			user.setId(rs.getInt("id"));
			user.setLogin(rs.getString("login"));
			user.setPassword(rs.getString("password"));
			user.setEmail(rs.getString("email"));
			Role role = new Role();
			role.setId(rs.getInt("role_id"));
			role.setName(rs.getString("name"));
			user.setRoleId(role);
		} catch (SQLException e) {
		//	LOGGER.error("SQL exception1 " + e.getMessage());
			e.printStackTrace();
		}
		return user;
	}
}
