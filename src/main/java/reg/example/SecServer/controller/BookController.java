package reg.example.SecServer.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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


@Tag(name="Книги", description="Основной контроллер с книгами")
@RequestMapping("api/v1/book")
@RestController
@AllArgsConstructor
public class BookController {
    private final BookService service;

/*    @GetMapping("/all")
    public ResponseEntity<ListResponse<BookEntity>> getAll() {
        return ResponseEntity.ok(new ListResponse<BookEntity>(true,"Книги", service.getAll()));
    }*/

    @Operation(
            summary = "Вывод книг",
            description = "Позволяет вывести все книги"
    )
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

    @Operation(
            summary = "Поиск книги",
            description = "Параметризированный поиск по идентификатору"
    )
    @GetMapping
    public ResponseEntity<BaseResponse> findBy_id(
            @RequestParam @Parameter(description = "Идентификатор книги") Long id
    ) {
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

    @Operation(
            summary = "Регистрация книги",
            description = "Позволяет добавить книгу"
    )
    @PostMapping
    public ResponseEntity<DataResponse<BookEntity>> save(
            @RequestBody @Parameter(description = "Новая книга") BookEntity book
    ) {
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

    @Operation(
            summary = "Обновление книги",
            description = "Изменяет текущие данные книги"
    )
    @PutMapping
    public ResponseEntity<BaseResponse> update(
            @RequestBody @Parameter(description = "Обновляемая книга") BookEntity book
    ) {
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

    @Operation(
            summary = "Удаление книги",
            description = "Позволяет удалить книгу"
    )
    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteBy_id(
            @RequestParam @Parameter(description = "Идентификатор книги") Long id
    ) {
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

    @Operation(
            summary = "Поиск книги по параметру (1)",
            description = "Позволяет найти книгу по идентификатору автора"
    )
    // Поиск по id автора
    @GetMapping("/author")
    public ResponseEntity<BookListResponse> getBy_authorId(
            @RequestParam @Parameter(description = "Идентификатор автора") Long id
    ) {
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

    @Operation(
            summary = "Поиск книги по параметру (3)",
            description = "Позволяет найти книгу по автору"
    )
    // Поиск по id автора
    @GetMapping("/{author}")
    public ResponseEntity<BookListResponse> getBy_authorSurname(
            @RequestParam @Parameter(description = "Aвтора") String surname
    ) {
        try {
            if (!service.findByAuthorSurname(surname).isEmpty()) {
                return ResponseEntity.ok(new BookListResponse(true, "Книга найдена", service.findByAuthorSurname(surname)));
            } else {
                return ResponseEntity.ok(new BookListResponse(false, "Книга не найдена", null));
            }
        } catch (RuntimeException e) {
//            return ResponseEntity.ok(new BookListResponse(false, "Книга не найдена", null));
            return ResponseEntity.badRequest().body(new BookListResponse(false, e.getMessage(), null));
        }
    }

    @Operation(
            summary = "Поиск книги по параметру (2)",
            description = "Позволяет найти книгу по названию"
    )
    // Поиск по названию книги
    @GetMapping("/{title}")
    public ResponseEntity<BookListResponse> findByTitle(
            @PathVariable @Parameter(description = "Название книги") String title
    ) {
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
