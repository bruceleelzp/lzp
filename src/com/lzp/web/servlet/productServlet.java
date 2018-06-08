package com.lzp.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzp.dao.ProductDao;
import com.lzp.pojo.PageBean;
import com.lzp.pojo.Product;
@WebServlet("/product")
public class productServlet extends HttpServlet {
	ProductDao dao = new ProductDao();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		
		PageBean<Product> pageBean = new PageBean<>();
		//客户端提交的数据
		String page = request.getParameter("param");
		if(page==null)page="1";
		
		int currentPage = Integer.parseInt(page);
		//int currentPage=1;//模拟当前是第一页
		//1、当前页private int currentPage;
		pageBean.setCurrentPage(currentPage);
		
		//2、当前显示的条数private int currentCount;
		int currentCount=12;
		pageBean.setCurrentCount(currentCount);
		
		//3、总条数private int totalCount;
		int totalCount = dao.getTotalCount();
		pageBean.setTotalCount(totalCount);
		
		//4、总页数private int totalPage;
		//公式：总页数=Math.ceil(总条数/当前显示的条数)
		int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);
		pageBean.setTotalPage(totalPage);
		
		//5、每页显示的数据,设置泛型，提高扩展性private List<T> productList = new ArrayList<T>();
		//索引index=(当前页数-1)*每页赤示的条数;
		int index=(currentPage-1)*currentCount;
		List<Product> productList = dao.findProductListForPageBean(index,currentCount);
		pageBean.setProductList(productList);
		
		
		
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("product_list.jsp").forward(request, response);
		
	}
}
