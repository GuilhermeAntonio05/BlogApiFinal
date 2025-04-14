package com.web.BlogApp.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.web.BlogApp.model.PostCommentModel;

public interface PostCommentService {
	List<PostCommentModel> findAllComments();
	Optional<PostCommentModel> findByIdComments(UUID id);
	PostCommentModel saveComments(PostCommentModel post);
	void deleteComments(PostCommentModel post);
	void deleteByIdComments(UUID postId);
}
