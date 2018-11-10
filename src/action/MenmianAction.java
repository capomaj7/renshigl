package action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import util.Pager;
import model.Menmian;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.MenmianDao;

@SuppressWarnings("all")
public class MenmianAction extends ActionSupport implements ModelDriven<Menmian> {
	private MenmianDao menmianDao;
	private Menmian menmian = new Menmian();
	private String searchname;
	int currentpage = 1;
	int pagesize =10;
	public String getAllMenmian() throws Exception {
		System.out.println("进来了");
		List<Menmian>menmians=menmianDao.getAll();
		int total=menmians.size();
		ActionContext.getContext().put("menmians", menmians);
		ActionContext.getContext().put("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!bumenlist", "共有" + total + "条记录"));
		return "menmianlist";
	}
	public String searchMenmian() throws Exception {
		DetachedCriteria dc=DetachedCriteria.forClass(Menmian.class);
		dc.add(Restrictions.like("name", searchname));
		List<Menmian>menmians=menmianDao.getAllByDc(dc);
		int total=menmians.size();
		ActionContext.getContext().put("menmians", menmians);
		ActionContext.getContext().put("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!bumenlist", "共有" + total + "条记录"));
		return "menmianlist";
	}
	public String deletemenmian() throws Exception {
		menmianDao.delete(menmian.getId());
		return "menmianAction_getAllMenmian";
	}
	public String addMenmian() throws Exception {
		menmianDao.save(menmian);
		return "menmianAction_getAllMenmian";
	}
	public String findmenmianbyid() throws Exception {
		Menmian menmian1 = menmianDao.getById(menmian.getId());
		ActionContext.getContext().put("bean", menmian1);
		return "updatemenmian";
	}
	public String updateMenmian() throws Exception {
		menmianDao.update(menmian);
		return "menmianAction_getAllMenmian";
	}
	public Menmian getModel() {
		// TODO Auto-generated method stub
		return menmian;
	}

	public void setMenmianDao(MenmianDao menmianDao) {
		this.menmianDao = menmianDao;
	}
	public String getSearchname() {
		return searchname;
	}
	public void setSearchname(String searchname) {
		this.searchname = searchname;
	}


	
}
