package com.elearn.app.repositories;

import com.elearn.app.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course,String> {


    Optional<Course> findByTitle(String title);

   Page<Course> findByLive(boolean live, Pageable pageable);


    List<Course> findByTitleContainingIgnoreCaseOrShortDescContainingIgnoreCase(String keyword, String keyword1);

    // search the course by title

//    @Query("select c from Course c where c.category.i")
//    List<Course> findByCategoryId(String categoryId);

}
