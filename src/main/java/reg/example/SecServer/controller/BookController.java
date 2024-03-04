package reg.example.SecServer.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reg.example.SecServer.entity.BookEntity;
import reg.example.SecServer.response.BaseResponse;
import reg.example.SecServer.response.DataResponse;
import reg.example.SecServer.response.ListResponse;
import reg.example.SecServer.service.BookService;



@RequestMapping("api/v1/book")
@RestController
@AllArgsConstructor
public class BookController {
    private final BookService service;

    @GetMapping("/all")
    public ResponseEntity<ListResponse<BookEntity>> getAll() {
        return ResponseEntity.ok(new ListResponse<BookEntity>(true,"Книги", service.getAll()));
    }

    @GetMapping("/find{id}")
    public ResponseEntity<DataResponse<BookEntity>> getBy_id(@RequestParam Long id) {
        try {
            if (service.findById(id).isPresent()) {
                return ResponseEntity.ok(new DataResponse<BookEntity>(true, "Книги по id = " + id, service.findById(id).orElseThrow()));
            } else {
                return ResponseEntity.badRequest().body(new DataResponse<BookEntity>(false, "Книга не найдена", service.findById(id).orElseThrow()));
            }
        } catch (Exception e ) {
            return ResponseEntity.badRequest().body(new DataResponse<BookEntity>(false, "Книга не найдена", service.findById(id).orElseThrow()));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<DataResponse<BookEntity>> save(@RequestBody BookEntity book) {
        return ResponseEntity.ok(new DataResponse<BookEntity>(true, "Книга сохранена", service.save(book)));
    }

    @PutMapping("/update")
    private ResponseEntity<BaseResponse> updateBy_body(@RequestBody BookEntity book) {
        try {
            if (service.findById(book.getId()).isPresent()) {
                service.save(book);
                return ResponseEntity.ok(new BaseResponse(true, "Книга была обновлена"));
            } else {
                return ResponseEntity.badRequest().body(new BaseResponse(false, "Книга не была обновлена"));
            }
        } catch (Exception e ) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Книга не была обновлена"));
        }
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<BaseResponse> deleteBy_id(@PathVariable Long id) {
        try {
            if (service.findById(id).isPresent()) {
                service.delete(id);
                return ResponseEntity.ok(new BaseResponse(true, "Книга была удалена"));
            } else {
                return ResponseEntity.badRequest().body(new BaseResponse(false, "Книга не удалена"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Книга не удалена" + e.getMessage()));
        }
    }
}
