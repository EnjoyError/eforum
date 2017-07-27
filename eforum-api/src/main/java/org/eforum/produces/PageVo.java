package org.eforum.produces;

import java.util.List;

public class PageVo<T> {
	/**
	 * 数据
	 */
	private List<T> data;
	/**
	 * 每页大小
	 */
	private int pageSize;
	/**
	 * 当前页
	 */
	private int pageIndex;
	/**
	 * 页面总数
	 */
	private long pageCount;
	/**
	 * 数据总条数
	 */
	private long dataCount;

	public long getDataCount() {
		return dataCount;
	}

	public void setDataCount(long dataCount) {
		this.dataCount = dataCount;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
}