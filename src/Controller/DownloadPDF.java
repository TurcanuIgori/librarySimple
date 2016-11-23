package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Book;

/**
 * Servlet implementation class DownloadPDF
 */
public class DownloadPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadPDF() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		List<Book> books = (List<Book>) request.getSession().getAttribute("books");
		for(Book book : books){
			if(book.getId() == id){
				try {
					response.setContentType("application/pdf");
					String headerKey = "Content-Disposition";
				    String headerValue = String.format("attachment; filename=" + book.getName() + ".pdf");
				    response.setHeader(headerKey, headerValue);

					response.getOutputStream().write(book.getFile());
					response.getOutputStream().flush();
					response.getOutputStream().close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
