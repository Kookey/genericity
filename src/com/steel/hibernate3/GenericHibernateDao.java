package com.steel.hibernate3;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class GenericHibernateDao<T extends Serializable,PK extends Serializable> extends HibernateDaoSupport implements GenericDao<T,PK> {

	//实体类类型(由构造方法自动赋值)
    public Class<T> entityClass;
    
	@SuppressWarnings("unchecked")
	public GenericHibernateDao(){
		this.entityClass = null;
		@SuppressWarnings("rawtypes")
		Class clazz = getClass();
		Type t = clazz.getGenericSuperclass();
		
		if(t instanceof ParameterizedType){
			Type[] p = ((ParameterizedType)t).getActualTypeArguments();
			this.entityClass = ((Class<T>) p[0]);
		}
	}
	

	@Override
	public T get(PK id) {
		return (T)getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public T getWithLock(PK id, LockMode lock) {
		T t = (T)getHibernateTemplate().get(entityClass, id, lock);
		if(t != null){
			this.flush();
		}
		return t ;
	}

	@Override
	public T load(PK id) {
		return (T)getHibernateTemplate().load(entityClass, id);
	}

	@Override
	public T loadWithLock(PK id, LockMode lock) {
		T t = getHibernateTemplate().load(entityClass, id, lock);
		if(t != null){
			this.flush();
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByExample(T user) {
		return getHibernateTemplate().findByExample(user);
	}
	
	@Override
	public List<T> loadAll() {
		return (List<T>)getHibernateTemplate().loadAll(entityClass);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public void updateWithLock(T entity, LockMode lock) {
		this.getHibernateTemplate().update(entity, lock);
		this.flush();
	}

	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
		System.out.println("数据保存成功");
	}

	@Override
	public void saveOrUpdate(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<T> entities) {
		this.getHibernateTemplate().saveOrUpdate(entities);
	}

	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public void deleteWithLock(T entity, LockMode lock) {
		this.getHibernateTemplate().delete(entity, lock);
		this.flush();
	}

	@Override
	public void deleteById(PK id) {
		this.delete(this.load(id));
	}

	@Override
	public void deleteByIdWithLock(PK id, LockMode lock) {
		this.deleteWithLock(this.load(id), lock);
	}

	@Override
	public void deleteAll(Collection<T> entities) {
		this.getHibernateTemplate().deleteAll(entities);
	}

	@Override
	public int bulkUpdate(String queryString) {
		return this.getHibernateTemplate().bulkUpdate(queryString);
	}

	@Override
	public int bulkUpdate(String queryString, Object[] values) {
		return this.getHibernateTemplate().bulkUpdate(queryString, values);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String queryString) {
		return this.getHibernateTemplate().find(queryString);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String queryString, Object[] values) {
		return this.getHibernateTemplate().find(queryString, values);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedParam(String queryString, String[] params,
			Object[] values) {
		return this.getHibernateTemplate().findByNamedParam(queryString, params, values);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedQuery(String queryName) {
		return this.getHibernateTemplate().findByNamedQuery(queryName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedQuery(String queryName, Object[] values) {
		return this.getHibernateTemplate().findByNamedQuery(queryName, values);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedQueryAndNamedParam(String queryName,
			String[] paramNames, Object[] values) {
		return this.getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, paramNames, values);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<T> iterate(String queryString) {
		return this.getHibernateTemplate().iterate(queryString);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<T> iterate(String queryString, Object[] values) {
		return this.getHibernateTemplate().iterate(queryString, values);
	}

	@Override
	public void closeIterator(Iterator<T> it) {
		this.getHibernateTemplate().closeIterator(it);
	}

	@Override
	public DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(this.entityClass);
	}

	@Override
	public Criteria createCriteria() {
		return this.createDetachedCriteria().getExecutableCriteria(this.getSession());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByCriteria(DetachedCriteria criteria) {
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByCriteria(DetachedCriteria criteria, int firstResult,
			int maxResults) {
		return this.getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findEqualByEntity(T entity, String[] propertyNames) {
		Criteria criteria = this.createCriteria();
		Example example = Example.create(entity);
		example.excludeZeroes();
		String[] defPropertys = getSessionFactory().getClassMetadata(entityClass).getPropertyNames();
		for(String defProperty:defPropertys){
			int ii = 0;
			for(ii=0;ii<defPropertys.length;++ii){
				if(defProperty.equals(propertyNames[ii])){
					criteria.addOrder(Order.asc(defProperty));
					break;
				}
			}
			if(ii==propertyNames.length){
				example.excludeProperty(defProperty);
			}
		}
		criteria.add(example);
		return ((List<T>)criteria.list());
	}

	@Override
	public List<T> findLikeByEntity(T entity, String[] propertyNames) {
		return null;
	}

	@Override
	public Integer getRowCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List<T> list = this.findByCriteria(criteria,0,1);
		return (Integer) list.get(0);
	}

	@Override
	public Object getStatValue(DetachedCriteria criteria, String propertyNames,
			String StatName) {
		if(StatName.toLowerCase().equals("max")){
			criteria.setProjection(Projections.max(propertyNames));
		}
		else if(StatName.toLowerCase().equals("min")){
			criteria.setProjection(Projections.min(propertyNames));
		}
		else if(StatName.toLowerCase().equals("avg")){
			criteria.setProjection(Projections.avg(propertyNames));
		}
		else if(StatName.toLowerCase().equals("sum")){
			criteria.setProjection(Projections.sum(propertyNames));
		}
		else 
		return null;
		List<T> list = this.findByCriteria(criteria, 0, 1);
        return list.get(0);
	}

	@Override
	public void lock(T entity, LockMode lockMode) {
		this.getHibernateTemplate().lock(entity, lockMode);
	}

	@Override
	public void initialize(Object proxy) {
		this.getHibernateTemplate().initialize(proxy);
	}

	@Override
	public void flush() {
		this.getHibernateTemplate().flush();
	}

}
