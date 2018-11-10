package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Peixun;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.PeixunDao;









public class PeixunDaoImpl extends BaseDaoImpl<Peixun> implements  PeixunDao{


	public void deleteBean(Peixun Peixun) {
		this.getHibernateTemplate().delete(Peixun);
		
	}

	public void insertBean(Peixun Peixun) {
		this.getHibernateTemplate().save(Peixun);
		
	}

	@SuppressWarnings("unchecked")
	public Peixun selectBean(String where) {
		List<Peixun> list = this.getHibernateTemplate().find("from Peixun " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Peixun "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Peixun> selectBeanList(final int start,final int limit,final String where) {
		return (List<Peixun>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Peixun> list = session.createQuery("from Peixun "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Peixun Peixun) {
		this.getHibernateTemplate().update(Peixun);
		
	}
	
	
}
