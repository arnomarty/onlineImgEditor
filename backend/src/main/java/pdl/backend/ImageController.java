package pdl.backend;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class ImageController {

  @Autowired
  private ObjectMapper mapper;
  private final ImageDao imageDao;

  @Autowired
  public ImageController(ImageDao imageDao) {
    this.imageDao = imageDao;
  }

  @RequestMapping(value = "/images/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<?> getImage(@PathVariable("id") long id, @RequestParam(required = false) String algorithm) {

    Optional<Image> opt = imageDao.retrieve(id);

    if(opt.isPresent()) {

      if(algorithm != null){
        Image i = new Image("edited.jpg", ImageLogic.handle(opt.get()));

        opt.get().setData(i.getData());
      }

      return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
              .body(new InputStreamResource( new ByteArrayInputStream(opt.get().getData()) ));
    }
    else
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value = "/images/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteImage(@PathVariable("id") long id) {

    Optional<Image> optImg = imageDao.retrieve(id);
    if(optImg.isPresent()) {
      imageDao.delete(optImg.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

  }

  @RequestMapping(value = "/images", method = RequestMethod.POST)
  public ResponseEntity<?> addImage(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes) {
    try {
      Image img = new Image(file.getOriginalFilename(),file.getBytes());
      imageDao.create(img);
      return new ResponseEntity<>(HttpStatus.OK);
    }catch(IOException e){
      e.printStackTrace();
    }
    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
  }

  @RequestMapping(value = "/images", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
  @ResponseBody
  public ArrayNode getImageList() {
    ArrayNode nodes = mapper.createArrayNode();

    for( Image img : imageDao.retrieveAll()){
      ObjectNode entry = mapper.createObjectNode();
      entry.put("id", img.getId());
      entry.put("name", img.getName());
      nodes.add(entry);
    }
    return nodes;
  }

  @RequestMapping(value = "/algorithms", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
  @ResponseBody
  public ArrayNode getAlgorithmList() {
    System.out.println("I'M IN!!!");
    ArrayNode nodes = mapper.createArrayNode();
    int i=1;

    for( String name : imageDao.getAlgoList().keySet()){
      ObjectNode entry = mapper.createObjectNode();
      entry.put("name", name);
      for( String parameter : imageDao.getAlgoList().get(name)){
        entry.put("p"+i, parameter);
        i++;
      }
      nodes.add(entry);
    }
    return nodes;
  }

}
