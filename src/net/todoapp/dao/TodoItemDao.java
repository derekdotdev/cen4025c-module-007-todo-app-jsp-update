package net.todoapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import net.todoapp.model.TodoItem;
import net.todoapp.utl.HibernateUtil;

/**
 * CRUD database operations
 * 
 * @author Derek DiLeo (via Ramesh Fadatare)
 */

public class TodoItemDao {

	public void saveTodoItem(TodoItem item) {
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the to do item
			session.save(item);
			// commit transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	public void updateTodoItem(TodoItem item) {
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the to do item
			session.update(item);
			// commit transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	public void deleteTodoItem(int id) {

		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// delete to do item object
			TodoItem item = session.get(TodoItem.class, id);
			if (item != null) {
				session.delete(item);
				System.out.println("To do item is deleted");
			}

			// commit transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	public TodoItem getTodoItem(int id) {

		Transaction transaction = null;
		TodoItem item = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get a to do item object
			item = session.get(TodoItem.class, id);
			// commit transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return item;

	}

	@SuppressWarnings("unchecked")
	public List<TodoItem> getAllTodoItems() {

		Transaction transaction = null;
		List<TodoItem> listOfTodoItems = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get to do item object
			listOfTodoItems = session.createQuery("FROM TodoItem").getResultList();
			// commit transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfTodoItems;

	}

}
