package reg.example.SecServer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reg.example.SecServer.entity.AuthorEntity;
import reg.example.SecServer.response.BaseResponse;
import reg.example.SecServer.response.DataResponse;
import reg.example.SecServer.response.ListResponse;
import reg.example.SecServer.service.AuthorService;

@RequestMapping("api/v1/author")
@RestController
@AllArgsConstructor
public class AuthorController {
    private final AuthorService service;

    @GetMapping("/all")
    public ResponseEntity<ListResponse<AuthorEntity>> getAll() {
        return ResponseEntity.ok(new ListResponse<AuthorEntity>(true,"Авторы", service.getAll()));
    }

    @GetMapping("/find{id}")
    public ResponseEntity<DataResponse<AuthorEntity>> getBy_id(@RequestParam Long id) {
        try {
            if (service.findById(id).isPresent()) {
                return ResponseEntity.ok(new DataResponse<AuthorEntity>(true, "Автор по id = " + id, service.findById(id).orElseThrow()));
            } else {
            return ResponseEntity.badRequest().body(new DataResponse<AuthorEntity>(false, "Автор не найден", service.findById(id).orElseThrow()));
            }
        } catch (Exception e ) {
            return ResponseEntity.badRequest().body(new DataResponse<AuthorEntity>(false, "Автор не найден", service.findById(id).orElseThrow()));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<DataResponse<AuthorEntity>> save(@RequestBody AuthorEntity author) {
        return ResponseEntity.ok(new DataResponse<AuthorEntity>(true, "Автор сохранен", service.save(author)));
    }

    @PutMapping("/update")
    private ResponseEntity<BaseResponse> updateBy_body(@RequestBody AuthorEntity author) {
        try {
            if (service.findById(author.getId()).isPresent()) {
                service.save(author);
                return ResponseEntity.ok(new BaseResponse(true, "Автор был обновлен"));
            } else {
                return ResponseEntity.badRequest().body(new BaseResponse(false, "Автор не был обновлен"));
            }
        } catch (Exception e ) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Автор не был обновлен"));
        }
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<BaseResponse> deleteBy_id(@PathVariable Long id) {
        try {
            if (service.findById(id).isPresent()) {
                service.delete(id);
                return ResponseEntity.ok(new BaseResponse(true, "Автор был удален"));
            } else {
                return ResponseEntity.badRequest().body(new BaseResponse(false, "Автор не был найден и не был удален"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Автор не был удален" + e.getMessage()));
        }
    }
}
