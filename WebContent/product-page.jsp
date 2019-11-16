<%@page import="models.Category"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controllers.IndexController"%>
<%@page import="controllers.ProductController"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="model.Product"%>
<%@page import="model.ProductInCart"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<title>E-SHOP HTML Template</title>

	<!-- Google font -->
	<link href="https://fonts.googleapis.com/css?family=Hind:400,700" rel="stylesheet">

	<!-- Bootstrap -->
	<link type="text/css" rel="stylesheet" href="/tiw-p1/theme/bootstrap.min.css" />

	<!-- Slick -->
	<link type="text/css" rel="stylesheet" href="/tiw-p1/theme/slick.css" />
	<link type="text/css" rel="stylesheet" href="/tiw-p1/theme/slick-theme.css" />

	<!-- nouislider -->
	<link type="text/css" rel="stylesheet" href="/tiw-p1/theme/nouislider.min.css" />

	<!-- Font Awesome Icon -->
	<link rel="stylesheet" href="/tiw-p1/theme/font-awesome.min.css">

	<!-- Custom stlylesheet -->
	<link type="text/css" rel="stylesheet" href="/tiw-p1/theme/style.css" />

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

</head>

<body>
<%
String user=(String)session.getAttribute("user");
ArrayList<Category> categories=IndexController.getCategories();
%>
	<!-- HEADER -->
	<header>
		<!-- top Header -->
		<div id="top-header">
			<div class="container">
				<div class="pull-left">
					<span>Welcome to E-shop!</span>
				</div>
				<div class="pull-right">
					<ul class="header-top-links">
						<li><a href="#">Store</a></li>
						<li><a href="#">Newsletter</a></li>
						<li><a href="#">FAQ</a></li>
						<li class="dropdown default-dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">ENG <i class="fa fa-caret-down"></i></a>
							<ul class="custom-menu">
								<li><a href="#">English (ENG)</a></li>
								<li><a href="#">Russian (Ru)</a></li>
								<li><a href="#">French (FR)</a></li>
								<li><a href="#">Spanish (Es)</a></li>
							</ul>
						</li>
						<li class="dropdown default-dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">USD <i class="fa fa-caret-down"></i></a>
							<ul class="custom-menu">
								<li><a href="#">USD ($)</a></li>
								<li><a href="#">EUR (€)</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- /top Header -->

		<!-- header -->
		<div id="header">
			<div class="container">
				<div class="pull-left">
					<!-- Logo -->
					<div class="header-logo">
						<a class="logo" href="index.jsp">
							<img src="/tiw-p1/images/logo.png" alt="">
						</a>
					</div>
					<!-- /Logo -->

					<!-- Search -->
					<div class="header-search">
						<form action="products.jsp" method="post">
							<input class="input search-input" type="text" placeholder="Enter your keyword" name="query">
							<select class="input search-categories" name="category">
								<option value="">All Categories</option>
								<%if(categories != null) { %>
									<% for(Category category : categories) { %>
										<option value="<%=category.getName() %>"><%=category.getName() %></option>
									<%} %>
								<%} %>
							</select>
							<button class="search-btn" type="submit"><i class="fa fa-search"></i></button>
						</form>
					</div>
					<!-- /Search -->
				</div>
				<div class="pull-right">
					<ul class="header-btns">
						<!-- Account -->
						<li class="header-account dropdown default-dropdown">
							<div class="dropdown-toggle" role="button" data-toggle="dropdown" aria-expanded="true">
								<div class="header-btns-icon">
									<i class="fa fa-user-o"></i>
								</div>
								<%if(user != null) { %>
									<strong class="text-uppercase">Hi, <%=((String)session.getAttribute("username")) %>! <i class="fa fa-caret-down"></i></strong>
								<%}else{ %>
									<strong class="text-uppercase">My Account <i class="fa fa-caret-down"></i></strong>
								<%}%>
							</div>
							
							<%if(user != null) { %>
								<a class="text-camelcase" href="profile.jsp">My profile</a>
							<%} else{ %>
								<a href="login-page.jsp" class="text-uppercase">Login</a> / <a href="register-page.jsp" class="text-uppercase">Join</a>
							<%} %>
							
							<ul class="custom-menu">
								<%if(user != null) { %>
									<li><a href="Order?type=my-orders"><i class="fa fa-comment-o"></i> My orders</a></li>
									<li><a href="wish-list.jsp"><i class="fa fa-user-o"></i> My wish list</a></li>
									<li><a href="/tiw-p1/jms-controller?op=2&correlationId=<%=user%>"><i class="fa fa-comment-o"></i> My messages</a></li>
									<li><a href="UserServlet?operation=log_out"><i class="fa fa-user-o"></i> Log out</a></li>
									<li><a href="delete-account.jsp"><i class="fa fa-user-times"></i> Delete my account</a></li>
								<%}else{ %>
									<li><a href="register-page.jsp"><i class="fa fa-unlock-alt"></i> Create an account</a></li>
									<li><a href="login-page.jsp"><i class="fa fa-unlock-alt"></i> Login</a></li>
								<%}%>
								
								
							</ul>
						</li>
						<!-- /Account -->
						<%if(user != null) { %>
							<!-- Cart -->
							<%
							ArrayList<ProductInCart> productsInCart = (ArrayList<ProductInCart>)session.getAttribute("cartList");
							double cartTotal=0;
							int cartNumber=0;
							if (productsInCart!=null) {
								cartNumber=productsInCart.size();
								for (int i=0; i<productsInCart.size(); i++)
									cartTotal+=productsInCart.get(i).getCost();
							}
							%>
							<li class="header-cart dropdown default-dropdown">
								<a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
									<div class="header-btns-icon">
										<i class="fa fa-shopping-cart"></i>
										<span class="qty"><%=cartNumber %></span>
									</div>
									<strong class="text-uppercase">My Cart:</strong>
									<br>
									<span>$<%=cartTotal %></span>
								</a>
								<div class="custom-menu">
									<div id="shopping-cart">
										<div class="shopping-cart-list">
											<%
											if (productsInCart!=null) {
												for (int i=0; i<productsInCart.size(); i++) {
											%>
											<div class="product product-widget">
												<div class="product-thumb">
													<img src="<%=productsInCart.get(i).getProduct().getImagePath() %>" alt="">
												</div>
												<div class="product-body">
													<h3 class="product-price">$<%=productsInCart.get(i).getProduct().getPrice().doubleValue() %> <span class="qty">x<%=productsInCart.get(i).getQuantity() %></span></h3>
													<h2 class="product-name"><a href="/tiw-p1/product-page.jsp?id=<%=productsInCart.get(i).getProduct().getId() %>"><%=productsInCart.get(i).getProduct().getName() %></a></h2>
												</div>
											</div>
											<%} 
												} %>
										</div>
										
										<form action="ShoppingCart" method="get">
											<div class="shopping-cart-btns">
												<input type="submit" class="primary-btn add-to-cart" value="View Cart" />
											</div>
										</form>
										
									</div>
								</div>
							</li>
						<%} %>
						<!-- /Cart -->
						<!-- Mobile nav toggle-->
						<li class="nav-toggle">
							<button class="nav-toggle-btn main-btn icon-btn"><i class="fa fa-bars"></i></button>
						</li>
						<!-- / Mobile nav toggle -->
					</ul>
				</div>
			</div>
			<!-- header -->
		</div>
		<!-- container -->
	</header>
	<!-- /HEADER -->

	<!-- NAVIGATION -->
	<div id="navigation">
		<!-- container -->
		<div class="container">
			<div id="responsive-nav">
				<!-- category nav -->
				<div class="category-nav show-on-click">
					<span class="category-header">Categories <i class="fa fa-list"></i></span>
					<form action="products.jsp" method="post" id="form_category" style="display:hidden;">
						<input type="hidden" name="category" value="" id="form_category_input">
					</form>
					<ul class="category-list">
						<%if(categories != null) { %>
							<% for(Category category : categories) { %>
								<li><a href="#" onclick="document.getElementById('form_category_input').value='<%=category.getName() %>';document.getElementById('form_category').submit();"><%=category.getName() %></a></li>
							<%} %>
						<%} %>
						<li><a href="products.jsp">View all</a></li>
					</ul>
				</div>
				<!-- /category nav -->
			</div>
		</div>
		<!-- /container -->
	</div>
	<!-- /NAVIGATION -->
	
	<%
	String seller=(String)request.getAttribute("seller");
	boolean isSeller=(seller != null && !seller.equals("") && !seller.equals("null") && seller.equals("yes"));
	
	String operation=(String)request.getAttribute("operation");
	boolean modify=(operation != null && !operation.equals("") && !operation.equals("null") && operation.equals("modify"));
	boolean add=(operation != null && !operation.equals("") && !operation.equals("null") && operation.equals("add"));
	
	String strId=request.getParameter("id");
	int id=-1;
	if (strId != null && !strId.equals("") && !strId.equals("null")) {
		id=Integer.parseInt(strId);
	}
	Product product=(Product)request.getAttribute("product");
	Product p;
	if (isSeller) {
		p=new Product();
		if (add) {
			p.setId(-1);
			p.setName("Insert name for the new product");
			model.Category p_category=new model.Category();
			p_category.setName("");
			p.setCategoryBean(p_category);
			p.setPrice(new BigDecimal("0"));
			p.setShortDescription("Insert short description for the new product");
			p.setDescription("Insert description for the new product");
			p.setImagePath("/tiw-p1/images/default-img.png");
			p.setStock(0);
			p.setSalePrice(new BigDecimal("0"));
			p.setShipPrice(new BigDecimal("0"));
		}
		if (modify) {
			p.setId(product.getId());
			p.setName(product.getName());
			p.setPrice(product.getPrice());
			p.setShortDescription(product.getShortDescription());
			p.setDescription(product.getDescription());
			p.setImagePath(product.getImagePath());
			p.setStock(product.getStock());
			if (product.getSalePrice()==null)
				p.setSalePrice(new BigDecimal("0"));
			else
				p.setSalePrice(product.getSalePrice());
			p.setShipPrice(product.getShipPrice());
			p.setCategoryBean(product.getCategoryBean());
		}
	} else {
		p=ProductController.getProduct(id);
	}
	%>
	<!-- TODO -->
	---------------------</br>
	<%=p.getId() %></br>
	<%=p.getImagePath() %></br>
	<%=p.getPrice().doubleValue() %></br>
	<%=p.getSalePrice().doubleValue() %></br>
	<%=p.getShipPrice().doubleValue() %></br>
	<%=p.getName() %></br>
	<%=p.getStock() %></br>
	<%=p.getCategoryBean().getName() %></br>
	modify=<%=modify %></br>
	add=<%=add%></br>
	<!-- BREADCRUMB -->
	<div id="breadcrumb">
		<div class="container">
			<form action="products.jsp" method="post" id="form_category" hidden>
				<input type="hidden" name="category" value="" id="form_category_input">
			</form>
			<ul class="breadcrumb">
				<%
				if (isSeller) {
				%>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="catalogue.jsp">Catalogue</a></li>
					<%if (add) { %>
					<li class="active">Add product</li>
					<%} %>
					<%if (modify) { %>
					<li class="active">Modify product</li>
					<%} %>
				<%} else { %>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="#" onclick="document.getElementById('form_category_input').value='<%=p.getCategoryBean().getName() %>';document.getElementById('form_category').submit();"><%=p.getCategoryBean().getName() %></a></li>
				<li class="active"><%=p.getName() %></li>
				<%}%>
			</ul>
		</div>
	</div>
	<!-- /BREADCRUMB -->
	
	<!-- section -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!--  Product Details -->
				<div class="product product-details clearfix">
					<div class="col-md-6">
						<div id="product-main-view">
							<div class="product-view">
								<%if (isSeller) {%><!-- TODO -->
								<img src="<%=p.getImagePath() %>" alt="">
								<%} else {%>
								<img src="<%=p.getImagePath() %>" alt="">
								<%} %>
							</div>
						</div>
						
					</div>
					<div class="col-md-6">
						<div class="product-body">
							<%if (isSeller) {%>
							<label for="product_name">Name</label>
							<input class="input" id="product_name" value="<%=p.getName() %>" required>
							<%} else {%>
							<h2 class="product-name">
								<%=p.getName() %>
							</h2>
							<%} %>
							<%if (isSeller) {%>
								<%if(categories != null) { %>
									<br></br>
									<label for="product_category">Category</label>
									<select class="input search-categories" name="category" id="product_category">
									<option value="">Select a category for the new product</option>
									<% for(Category category : categories) { %>
										<%if ( p.getCategoryBean().getName().equals(category.getName()) ) {%>
										<option value="<%=category.getName() %>" selected><%=category.getName() %></option>
										<%} else {%>
										<option value="<%=category.getName() %>"><%=category.getName() %></option>
										<%} %>
									<%} %>
									</select>
								<%} %>
							<br></br>
							<label for="product_price">Price</label>
							<input class="input" type="text" id="product_price" pattern="^\d*(\.\d{0,2})?$" required value="<%=p.getPrice().doubleValue() %>">
							<br></br>
							<label for="product_sale_price">Sale price</label>
							<input class="input" type="text" id="product_sale_price" pattern="^\d*(\.\d{0,2})?$" required value="<%=p.getSalePrice().doubleValue() %>">
							<br></br>
							<label for="product_ship_price">Ship price</label>
							<input class="input" type="text" id="product_ship_price" pattern="^\d*(\.\d{0,2})?$" required value="<%=p.getShipPrice().doubleValue() %>">
							<br></br>
							<label for="product_stock">Stock</label>
							<input class="input" type="text" id="product_stock" pattern="^\d*(\.\d{0,2})?$" required value="<%=p.getStock() %>">
							<%} else {%>
							<h3 class="product-price">$<%=p.getPrice().doubleValue() %> <del class="product-old-price" hidden>$45.00</del></h3>
							<p><strong>In Stock:</strong> <%=p.getStock() %></p>
							<%} %>
							<p hidden><strong>Brand:</strong> E-SHOP</p>
							<%if (isSeller) {%>
							<br></br>
							<label for="product_short_description">Short description</label>
							<input class="input" id="product_short_description" value="<%=p.getShortDescription() %>" required>
							<%} else {%>
							<p><%=p.getShortDescription() %></p>
							<%} %>

							<%if (!isSeller) {%>
							<div class="product-btns">
								<form action="ShoppingCart" method="post">
								
									<div class="qty-input">
										<span class="text-uppercase">QTY: </span>
										<input class="input" type="number" name="numOrder" required value="1" min="1">
										<input type="hidden" name="id" value= "<%= id %>" >
										<input type="hidden" name="type" value= "addToCart" >
									</div>
									<input type="submit" class="primary-btn add-to-cart" value="ADD TO CART" />
								</form>
								<div class="pull-right">
									<form action="WishList" method="post">	
										<input type="hidden" name="id" value= <%= id %> ><!-- TODO rehacer el WishList.java -->
										<input type="hidden" name="type" value= "addToWishList" >
										<input type="submit" class="primary-btn add-to-cart" value="ADD TO WISH LIST" />
										<button class="main-btn icon-btn"><i class="fa fa-exchange"></i></button>
										<button class="main-btn icon-btn"><i class="fa fa-share-alt"></i></button>
									</form>
								</div>
							</div>
							<%} %>
						</div>
						
						<%if (!isSeller) {%>
						<br><br><hr>
						<div class="pull-right">
							<form action="/tiw-p1/jms-controller" method="post">	
								<input type="hidden" name="op" value="1">
								
								<!--  Aqui tiene que ir el email del vendedor -->
								<input type="hidden" name="correlationId" value="123@gmail.com">
								<INPUT type="hidden" name="sender" value="<%=user%>"> 
								<input type="text" name="message" value= "Hello there! Im interested in this item!" >
								<input type="submit" class="btn btn-primary" value="CONTACT TO SELLER" />
							</form>
						</div>
						<%} %>
						
						
						<%
						String value_form="";// TODO
						if (modify) value_form="Modify";
						else if (add) value_form="Add";

						int new_product_id=p.getId(), new_product_stock;
						String new_product_image_path, new_product_name, new_product_category;
						BigDecimal new_product_price, new_product_sale_price, new_product_ship_price;

						%>
						<script type="text/javascript">
							function ola() {
								alert("ola");
							}
						</script>
						<%if (isSeller) {%>
						<br><form action="Catalogue" method="post">
							<button onclick="getData();" class="primary-btn" name="product_operation" value="<%=value_form %>"><%=value_form %></button>
						</form>
						<%} %>
					</div>
					
					<div class="col-md-12">
						<div class="product-tab">
							<ul class="tab-nav">
								<li class="active"><a data-toggle="tab" href="#tab1">Description</a></li>
								<!-- <li><a data-toggle="tab" href="#tab1">Details</a></li>
								<li><a data-toggle="tab" href="#tab2">Reviews (3)</a></li> -->
							</ul>
							<div class="tab-content">
								<div id="tab1" class="tab-pane fade in active">
									<%if (isSeller) {%>
									<p><textarea id="product_description" required style="width: 100%;height: 200px;"><%=p.getDescription() %></textarea></p>
									<%} else {%>
									<p><%=p.getDescription() %></p>
									<%} %>
								</div>
							</div>
						</div>
					</div>

				</div>
				<!-- /Product Details -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /section -->

	<!-- FOOTER -->
	<footer id="footer" class="section section-grey">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!-- footer widget -->
				<div class="col-md-6 col-sm-6 col-xs-6">
					<div class="footer">
						<!-- footer logo -->
						<div class="footer-logo">
							<a class="logo" href="#">
		            <img src="/tiw-p1/images/logo.png" alt="">
		          </a>
						</div>
						<!-- /footer logo -->

						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna</p>

						<!-- footer social -->
						<ul class="footer-social">
							<li><a href="#"><i class="fa fa-facebook"></i></a></li>
							<li><a href="#"><i class="fa fa-twitter"></i></a></li>
							<li><a href="#"><i class="fa fa-instagram"></i></a></li>
							<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
							<li><a href="#"><i class="fa fa-pinterest"></i></a></li>
						</ul>
						<!-- /footer social -->
					</div>
				</div>
				<!-- /footer widget -->

				<div class="clearfix visible-sm visible-xs"></div>

				<!-- footer widget -->
				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="footer">
						<h3 class="footer-header">Customer Service</h3>
						<ul class="list-links">
							<li><a href="#">About Us</a></li>
							<li><a href="#">Shiping & Return</a></li>
							<li><a href="#">Shiping Guide</a></li>
							<li><a href="#">FAQ</a></li>
						</ul>
					</div>
				</div>
				<!-- /footer widget -->

				<!-- footer subscribe -->
				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="footer">
						<h3 class="footer-header">Stay Connected</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor.</p>
						<form>
							<div class="form-group">
								<input class="input" placeholder="Enter Email Address">
							</div>
							<button class="primary-btn">Join Newslatter</button>
						</form>
					</div>
				</div>
				<!-- /footer subscribe -->
			</div>
			<!-- /row -->
			<hr>
			<!-- row -->
			<div class="row">
				<div class="col-md-8 col-md-offset-2 text-center">
					<!-- footer copyright -->
					<div class="footer-copyright">
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					</div>
					<!-- /footer copyright -->
				</div>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</footer>
	<!-- /FOOTER -->

	<!-- jQuery Plugins -->
	<script src="/tiw-p1/animation/jquery.min.js"></script>
	<script src="/tiw-p1/animation/bootstrap.min.js"></script>
	<script src="/tiw-p1/animation/slick.min.js"></script>
	<script src="/tiw-p1/animation/nouislider.min.js"></script>
	<script src="/tiw-p1/animation/main.js"></script>

</body>

</html>
