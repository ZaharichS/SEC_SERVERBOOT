package reg.example.SecServer.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
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

import java.util.List;

@Tag(name="Издания", description="Описание . . .")
@RequestMapping("api/v1/publisher")
@RestController
@AllArgsConstructor
public class PublisherController {
    private final PublisherService service;

    @GetMapping("/all")
    public ResponseEntity<ListResponse<PublisherEntity>> getAll() {
        return ResponseEntity.ok(new ListResponse<PublisherEntity>(true,"Издания", service.getAll()));
    }

    @GetMapping
    public  ResponseEntity<DataResponse<PublisherEntity>> by_id(@RequestParam Long id) {
        try {
            if (service.findById(id).isPresent()) {
                return ResponseEntity.ok(new DataResponse<PublisherEntity>(true, "Найдено издание", service.findById(id).orElseThrow()));
            } else {
                return ResponseEntity.ok(new DataResponse<PublisherEntity>(false, "Издание не найдено", service.findById(id).orElseThrow()));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new DataResponse<PublisherEntity>(false, e.getMessage(),service.findById(id).orElseThrow()));
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> save(@RequestBody PublisherEntity publisher) {
        try {
            return ResponseEntity.ok(new DataResponse<PublisherEntity>(true, "Издание сохранен", service.save(publisher)));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody PublisherEntity publisher) {
        try {
            if (service.findById(publisher.getId()).isPresent()) {
                service.update(publisher);
                return ResponseEntity.ok(new BaseResponse(true, "Издание обновлено"));
            } else {
                return ResponseEntity.ok(new BaseResponse(false, "Издание не было найдено"));
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
                return ResponseEntity.ok(new BaseResponse(true, "Издание удалено"));
            } else {
                return ResponseEntity.ok(new BaseResponse(true, "Издание не было найдено"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
        }
    }
}
