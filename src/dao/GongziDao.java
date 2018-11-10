package dao;

import java.util.List;

import model.Gongzi;


public interface GongziDao  {
	
	
	
	public void insertBean(Gongzi Gongzi);
	
	public void deleteBean(Gongzi Gongzi);
	
	public void updateBean(Gongzi Gongzi);

	public Gongzi selectBean(String where);
	
	public List<Gongzi> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
