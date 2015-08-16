package com.hand.service;

import java.sql.Connection;

import java.sql.ResultSet;

import com.hand.dao.UserDap;
import com.hand.dao.impl.UserDaoImpl;
import com.hand.entity.User;

public class CheckUserService {

	private  UserDap userDap=new UserDaoImpl();
	
	public boolean check(User user){
		Connection conn=null;
		try {
			conn=com.jikexueyuan.util.ConnectionFactory.getInstance().makeConnection();
		    conn.setAutoCommit(false);
			
			ResultSet resultSet=userDap.get(conn, user);
			
			while(resultSet.next()){
				return true;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}finally{
			try{
				conn.close();
			}catch(Exception e3){
				e3.printStackTrace();
			}
		}
		return false;
	}
	
	
}