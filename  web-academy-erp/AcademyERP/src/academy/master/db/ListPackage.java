package academy.master.db;

import java.util.List;

public class ListPackage {
	private int page;
	private int maxpage;
	private int startpage;
	private int endpage;
	private int listcount;
	private List classlist;
	private String level;
	private String gid;
	public String getGid() {
		return gid;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	// 생성자에 저장
	public ListPackage(int page, int maxpage, int startpage, int endpage,
			int listcount, List classlist) {
		this.page = page;
		this.maxpage = maxpage;
		this.startpage = startpage;
		this.endpage = endpage;
		this.listcount = listcount;
		this.classlist = classlist;
	}

	public int getEndpage() {
		return endpage;
	}

	public int getPage() {
		return page;
	}

	public int getMaxpage() {
		return maxpage;
	}

	public int getStartpage() {
		return startpage;
	}

	public int getListcount() {
		return listcount;
	}

	public List getClasslist() {
		return classlist;
	}
}
