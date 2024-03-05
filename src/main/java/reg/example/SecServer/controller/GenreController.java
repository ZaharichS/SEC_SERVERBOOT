package reg.example.SecServer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reg.example.SecServer.entity.CityEntity;
import reg.example.SecServer.entity.GenreEntity;
import reg.example.SecServer.entity.PublisherEntity;
import reg.example.SecServer.response.BaseResponse;
import reg.example.SecServer.response.DataResponse;
import reg.example.SecServer.response.ListResponse;
import reg.example.SecServer.service.CityService;
import reg.example.SecServer.service.GenreService;

@Tag(name="Жанр", description="Контроллер для работы с жанром книг")
@RequestMapping("api/v1/genre")
@RestController
@AllArgsConstructor
public class GenreController {
    private final GenreService service;

    @Operation(
            summary = "Вывод жанров",
            description = "Позволяет вывести все жанры"
    )
    @GetMapping("/all")
    public ResponseEntity<ListResponse<GenreEntity>> getAll() {
        return ResponseEntity.ok(new ListResponse<GenreEntity>(true,"Жанры", service.getAll()));
    }

    @Operation(
            summary = "Поиск жанра",
            description = "Параметризированный поиск по идентификатору"
    )
    @GetMapping
    public ResponseEntity<DataResponse<GenreEntity>> by_id(@RequestParam Long id) {
        try {
            if (service.findById(id).isPresent()) {
                return ResponseEntity.ok(new DataResponse<GenreEntity>(true, "Найден жанр", service.findById(id).orElseThrow()));
            } else {
                return ResponseEntity.ok(new DataResponse<GenreEntity>(false, "Жанр не найден", service.findById(id).orElseThrow()));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new DataResponse<GenreEntity>(false, e.getMessage(),service.findById(id).orElseThrow()));
        }
    }

    @Operation(
            summary = "Регистрация жанра",
            description = "Позволяет добавить жанр"
    )
    @PostMapping
    public ResponseEntity<BaseResponse> save(@RequestBody GenreEntity genre) {
        try {
            return ResponseEntity.ok(new DataResponse<GenreEntity>(true, "Жанр сохранен", service.save(genre)));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
        }
    }

    @Operation(
            summary = "Обновление жанра",
            description = "Изменяет текущие данные жанра"
    )
    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody GenreEntity genre) {
        try {
            if (service.findById(genre.getId()).isPresent()) {
                service.update(genre);
                return ResponseEntity.ok(new BaseResponse(true, "Жанр обновлен"));
            } else {
                return ResponseEntity.ok(new BaseResponse(false, "Жанр не был найден"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
        }
    }

    @Operation(
            summary = "Удаление жанра",
            description = "Позволяет удалить жанр по идентификатору"
    )
    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteBy_id(@RequestParam Long id) {
        try {
            if (service.findById(id).isPresent()) {
                service.delete(id);
                return ResponseEntity.ok(new BaseResponse(true, "Жанр удален"));
            } else {
                return ResponseEntity.ok(new BaseResponse(true, "Жанр не был найден"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
        }
    }
}
