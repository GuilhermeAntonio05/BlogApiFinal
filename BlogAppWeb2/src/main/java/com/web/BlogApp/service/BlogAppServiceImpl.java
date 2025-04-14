package com.web.BlogApp.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.BlogApp.model.PostCommentModel;
//import com.web.BlogApp.model.postCommentRepository;
import com.web.BlogApp.model.PostModel;
import com.web.BlogApp.repository.BlogAppRepository;
//import com.web.BlogApp.repository.PostComentarioRepository;
import com.web.BlogApp.repository.PostCommentRepository;

import jakarta.transaction.Transactional;

//regras de negocio podem serem colocadas nessa classe de serviço
//ela serve como uma camada intermediaria entre o controler e repository e
//diminuir o acoplamento

@Service
public class BlogAppServiceImpl implements BlogAppService, PostCommentService {

	@Autowired
	private BlogAppRepository blogapprepository;

	@Autowired
	private PostCommentRepository postCommentRepository;

	@Override
	public List<PostModel> findAll() {
		// TODO Auto-generated method stub
		return blogapprepository.findAll();
	}

	@Override
	public Optional<PostModel> findById(UUID id) {
		// TODO Auto-generated method stub
		return blogapprepository.findById(id);
	}

	@Override
	public PostModel save(PostModel post) {
		// TODO Auto-generated method stub
		return blogapprepository.save(post);
	}

	@Override
	@Transactional
	public void delete(PostModel post) {
		// TODO Auto-generated method stub
		blogapprepository.delete(post);

	}

	@Override
	public void deleteById(UUID post) {
		blogapprepository.deleteById(post);
	}

	// TRATA COMENTÁRIOS DOS POSTS

	@Override
	public List<PostCommentModel> findAllComments() {
		return postCommentRepository.findAll();
	}

	@Override
	public Optional<PostCommentModel> findByIdComments(UUID id) {
		return postCommentRepository.findById(id);
	}

	@Override
	public PostCommentModel saveComments(PostCommentModel post) {
		return postCommentRepository.save(post);
	}

	@Override
	public void deleteComments(PostCommentModel post) {
		postCommentRepository.delete(post);
	}

	@Override
	public void deleteByIdComments(UUID postId) {
		postCommentRepository.deleteById(postId);
	}

}
