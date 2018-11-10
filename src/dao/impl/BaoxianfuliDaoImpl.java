package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Baoxianfuli;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.BaoxianfuliDao;









public class BaoxianfuliDaoImpl extends HibernateDaoSupport implements  BaoxianfuliDao{


	public void deleteBean(Baoxianfuli Baoxianfuli) {
		this.getHibernateTemplate().delete(Baoxianfuli);
		
	}

	public void insertBean(Baoxianfuli Baoxianfuli) {
		this.getHibernateTemplate().save(Baoxianfuli);
		
	}

	@SuppressWarnings("unchecked")
	public Baoxianfuli selectBean(String where) {
		List<Baoxianfuli> list = this.getHibernateTemplate().find("from Baoxianfuli " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Baoxianfuli "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Baoxianfuli> selectBeanList(final int start,final int limit,final String where) {
		return (List<Baoxianfuli>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Baoxianfuli> list = session.createQuery("from Baoxianfuli "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Baoxianfuli Baoxianfuli) {
		this.getHibernateTemplate().update(Baoxianfuli);
		
	}
	
	
}
