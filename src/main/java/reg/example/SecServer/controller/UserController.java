package reg.example.SecServer.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reg.example.SecServer.entity.PublisherEntity;
import reg.example.SecServer.entity.UserEntity;
import reg.example.SecServer.response.BaseResponse;
import reg.example.SecServer.response.DataResponse;
import reg.example.SecServer.response.ListResponse;
import reg.example.SecServer.service.PublisherService;
import reg.example.SecServer.service.UserService;

// ОБНОВИТЬ ДАННЫЙ КЛАСС
// СТАРЫЕ МЕТОДЫ
@Hidden
@Tag(name="Пользователь", description="Описание . . .")
@RequestMapping("api/v1/user")
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/all")
    public ResponseEntity<ListResponse<UserEntity>> getAll() {
        return ResponseEntity.ok(new ListResponse<UserEntity>(true,"Пользователи", service.getAll()));
    }

    @GetMapping("/find{id}")
    public ResponseEntity<DataResponse<UserEntity>> getBy_id(@RequestParam Long id) {
        try {
            if (service.findById(id).isPresent()) {
                return ResponseEntity.ok(new DataResponse<UserEntity>(true, "Пользователи по id = " + id, service.findById(id).orElseThrow()));
            } else {
                return ResponseEntity.badRequest().body(new DataResponse<UserEntity>(false, "Пользователи не найдены", service.findById(id).orElseThrow()));
            }
        } catch (Exception e ) {
            return ResponseEntity.badRequest().body(new DataResponse<UserEntity>(false, "Пользователи не найдены", service.findById(id).orElseThrow()));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<DataResponse<UserEntity>> save(@RequestBody UserEntity user) {
        return ResponseEntity.ok(new DataResponse<UserEntity>(true, "Пользователь сохранен", service.save(user)));
    }

    @PutMapping("/update")
    private ResponseEntity<BaseResponse> updateBy_body(@RequestBody UserEntity user) {
        try {
            if (service.findById(user.getId()).isPresent()) {
                service.save(user);
                return ResponseEntity.ok(new BaseResponse(true, "Пользователь обновлен"));
            } else {
                return ResponseEntity.badRequest().body(new BaseResponse(false, "Пользователь не был обновлен"));
            }
        } catch (Exception e ) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Пользователь не был обновлен"));
        }
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<BaseResponse> deleteBy_id(@PathVariable Long id) {
        try {
            if (service.findById(id).isPresent()) {
                service.delete(id);
                return ResponseEntity.ok(new BaseResponse(true, "Пользователь удален"));
            } else {
                return ResponseEntity.badRequest().body(new BaseResponse(false, "Пользователь не был удален"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Пользовател не был удален" + e.getMessage()));
        }
    }
}
