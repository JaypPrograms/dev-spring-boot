package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    //define field for entity manager
    private EntityManager entityManager;
    //inject entity manager using constructor injections

    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }


    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);

    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        //retrieve the instructor
        Instructor theInstructor= entityManager.find(Instructor.class, theId);

        List<Course> courses = theInstructor.getCourses();

        for(Course tempCourse: courses){
            tempCourse.setInstructor(null);
        }

        //delete the instructor
        entityManager.remove(theInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail theInstructorDetail=entityManager.find(
                InstructorDetail.class, theId);

        //remove the associated object reference
        //break bi-directional link
        theInstructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(theInstructorDetail);

    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {

        TypedQuery<Course> query= entityManager.createQuery("from Course where instructor.id=:data", Course.class);
        query.setParameter("data", theId);

        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i "+
                "JOIN FETCH i.courses "+
                "JOIN FETCH i.instructorDetail "+
                "where i.id=:data", Instructor.class);
        query.setParameter("data", theId);

        Instructor instructor= query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {

        entityManager.merge(tempInstructor);

    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course tempCourse = entityManager.find(Course.class, theId);

        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        TypedQuery<Course> query= entityManager.
                createQuery("select c from Course c "+
                    "JOIN FETCH c.reviews "+
                        "where c.id=:data", Course.class);
        query.setParameter("data", theId );

        Course tempCourse =query.getSingleResult();

        return tempCourse;
    }

    @Override
    public Course findCourseAndStudentByCourseId(int theId) {

        TypedQuery<Course> query =entityManager.createQuery("select c from Course c "+
                "JOIN FETCH c.students "+
                "where c.id=:data", Course.class);
        query.setParameter("data", theId);

        Course tempCourse = query.getSingleResult();
        return tempCourse;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {

        TypedQuery<Student> query=entityManager.createQuery("select s from Student s "+
                "JOIN FETCH s.courses "+
                "where s.id=:data", Student.class);
        query.setParameter("data", theId);

        Student tempStudent=query.getSingleResult();
        return tempStudent;
    }

    @Override
    @Transactional
    public void update(Student tempStudent) {

        entityManager.merge(tempStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {

        Student tempStudent=entityManager.find(Student.class, theId);
        entityManager.remove(tempStudent);
    }

    @Override
    public Instructor findInstructorById(int theId) {

        return entityManager.find(Instructor.class, theId);
    }

}
