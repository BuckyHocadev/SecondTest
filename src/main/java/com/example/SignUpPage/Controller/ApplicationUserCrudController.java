package com.example.SignUpPage.Controller;
import com.example.SignUpPage.Model.RegistrationFormModel;
import com.example.SignUpPage.Service.ApplicationUserCrudService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/applicationUserCrudController")
@CrossOrigin(origins = "*")
public class ApplicationUserCrudController {
    @Autowired
    ApplicationUserCrudService service;

    @GetMapping("/add")
    public ResponseEntity<?> add(@RequestBody RegistrationFormModel model){
        if(service.add(model)){
            return ResponseEntity.ok("添加成功 -- Added successfully");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("用户已存在: User already exits");
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody RegistrationFormModel model){
        System.out.println("IM here and working ");
        service.update(model);
        if(service.update(model)){
            return ResponseEntity.ok("更新成功 -- Updated successfully");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("无法更新重试 -- Could not update try again ");
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody String username) throws JsonProcessingException {
        if(service.delete(username)){
            return ResponseEntity.ok("删除成功 --- Deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("找不到用户重试 --- Could not find the user try again ");
    }
}
