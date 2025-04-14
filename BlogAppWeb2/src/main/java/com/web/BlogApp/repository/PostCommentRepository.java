package com.web.BlogApp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.BlogApp.model.PostCommentModel;

public interface PostCommentRepository extends JpaRepository<PostCommentModel, UUID>{

}
