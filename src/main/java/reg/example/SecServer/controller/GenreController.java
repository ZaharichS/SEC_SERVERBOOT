package reg.example.SecServer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reg.example.SecServer.entity.CityEntity;
import reg.example.SecServer.entity.GenreEntity;
import reg.example.SecServer.response.BaseResponse;
import reg.example.SecServer.response.DataResponse;
import reg.example.SecServer.response.ListResponse;
import reg.example.SecServer.service.CityService;
import reg.example.SecServer.service.GenreService;

@RequestMapping("api/v1/genre")
@RestController
@AllArgsConstructor
public class GenreController {
    private final GenreService service;

    @GetMapping("/all")
    public ResponseEntity<ListResponse<GenreEntity>> getAll() {
        return ResponseEntity.ok(new ListResponse<GenreEntity>(true,"Жанры", service.getAll()));
    }

    @GetMapping("/find{id}")
    public ResponseEntity<DataResponse<GenreEntity>> getBy_id(@RequestParam Long id) {
        try {
            if (service.findById(id).isPresent()) {
                return ResponseEntity.ok(new DataResponse<GenreEntity>(true, "Жанры по id = " + id, service.findById(id).orElseThrow()));
            } else {
                return ResponseEntity.badRequest().body(new DataResponse<GenreEntity>(false, "Жанры не найдены", service.findById(id).orElseThrow()));
            }
        } catch (Exception e ) {
            return ResponseEntity.badRequest().body(new DataResponse<GenreEntity>(false, "Жанры не найдены", service.findById(id).orElseThrow()));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<DataResponse<GenreEntity>> save(@RequestBody GenreEntity genre) {
        return ResponseEntity.ok(new DataResponse<GenreEntity>(true, "Жанр сохранен", service.save(genre)));
    }

    @PutMapping("/update")
    private ResponseEntity<BaseResponse> updateBy_body(@RequestBody GenreEntity genre) {
        try {
            if (service.findById(genre.getId()).isPresent()) {
                service.save(genre);
                return ResponseEntity.ok(new BaseResponse(true, "Жанр был обновлен"));
            } else {
                return ResponseEntity.badRequest().body(new BaseResponse(false, "Жанр не был обновлен"));
            }
        } catch (Exception e ) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Жанр не был обновлен"));
        }
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<BaseResponse> deleteBy_id(@PathVariable Long id) {
        try {
            if (service.findById(id).isPresent()) {
                service.delete(id);
                return ResponseEntity.ok(new BaseResponse(true, "Жанр удален"));
            } else {
                return ResponseEntity.badRequest().body(new BaseResponse(false, "Жанр не был удален"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Жанр не был удален" + e.getMessage()));
        }
    }
}
