package uwu.smsgamer.testgame.screens;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import uwu.smsgamer.testgame.entity.*;
import uwu.smsgamer.testgame.utils.*;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;

import static de.gurkenlabs.litiengine.Game.graphics;

public class InGameScreen extends GameScreen {
    public static final String NAME = "INGAME_SCREEN";

    public InGameScreen() {
        super(NAME);
    }

    private final Camera camera = new Camera();
    private V2D prev = new V2D();

    /**
     * Prepare the GuiComponent and all its child Components (Makes the GuiComponent visible and adds mouse listeners.).
     * This is, for example, done right before switching to a new screen.
     */
    @Override
    public void prepare() {
        Game.world().setCamera(camera);
        super.prepare();
    }

    private final Color grid = new Color(255, 255, 255, 30);

    private LinearGradientPaint paint = new LinearGradientPaint(0, 0, 232, 232,
      new float[]{0, 1f / 29f, 2f / 29f, 3f / 29f, 4f / 29f, 5f / 29f, 6f / 29f, 7f / 29f, 8f / 29f, 9f / 29f, 10f / 29f,
        11f / 29f, 12f / 29f, 13f / 29f, 14f / 29f, 15f / 29f, 16f / 29f, 17f / 29f, 18f / 29f, 19f / 29f, 20f / 29f,
        21f / 29f, 22f / 29f, 23f / 29f, 24f / 29f, 25f / 29f, 26f / 29f, 27f / 29f, 28f / 29f, 1},
      new Color[]{new Color(0, 255, 0), new
        Color(51, 255, 0), new
        Color(102, 255, 0), new
        Color(153, 255, 0), new
        Color(204, 255, 0), new
        Color(255, 255, 0), new
        Color(255, 204, 0), new
        Color(255, 153, 0), new
        Color(255, 102, 0), new
        Color(255, 51, 0), new
        Color(255, 0, 0), new
        Color(255, 0, 51), new
        Color(255, 0, 102), new
        Color(255, 0, 153), new
        Color(255, 0, 204), new
        Color(255, 0, 255), new
        Color(204, 0, 255), new
        Color(153, 0, 255), new
        Color(102, 0, 255), new
        Color(51, 0, 255), new
        Color(0, 0, 255), new
        Color(0, 51, 255), new
        Color(0, 102, 255), new
        Color(0, 153, 255), new
        Color(0, 204, 255), new
        Color(0, 255, 255), new
        Color(0, 255, 204), new
        Color(0, 255, 153), new
        Color(0, 255, 102), new
        Color(0, 255, 51)},
      MultipleGradientPaint.CycleMethod.REFLECT);

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        double diff = this.prev.diffS(EntityManager.getInstance().player.pos.clone());
        if (diff > 16) {
            if (diff > 64) {
                this.prev = EntityManager.getInstance().player.pos.clone();
            } else
                this.prev.subtract(EntityManager.getInstance().player.gridVel);
        }
        Point2D cameraLocation = this.prev.toPoint2D();
        this.camera.setFocus(cameraLocation);

        g.setColor(grid);
        int ix = (int) ((EntityManager.getInstance().player.pos.x) - EntityManager.getInstance().player.pos.x % 16);
        int iy = (int) ((EntityManager.getInstance().player.pos.y) - EntityManager.getInstance().player.pos.y % 16);
        for (int x = -768; x <= 768; x += 16) {
            Rectangle shape = new Rectangle(x + ix, -1028 + iy, 1, 2048);
            graphics().renderShape(g, shape);
        }

        for (int y = -512; y <= 512; y += 16) {
            Rectangle shape = new Rectangle(-1028 + ix, y + iy, 2048, 1);
            graphics().renderShape(g, shape);
        }

        g.setColor(Color.white);
        g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 32));
        //TextRenderer.render(g, "owo", 30, 30, false);
        for (GEntity entity : EntityManager.getInstance().getEntities()) {
            g.setColor(entity.getClass().equals(GPlayer.class) ? Color.CYAN : Color.white);
            HitBox box = entity.getStaticHitBox();
            /*V2D size = entity.getHitBox().getSize();
            V2D pos = box.getCenter();*/
            //System.out.println(box + "  " + entity.getClass().getSimpleName());
            Shape shape = new Rectangle((int) (entity.pos.x - box.max.x / 2),
              (int) (entity.pos.y - box.max.y / 2), (int) box.max.x, (int) box.max.y);
            //Shape shape = new Rectangle((int) pos.x, (int) pos.y, (int) size.x, (int) size.y);
            graphics().renderShape(g, shape);
            g.setColor(Color.red);
            Rectangle shape1 = new Rectangle((int) (entity.pos.x + MathUtils.cos(entity.direction + 90) * 12),
              (int) (entity.pos.y + MathUtils.sin(entity.direction + 90) * 12 - 12), 1, 24);
            graphics().renderShape(g, shape1, true, entity.direction);
            HitBox box1 = entity.getHitBox();
            Rectangle shape2 = new Rectangle((int) box1.min.x, (int) box1.min.y, 1, 1);
            Rectangle shape3 = new Rectangle((int) box1.max.x - 1, (int) box1.max.y - 1, 1, 1);
            g.setColor(Color.GREEN);
            graphics().renderShape(g, shape2);
            graphics().renderShape(g, shape3);
        }
        g.setColor(Color.green);
        Rectangle shape = new Rectangle(63,
          63, 3, 3);
        graphics().renderShape(g, shape);
        //Me just playing around w/ textures'n'shit lol
        /*g.setPaint(paint);
        paint = new LinearGradientPaint((float) paint.getStartPoint().getX() + 1,
          (float) paint.getStartPoint().getY() + 1,
          (float) paint.getEndPoint().getX() + 1,
          (float) paint.getEndPoint().getY() + 1, paint.getFractions(),
          paint.getColors(), paint.getCycleMethod());
//        Shape s = new Rectangle(0, 0, 600, 600);
//        g.fill(s);
        g.setFont(new Font(g.getFont().getName(), Font.BOLD, 60));
        g.drawString("OwOOwO This is really cool\n don't ya think", 30, 70);
        g.drawString("OwOOwO This is really cool\n don't ya think", 30, 130);*/
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);
        float f = Game.graphics().getBaseRenderScale();
        if (e.getWheelRotation() > 0) f *= 0.9;
        else f *= 1.1;
        Game.graphics().setBaseRenderScale(f);
        System.out.println(f);
    }
}
