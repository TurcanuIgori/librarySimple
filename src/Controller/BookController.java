package Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Actions;
import Model.Book;
import Service.BookService;
import Service.UserService;

/**
 * Servlet implementation class BookController
 */
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserService();
	BookService bookService = new BookService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Actions action = Actions.ADD_EDIT_BOOK;
			System.out.println(request.getParameter("action"));
			if (request.getParameter("action") != null)
				action = Actions.valueOf(request.getParameter("action"));
			switch(action){
			case ADD_EDIT_BOOK:
				try {
					session.setAttribute("listGenre", userService.getAllGenre());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getRequestDispatcher("/book.jsp").forward(request, response);
				break;
			}
			System.out.println("Book Controller! GET");
			response.getWriter().append("Served att: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
  		Actions action = Actions.ADD_EDIT_BOOK;
  		System.out.println(request.getParameter("action"));
  		if (request.getParameter("action") != null)
  			action = Actions.valueOf(request.getParameter("action"));
  		switch(action){
  			case ADD_EDIT_BOOK:
  				Book newBook = (Book) request.getAttribute("newBook");
  				System.out.println(newBook.getName() + newBook.getAuthor().getFirstName() + newBook.getPicture());
  				bookService.addBook(newBook);
  				request.getRequestDispatcher("/home.jsp").forward(request, response);
  				break;
  		}
	}

}
