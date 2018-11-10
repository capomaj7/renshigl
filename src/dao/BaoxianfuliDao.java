package dao;

import java.util.List;

import model.Baoxianfuli;


public interface BaoxianfuliDao  {
	
	
	
	public void insertBean(Baoxianfuli Baoxianfuli);
	
	public void deleteBean(Baoxianfuli Baoxianfuli);
	
	public void updateBean(Baoxianfuli Baoxianfuli);

	public Baoxianfuli selectBean(String where);
	
	public List<Baoxianfuli> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
