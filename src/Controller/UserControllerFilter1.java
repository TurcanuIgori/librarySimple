package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import Model.User;

/**
 * Servlet Filter implementation class UserControllerFilter
 */
public class UserControllerFilter1 implements Filter {

    /**
     * Default constructor. 
     */
    public UserControllerFilter1() {
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
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		Actions action = Actions.ADD_EDIT_USER;
		if (request.getParameter("action") != null)
			action = Actions.valueOf(req.getParameter("action"));
		switch (action) {
			case ADD_EDIT_USER:
				addEditUser(request, response);
				break;
		}
		chain.doFilter(request, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	public void addEditUser(ServletRequest request, ServletResponse response){
		HttpServletRequest req = (HttpServletRequest) request;

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		if (isMultipart) {
			User newUser = new User();
			newUser.setAdmin(false);
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
						case "idUser":
							try {
								newUser.setId(Integer.parseInt(item.getString()));
							} catch (NumberFormatException e) {
								newUser.setId(0);
							}
							break;
						case "firstName":
							newUser.setFirstName(item.getString());
							break;
						case "lastName":
							newUser.setLastName(item.getString());
							break;
						case "dob":
							String date1 = item.getString().replaceAll("/", "-");
							Date date;
							DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
							date = (Date) formatter.parse(date1);
							newUser.setDob(date);
							break;
						case "gender":
							newUser.setGender(item.getString().toCharArray()[0]);
							break;
						case "email":
							newUser.setEmail(item.getString());
							break;
						case "phone":
							newUser.setPhone(item.getString());
							break;
						case "password":
							newUser.setPassword(item.getString());
							break;
						case "passwordConfirmation":
							String passConfirmation=item.getString();
							break;							
						default:							
							break;
						}
					} else {
						byte[] data = item.get();
						if (item.getSize() > 0) {
							newUser.setPicture(data);
						} else if (newUser.getId() != 0) {
							@SuppressWarnings("unchecked")
							List<User> listUsers = (List<User>) req.getSession().getAttribute("listUsers");
							for (User user : listUsers) {
								if (user.getId() == newUser.getId()) {
									newUser.setPicture(user.getPicture());
								}
							}
						} else {
							File img = new File("C://Users//admin//work//BookLibrary//WebContent//Resources//images//noImg.png");
							InputStream is = new FileInputStream(img);
							newUser.setPicture(IOUtils.toByteArray(is));
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("newUser", newUser);
		}
	}	
}
