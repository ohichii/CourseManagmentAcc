package dao;

import java.util.ArrayList;

import entity.*;

public interface Dao {
	public ArrayList<Studente> getAll();
	public Studente getById(int id);
	public ArrayList<?> getBy(String s);
	public void update(Object bean);
	public void delete(Object bean);
	public void add(Object bean);
}
