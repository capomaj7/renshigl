package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import dao.MenmianDao;
import model.Menmian;

public class MenmianDaoImpl extends BaseDaoImpl<Menmian> implements MenmianDao{

	public List<Menmian> searchMenmian(final String name) {
		
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Menmian>>() {

			public List<Menmian> doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				String hql="from Menmian where name like %"+name+"%";
				Query query = session.createQuery(hql);
				List list = query.list();
				return list;
			}
		});
	}

}
