package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;
import exercise.Data;

@RestController
@RequestMapping("/api/")
public class PostsController {
        private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<Post>> show(@PathVariable int id) {
        var result = posts.stream()
                .filter(p -> p.getUserId() == id).toList();
        return ResponseEntity.ok()
                .body(result);
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> create(@RequestBody Post post, @PathVariable int id )  {
        var userPost = new Post();

        userPost.setSlug(post.getSlug());
        userPost.setTitle(post.getTitle());
        userPost.setBody(post.getBody());
        userPost.setUserId(id);

        posts.add(userPost);
        return new ResponseEntity<>(userPost,HttpStatus.CREATED);
    }
}

//    @PostMapping("/posts")
//    public ResponseEntity<Post> create(@RequestBody Post post) {
//        posts.add(post);
//        return new ResponseEntity<>(post, HttpStatus.CREATED);
//    }
//
//
//    @PutMapping("/posts/{id}")
//    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post data) {
//        var maybePost = posts.stream()
//                .filter(p -> p.getId().equals(id))
//                .findFirst();
//        if (maybePost.isPresent()) {
//            var post = maybePost.get();
//            post.setId(data.getId());
//            post.setTitle(data.getTitle());
//            post.setBody(data.getBody());
//            return new ResponseEntity<>(post, HttpStatus.OK);
//        }
//        else
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//    // END
//
//    @DeleteMapping("/posts/{id}")
//    public void destroy(@PathVariable String id) {
//        posts.removeIf(p -> p.getId().equals(id));
//    }

// END
