package module2;

import processing.core.PApplet;
import processing.core.PImage;

public class MyApplet extends PApplet {
//  private String URL = "/Users/mateusz/IdeaProjects/UCSDUnfoldingMaps/data/palmTrees.jpg";
  private String URL = "http://cseweb.ucsd.edu/~minnes/palmTrees.jpg";
  private PImage backgroundImg;

  @Override
  public void setup() {
    size(400, 400);
    // load image from a webserver
    backgroundImg = loadImage(URL, "jpg");
    backgroundImg.resize(0, height);
    image(backgroundImg, 0, 0);
  }

  @Override
  public void draw() {
    // pink sun at 7, grey sun at dusk
    int [] color = sunColorSec(second());          // get color code for sun
    fill(color[0], color[1], color[2]);            // set sun color
    ellipse(width/4, height/5, width/5, height/5); // draw sun
  }

  // calculate sun color
  private int[] sunColorSec(int seconds) {
    int[] rgb = new int[3];
    // Scale the brightness of the yellow based on the seconds.
    // 0 seconds is black
    // 30 seconds is bright yellow
    float diffFrom30 = Math.abs(30-seconds);
    float ratio = diffFrom30/30;
    rgb[0] = (int)(255*ratio);
    rgb[1] = (int)(255*ratio);
    rgb[2] = 0;
    System.out.println("Red: " + rgb[0] + " Green: " + rgb[1] + " Blue: " + rgb[2]);
    return rgb;
  }
}
