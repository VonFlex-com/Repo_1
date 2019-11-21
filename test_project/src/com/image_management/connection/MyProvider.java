package com.image_management.connection;

public interface MyProvider {
	
	   final String host = "localhost:3306";
	   final String user = "root";
	   final String passwd = "";
	   final String DbName = "";
	   String connUrl = "jdbc:mysql://" + host + "/"+DbName+"?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&user=" + user + "&password=" + passwd;
}
