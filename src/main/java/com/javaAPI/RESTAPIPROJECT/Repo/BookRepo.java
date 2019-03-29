package com.javaAPI.RESTAPIPROJECT.Repo;

import com.javaAPI.RESTAPIPROJECT.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BookRepo extends JpaRepository <Book, Long>{

}
