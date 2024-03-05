package reg.example.SecServer.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reg.example.SecServer.entity.BookEntity;
import reg.example.SecServer.entity.CityEntity;
import reg.example.SecServer.entity.PublisherEntity;
import reg.example.SecServer.response.BaseResponse;
import reg.example.SecServer.response.DataResponse;
import reg.example.SecServer.response.ListResponse;
import reg.example.SecServer.service.BookService;
import reg.example.SecServer.service.CityService;

@Tag(name="Город", description="Описание . . .")
@RequestMapping("api/v1/city")
@RestController
@AllArgsConstructor
public class CityController {
    private final CityService service;

    @GetMapping("/all")
    public ResponseEntity<ListResponse<CityEntity>> getAll() {
        return ResponseEntity.ok(new ListResponse<CityEntity>(true,"Города", service.getAll()));
    }

    @GetMapping
    public ResponseEntity<DataResponse<CityEntity>> by_id(@RequestParam Long id) {
        try {
            if (service.findById(id).isPresent()) {
                return ResponseEntity.ok(new DataResponse<CityEntity>(true, "Найден город", service.findById(id).orElseThrow()));
            } else {
                return ResponseEntity.ok(new DataResponse<CityEntity>(false, "Город не найден", service.findById(id).orElseThrow()));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new DataResponse<CityEntity>(false, e.getMessage(), service.findById(id).orElseThrow()));
        }
    }

    @PostMapping
    public ResponseEntity<DataResponse<CityEntity>> save(@RequestBody CityEntity city) {
        try {
            return ResponseEntity.ok(new DataResponse<CityEntity>(true, "Город сохранен", service.save(city)));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new DataResponse<CityEntity>(false, e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody CityEntity city) {
        try {
            if (service.findById(city.getId()).isPresent()) {
                service.update(city);
                return ResponseEntity.ok(new BaseResponse(true, "Город обновлен"));
            } else {
                return ResponseEntity.ok(new BaseResponse(false, "Город не был найден"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteBy_id(@RequestParam Long id) {
        try {
            if (service.findById(id).isPresent()) {
                service.delete(id);
                return ResponseEntity.ok(new BaseResponse(true, "Город удален"));
            } else {
                return ResponseEntity.ok(new BaseResponse(true, "Город не был найден"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
        }
    }
}
