package com.hnb.global;

public class Command implements Orderable {
	private String column, keyword; 
	private int pageNO, start, end;
	public final int PAGESIZE = 5;
	public Command(String column, String keyword, String pageNo) {
		this.column = column;
		this.keyword = keyword;
		this.pageNO = Integer.parseInt(pageNo);
		this.start = (Integer.parseInt(pageNo)-1)*PAGESIZE+1;
		this.end = (Integer.parseInt(pageNo))*PAGESIZE;
	}
	
	public Command(String pageNo) {
		this.pageNO = Integer.parseInt(pageNo);
		this.start = (Integer.parseInt(pageNo)-1)*PAGESIZE+1;
		this.end = (Integer.parseInt(pageNo))*PAGESIZE;
	}

	public String getColumn() {
		return column;
	}
	public String getKeyword() {
		return keyword;
	}
	public int getPageNO() {
		return pageNO;
	}
	public int getStart() {
		return start;
	}
	public int getEnd() {
		return end;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public void setPageNO(int pageNO) {
		this.pageNO = pageNO;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}
