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

    @GetMapping
    public ResponseEntity<DataResponse<AuthorEntity>> getBy_id(@RequestParam Long id) {
        // Нет проверки на существующего автора с таким id
        return ResponseEntity.ok(new DataResponse<AuthorEntity>(true, "Автор по id = " + id, service.findById(id).orElseThrow()));
    }

    @PostMapping
    public ResponseEntity<DataResponse<AuthorEntity>> save(@RequestBody AuthorEntity author) {
        return ResponseEntity.ok(new DataResponse<AuthorEntity>(true, "Автор сохранен", service.save(author)));
    }

    @PutMapping
    private ResponseEntity<BaseResponse> updateBy_body(@RequestBody AuthorEntity author) {
        // Обновляем автора
        // Нет проверки на существующего автора с таким id
        service.update(author);
        return ResponseEntity.ok(new BaseResponse(true, "Автор был обновлен"));
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<BaseResponse> deleteBy_id(@PathVariable Long id) {
        try {
            // Нет проверки на существующего автора с таким id
            service.delele(id);
            return ResponseEntity.ok(new BaseResponse(true, "Автор был удален"));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponse(false, "Автор не был удален\n" + e.getMessage()));
        }
    }
}
