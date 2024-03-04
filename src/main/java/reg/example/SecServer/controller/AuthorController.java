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

/*    @GetMapping("/find{id}")
    public ResponseEntity<BaseResponse> getBy_id(@RequestParam Long id) {
        try {
            if (service.findById(id).isPresent()) {
                return ResponseEntity.ok(new DataResponse<AuthorEntity>(true, "Автор по id = " + id, service.findById(id).orElseThrow()));
            } else {
                return ResponseEntity.ok(new DataResponse<AuthorEntity>(false, "Автор не найден", service.findById(id).orElseThrow()));
            }
        } catch (RuntimeException  e ) {
            return ResponseEntity.ok(new DataResponse<AuthorEntity>(false, "Автор не найден", service.findById(id).orElseThrow()));
        }
    }*/

    // find by id
    @GetMapping
    public ResponseEntity<DataResponse<AuthorEntity>> by_id(@RequestParam Long id) {
        try {
            if (service.findById(id).isPresent()) {
                return ResponseEntity.ok(new DataResponse<AuthorEntity>(true, "Найден следующий автор", service.findById(id).orElseThrow()));
            } else {
                return ResponseEntity.ok(new DataResponse<AuthorEntity>(false, "Автор не найден", service.findById(id).orElseThrow()));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new DataResponse<AuthorEntity>(false, e.getMessage(), service.findById(id).orElseThrow()));
        }
    }

/*    @PostMapping("/add")
    public ResponseEntity<BaseResponse> save(@RequestBody AuthorEntity author) {
        return ResponseEntity.ok(new DataResponse<AuthorEntity>(true, "Автор сохранен", service.save(author)));
    }*/

    // add new author
    @PostMapping
    public ResponseEntity<DataResponse<AuthorEntity>> save(@RequestBody AuthorEntity author) {
        try {
            return ResponseEntity.ok(new DataResponse<AuthorEntity>(true, "Автор сохранен", service.save(author)));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new DataResponse<AuthorEntity>(false, e.getMessage(), service.findById(author.getId()).orElseThrow()));
        }
    }

/*    @PutMapping("/update")
    private ResponseEntity<BaseResponse> updateBy_body(@RequestBody AuthorEntity author) {
        try {
            if (service.findById(author.getId()).isPresent()) {
                service.save(author);
                return ResponseEntity.ok(new BaseResponse(true, "Автор был обновлен"));
            } else {
                return ResponseEntity.ok(new BaseResponse(false, "Автор не был обновлен"));
            }
        } catch (RuntimeException  e ) {
            return ResponseEntity.ok(new BaseResponse(false, "Автор не был обновлен"));
        }
    }*/

    // update
    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody AuthorEntity author) {
        try {
            if (service.findById(author.getId()).isPresent()) {
                service.update(author);
                return ResponseEntity.ok(new BaseResponse(true, "Автор обновлен"));
            } else {
                return ResponseEntity.ok(new BaseResponse(false, "Автор не был обновлен"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
        }
    }

/*    @DeleteMapping("/del/{id}")
    public ResponseEntity<BaseResponse> deleteBy_id(@PathVariable Long id) {
        try {
            if (service.findById(id).isPresent()) {
                service.delete(id);
                return ResponseEntity.ok(new BaseResponse(true, "Автор был удален"));
            } else {
                return ResponseEntity.ok(new BaseResponse(false, "Автор не был найден и не был удален"));
            }
        } catch (RuntimeException  e) {
            return ResponseEntity.ok(new BaseResponse(false, "Автор не был удален: " + e.getMessage()));
        }
    }*/

    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteBy_id(@RequestParam Long id) {
        try {
            if (service.findById(id).isPresent()) {
                service.delete(id);
                return ResponseEntity.ok(new BaseResponse(true, "Автор удален"));
            } else {
                return ResponseEntity.ok(new BaseResponse(true, "Автор не был найден"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
        }
    }
}
