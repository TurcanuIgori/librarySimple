package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

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
					int book_id = Integer.parseInt(request.getParameter("id"));
					request.setAttribute("book", bookService.getBookById(book_id));
					session.setAttribute("listGenre", userService.getAllGenre());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getRequestDispatcher("/book.jsp").forward(request, response);
				break;
			case DELETE_BOOK:
  				int id = Integer.parseInt(request.getParameter("id"));
  				bookService.deleteBookById(id);
  				session.setAttribute("books", bookService.getBooksByCriteria("genre_id", 1));
  				request.getRequestDispatcher("/home.jsp").forward(request, response);
  				break;
			case READ_BOOK:
				id = Integer.parseInt(request.getParameter("id"));
  				Book book = bookService.getBookById(id);
  				try {
					response.setContentType("application/pdf");
					response.getOutputStream().write(book.getFile());
					response.getOutputStream().flush();
					response.getOutputStream().close();
				} catch (Exception e) {
				}
			case GET_BOOKS:
				int genre_id = Integer.parseInt(request.getParameter("id"));
				String json;
				System.out.println("Send request to database...");
				List<Book> listBooks =bookService.getBooksByCriteria("genre_id", genre_id);
				json = new Gson().toJson(listBooks);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
  				break;
			}
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
  				if(newBook.getId() == 0){
  					bookService.addBook(newBook);
  				}else if(newBook.getId() != 0){
  					bookService.updateBook(newBook);
  				}  				
//  				request.setAttribute("books", bookService.getBooksByCriteria("genre_id", 1));
  				session.setAttribute("books", bookService.getBooksByCriteria("genre_id", 1));
  				request.getRequestDispatcher("/home.jsp").forward(request, response);
  				break;  			
  		}
	}

}
