package Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Actions;
import Model.User;
import Service.BookService;
import Service.UserService;

/**
 * Servlet implementation class UserController
 */
//@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserService();
	BookService bookService = new BookService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Actions action = Actions.ADD_EDIT_USER;
		if (request.getParameter("action") != null)
			action = Actions.valueOf(request.getParameter("action"));
		switch(action){
			case ADD_EDIT_USER:
				int id = Integer.parseInt(request.getParameter("id"));
				session.setAttribute("user", userService.findUserById(id));
				request.getRequestDispatcher("/singup.jsp").forward(request, response);
				break;
			case LOGOUT:
				session.invalidate();
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				break;

		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Actions action = Actions.ADD_EDIT_USER;
		if (request.getParameter("action") != null)
			action = Actions.valueOf(request.getParameter("action"));
		switch(action){
			case ADD_EDIT_USER:
				User newUser = (User) request.getAttribute("newUser");
				if(newUser.getId() != 0){
					userService.updateUser(newUser);
					session.setAttribute("textStatus", "User succes updated!");
					session.setAttribute("user", newUser);
					try {
						session.setAttribute("listGenre", userService.getAllGenre());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					request.getRequestDispatcher("/home.jsp").forward(request, response);
				}else{
//					if(userService.checkUser(newUser)){
						try {
							userService.addUser(newUser);
//							session.setAttribute("user", newUser);
							request.getRequestDispatcher("/login.jsp").forward(request, response);
						} catch (SQLException e) {
							request.setAttribute("user", newUser);
							request.setAttribute("textStatus", "* user with this email already exists");
							request.getRequestDispatcher("/singup.jsp").forward(request, response);
						}
//					}else{
//						request.setAttribute("user", newUser);
//						request.setAttribute("textStatus", "* user with this email already exists");
//						request.getRequestDispatcher("/singup.jsp").forward(request, response);
//					}
				}
				break;
			case LOGIN:
//				HttpServletRequest req = (HttpServletRequest) request;
				try{
					User user = userService.findUserByEmail(request.getParameter("login"));				
					if(user.getPassword().equals(request.getParameter("password"))){
						request.setAttribute("user", user);
						System.out.println("add user in session");
						session.setAttribute("user", user);
						try {
							session.setAttribute("listGenre", userService.getAllGenre());
							session.setAttribute("books", bookService.getBooksByCriteria("genre_id", 1));
							request.getRequestDispatcher("/home.jsp").forward(request, response);
						} catch (ServletException | IOException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						throw new NullPointerException();
					}
				}catch(NullPointerException e){
				
					request.setAttribute("login", request.getParameter("login"));
					request.setAttribute("password", request.getParameter("password"));
					request.setAttribute("textStatus", "* login or password not corect");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
				break;
		}
		
	}
	public void login(ServletRequest request, ServletResponse response){
		HttpServletRequest req = (HttpServletRequest) request;
		User user = userService.findUserByEmail(req.getParameter("login"));
		if(user.getPassword() == req.getParameter("password")){
			request.setAttribute("user", user);
			try {
				request.getRequestDispatcher("/home.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
