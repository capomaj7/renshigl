package dao;

import java.util.List;

import model.Yuangong;


public interface YuangongDao  {
	
	
	
	public void insertBean(Yuangong Yuangong);
	
	public void deleteBean(Yuangong Yuangong);
	
	public void updateBean(Yuangong Yuangong);

	public Yuangong selectBean(String where);
	
	public List<Yuangong> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
