package net.todoapp.web;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.todoapp.dao.TodoItemDao;
import net.todoapp.model.TodoItem;

/*
 * ControllerServlet.java
 * This servlet acts as a page controller for the application,
 * hanldling all requests from the user.
 * @author Derek Dileo (via Ramesh Fadatare)
 * */


@WebServlet("/")
public class TodoItemServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private TodoItemDao todoItemDao;

	public void init() {
		todoItemDao = new TodoItemDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertTodoItem(request, response);
				break;
			case "/delete":
				deleteTodoItem(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateTodoItem(request, response);
				break;
			default:
				listTodoItem(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

	}

	private void listTodoItem(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		List<TodoItem> listOfTodoItems = todoItemDao.getAllTodoItems();
		request.setAttribute("listOfTodoItems", listOfTodoItems);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo-list.jsp");
		dispatcher.forward(request, response);

	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("todo-form.jsp");
		dispatcher.forward(request, response);

	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		TodoItem existingTodoItem = todoItemDao.getTodoItem(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo-form.jsp");
		request.setAttribute("todoItem", existingTodoItem);
		dispatcher.forward(request, response);

	}
	
	private void insertTodoItem(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String description = request.getParameter("description");
		TodoItem newTodoItem = new TodoItem(description);
		todoItemDao.saveTodoItem(newTodoItem);
		response.sendRedirect("list");

	}

	private void updateTodoItem(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String description = request.getParameter("description");

		TodoItem todoItem = new TodoItem(id, description);
		todoItemDao.updateTodoItem(todoItem);
		response.sendRedirect("list");

	}

	private void deleteTodoItem(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		todoItemDao.deleteTodoItem(id);
		response.sendRedirect("list");

	}

}
