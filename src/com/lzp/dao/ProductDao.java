package com.lzp.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.lzp.pojo.PageBean;
import com.lzp.pojo.Product;
import com.lzp.util.DataSourceUtils;

public class ProductDao {

	public List<Product> findAllProduct() {
		List<Product> list = null;
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select * from product";
		try {
			list = runner.query(sql, new BeanListHandler<Product>(Product.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * 分页查询
	 */
	public PageBean findPageBean() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 获取所有商品的总条数
	 */
	public int getTotalCount()  {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(pid) from product";
		Long query = null;
		try {
			query = (Long) runner.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return query.intValue();
	}

	/*
	 * 获得分页的商品数据
	 */
	public List<Product> findProductListForPageBean(int index,int currentCount) {
		List<Product> list = null;
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product limit ?,?";
		try {
			list = runner.query(sql,new BeanListHandler<Product>(Product.class), index,currentCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * 根据商品ID查询出当前商品的详细信息
	 */
	public Product findById(String pid) {
		Product product = null;
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid=?";
		try {
			product = runner.query(sql, new BeanHandler<Product>(Product.class),pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
		
	}
	
}
