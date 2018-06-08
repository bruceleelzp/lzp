package com.lzp.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzp.pojo.Product;
import com.lzp.service.ProductServict;

public class IndexServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProductServict service = new ProductServict();
		//获得热门商品
		List<Product> hotProductList = service.findHotProductList();
		//获得最新商品
		List<Product> newProductList = service.findNewProductList();
		
	}
}