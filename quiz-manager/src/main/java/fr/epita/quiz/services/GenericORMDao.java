/**
 * Ce fichier est la propriété de Thomas BROUSSARD
 * Code application :
 * Composant :
 */
package fr.epita.quiz.services;

import java.util.List;
import java.util.Map.Entry;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * <h3>Description</h3>
 * <p>This class allows to search create update and delete</p>
 *
 * <h3>Usage</h3>
 * <p>This class should be used as follows:
 *   <pre><code>GenericORMDao dao = new GenericORMDao()
 *   dao.create();
 *   List<T> list = dao.search();
 *   dao.delete();
 *   dao.update();
 *   </code></pre>
 * </p>
 *
 * @since $${version}
 * @see See also $${link}
 * @author ${user}
 *
 * ${tags}
 */
public abstract class GenericORMDao<T> {

	@Inject
	SessionFactory sf;

	public final void create(T entity) {
		if (!beforeCreate(entity)) {
			return;
		}

		final Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		try {
		tx.commit();
		}
		catch (Exception e) {
			tx.rollback();
		}

	}

	protected boolean beforeCreate(T entity) {
		return entity != null;
	}

	public final void update(T entity) {
		final Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		try {
		tx.commit();
		}
		catch (Exception e) {
			tx.rollback();
		}
	}

	public final void delete(T entity) {
		final Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		try {
		tx.commit();
		}
		catch (Exception e) {
			tx.rollback();
		}
	}

	public final List<T> search(T entity) {
		final Session session = sf.openSession();
		final WhereClauseBuilder<T> wcb = getWhereClauseBuilder(entity);
		final Query searchQuery = session.createQuery(wcb.getQueryString());
		for (final Entry<String, Object> parameterEntry : wcb.getParameters().entrySet()) {
			searchQuery.setParameter(parameterEntry.getKey(), parameterEntry.getValue());
		}

		return searchQuery.list();
	}

	protected abstract WhereClauseBuilder getWhereClauseBuilder(T entity);

	// Old conception
	// protected abstract String getSearchQuery(T entity);
	//
	// protected abstract void completeQuery(T entity, Query toBeCompleted);

}
