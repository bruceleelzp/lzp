package com.lzp.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lzp.dao.ProductDao;
import com.lzp.pojo.Product;
@WebServlet("/productInfo")
public class ProductInfo extends HttpServlet {
	private ProductDao dao = new ProductDao();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("param");
		System.out.println(action);
		if("info".equals(action)) {
			findById(request,response);
			
		}
		

	}

	
	/**
	 * 根据ID查询单个商品的详细信息
	 * @param request
	 * @param response
	 */
	private void findById(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String pid = request.getParameter("pid");
		Product product = dao.findById(pid);
		session.setAttribute("product", product);
		try {
			response.sendRedirect("product_info.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
