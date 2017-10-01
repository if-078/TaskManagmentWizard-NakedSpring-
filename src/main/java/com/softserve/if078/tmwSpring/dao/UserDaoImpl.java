package com.softserve.if078.tmwSpring.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.softserve.if078.tmwSpring.entities.User;
import org.springframework.stereotype.Repository;

@Component
public class UserDaoImpl implements UserDao {

	private final String tabName = "tmw.user";

	@Autowired
	@Qualifier("dataSource")
	DataSource datasource;

	@Override
	public List<User> getAll() throws SQLException {
		String sql = "SELECT * FROM " + tabName;
		List users = new ArrayList<>(0);
		try (Statement stmt = datasource.getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				User user = extractUserFromResultSet(rs);
				users.add(user);
			}
		}
		return users;
	}

	@Override
	public User findOne(int id) throws SQLException {
		String sql = "SELECT * FROM " + tabName + " WHERE user_id=" + id;
		try (Statement stmt = datasource.getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(sql)) {
			return rs.next() ? extractUserFromResultSet(rs) : null;
		}
	}

	private User extractUserFromResultSet(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("user_id"));
		user.setName(rs.getString("name"));
		user.setPass(rs.getString("pass"));
		user.setEmail(rs.getString("email"));

		return user;
	}

	@Override
	public boolean update(User entity) throws SQLException {
		int countUpdate = 0;
		String sql = "UPDATE " + tabName + " SET name=?, pass=?, email=? WHERE user_id=?";
		try (PreparedStatement ps = datasource.getConnection().prepareStatement(sql);) {
			ps.setString(1, entity.getName());
			ps.setString(2, entity.getPass());
			ps.setString(3, entity.getEmail());
			ps.setInt(4, entity.getId());
			countUpdate = ps.executeUpdate();
		}

		return countUpdate == 1;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		int countUpdate = 0;
		String sql = "DELETE FROM " + tabName + " WHERE user_id=" + id;
		try (Statement stmt = datasource.getConnection().createStatement();) {
			countUpdate = stmt.executeUpdate(sql);
		}

		return countUpdate == 1;

	}

	@Override
	public User create(User entity) throws SQLException {
		String sql = "INSERT INTO " + tabName + " (name, pass, email)  VALUES (?, ?, ?)";
		try (PreparedStatement ps = datasource.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, entity.getName());
			ps.setString(2, entity.getPass());
			ps.setString(3, entity.getEmail());
			ps.executeUpdate();

			ResultSet rsI = ps.getGeneratedKeys();
			if (rsI.next()) {
				entity.setId(rsI.getInt(1));
			}
		}

		return entity;
	}

	@Override
	public User findByEmail(String email) throws SQLException {
		String sql = "SELECT * FROM " + tabName + " WHERE email=?";
		try (PreparedStatement ps = datasource.getConnection().prepareStatement(sql)) {
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			return rs.next() ? extractUserFromResultSet(rs) : null;
		}
	}
}