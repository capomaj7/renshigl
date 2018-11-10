package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Zhiweijilu;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.ZhiweijiluDao;









public class ZhiweijiluDaoImpl extends HibernateDaoSupport implements  ZhiweijiluDao{


	public void deleteBean(Zhiweijilu Zhiweijilu) {
		this.getHibernateTemplate().delete(Zhiweijilu);
		
	}

	public void insertBean(Zhiweijilu Zhiweijilu) {
		this.getHibernateTemplate().save(Zhiweijilu);
		
	}

	@SuppressWarnings("unchecked")
	public Zhiweijilu selectBean(String where) {
		List<Zhiweijilu> list = this.getHibernateTemplate().find("from Zhiweijilu " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Zhiweijilu "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Zhiweijilu> selectBeanList(final int start,final int limit,final String where) {
		return (List<Zhiweijilu>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Zhiweijilu> list = session.createQuery("from Zhiweijilu "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Zhiweijilu Zhiweijilu) {
		this.getHibernateTemplate().update(Zhiweijilu);
		
	}
	
	
}
