package org.sparta.springlv3.lecture;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Optional<Lecture> findById(Long id);
    List<Lecture> findByTeacher(String name);
    List<Lecture> findByCategory(LectureCategoryEnum category);
}
