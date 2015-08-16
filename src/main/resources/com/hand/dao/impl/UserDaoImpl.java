package com.hand.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




import com.jikexueyuan.dao.UserDap;
import com.jikexueyuan.entity.User;

public class UserDaoImpl implements UserDap{
    //保存用户信息
	@Override
	public void save(Connection conn, User user) throws SQLException {
		PreparedStatement ps=conn
				.prepareCall("INSERT INTO tbl_user(name,password,email) Values (?,?,?)");
		ps.setString(1, user.getName());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getEmail());
		ps.execute();
		
	}

	
	//根据用户指定的ID更新用户信息
	@Override
	public void update(Connection conn, Long id, User user) throws SQLException {
		String updateSql="UPDATE tbl_user SET name=?,password=?,email=? where id=?";
		PreparedStatement ps=conn.prepareStatement(updateSql);
		
		ps.setString(1, user.getName());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getEmail());
		ps.setLong(4, id);
		ps.execute();
		
		
	}
    //删除指定的用户信息
	@Override
	public void delete(Connection conn, User user) throws SQLException {
		PreparedStatement ps=conn.prepareStatement("DELETE FROM tbl_user WHERE id= ?");
		ps.setLong(1, user.getId());
		ps.execute();
		
	}

	@Override
	public ResultSet get(Connection conn, User user) throws SQLException {
       PreparedStatement ps=conn.prepareStatement("SELECT * FROM tbl_user WHERE name=? AND password=?");
       ps.setString(1, user.getName());
       ps.setString(2, user.getPassword());
		return ps.executeQuery();
	}

}
