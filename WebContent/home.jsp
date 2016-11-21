<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Online Library</title>
		<link type="image/png" rel="shortcut icon" href="Resources/images/favicon.gif"/>
		<link rel="stylesheet" type="text/css" href="Resources/styles/style.css">
	</head>
	<body>		
		<div class="header">
			<div class="header-wrap">
				<div class="language">
                    <div class="lang" style="display: inline;">        
						<a href="?lang=ru">
							<img src="Resources/images/rus.png" title="Russian" width="16" height="13"/>
						</a>
						<a href="?lang=en">
							<img src="Resources/images/usa.png" title="English" width="16" height="13"/>
						</a>
					</div>
                </div>				
				<div class="aut">
<%-- 					  background:url('Picture?id=${user.id}') no-repeat; --%>
					<p style="padding-left: 25px; float: left; margin-top: 5px; color: #fff;"><img src="Picture?id=${user.id}" height="32px"/>  <a href="UserController?action=ADD_EDIT_USER&id=${user.id}">${user.firstName} ${user.lastName}</a></p>
					<a href="UserController?action=LOGOUT">exit</a>                    
				</div>
			</div>			
		</div>
		<div id="wrap"> 
			<div class="logo">
				<a href="home.jsp">
					<img src="Resources/images/logo.png"/>
				</a>
			</div>
			<div class="menu">
				<ul>
					<li><a href="home.jsp">home</a></li>                            
					<li><a href="about.jsp">about</a></li>
					<li><a href="contact.jsp">contact</a></li>                            
				</ul>
			</div> 
			<div class="search">
				<form id="search_form" class="search_form">
					<input type="submit" class="button-text"/>
					<input type="text"  id="search_string" class="search-text"/>
					<input type="submit" id="btnSearch" value="Search" class="button"/>
					<div class="dropdown">                   
						<select id="search_select" class="select_search">
							<option>Title</option>
							<option>Author</option>
						</select>
					</div>				
				</form>
			</div>
			<div class="content">
				<div class="left-menu">
					<form class="genres" id="genresForm">
						<ul>
							<c:forEach items="${listGenre}" var="genre">	
								<li>
									<a href="#" value="${genre.id}" class="genre">${genre.name}</a>										
									
								</li>
							</c:forEach>						
						</ul>
					</form>
				</div>
				<div class="right-content">
					<div class="box-sh">
						<h1><p id="booksCount">Books size:  10</p></h1>
						<form id="add-action">
							<a href="BookController?action=ADD_EDIT_BOOK&id=${book.id}">Add Book</a>							
						</form>
						<hr style="width:96%; color: #aeaeae"/>
					</div>					
					<form id="booksForm">
						<div id="booksList" class="books_list">
							<div class="item">
								<div class="image-info">

									<a class="content_link" target="_blank">
										<img src="Resources/images/book1.jpg" width="110" height="150"/>										
									</a>


									<div id="ratingPanel">
										
									</div>
								</div>


								<div class="info">
									<div class="book_name">
										<a href="#" id="bookName" class="title" target="_blank">
											Amintiri din Copilarie											
										</a>

									</div>
									<span>Ion Creanga</span>

									<p><strong>Pages:</strong> 55</p>

									<p><strong>Publisher:</strong> Liter</p>

									<p><strong>Year:</strong> 2016</p>

									<p><strong>ISBN:</strong> 248979874asd154sa</p>

								</div>


								<a class="mr" href="#"target="_blank">
									<p class="read">Read</p>
								</a>
								<a href="#" class="mr" value="Download" target="_blank">
									<p class="download">Download</p>
								</a>
								<a href="#" class="mr" title="Edit Book">
									<p class="edit">Edit</p>
								</a>
								<a href="#" class="delete-button" title="Delete">
									<p class="delete">Delete</p>
								</a>
							</div>
							<div class="item">
								<div class="image-info">

									<a class="content_link" target="_blank">
										<img src="Resources/images/book1.jpg" width="110" height="150"/>										
									</a>


									<div id="ratingPanel">
										<!--<p:rating id="rating" value="#{b.rating}" styleClass="rating" cancel="false" stars="5"/>
										<p:outputLabel id="voteCount" value="(#{b.voteCount})" styleClass="vote-count"/>-->
									</div>
								</div>


								<div class="info">
									<div class="book_name">
										<a href="#" id="bookName" class="title" target="_blank">
											Amintiri din Copilarie											
										</a>

									</div>
									<span>Ion Creanga</span>

									<p><strong>Pages:</strong> 55</p>

									<p><strong>Publisher:</strong> Liter</p>

									<p><strong>Year:</strong> 2016</p>

									<p><strong>ISBN:</strong> 248979874asd154sa</p>

								</div>


								<a class="mr" href="#"target="_blank">
									<p class="read">Read</p>
								</a>
								<a href="#" class="mr" value="Download" target="_blank">
									<p class="download">Download</p>
								</a>
								<a href="#" class="mr" title="Edit Book">
									<p class="edit">Edit</p>
								</a>
								<a href="#" class="delete-button" title="Delete">
									<p class="delete">Delete</p>
								</a>
							</div>
							<div class="item">
								<div class="image-info">

									<a class="content_link" target="_blank">
										<img src="Resources/images/book1.jpg" width="110" height="150"/>										
									</a>


									<div id="ratingPanel">
										<!--<p:rating id="rating" value="#{b.rating}" styleClass="rating" cancel="false" stars="5"/>
										<p:outputLabel id="voteCount" value="(#{b.voteCount})" styleClass="vote-count"/>-->
									</div>
								</div>


								<div class="info">
									<div class="book_name">
										<a href="#" id="bookName" class="title" target="_blank">
											Amintiri din Copilarie											
										</a>

									</div>
									<span>Ion Creanga</span>

									<p><strong>Pages:</strong> 55</p>

									<p><strong>Publisher:</strong> Liter</p>

									<p><strong>Year:</strong> 2016</p>

									<p><strong>ISBN:</strong> 248979874asd154sa</p>

								</div>


								<a class="mr" href="#"target="_blank">
									<p class="read">Read</p>
								</a>
								<a href="#" class="mr" value="Download" target="_blank">
									<p class="download">Download</p>
								</a>
								<a href="#" class="mr" title="Edit Book">
									<p class="edit">Edit</p>
								</a>
								<a href="#" class="delete-button" title="Delete">
									<p class="delete">Delete</p>
								</a>
							</div>
							<div class="item">
								<div class="image-info">

									<a class="content_link" target="_blank">
										<img src="Resources/images/book1.jpg" width="110" height="150"/>										
									</a>


									<div id="ratingPanel">
										<!--<p:rating id="rating" value="#{b.rating}" styleClass="rating" cancel="false" stars="5"/>
										<p:outputLabel id="voteCount" value="(#{b.voteCount})" styleClass="vote-count"/>-->
									</div>
								</div>


								<div class="info">
									<div class="book_name">
										<a href="#" id="bookName" class="title" target="_blank">
											Amintiri din Copilarie											
										</a>

									</div>
									<span>Ion Creanga</span>

									<p><strong>Pages:</strong> 55</p>

									<p><strong>Publisher:</strong> Liter</p>

									<p><strong>Year:</strong> 2016</p>

									<p><strong>ISBN:</strong> 248979874asd154sa</p>

								</div>


								<a class="mr" href="#"target="_blank">
									<p class="read">Read</p>
								</a>
								<a href="#" class="mr" value="Download" target="_blank">
									<p class="download">Download</p>
								</a>
								<a href="#" class="mr" title="Edit Book">
									<p class="edit">Edit</p>
								</a>
								<a href="#" class="delete-button" title="Delete">
									<p class="delete">Delete</p>
								</a>
							</div>
						</div>						
					</form>
				</div>
			</div>
			<div class="footer_blank"></div>
		</div>
		 <div id="footer">
                <div class="footer-wrap">
                    <p>© 2016 Online Library <a href="contact.jsp" class="footerLink">Contact Us  </a><a class="footerLink" href="about.jsp">About Us</a></p>
                </div>
            </div>
	</body>
</html>