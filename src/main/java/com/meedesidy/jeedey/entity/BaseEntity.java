package com.meedesidy.jeedey.entity;

import org.hibernate.validator.constraints.NotBlank;

import com.meedesidy.jeedey.entity.enums.Status;

public class BaseEntity {

	private Integer id;
	
	@NotBlank(message = "名称不能为空")
	private String name;
	private Integer pageSize;
	private Integer pageIndex;
	private Integer totalPage;
	private Status status;

	public Status getStatus() {
		return status == null ? Status.active : this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

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
