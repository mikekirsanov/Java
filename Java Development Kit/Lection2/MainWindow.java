import java.awt.Graphics;
import javax.swing.WindowConstants;
import javax.swing.JFrame;

public class MainWindow extends JFrame implements CanvasRepaintListener{
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WIDTH = 800;
    private static final int HEIGTH = 600;
    private final Interactable[] interactables = new Interactable[11];

    private MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGTH);
        setTitle("Circles");
        interactables[0] = new Background();
        for (int i = 1; i < interactables.length; i++) {
            interactables[i] = new Ball();
        }

        MainCanvas canvas = new MainCanvas(this);
        add(canvas);
        setVisible(true);
    }

    @Override
    public void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    public void update(MainCanvas canvas, float deltaTime) {
        for (Interactable interactable : interactables) {
            interactable.update(canvas, deltaTime);
        }
    }

    public void render(MainCanvas canvas, Graphics g) {
        for (Interactable interactable : interactables) {
            interactable.render(canvas, g);
        }
    }
    public static void main(String[] args) {
        new MainWindow();
    }
}
