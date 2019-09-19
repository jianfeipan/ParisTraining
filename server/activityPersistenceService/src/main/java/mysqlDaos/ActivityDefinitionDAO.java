package mysqlDaos;

import activityApi.IActivityDefinition;
import daoAPI.IActivityDefinitionDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class ActivityDefinitionDAO implements IActivityDefinitionDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ActivityDefinition");
    EntityManager em = emf.createEntityManager();

    @Override
    public Optional<IActivityDefinition> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<IActivityDefinition> getAll() {
        return null;
    }

    @Override
    public void save(IActivityDefinition activityDef) {
/*
  em.getTransaction().begin();
      em.persist(entity1);
      em.persist(entity2);
     em.getTransaction().commit();


     //创建配置对象
		Configuration config = new Configuration().configure();
		//创建会话工厂对象
		sessionFactory = config.buildSessionFactory();
		//创建会话对象
		session = sessionFactory.openSession();
		//开启事务
		transaction = session.beginTransaction()
* */
    }

    @Override
    public void update(IActivityDefinition activityDef, String name, String description) {

    }

    @Override
    public void delete(IActivityDefinition activityDef) {

    }
}
