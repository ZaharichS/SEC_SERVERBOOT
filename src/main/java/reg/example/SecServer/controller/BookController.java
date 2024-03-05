package reg.example.SecServer.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reg.example.SecServer.entity.BookEntity;
import reg.example.SecServer.response.BaseResponse;
import reg.example.SecServer.response.BookListResponse;
import reg.example.SecServer.response.DataResponse;
import reg.example.SecServer.response.ListResponse;
import reg.example.SecServer.service.BookService;


@Tag(name="Книги", description="Описание . . .")
@RequestMapping("api/v1/book")
@RestController
@AllArgsConstructor
public class BookController {
    private final BookService service;

/*    @GetMapping("/all")
    public ResponseEntity<ListResponse<BookEntity>> getAll() {
        return ResponseEntity.ok(new ListResponse<BookEntity>(true,"Книги", service.getAll()));
    }*/
    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAll() {
        return ResponseEntity.ok(new ListResponse<BookEntity>(true,"Книги", service.getAll()));
    }

/*    @GetMapping("/find{id}")
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
    }*/
    @GetMapping
    public ResponseEntity<BaseResponse> findBy_id(@RequestParam Long id) {
        try {
            if (service.findById(id).isPresent()) {
                return ResponseEntity.ok(new DataResponse<BookEntity>(true, "Найден книга", service.findById(id).orElseThrow()));
            } else {
                return ResponseEntity.ok(new DataResponse<BookEntity>(false, "Книга не найдена", service.findById(id).orElseThrow()));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
        }
    }

/*    @PostMapping("/add")
    public ResponseEntity<DataResponse<BookEntity>> save(@RequestBody BookEntity book) {
        return ResponseEntity.ok(new DataResponse<BookEntity>(true, "Книга сохранена", service.save(book)));
    }*/
    @PostMapping
    public ResponseEntity<DataResponse<BookEntity>> save(@RequestBody BookEntity book) {
        try {
            return ResponseEntity.ok(new DataResponse<BookEntity>(true, "Книга сохранена", service.save(book)));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new DataResponse<BookEntity>(false, e.getMessage()));
        }
    }

/*    @PutMapping("/update")
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
    }*/
    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody BookEntity book) {
        try {
            if (service.findById(book.getId()).isPresent()) {
                service.update(book);
                return ResponseEntity.ok(new BaseResponse(true, "Книга обновлена"));
            } else {
                return ResponseEntity.ok(new BaseResponse(false, "Книга не была найдена"));
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
                return ResponseEntity.ok(new BaseResponse(true, "Книга была удалена"));
            } else {
                return ResponseEntity.badRequest().body(new BaseResponse(false, "Книга не удалена"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Книга не удалена" + e.getMessage()));
        }
    }*/
    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteBy_id(@RequestParam Long id) {
        try {
            if (service.findById(id).isPresent()) {
                service.delete(id);
                return ResponseEntity.ok(new BaseResponse(true, "Книга удалена"));
            } else {
                return ResponseEntity.ok(new BaseResponse(true, "Книга не была найдена"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
        }
    }

/*    @GetMapping
    public ResponseEntity<BookListResponse> getBy_publisher(@RequestParam String title, @RequestParam String city) {
        return ResponseEntity.ok( new BookListResponse(true, "Книга найдена",service.findByPublisher(title,city)) );
    }*/

    // Поиск по id автора
    @GetMapping("/author")
    public ResponseEntity<BookListResponse> getBy_authorId(@RequestParam Long id) {
        try {
            if (!service.findByAuthorId(id).isEmpty()) {
                return ResponseEntity.ok(new BookListResponse(true, "Книга найдена", service.findByAuthorId(id)));
            } else {
                return ResponseEntity.ok(new BookListResponse(false, "Книга не найдена", null));
            }
        } catch (RuntimeException e) {
//            return ResponseEntity.ok(new BookListResponse(false, "Книга не найдена", null));
            return ResponseEntity.badRequest().body(new BookListResponse(false, e.getMessage(), null));
        }
    }

    // Поиск по названию книги
    @GetMapping("/{title}")
    public ResponseEntity<BookListResponse> findByTitle(@PathVariable String title) {
        try {
            if (!service.findByTitle(title).isEmpty()) {
                return ResponseEntity.ok(new BookListResponse(true, "Книга найдена", service.findByTitle(title)));
            } else {
                return ResponseEntity.ok(new BookListResponse(false, "Книга не найдена", null));
            }
        } catch (RuntimeException e ) {
            return ResponseEntity.badRequest().body(new BookListResponse(false, e.getMessage(), null));
        }
    }
}
