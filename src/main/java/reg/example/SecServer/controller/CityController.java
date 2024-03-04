package reg.example.SecServer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reg.example.SecServer.entity.BookEntity;
import reg.example.SecServer.entity.CityEntity;
import reg.example.SecServer.response.BaseResponse;
import reg.example.SecServer.response.DataResponse;
import reg.example.SecServer.response.ListResponse;
import reg.example.SecServer.service.BookService;
import reg.example.SecServer.service.CityService;

@RequestMapping("api/v1/city")
@RestController
@AllArgsConstructor
public class CityController {
    private final CityService service;

    @GetMapping("/all")
    public ResponseEntity<ListResponse<CityEntity>> getAll() {
        return ResponseEntity.ok(new ListResponse<CityEntity>(true,"Города", service.getAll()));
    }

    @GetMapping("/find{id}")
    public ResponseEntity<DataResponse<CityEntity>> getBy_id(@RequestParam Long id) {
        try {
            if (service.findById(id).isPresent()) {
                return ResponseEntity.ok(new DataResponse<CityEntity>(true, "Города по id = " + id, service.findById(id).orElseThrow()));
            } else {
                return ResponseEntity.badRequest().body(new DataResponse<CityEntity>(false, "Города не найдены", service.findById(id).orElseThrow()));
            }
        } catch (Exception e ) {
            return ResponseEntity.badRequest().body(new DataResponse<CityEntity>(false, "Города не найдены", service.findById(id).orElseThrow()));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<DataResponse<CityEntity>> save(@RequestBody CityEntity city) {
        return ResponseEntity.ok(new DataResponse<CityEntity>(true, "Город сохранен", service.save(city)));
    }

    @PutMapping("/update")
    private ResponseEntity<BaseResponse> updateBy_body(@RequestBody CityEntity city) {
        try {
            if (service.findById(city.getId()).isPresent()) {
                service.save(city);
                return ResponseEntity.ok(new BaseResponse(true, "Город был обновлен"));
            } else {
                return ResponseEntity.badRequest().body(new BaseResponse(false, "Город не был обновлен"));
            }
        } catch (Exception e ) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Город не был обновлен"));
        }
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<BaseResponse> deleteBy_id(@PathVariable Long id) {
        try {
            if (service.findById(id).isPresent()) {
                service.delete(id);
                return ResponseEntity.ok(new BaseResponse(true, "Город удален"));
            } else {
                return ResponseEntity.badRequest().body(new BaseResponse(false, "Город не был удален"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Город не был удален" + e.getMessage()));
        }
    }
}
