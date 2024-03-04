package reg.example.SecServer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reg.example.SecServer.entity.GenreEntity;
import reg.example.SecServer.entity.PublisherEntity;
import reg.example.SecServer.response.BaseResponse;
import reg.example.SecServer.response.DataResponse;
import reg.example.SecServer.response.ListResponse;
import reg.example.SecServer.service.GenreService;
import reg.example.SecServer.service.PublisherService;

@RequestMapping("api/v1/publisher")
@RestController
@AllArgsConstructor
public class PublisherController {
    private final PublisherService service;

    @GetMapping("/all")
    public ResponseEntity<ListResponse<PublisherEntity>> getAll() {
        return ResponseEntity.ok(new ListResponse<PublisherEntity>(true,"Издания", service.getAll()));
    }

    @GetMapping("/find{id}")
    public ResponseEntity<DataResponse<PublisherEntity>> getBy_id(@RequestParam Long id) {
        try {
            if (service.findById(id).isPresent()) {
                return ResponseEntity.ok(new DataResponse<PublisherEntity>(true, "Издания по id = " + id, service.findById(id).orElseThrow()));
            } else {
                return ResponseEntity.badRequest().body(new DataResponse<PublisherEntity>(false, "Издания не найдены", service.findById(id).orElseThrow()));
            }
        } catch (Exception e ) {
            return ResponseEntity.badRequest().body(new DataResponse<PublisherEntity>(false, "Издания не найдены", service.findById(id).orElseThrow()));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<DataResponse<PublisherEntity>> save(@RequestBody PublisherEntity publisher) {
        return ResponseEntity.ok(new DataResponse<PublisherEntity>(true, "Издание сохранено", service.save(publisher)));
    }

    @PutMapping("/update")
    private ResponseEntity<BaseResponse> updateBy_body(@RequestBody PublisherEntity publisher) {
        try {
            if (service.findById(publisher.getId()).isPresent()) {
                service.save(publisher);
                return ResponseEntity.ok(new BaseResponse(true, "Издание обновлено"));
            } else {
                return ResponseEntity.badRequest().body(new BaseResponse(false, "Издание не было обновлено"));
            }
        } catch (Exception e ) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Издание не было обновлено"));
        }
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<BaseResponse> deleteBy_id(@PathVariable Long id) {
        try {
            if (service.findById(id).isPresent()) {
                service.delete(id);
                return ResponseEntity.ok(new BaseResponse(true, "Издание удалено"));
            } else {
                return ResponseEntity.badRequest().body(new BaseResponse(false, "Издание не было удалено"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Издание не было удалено" + e.getMessage()));
        }
    }
}
