package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public BookController() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Actions action = Actions.ADD_EDIT_BOOK;
		List<Book> listBooks = null;
			if (request.getParameter("action") != null)
				action = Actions.valueOf(request.getParameter("action"));
			switch(action){
			case ADD_EDIT_BOOK:
				try {
					int book_id = Integer.parseInt(request.getParameter("id"));
					request.setAttribute("book", bookService.getBookById(book_id));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					session.setAttribute("listGenre", userService.getAllGenre());
					request.getRequestDispatcher("/book.jsp").forward(request, response);
				}
				break;
			case DELETE_BOOK:
  				int id = Integer.parseInt(request.getParameter("id"));
  				bookService.deleteBookById(id);
//  				session.setAttribute("books", bookService.getBooksByCriteria("genre_id", 1));
  				List<Book> lBooks = new ArrayList();
  				listBooks = bookService.getBooksByCriteria("genre_id", Integer.parseInt((String) request.getAttribute("genre_id"))); 
  				try{
	  				for(int i = 4*1-4; i < 4*1; i++){
	  					if(listBooks.get(i) == null)
							break;
						lBooks.add(listBooks.get(i));
					}
  				}catch(Exception e){}
  				session.setAttribute("books", lBooks);
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
			case GET_PAGES:
				String gson;
				int pages = 0;
				int genre = Integer.parseInt(request.getParameter("id"));
				listBooks = bookService.getBooksByCriteria("genre_id", genre);
				pages = listBooks.size()/4;
				if(((float)listBooks.size() / 4) > pages){
					pages++;
				}
				gson = new Gson().toJson(pages);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(gson);
				break;
			case GET_BOOKS:
				int numberOfBooks = 4;
				int genre_id = Integer.parseInt(request.getParameter("id"));
				int pag = Integer.parseInt(request.getParameter("p"));
				String json;
				listBooks = bookService.getBooksByCriteria("genre_id", genre_id);
				for(Book newBook : listBooks){
					System.out.println(newBook.getName() + newBook.getFile());
					newBook.setPicture(null);
					newBook.setFile(null);
				}
				List<Book> books = new ArrayList();
				try{
					for(int i = numberOfBooks*pag-4; i < numberOfBooks*pag; i++){
						if(listBooks.get(i) == null)
							break;
						books.add(listBooks.get(i));
					}
				}catch(Exception e){}
				json = new Gson().toJson(books);
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
  		if (request.getParameter("action") != null)
  			action = Actions.valueOf(request.getParameter("action"));
  		switch(action){
  			case ADD_EDIT_BOOK:
  				Book newBook = (Book) request.getAttribute("newBook");
  				if(newBook.getId() == 0){
  					try {
						bookService.addBook(newBook);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						request.setAttribute("textStatus", "Book with this isbn already exists in database");
						request.setAttribute("book", newBook);
		  				request.getRequestDispatcher("/book.jsp").forward(request, response);
					}
  				}else if(newBook.getId() != 0){
  					bookService.updateBook(newBook);
  				}  		
//  				request.setAttribute("books", bookService.getBooksByCriteria("genre_id", Integer.parseInt((String) request.getAttribute("genre_id"))));
  				List<Book> lBooks = new ArrayList();
  				List<Book> listBooks = bookService.getBooksByCriteria("genre_id", Integer.parseInt((String) request.getAttribute("genre_id"))); 
  				try{
	  				for(int i = 4*1-4; i < 4*1; i++){
	  					if(listBooks.get(i) == null)
							break;
						lBooks.add(listBooks.get(i));
					}
  				}catch(Exception e){}
  				session.setAttribute("books", lBooks);
  				request.getRequestDispatcher("/home.jsp").forward(request, response);
  				break;  			
  		}
	}

}
