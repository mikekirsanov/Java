import java.awt.Color;
import java.awt.Graphics;

public class Background implements Interactable {
    private float time;
    private static final float AMPLITUDE = 255f / 2f;
    private Color color;

    @Override
    public void update(MainCanvas canvas, float deltaTime) {
        time += deltaTime;
        int red = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 0.3f));
        int green = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 0.5f));
        int blue = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 0.7f));
        color = new Color (red, green, blue);
    }

    @Override
    public void render (MainCanvas canvas, Graphics g) {
        canvas.setBackground(color);
    }
}
