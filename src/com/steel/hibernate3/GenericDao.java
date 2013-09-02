package com.steel.hibernate3;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
/**
 * 
 * @author ������
 *
 * @param <T>
 * @param <PK>
 */
public interface GenericDao<T extends Serializable,PK extends Serializable> {
	//�������������ӡ��޸ġ�ɾ������
	
	//����������ȡ�����û����Ӧ��ʵ���࣬����null��
	public T get(PK id);
	
	//����������ȡʵ�岢���������û����Ӧ��ʵ���࣬����null
	public T getWithLock(PK id,LockMode lock);
	
	//����������ȡʵ�壬���û����Ӧ��ʵ�壬�׳��쳣
	public T load(PK id);
	
	//����������ȡʵ�岢���������û����Ӧ��ʵ��,�׳��쳣
	public T loadWithLock(PK id,LockMode lock);
	
	//��ȡȫ��ʵ��
	public List<T> loadAll();
	
	//����ʵ��
	public void update(T entity);
	
	//����ʵ�岢����
	public void updateWithLock(T entity,LockMode lock);
	
	//�洢�����ݿ�
	public void save(T entity);
	
	//���ӻ����޸�ʵ��
	public void saveOrUpdate(T entity);
	
	//���ӻ��߸��¼����е�ȫ��ʵ��
	public void saveOrUpdateAll(Collection<T> entities);
	
	//ɾ��ָ����ʵ��
	public void delete(T entity);
	
	//������ɾ��ָ����ʵ��
	public void deleteWithLock(T entity,LockMode lock);
	
	//��������ɾ��ָ��ʵ��
	public void deleteById(PK id);
	
	//��������������ɾ��ָ����ʵ��
	public void deleteByIdWithLock(PK id,LockMode lock);
	
	//ɾ�������е�ȫ��ʵ��
	public void deleteAll(Collection<T> entities);
	
	//ʹ��HSQL���ֱ�����ӣ����£�ɾ��ʵ��
	public int bulkUpdate(String queryString);
	
	//ʹ�ô��ε�HSQL������ӣ����£�ɾ��ʵ��
	public int bulkUpdate(String queryString,Object[] values);
	
	//ʹ��SHQL����������
	public List<T> find (String queryString);
	
	//ʹ�ô��ε�HSQL����������
	public List<T> find (String queryString,Object[] values);
	
	//ʹ�ô������Ĳ�����HSQL����������
	public List<T> findByNamedParam(String queryString,String[] params,Object[] values);
	
	//ʹ��������HSQL����������
	public List<T> findByNamedQuery(String queryName);
	
	//ʹ�ô��ε�HSQL����������
	public List<T> findByNamedQuery(String queryName,Object[] values);
	
	//ʹ�ô���������������HSQL����������
	public List<T> findByNamedQueryAndNamedParam(String queryName,String[] paramNames,Object[] values);
	
	//ʹ��HSQL���������ݣ�����Iterator
	public Iterator<T> iterate(String queryString);
	
	//ʹ�ô��ε�HSQL���������ݣ�����Iterator
	public Iterator<T> iterate(String queryString,Object[] values);
	
	//�رռ������ص�Iterator
	public void closeIterator(Iterator<T> it);
	
	//---------------Criteria--------------------
	
	//������Ự�޹صļ�����׼����
	public DetachedCriteria createDetachedCriteria();
	
	//������Ự�󶨵ļ�����׼����
	public Criteria createCriteria();
	
	//ʹ��ָ���ļ�����׼��������
	public List<T> findByCriteria(DetachedCriteria criteria);
	
	//ʹ��ָ���ļ�����׼�������ݣ����ز��ּ�¼
	public List<T> findByCriteria(DetachedCriteria criteria,int firstResult,int maxResults);
	
	//ʹ��ָ����ʵ�弰���Լ��������������������=ʵ��ֵ������
	public List<T> findEqualByEntity(T entity,String[] propertyNames);
	
	 // ʹ��ָ����ʵ�弰����(������)�������������� like ��ʵ��ֵ������
	public List<T> findLikeByEntity(T entity,String[] propertyNames);
	
	//ʹ��ָ���ļ�����׼�������ݣ�����ָ����Χ�ļ�¼
	public Integer getRowCount(DetachedCriteria criteria);
	
	// ʹ��ָ���ļ�����׼�������ݣ�����ָ��ͳ��ֵ
	public Object getStatValue(DetachedCriteria criteria,String propertyNames,String StatName);
	
	//----------------------Others
	
	//����ָ����ʵ��
	public void lock(T entity,LockMode lockMode);
	
	//ǿ�Ƴ�ʼ��ָ����ʵ��
	public void initialize(Object proxy);
	
	// ǿ���������»������ݵ����ݿ⣨������������ύʱ�Ÿ��£�
    public void flush();

	List<T> findByExample(T user);
}
