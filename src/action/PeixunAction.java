package action;

import model.Peixun;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.PeixunDao;

@SuppressWarnings("all")
public class PeixunAction extends ActionSupport implements ModelDriven<Peixun> {
	private PeixunDao peixunDao;
	private Peixun peixun = new Peixun();
	private long ids[];
	private Integer grades[];

	//需要生成service的set方法

	public String updateGrade() throws Exception {
		if (ids!=null&&ids.length>0) {
			for (int i = 0; i < ids.length; i++) {
				Peixun peixun2=peixunDao.getById(ids[i]);
				if(grades[i]==null) {
					grades[i]=-1;
				}
				peixun2.setGrade(grades[i]);;
				peixunDao.update(peixun2);
			}
		}
		ActionContext.getContext().put("msg", "更新成绩成功");
		return "updategradeSuccess";
	}

	public Peixun getModel() {
		// TODO Auto-generated method stub
		return peixun;
	}

	public long[] getIds() {
		return ids;
	}

	public void setIds(long[] ids) {
		this.ids = ids;
	}

	

	public void setPeixunDao(PeixunDao peixunDao) {
		this.peixunDao = peixunDao;
	}

	public Integer[] getGrades() {
		return grades;
	}

	public void setGrades(Integer[] grades) {
		this.grades = grades;
	}

}
