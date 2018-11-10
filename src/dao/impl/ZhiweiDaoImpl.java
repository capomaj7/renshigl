package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Zhiwei;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.ZhiweiDao;









public class ZhiweiDaoImpl extends HibernateDaoSupport implements  ZhiweiDao{


	public void deleteBean(Zhiwei Zhiwei) {
		this.getHibernateTemplate().delete(Zhiwei);
		
	}

	public void insertBean(Zhiwei Zhiwei) {
		this.getHibernateTemplate().save(Zhiwei);
		
	}

	@SuppressWarnings("unchecked")
	public Zhiwei selectBean(String where) {
		List<Zhiwei> list = this.getHibernateTemplate().find("from Zhiwei " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Zhiwei "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Zhiwei> selectBeanList(final int start,final int limit,final String where) {
		return (List<Zhiwei>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Zhiwei> list = session.createQuery("from Zhiwei "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Zhiwei Zhiwei) {
		this.getHibernateTemplate().update(Zhiwei);
		
	}
	
	
}
