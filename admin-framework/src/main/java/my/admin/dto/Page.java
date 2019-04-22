package my.admin.dto;

import java.io.Serializable;
import java.util.List;

import org.beetl.sql.core.engine.PageQuery;

public class Page<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected List<T> list;		//分页结果List
	protected long pageNo;		//页数
	
	protected long pageSize = 20;		//每页记录数
	protected long last;		//总页数
	protected long count=-1;		//总行数,如果不为-1，则不需要再次查询
	
	public Page(PageQuery<T> query) {
		if(query!=null) {
			list = query.getList();
			pageNo = query.getPageNumber();
			pageSize = query.getPageSize();
			last = query.getTotalPage();
			count = query.getTotalRow();
		}
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public long getPageNo() {
		return pageNo;
	}

	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getLast() {
		return last;
	}

	public void setLast(long last) {
		this.last = last;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
	
	public long getNext() {
		if(pageNo<last) {
			return ++pageNo;
		}
		return pageNo;
	}
}
