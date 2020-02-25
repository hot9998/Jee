package com.address.config;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;


public class Config {
	private static final SqlMapClient sqlMap;
	static {
		try {
			String resource = "com/address/config/SqlMapConfig.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error:" + e);
		}

	}

	public static SqlMapClient getMapInstance() {
		return sqlMap;
	}

}