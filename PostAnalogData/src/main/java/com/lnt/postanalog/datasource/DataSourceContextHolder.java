package com.lnt.postanalog.datasource;

public class DataSourceContextHolder {
	private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

	public static void setDataSourceContext(String dataSourceEnum) {
		threadLocal.set(dataSourceEnum);
	}

	public static String getDataSourceContext() {
		return threadLocal.get();
	}

	public static void clearDataSourceContext() {
		threadLocal.remove();
	}
}


