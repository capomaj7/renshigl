package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Yuangong;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.YuangongDao;









public class YuangongDaoImpl extends HibernateDaoSupport implements  YuangongDao{


	public void deleteBean(Yuangong Yuangong) {
		this.getHibernateTemplate().delete(Yuangong);
		
	}

	public void insertBean(Yuangong Yuangong) {
		this.getHibernateTemplate().save(Yuangong);
		
	}

	@SuppressWarnings("unchecked")
	public Yuangong selectBean(String where) {
		List<Yuangong> list = this.getHibernateTemplate().find("from Yuangong " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Yuangong "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Yuangong> selectBeanList(final int start,final int limit,final String where) {
		return (List<Yuangong>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Yuangong> list = session.createQuery("from Yuangong "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Yuangong Yuangong) {
		this.getHibernateTemplate().update(Yuangong);
		
	}
	
	
}
