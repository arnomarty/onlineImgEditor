package pdl.backend;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;

@Repository
public class ImageDao implements Dao<Image> {

  private final Map<Long, Image> images = new HashMap<>();

  public ImageDao() {
    ClassPathResource imgFile;
    byte[] fileContent;

    try {
      for(String filename : resourcesList("src/main/resources", null)) {
        imgFile = new ClassPathResource(filename);
        fileContent = Files.readAllBytes(imgFile.getFile().toPath());
        Image img = new Image(filename, fileContent);
        images.put(img.getId(), img);
      }
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  public Set<String> resourcesList(String dir, String subdir) throws IOException {
    File[] f = new File(dir).listFiles();
    Set<String> res = new HashSet<>();
    if(f == null) return res;

    for( File entry : f){
      if(entry.isDirectory()){
        res.addAll(resourcesList(entry.getPath(), entry.getName() + "/"));
      }

      String mimeType = Files.probeContentType(entry.toPath());
      if(mimeType != null && (mimeType.equals("image/jpeg") || mimeType.equals("image/tiff")) ){
        if(subdir != null)
          res.add(subdir + entry.getName());
        else res.add(entry.getName());
      }
    }
    return res;
  }

  @Override
  public Optional<Image> retrieve(final long id) {

    return Optional.ofNullable(images.get(id));

  }

  @Override
  public List<Image> retrieveAll() {
    ArrayList<Image> values = new ArrayList<Image>();
    for( Long id : images.keySet()){
      values.add(images.get(id));
    }
    return values;
  }

  @Override
  public void create(final Image img) {
    images.put(img.getId(), img);
  }

  @Override
  public void update(final Image img, final String[] params) {
    // Not used
  }

  @Override
  public void delete(final Image img) {
    images.remove(img.getId());
  }

}
