package pdl.backend;

import io.scif.SCIFIO;
import io.scif.Writer;
import io.scif.formats.JPEGFormat;
import io.scif.img.ImgOpener;
import io.scif.img.ImgSaver;
import io.scif.img.SCIFIOImgPlus;
import net.imglib2.Cursor;
import net.imglib2.RandomAccess;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgFactory;
import net.imglib2.type.numeric.integer.UnsignedByteType;
import org.scijava.Context;
import org.scijava.io.location.BytesLocation;

import java.util.*;

public class ImageLogic {


    public static byte[] handle(Image img){
        try {
            SCIFIOImgPlus<UnsignedByteType> input = ImageConverter.imageFromJPEGBytes(img.getData());

            rgbToGray(input);

            return ImageConverter.imageToJPEGBytes(input);

        }catch(Exception e){
            System.err.println(e);
        }

        return null;
    }






    public static void rgbToGray(Img<UnsignedByteType> img){
        final RandomAccess<UnsignedByteType> r = img.randomAccess();
        final int iw = (int) img.max(0);
        final int ih = (int) img.max(1);

        for (int y = 0; y <= ih; ++y) {
            for (int x = 0; x <= iw; ++x) {
                double greylevel = r.setPositionAndGet(x,y,0).get()*0.3 + r.setPositionAndGet(x,y,1).get()*0.59 + r.setPositionAndGet(x,y,2).get()*0.11;

                for(int channel=0; channel < 3; channel++){
                    r.setPosition(channel, 2);
                    r.get().set((int) greylevel);
                }
            }
        }

    }


}
