package com.vermouthx.entity.item;

import com.vermouthx.config.Direction;
import com.vermouthx.config.GameConfig;
import com.vermouthx.controller.GameController;
import com.vermouthx.dto.GameDTO;
import com.vermouthx.util.RandomUtil;
import com.vermouthx.util.ResourceUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LaserBulletItem extends BaseItem {

    private static BufferedImage image;

    static {
        try {
            image = ImageIO.read(ResourceUtil.getResource(GameConfig.getPlayerLaserBulletItemPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LaserBulletItem() {
        super();
        setImage(image);
        setWidth(image.getWidth());
        setHeight(image.getHeight());
        setX(RandomUtil.randomInt(0, GameConfig.getWindowWidth() - getWidth()));
        setY(-getHeight());
    }

    @Override
    public void startThread(GameController gameController) {
        setThread(new Thread(() -> {
            while (getY() <= GameConfig.getWindowHeight() && !isGot()) {
                move(Direction.DOWN);
                gameController.repaintGamePanel();
                try {
                    Thread.sleep(GameConfig.getPlayerItemThreadSleep());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            GameDTO.getDto().removeItem(this);
        }));
        getThread().start();
    }
}
