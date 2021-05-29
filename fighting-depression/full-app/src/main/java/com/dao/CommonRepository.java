package com.dao;


import com.model.common.AppUser;
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
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Repository
@Transactional(readOnly = true)
public class CommonRepository {

    private static Logger logger = LoggerFactory.getLogger(CommonRepository.class);
    @Autowired
    private JpaContext jpaContext;

    @PersistenceContext
    EntityManager em;


    @Transactional
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

    @Transactional
    public <T extends IEntity> void delete(T entity){

       if(entity!=null && entity.getId()!=null) {
            em.remove(entity);
        }

    }

    @Transactional
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

    public List  findAllDayContent(){
       String queryString="SELECT new com.dto.DayContentDto(d.id,d.day, d.time,d.title,d.message, d.genre1,d.genre2,d.link,d.popup,f.id as docId, f.name as name , d.askMail )  FROM DayWiseContent d left join d.fileList f order by d.day,d.time";
        Query query = em.createQuery(queryString);
        List resultList = query.getResultList();
        return resultList;

    }


    public List  findAllDayContentById(Long id){
        String queryString="SELECT new com.dto.DayContentDto(d.id,d.day, d.time,d.title,d.message, d.genre1,d.genre2,d.link,d.popup,f.id as docId, f.name as name , d.askMail)  FROM DayWiseContent d left join d.fileList f where d.id=:id";
        Query query = em.createQuery(queryString);
        query.setParameter("id",id);
        List resultList = query.getResultList();
        return resultList;

    }


    public List  findDaysDataInRange(Integer startDay,Integer endDay){
        String queryString="From DayWiseContent where day >=:startDay AND day <=:endDay";
        Query query = em.createQuery(queryString);
        query.setParameter("startDay",startDay);
        query.setParameter("endDay",endDay);
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





    public List<Long> getAllStoredFileIds(Long id){
        List<Long> list= new ArrayList<>();
        AppUser user=null;
        Query query = em.createQuery("SELECT f.id from DayWiseContent d inner join d.fileList f where d.id=:id");
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

   @Transactional
    public void deleteDayData(Long id) {
       Query queryNative = em.createNativeQuery("DELETE from stored_file  where day_id =:id");
       queryNative.setParameter("id",id);
       int childCount = queryNative.executeUpdate();


        Query query = em.createQuery("DELETE from DayWiseContent  where id =:id");
        query.setParameter("id",id);
       int parentCount = query.executeUpdate();
       System.out.println();
   }

    @Transactional
    public int deleteStoredFiles(List<Long> idList) {
        Query query = em.createQuery("DELETE from StoredFile  where id IN (:idList)");
        query.setParameter("idList",idList);
        int count = query.executeUpdate();
        return count;
    }

    public StoredFile findExistingImageByDashboardId(Long id) {
        StoredFile storedFile=null;
        Query query = em.createQuery("SELECT d.file from DashboardContent d  where d.id =:id");
        query.setParameter("id",id);
        try {
            storedFile = (StoredFile) query.getSingleResult();
        }catch (NoResultException ne){
            storedFile=null;
        }
        return storedFile;
    }
}
