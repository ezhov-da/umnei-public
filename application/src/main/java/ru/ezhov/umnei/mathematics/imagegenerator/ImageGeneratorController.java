package ru.ezhov.umnei.mathematics.imagegenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/image-generator")
public class ImageGeneratorController {
    private static final Logger LOG = LoggerFactory.getLogger(ImageGeneratorController.class);

    private final SessionImageStore sessionImageStore;
    private final ImageGeneratorApplicationService imageGeneratorApplicationService;

    public ImageGeneratorController(SessionImageStore sessionImageStore, ImageGeneratorApplicationService imageGeneratorApplicationService) {
        this.sessionImageStore = sessionImageStore;
        this.imageGeneratorApplicationService = imageGeneratorApplicationService;
    }

    @GetMapping(path = "/tasks/generate", produces = MediaType.APPLICATION_JSON_VALUE)
    public String generateImage(@NotNull @RequestParam String type,
                                @NotNull @RequestParam String actions,
                                @NotNull @RequestParam int from,
                                @NotNull @RequestParam int to
    ) {
        LOG.debug(
                "method=tasks action=\"запрошена генерация изображения математического примера\" type={} actions={} from={} to={}",
                type, actions, from, to
        );

        try {
            final byte[] image = imageGeneratorApplicationService.createImage(type, actions, from, to);

            final String guid = UUID.randomUUID().toString();
            sessionImageStore.put(guid, image);
            final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

            objectBuilder.add("link", createLink(guid));

            return objectBuilder.build().toString();
        } catch (ImageGeneratorApplicationServiceException e) {
            throw new RuntimeException("бла бла");
        }
    }

    private String createLink(String guid) {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ImageGeneratorController.class)
                        .getImage(guid)
        ).withSelfRel().toUri().toString();
    }

    @GetMapping(path = "/tasks/image/{guid}")
    public ResponseEntity<Resource> getImage(@PathVariable("guid") @NotNull String guid) {
        LOG.debug("method=tasks action=\"запрошен изображение\" guid={}", guid);
        try {
            final Optional<byte[]> optionalBytes = sessionImageStore.get(guid);
            if (optionalBytes.isPresent()) {
                ByteArrayResource resource = new ByteArrayResource(optionalBytes.get());
                return ResponseEntity.ok().header(
                        "Content-Disposition",
                        "attachment; filename*=UTF-8''" +
                                /*избавляемся от +ков в названии*/
                                URLEncoder.encode("Математические примеры.png", "UTF-8").replaceAll("\\+", "%20"))
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            } else {
                return ResponseEntity.status(500).body(new ByteArrayResource("Отсутствует изображение".getBytes()));
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("бла бла");
        }
    }
}
