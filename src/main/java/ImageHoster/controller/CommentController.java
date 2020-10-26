package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.model.UserProfile;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import ImageHoster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    // save the comment under an image by applicant or user.
    @RequestMapping(value = "image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String saveComments(@RequestParam("comment") String commentValue, @PathVariable("imageTitle") String imageTitle, @PathVariable("imageId") Integer imageId, HttpSession session, Model model) {
        Comment comment = new Comment();
        comment.setText(commentValue);
        Image image = imageService.getImage(imageId);
        comment.setImage(image);
        comment.setCreatedDate(new Date());
        User user = (User) session.getAttribute("loggeduser");
        comment.setUser(user);
        commentService.saveComments(comment);
        model.addAttribute("comments", comment);
        return "redirect:/images/" + imageId + '/' + imageTitle;
    }
}
