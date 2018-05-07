package dao;

import java.util.ArrayList;

import entity.*;

public interface Dao {
	public ArrayList<Studente> getAll();
	public Studente getById(int id);
	public ArrayList<?> getBy(String s);
	public void update(Studente s);
	public void delete(Studente s);
	public void add(Studente s);
}
