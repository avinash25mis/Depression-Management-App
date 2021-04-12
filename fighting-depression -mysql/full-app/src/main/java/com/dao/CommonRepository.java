package com.dao;


import com.model.AppUser;
import com.model.StoredFile;
import com.model.common.IEntity;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Repository
@Transactional
public class CommonRepository {

    private static Logger logger = LoggerFactory.getLogger(CommonRepository.class);
    @Autowired
    private JpaContext jpaContext;

    @PersistenceContext
    EntityManager em;



   public <T extends IEntity> T saveOrUpdate(T entity){
        T managedEntity=null;
        if(entity!=null && entity.getId()==null) {
            em.persist(entity);
            managedEntity=entity;
       }else{
            managedEntity=em.merge(entity);
       }
        return managedEntity;
    }


    public <T extends IEntity> void delete(T entity){

       if(entity!=null && entity.getId()!=null) {
            em.remove(entity);
        }

    }


   public <T extends IEntity> List<T> saveOrUpdateList(List<T> entityList){
      List<T> managedEntityList=new ArrayList<>();
       for(T entity:entityList) {
          if (entity.getId() == null) {
              em.persist(entity);
          } else {
              em.merge(entity);

          }
           managedEntityList.add(entity);
      }
       return managedEntityList;
   }


    public <T extends IEntity> T findById(Class<T> entity, Long id){
        return em.find(entity,id);

    }

    public List  findAll(String className){
       String queryString="From "+className;
        Query query = em.createQuery(queryString);
        List resultList = query.getResultList();
        return resultList;

    }


    public AppUser getUserByUserName(String username){
        List<AppUser> list= new ArrayList<>();
        AppUser user=null;
        Query query = em.createQuery("from AppUser u where u.username=:username");
        query.setParameter("username",username);
        list=query.getResultList();
        if(CollectionUtils.isNotEmpty(list)){
            user=list.get(0);
        }
     return user;
    }

    public List getLastRecords(String className, int n) {
        String from = "FROM ";
        String orderBy = " order by id DESC";
        String queryString = from + className + orderBy;
        Query query = em.createQuery(queryString);
        query.setMaxResults(n);
        return query.getResultList();

    }

    public List getInitialRecords(String className, int n) {
        String from = "FROM ";
        String orderBy = " order by id";
        String queryString = from + className + orderBy;
        Query query = em.createQuery(queryString);
        query.setMaxResults(n);
        return query.getResultList();

    }

    public List getRecordWithId(String className, Long id) {
        String from = "FROM ";
        String orderBy = " WHERE  id =:id";
        String queryString = from + className + orderBy;
        Query query = em.createQuery(queryString);
        query.setParameter("id", id);
        return query.getResultList();

    }



    public List<StoredFile> getAllStoredFile(Long id){
        List<StoredFile> list= new ArrayList<>();
        AppUser user=null;
        Query query = em.createQuery("from DayWiseContent d inner join d.fileList f where d.id=:id");
        query.setParameter("id",id);
        list=query.getResultList();
        return list;
    }

    public List<StoredFile> getAllStoredFileWithId(List<Long> idList){
        List<StoredFile> list= new ArrayList<>();
        AppUser user=null;
        Query query = em.createQuery("from StoredFile  where id IN (:idList)");
        query.setParameter("idList",idList);
        list=query.getResultList();
        return list;
    }












}
