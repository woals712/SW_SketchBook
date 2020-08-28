package swsketch.infrastructure.repository;

import org.hibernate.Session;

import java.util.List;

import javax.persistence.EntityManager;

abstract class HibernateSupport<T> {

  EntityManager entityManager;

  HibernateSupport(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  Session getSession() {
    return entityManager.unwrap(Session.class);
  }
  
  public void save(T object) {
	  entityManager.persist(object);
	  entityManager.flush();
  }
  
  public void Insert(T object) {
	  entityManager.persist(object);
  }
  
  public void InsertAll(List<T> list) {
	  int lSize = list.size();
	  for(int i = 0; i < lSize; ++i)
		  entityManager.persist(list.get(i));
  }
  public void flush() {
	  entityManager.flush();
  }
}
