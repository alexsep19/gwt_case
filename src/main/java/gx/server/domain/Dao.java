package gx.server.domain;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jpaSimple.Category;

import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.SortInfoBean;

public class Dao {
	private static final EntityManagerFactory emfSimple = Persistence.createEntityManagerFactory("jpaSimple");
	private static final String  sqlSelFrom = "select t from ";
	public static EntityManager emSimple() {
		   EntityManager em = emfSimple.createEntityManager();
//		   em.setFlushMode(FlushModeType.COMMIT);
		   return em;
	}
	
	
	public List<String> getUserInfo(){
		
	 return Arrays.asList(new String[]{"hom","A"});
	}
//=============================================================	
	   public CategoryLoadResultBean getListCategory(List<SortInfoBean> sortInfo){
	       EntityManager em = emSimple();
	       StringBuilder sql = new StringBuilder(sqlSelFrom).append(Category.class.getSimpleName()).append(" t");
	       StringBuilder order = new StringBuilder(sortInfo.isEmpty()?" ":" order by");
//	       String orderIt = "";
	       try {
	           for(SortInfo it:sortInfo){
	             order = order.append(" t.").append(it.getSortField()).append(" ").append(it.getSortDir()).append(",");
	            }
	            order.setCharAt( order.length()-1, ' ');
//	            List r = em.createQuery(LIST_PARS + order).getResultList();
//	            System.out.println("r.size() = " + r.size());
	         return new CategoryLoadResultBean(em.createQuery(sql.append(order).toString()).getResultList());
	       }catch (RuntimeException re) {
	         re.printStackTrace();
	       throw re;
	       }
	   }

//=============================================================
	   public void remov(Object rec, Class<?> cl, long id){
	       EntityManager em = emSimple();
	       try {
		      em.getTransaction().begin();
		      em.remove(em.find(cl, id));
		      em.flush();
		      em.getTransaction().commit();
		    }catch(RuntimeException e){
		      if (em.getTransaction().isActive()) em.getTransaction().rollback();
	   	      e.printStackTrace();
		      throw e;
		    } 
	   }

	   public void remo(Object rec) throws RuntimeException{
		   EntityManager em = emSimple();
	       try {
		      em.getTransaction().begin();
		      em.remove(rec);
		      em.flush();
		      em.getTransaction().commit();
		    }catch(RuntimeException e){
		      if (em.getTransaction().isActive()) em.getTransaction().rollback();
	   	      e.printStackTrace();
		      throw e;
		    } 
	   }
	   
	   public void merg(Object rec){
			EntityManager em = emSimple();
		       try {
//		    	   System.out.println("merg = "+ rec.toString());
			      em.getTransaction().begin();
			      if (rec.toString().equals("0")) em.persist(rec);
			      else em.merge(rec);
			      em.flush();
			      em.getTransaction().commit();
			    }catch(RuntimeException e){
			      if (em.getTransaction().isActive()) em.getTransaction().rollback();
		  	      e.printStackTrace();
			      throw e;
			    }
		   }
	   
	   public static Object findObject(Class<?> cl, Integer id) {
	       if (id == null) return null; 
	       EntityManager em = emSimple();
	       try {
		     return em.find(cl, id);
	       }catch(RuntimeException e){
		     e.printStackTrace();
//		     throw e;
	       } 
	     return null;
  }
}
