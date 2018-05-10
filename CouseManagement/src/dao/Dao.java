package dao;

import java.util.ArrayList;

public interface Dao {
	public ArrayList<?> getAll() throws DaoException;
	public Object getById(int id) throws DaoException;
	public ArrayList<?> getBy(String s) throws DaoException;
	public void update(Object bean) throws DaoException;
	public void delete(Object bean) throws DaoException;
	public void add(Object bean) throws DaoException;
}
