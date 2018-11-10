package dao;

import java.util.List;

import model.Peixun;


public interface PeixunDao  extends BaseDao<Peixun>{
	
	
	
	public void insertBean(Peixun Peixun);
	
	public void deleteBean(Peixun Peixun);
	
	public void updateBean(Peixun Peixun);

	public Peixun selectBean(String where);
	
	public List<Peixun> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
