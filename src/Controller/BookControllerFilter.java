package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import Model.Actions;
import Model.Author;
import Model.Book;
import Model.Genre;

/**
 * Servlet Filter implementation class BookControllerFilter
 */
public class BookControllerFilter implements Filter {

    /**
     * Default constructor. 
     */
    public BookControllerFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("I do not know about multipart form data!");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		Actions action = Actions.ADD_EDIT_BOOK;
		if (request.getParameter("action") != null)
			action = Actions.valueOf(req.getParameter("action"));
		if(session.getAttribute("user") == null){
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		switch (action) {
			case ADD_EDIT_BOOK:
				addEditBook(request, response);
				break;
		}
		chain.doFilter(request, response);
	}
	public void addEditBook(ServletRequest request, ServletResponse response){
			HttpServletRequest req = (HttpServletRequest) request;
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			System.out.println("I do not know about multipart form data!");
			if (isMultipart) {
				System.out.println("Is multipart!");
				Book newBook = new Book();
				Author newAuthor = new Author();				
				Genre genre = new Genre();
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);			
				List<FileItem> items = null;
				try {
					items = upload.parseRequest(req);
				} catch (FileUploadException e1) {
				// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 	Iterator<FileItem> iter = items.iterator();
			 	try {
			 	while (iter.hasNext()) {
			 		FileItem item = iter.next();
			 		if (item.isFormField()) {
			 			String name = item.getFieldName();
			 			switch (name) {
			 				case "idBook":
			 					try {
			 						newBook.setId(Integer.parseInt(item.getString()));
			 					} catch (NumberFormatException e) {
			 						newBook.setId(0);
			 					}
			 					break;
			 				case "name":
			 					newBook.setName(item.getString());
			 					newBook.setAuthor(newAuthor);
			 					break;
			 				case "firstName":
			 					newAuthor.setFirstName(item.getString());
			 					newBook.setAuthor(newAuthor);
			 					break;
			 				case "lastName":
			 					newAuthor.setLastName(item.getString());
			 					newBook.setAuthor(newAuthor);
			 					break;
			 				case "pages":
			 					newBook.setPages(Integer.parseInt(item.getString()));
			 					break;
			 				case "genre":
			 					genre.setId(Integer.parseInt(item.getString()));
			 					newBook.setGenre(genre);
			 					break;
			 				case "publisher":
			 					newBook.setPublisher(item.getString());
			 					break;
			 				case "year":
			 					newBook.setYear(Integer.parseInt(item.getString()));
			 					break;
			 				case "isbn":
			 					newBook.setIsbn(item.getString());
			 					break;
			 				case "description":
			 					newBook.setDescription(item.getString());
			 					break;
			 				default:							
			 					break;
			 			}
			 		} else {
			 			byte[] data = item.get();
			 			System.out.println();
			 			if(item.getFieldName().equals("picture")){
			 				if (item.getSize() > 0) {
				 				newBook.setPicture(data);
				 			} else if (newBook.getId() != 0) {
				 				List<Book> listBooks = (List<Book>) req.getSession().getAttribute("books");
				 				for (Book book : listBooks) {
				 					if (book.getId() == newBook.getId()) {
				 						newBook.setPicture(book.getPicture());
				 					}
				 				}
				 			} else {
				 				File img = new File("E:\\Projects\\library\\WebContent\\Resources\\images\\noImgBook.png");
				 				InputStream is = new FileInputStream(img);
				 			newBook.setPicture(IOUtils.toByteArray(is));
				 			}
			 			}else if(item.getFieldName().equals("filePdf")){
			 				if(item.getSize() > 0){
			 					newBook.setFile(data);
			 				}else if(newBook.getId() != 0){
			 					List<Book> listBooks = (List<Book>) req.getSession().getAttribute("books");
				 				for (Book book : listBooks) {
				 					if (book.getId() == newBook.getId()) {
				 						newBook.setFile(book.getFile());
				 					}
				 				}
			 				}
			 				
			 			}
			 			
			 		}
			 	}
			 	} catch (Exception e) {
			 		e.printStackTrace();
			 	}
			 	request.setAttribute("newBook", newBook);
			}else{
				System.out.println("Merge la Book Controller.");
			}
		}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
