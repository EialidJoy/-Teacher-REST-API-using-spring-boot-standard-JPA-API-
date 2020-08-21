package org.eialid.joy.springboot.crudrestteacher.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.eialid.joy.springboot.crudrestteacher.entity.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDaoJPA implements TeacherDao {
	
	private EntityManager entityManager;
	
	public TeacherDaoJPA(EntityManager theEntityManager) {
		entityManager=theEntityManager;
	}
	@Override
	public List<Teacher> allTeachersList() {
		TypedQuery<Teacher> theQuery=entityManager.createQuery("from Teacher", Teacher.class);
		List<Teacher> teachersList=theQuery.getResultList();
		return teachersList;
	}

	@Override
	public Teacher teacherFindById(int teacherId) {
		Teacher theTeacher=entityManager.find(Teacher.class, teacherId);
		return theTeacher;
	}

	@Override
	public void saveTeacher(Teacher theTeacher) {
		Teacher postTeacher=entityManager.merge(theTeacher);
		
		theTeacher.setId(postTeacher.getId());

	}

	@Override
	public void deleteTeacherById(int teacherId) {
		Query theQuery=entityManager.createQuery("delete from Teacher where id=:theId");
		theQuery.setParameter("theId", teacherId);
		
		theQuery.executeUpdate();

	}

}
