package com.lzp.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.lzp.pojo.CommonsUtils;
import com.lzp.pojo.User;
import com.lzp.service.UserService;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		User user = new User();
		// 获得表单数据
		try {
			//自己指定一个类型转换器（将string转成Date）
			ConvertUtils.register(new Converter() {
				@Override
				public Object convert(Class clazz, Object value) {
					//将String转成date
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date parse=null;
					try {
						parse = sdf.parse(value.toString());
						//System.out.println(parse);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return parse;
				}
			} , Date.class);
			//映射封装
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		user.setUid(CommonsUtils.getUUID());
		user.setTelephone(null);
		user.setState(0);
		user.setCode(CommonsUtils.getUUID());
		
		//将user传递给service层
		UserService service = new UserService();
		boolean isRegisterSuccess =  service.regist(user);
		System.out.println(isRegisterSuccess);
		//是否注册成功
		if(isRegisterSuccess) {
			//注册成功页面
			response.sendRedirect("registerSuccess.jsp");
		}else {
			//注册失败
			response.sendRedirect("registerFail.jsp");
			
		}
	}
}