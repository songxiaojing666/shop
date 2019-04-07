package com.fh.shop.backend.po;

public class Page {
	//总条数
	private long  totalCount;
	//总页数
	private long pageCount;
	//每页的条数
	private long pageSize = 3;
	//当前页
	private long pageIndex = 1;
	//起始位置
	private long startPos;
	//终止位置
	private long endPos;	
	
	//自定义属性
	private String sortfield;
	//自定义一个asc或desc属性
	private String sort;

	//计算分页信息
	public void calculatePage(){
		//获取总页数
		//totalCount【总条数】取模 pageSize【每页的条数】等于总页数
		//取模为整数==0时就把总页数展示出来【相当于20条数/每页的5条就等于4页展示出来】
		if(totalCount % pageSize ==0){
			pageCount = totalCount / pageSize;//整除走这个
		}else{
			//如果除不尽有余数就再加一页展示出来
	//【相当于11条数/每页的5条就等于2页整数据余了一条数据就要在第二页的基础上再加一页展示出来剩的一条数据】
			pageCount = totalCount / pageSize + 1;//有余数走这个加一页把余出来的数据也展示出来
		}
		// 1 0 5 >0 <=5
		// 2 5 10 >5 <=10
		// 3 10 15 >10 <=15
		startPos = ( pageIndex - 1 ) * pageSize;
		endPos = pageIndex * pageSize;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getStartPos() {
		return startPos;
	}

	public void setStartPos(long startPos) {
		this.startPos = startPos;
	}

	public long getEndPos() {
		return endPos;
	}

	public void setEndPos(long endPos) {
		this.endPos = endPos;
	}

	public long getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(long pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getSortfield() {
		return sortfield;
	}

	public void setSortfield(String sortfield) {
		this.sortfield = sortfield;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
}
