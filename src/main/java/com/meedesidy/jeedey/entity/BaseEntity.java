package com.meedesidy.jeedey.entity;

public class BaseEntity {

	private Integer id;
	private String name;
	private Integer pageSize;
	private Integer pageIndex;
	private Integer totalPage;
	
	public BaseEntity(Integer id) {
		super();
		this.id = id;
	}

	public Integer getPageSize() {
		return pageSize == null ? 10 : pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex == null ? 1 : pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BaseEntity() {
		super();
	}
}
