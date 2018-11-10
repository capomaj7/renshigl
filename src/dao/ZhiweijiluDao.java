package dao;

import java.util.List;

import model.Zhiweijilu;


public interface ZhiweijiluDao  {
	
	
	
	public void insertBean(Zhiweijilu Zhiweijilu);
	
	public void deleteBean(Zhiweijilu Zhiweijilu);
	
	public void updateBean(Zhiweijilu Zhiweijilu);

	public Zhiweijilu selectBean(String where);
	
	public List<Zhiweijilu> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
