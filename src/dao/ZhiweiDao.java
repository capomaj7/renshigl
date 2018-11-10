package dao;

import java.util.List;

import model.Zhiwei;


public interface ZhiweiDao  {
	
	
	
	public void insertBean(Zhiwei Zhiwei);
	
	public void deleteBean(Zhiwei Zhiwei);
	
	public void updateBean(Zhiwei Zhiwei);

	public Zhiwei selectBean(String where);
	
	public List<Zhiwei> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
