package com.blogspot.that_java_guy;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.blogspot.that_java_guy.converters.type.QuestionKindType;

/**
 * @author Marko Bekhta
 */
public abstract class AbstractEntityTest {

	protected static Logger log = Logger.getLogger( AbstractEntityTest.class.getName() );
	private static final String PERSISTENCE_UNIT = "jpa-example";
	private static EntityManagerFactory emf;

	protected EntityManager entityManager;

	@BeforeClass
	public static void setUpClass() {

		ServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().build();
		MetadataSources sources = new MetadataSources( standardRegistry );
		MetadataBuilder metadataBuilder = sources.getMetadataBuilder();
		metadataBuilder.applyBasicType( QuestionKindType.INSTANCE );

		log.log( Level.CONFIG, "creating entity manager factory" );
		emf = Persistence.createEntityManagerFactory( PERSISTENCE_UNIT );
	}

	@Before
	public void setUp() {
		log.log( Level.CONFIG, "creating entity manager" );
		entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
	}

	@After
	public void tearDown() {
		log.log( Level.CONFIG, "closing entity manager" );
		EntityTransaction transaction = entityManager.getTransaction();
		if ( !transaction.isActive() ) {
			transaction.begin();
			transaction.commit();
		}
		else if ( transaction.getRollbackOnly() ) {
			transaction.rollback();
		}
		else {
			transaction.commit();
		}
		entityManager.close();
	}

	@AfterClass
	public static void tearDownClass() {
		log.log( Level.CONFIG, "closing entity manager factory" );
		if ( emf != null ) {
			emf.close();
		}
	}
}
