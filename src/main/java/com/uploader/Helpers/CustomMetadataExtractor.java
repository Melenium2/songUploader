package com.uploader.Helpers;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.uploader.Model.SongModel;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.springframework.stereotype.Service;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

@Service
public class CustomMetadataExtractor
{
    private SongModel songModel;

    public void extractMetadataFromAudio(File file)
    {
        try {
            InputStream inputStream = new FileInputStream(file);
            ContentHandler handler = new DefaultHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext prsContext = new ParseContext();
            parser.parse(inputStream, handler, metadata, prsContext);
            inputStream.close();

            String[] metadataNames = metadata.names();

            for (String name : metadataNames)
            {
                System.out.println(name + " " + metadata.get(name));
            }

            extractImageFromAudio(file);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        } catch (TikaException e)
        {
            e.printStackTrace();
        }
    }

    public void extractImageFromAudio(File file)
    {
        try
        {
            Mp3File song = new Mp3File(file.getAbsolutePath());
            if(song.hasId3v2Tag())
            {
                ID3v2 tag = song.getId3v2Tag();
                byte[] imgData = tag.getAlbumImage();
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(imgData));
                File image = new File("image.jpg");
                ImageIO.write(img, "jpg", image);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedTagException e)
        {
            e.printStackTrace();
        }
        catch (InvalidDataException e)
        {
            e.printStackTrace();
        }
    }
}
