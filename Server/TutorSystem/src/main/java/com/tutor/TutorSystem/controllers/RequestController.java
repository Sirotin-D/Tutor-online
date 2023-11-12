package com.tutor.TutorSystem.controllers;

import com.tutor.TutorSystem.POJOObjects.RequestCreater;
import com.tutor.TutorSystem.POJOObjects.RequestUpdater;
import com.tutor.TutorSystem.models.Lesson;
import com.tutor.TutorSystem.models.Request;
import com.tutor.TutorSystem.services.LessonService;
import com.tutor.TutorSystem.services.RequestService;
import com.tutor.TutorSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requestLesson")
public class RequestController {

    private final RequestService requestService;
    private final UserService userService;
    private final LessonService lessonService;

    @Autowired
    public RequestController(RequestService LessonService, UserService userService, LessonService lessonService) {
        this.requestService = LessonService;
        this.userService = userService;
        this.lessonService = lessonService;
    }

    @GetMapping()
    public List<Request> getLessons(){
        return requestService.findAll(); // Jackson конвертирует в json
    }

    @GetMapping("/{id}")
    public Request getLesson(@PathVariable("id")int id){
        return  requestService.findOne(id);
    }



    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody RequestCreater requestCreater)
    {
        int userId = requestCreater.getUserId();
        int lessonId = requestCreater.getLessonId();
        Request request = new Request ( userId , lessonService.findOne(lessonId));
        if(requestService.save(request))
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
            else
                return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id)
    {
        requestService.delete(id);
        return new ResponseEntity<HttpStatus>( HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody RequestUpdater requestUpdater )
    {
        int requestId = requestUpdater.getRequestId();
        String status = requestUpdater.getStatus();
        Request request = requestService.findOne(requestId);
        request.setStatus(status);
        requestService.update(request);
        return new ResponseEntity<HttpStatus>( HttpStatus.OK);
    }
}
