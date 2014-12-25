package com.handson.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handson.ejb.local.ShoppingCart;
import com.handson.entity.Product;

@WebServlet(name = "shoppingServletTest", urlPatterns = { "/testShopping" })
public class ShoppingServletTest extends HttpServlet {

	private static final long	serialVersionUID				= 1L;
	private static final String	SHOPPING_CART_BEAN_SESION_KEY	= "shoppingCart";
	private SimpleDateFormat	sdf								= new SimpleDateFormat("dd/MM/yyyy hh:mm");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Obtain the EJB from the HTTP session
		ShoppingCart shoppingCartBean = (ShoppingCart) request.getSession().getAttribute(SHOPPING_CART_BEAN_SESION_KEY);

		if (shoppingCartBean == null) {
			// EJB is not present in the HTTP session
			// so let's fetch a new one from the container
			try {
				InitialContext ic = new InitialContext();
				shoppingCartBean = (ShoppingCart) ic.lookup("java:global/HandSalesEAR/HandSalesEJB/ShoppingCartBean");

				// put EJB in HTTP session for future servlet calls
				request.getSession().setAttribute(SHOPPING_CART_BEAN_SESION_KEY, shoppingCartBean);

			} catch (NamingException e) {
				throw new ServletException(e);
			}
		}

		String productName = request.getParameter("product");
		String productPrice = request.getParameter("price");
		String productDate = request.getParameter("date");
		System.out.println(productName);
		if (productName != null && productName.length() > 0) {
			try {

				// Se o produto esta presente na requisi��o.
				// Grava o produto
				Product product = new Product();
				product.setDescription(productName);
				product.setPrice(productPrice);
				product.setValidateDate(sdf.parse(productDate));
				shoppingCartBean.addProduct(product);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Request instructs to complete the purchase
		String persist = request.getParameter("complete");
		if (persist != null && persist.equalsIgnoreCase("yes")) {
			shoppingCartBean.completePurchase();
		}

	}

	/*
	 * http://localhost:8080/HandSales/testShopping?product=book&price=20&date=
	 * 10122014 http://localhost:8080/HandSales/testShopping?complete=yes
	 */

}
