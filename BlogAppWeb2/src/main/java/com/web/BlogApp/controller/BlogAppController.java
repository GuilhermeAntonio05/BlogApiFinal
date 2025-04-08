package com.web.BlogApp.controller;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

//import com.web.BlogApp.model.PostComentarioModel;
import com.web.BlogApp.model.PostModel;
import com.web.BlogApp.service.BlogAppService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
//@RestController //não funciona para returno de string
public class BlogAppController {


    @Autowired
    BlogAppService blogappservice;

//	@Autowired
//	private PostComentarioRepository pr; 


    // LISTA TODOS OS POSTS
    @GetMapping(value = "/posts") // posso utilizar assim também chamando pelo navegador

    //	@RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getPosts() {
        ModelAndView mv = new ModelAndView("posts");
        List<PostModel> posts = blogappservice.findAll();
        mv.addObject("post", posts);
        return mv;
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public ModelAndView getPostDetails(@PathVariable("id") UUID id) {
        ModelAndView mv = new ModelAndView("postDeatils");
        Optional<PostModel> blogappModelOptional = blogappservice.findById(id);
        PostModel posts = blogappModelOptional.get();
        mv.addObject("posts", posts);
        return mv;
    }

    @GetMapping(value = "/newpost")
    public String getPostform() {
        return "newpostForm";
    }

    @PostMapping(value = "/newpost")
    public String savePost(@Valid PostModel post, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos");
            return "redirect:/newpost";
        }
        post.setData(LocalDate.now());
        blogappservice.save(post);
        return "redirect:/posts";
    }

    //função de delete
    @GetMapping(value = "/deletar/{id}")
    public String deletePost(@PathVariable UUID id) {
        blogappservice.deleteById(id);
        return "redirect:/posts";
    }

    //função de edit
    @RequestMapping(value = "/posts/edit/{id}", method = RequestMethod.GET)
    public ModelAndView getPostEditDetails(@PathVariable("id") UUID id) {
        ModelAndView mv = new ModelAndView("postEdit");
        Optional<PostModel> blogappModelOptional = blogappservice.findById(id);
        PostModel posts = blogappModelOptional.get();
        mv.addObject("posts", posts);
        return mv;
    }

    @RequestMapping(value = "/posts/edit/{id}", method = RequestMethod.POST)
    public String editPost(@PathVariable(value = "id") UUID id, @Valid PostModel post, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos");
            return "redirect:/posts/edit/{id}";
        }
        post.setData(LocalDate.now());
        blogappservice.save(post);
        return "redirect:/posts";
    }
}
